plugins {
    id("java")
    application
}

group = "com.airtribe.learntrack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.airtribe.learntrack.Main")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
