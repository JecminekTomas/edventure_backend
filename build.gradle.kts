import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("plugin.allopen") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
    id("com.github.johnrengelman.processes") version "0.5.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.3.0"
}

group = "com.jecminek"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {

    //Spring
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.1")
    implementation("org.springframework.boot:spring-boot-starter-parent:2.5.6")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //Open Api
    implementation("org.springdoc:springdoc-openapi-ui:1.5.12")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.5.12")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.12")

    // Spring Data JPA (Hibernate)
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa:2.6.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")

    // PostgreSQL
    runtimeOnly ("org.postgresql:postgresql:42.3.1")


    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security:2.6.1")
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    //Mapper
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    implementation("org.mapstruct:mapstruct-processor:1.4.2.Final")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    developmentOnly("org.springframework.boot:spring-boot-devtools")


    testImplementation("org.springframework.security:spring-security-test:5.6.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.1") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:3.0.1")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xopt-in=kotlin.RequiresOptIn")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
