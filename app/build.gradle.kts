plugins {
    id("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
}

dependencies {
    implementation(project(":proxy"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

tasks.jar {
    manifest {
        attributes["Manifest-Version"] = "1.0"
        attributes["Main-Class"] = "io.github.gstojsic.bitcoin.proxy.App"
    }
}

tasks.test {
    useJUnitPlatform()
}