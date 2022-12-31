plugins {
    id("java")
    id("maven-publish")
    id("signing")
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    annotationProcessor(project(":json-parse"))
    compileOnly(project(":json-parse"))

    testImplementation("org.testcontainers:junit-jupiter:1.17.6")
    testImplementation("org.testcontainers:testcontainers:1.17.6")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testImplementation("org.slf4j:slf4j-nop:2.0.5") //when logging is introduced in proxy move this dep to non-test deps
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testAnnotationProcessor(project(":json-parse"))
    testCompileOnly(project(":json-parse"))
}

tasks.javadoc {
    options {
        (this as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
    }
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("proxy") {
            from(components["java"])

            pom {
                name.set(project.name)
                description.set("A java lib to access bitcoind nodes via JSON RPC")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:gstojsic/bitcoin-proxy.git")
                    url.set("https://github.com/gstojsic/bitcoin-proxy")
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"
            val path = if (version.toString().endsWith("SNAPSHOT"))
                "content/repositories/snapshots/"
            else
                "service/local/staging/deploy/maven2/"
            setUrl("https://s01.oss.sonatype.org/$path")

            credentials {
                username = project.findProperty("maven.user") as String? ?: return@credentials
                password = project.findProperty("maven.password") as String? ?: return@credentials
            }
        }
    }
}

signing {
    sign(publishing.publications)
}