import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.20"
    id("com.diffplug.spotless") version "6.22.0"
    id("info.solidsoft.pitest") version "1.15.0"
    application
}

group = "com.codely"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk:1.13.8")
}

pitest {
    setProperty("junit5PluginVersion", "1.2.1")
    setProperty("testPlugin", "junit5")
    setProperty("targetClasses", listOf("com.isamadrid90.*"))
    setProperty("outputFormats", listOf("HTML"))
    setProperty("threads", 2)
    setProperty("withHistory", false)
}

application {
    mainClass.set("com.isamadrid90.fizzbuzz.MainKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    kotlin {
        ktlint()
            .userData(
                mapOf(
                    "insert_final_newline" to "true",
                ),
            )
    }
    kotlinGradle {
        ktlint()
    }
}

tasks.check {
    dependsOn(tasks.spotlessCheck)
}
