import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("com.moowork.node") version "1.3.1"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
}

group = "com.toccorp.problemsolving"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks {
	register<com.moowork.gradle.node.yarn.YarnTask>("yarnInstall") {
		description = "Installs front-end dependencies from package.json"
		setWorkingDir("${project.projectDir}/src/main/webapp")
		args = mutableListOf("install")
	}

	register<com.moowork.gradle.node.yarn.YarnTask>("yarnBuild") {
		dependsOn("yarnInstall")
		description = "Builds the production-ready version of the web front end"
		setWorkingDir("${project.projectDir}/src/main/webapp")
		args = mutableListOf("build")
	 }

	register<Copy>("copyWebApplication") {
		dependsOn("yarnBuild")
		from("${project.projectDir}/src/main/webapp/build")
		into("build/resources/main/static/.")
	}
}

tasks["assemble"].dependsOn("copyWebApplication")
