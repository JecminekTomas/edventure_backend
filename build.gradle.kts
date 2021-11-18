import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("plugin.allopen") version "1.3.61"
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
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-parent:2.4.0")

    //Open Api
    implementation("org.springdoc:springdoc-openapi-ui:1.5.6")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.5.6")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.6")

    /** Old way through springfox.(Not swagger way)
     * implementation("io.springfox:springfox-boot-starter:3.0.0")
     * implementation("io.springfox:springfox-swagger-ui:3.0.0")
     **/


    // Spring Data JPA (Hibernate)
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")

    // PostgreSQL
    runtimeOnly ("org.postgresql:postgresql:42.3.1")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.5")
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    //Mapper
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    implementation("org.mapstruct:mapstruct-processor:1.4.2.Final")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
