plugins {
	id("fabric-loom") version "1.11-SNAPSHOT"
	`maven-publish`
}

version = project.property("mod_version") as String
group = project.property("maven_group") as String

base {
	archivesName.set(project.property("archives_base_name") as String)
}

repositories {
	mavenCentral()
}

dependencies {
	val minecraft_version: String by project
	val yarn_mappings: String by project
	val loader_version: String by project
	val fabric_version: String by project

	// Minecraft and Fabric related
	minecraft("com.mojang:minecraft:$minecraft_version")
	mappings("net.fabricmc:yarn:$yarn_mappings:v2")

	// Other Dependencies
	compileOnly("org.projectlombok:lombok:1.18.38")
	annotationProcessor("org.projectlombok:lombok:1.18.38")

	testCompileOnly("org.projectlombok:lombok:1.18.38")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.38")

	// Mod Implementations
	modImplementation("net.fabricmc:fabric-loader:$loader_version")
	modImplementation("net.fabricmc.fabric-api:fabric-api:$fabric_version")
}

tasks.named<ProcessResources>("processResources") {
	inputs.property("version", project.version)
	filesMatching("fabric.mod.json") {
		expand(mapOf("version" to project.version))
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.release.set(21)
}

java {
	withSourcesJar()
}

tasks.named<Jar>("jar") {
	inputs.property("archivesName", base.archivesName)
	from("LICENSE") {
		rename { "${it}_${base.archivesName.get()}" }
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			artifactId = project.property("archives_base_name") as String
			from(components["java"])
		}
	}
}