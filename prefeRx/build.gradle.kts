import Versions.versionString

plugins {
    id("com.android.library")
    id("maven-publish")
    kotlin("android")
}

group = "com.github.vinted"

android {
    compileSdkVersion(Versions.targetSdkVersion)

    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
    }

    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        map { it.java.srcDir("src/main/kotlin") }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "$group"
                artifactId = "preferx"
                version = versionString
                from(components["release"])
            }
        }
    }
}

tasks {
    val main = android.sourceSets["main"]
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(main.java.srcDirs)
    }

    val javaDoc by creating(Javadoc::class) {
        isFailOnError = false
        source = main.java.getSourceFiles()
        classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))
    }

    val javaDocJar by creating(Jar::class) {
        dependsOn.add(javaDoc)
        archiveClassifier.set("javadoc")
        from(javaDoc.destinationDir)
    }

    artifacts {
        archives(sourcesJar)
        archives(javaDocJar)
    }
}

dependencies {
    implementation(Libs.kotlin_stdlib)
    implementation(Libs.rx_java_2)
    testImplementation(Libs.junit)
    testImplementation(Libs.mockito_kotlin)
    testImplementation(Libs.robolectric)
    testImplementation(Libs.test_core)
}
