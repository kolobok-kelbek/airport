import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.30"
	kotlin("plugin.spring") version "1.4.30"
	kotlin("plugin.jpa") version "1.4.30"
}

group = "com.kelbek"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.apache.logging.log4j:log4j-core:2.14.1")
	implementation("org.apache.logging.log4j:log4j-api:2.14.1")
	implementation("com.google.code.gson:gson:2.8.6")
	implementation("org.hibernate:hibernate-spatial:5.4.28.Final")
	compileOnly("org.locationtech.jts:jts-core:1.16.0")


	compileOnly("io.jsonwebtoken:jjwt-api:0.10.7")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.7")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.10.7")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = JavaVersion.VERSION_15.toString()
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
