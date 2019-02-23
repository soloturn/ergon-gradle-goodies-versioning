
plugins {
	groovy
	`java-gradle-plugin`
	id("com.gradle.plugin-publish") version "0.10.1"
}

allprojects {

	// Let's eat our own dog food (cannot be part of plugins block, though).
	apply(plugin = "ch.ergon.gradle.goodies.versioning.VersioningPlugin")
	group = "ch.ergon"

	repositories {
		jcenter()
	}

	tasks.withType<Test> {
		maxParallelForks = Runtime.getRuntime().availableProcessors() 
	}
}

tasks.withType<Test> {
	inputs.files files("./versioning-test-data/")
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_7
	targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
	compile(gradleApi())
	compile("org.codehaus.groovy:groovy-all:2.5.6")

	testCompile("org.spockframework:spock-core:1.2-groovy-2.5")
	testCompile("org.eclipse.jgit:org.eclipse.jgit:5.2.1.201812262042-r")
}

gradlePlugin {
	plugins {
		versioningPlugin {
			id = "ch.ergon.gradle.goodies.versioning"
			implementationClass = "ch.ergon.gradle.goodies.versioning.VersioningPlugin"
		}
	}
}

pluginBundle {
	vcsUrl = "https://github.com/kungfoo/ergon-gradle-goodies-versioning"
	description = "Simple and automatic versioning from your git history for your java and android projects."
	tags = ["versioning", "git", "java", "android"]

	plugins {
		versioningPlugin {
			displayName = "Simple and automatic versioning from your git history for your java and android projects."
			id = "ch.ergon.gradle.goodies.versioning"
			website = "https://github.com/kungfoo/ergon-gradle-goodies-versioning"
		}
	}
}