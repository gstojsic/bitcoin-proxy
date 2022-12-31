plugins {
    id("java")
}

group = "io.github.gstojsic.bitcoin.proxy"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.freemarker:freemarker:2.3.31")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

tasks.test {
    useJUnitPlatform()
}