plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "2.7.0"
//	id("org.springframework.boot") version "3.3.2"
	kotlin("plugin.jpa") version "1.9.24"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id ("org.flywaydb.flyway") version "7.2.1"
}

group = "com.circlesllc.collections.api"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

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
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.vladmihalcea:hibernate-types-52:2.4.4")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	runtimeOnly ("org.flywaydb:flyway-core")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("io.mockk:mockk:1.9.3.kotlin12")
	runtimeOnly("org.springframework.boot:spring-boot-properties-migrator")
}
	tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
		kotlinOptions {
			jvmTarget = "17"
		}
	}
		tasks.withType<Test> {
			useJUnitPlatform()
		}
		kotlin {
			compilerOptions {
				freeCompilerArgs.addAll("-Xjsr305=strict")
			}
		}
