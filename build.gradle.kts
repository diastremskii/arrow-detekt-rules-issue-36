import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.21"
    id("io.gitlab.arturbosch.detekt") version("1.23.0")
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    detektPlugins("com.wolt.arrow.detekt:rules:0.3.0")

    implementation(platform("io.arrow-kt:arrow-stack:1.1.3"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-fx-coroutines")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}

detekt {
    toolVersion = "1.23.0"
    autoCorrect = true
    buildUponDefaultConfig = true
    config.from("$rootDir/gradle/detekt-config-overrides.yml")
}
