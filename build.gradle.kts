plugins {
    kotlin("jvm") version "1.3.71"
    application
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("http://nexus.gluonhq.com/nexus/content/repositories/releases")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.gluonhq.attach:storage:4.0.2")
    implementation("com.gluonhq.attach:util:4.0.2")
    implementation("com.github.almasb:fxgl:11.5")

    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.2") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.2") // for kotest core jvm assertions
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

application {
    mainClassName = "MainKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

javafx {
    version = "12.0.1"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.swing")
}