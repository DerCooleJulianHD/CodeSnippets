plugins {
    id("java")
}

group = "de.xcuzimsmart"
version = "1.4.2-SNAPSHOT"

repositories {
    mavenCentral()

    // PaperMc
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    // PaperMc
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(24))
}