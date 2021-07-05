plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions.unitTests.apply {
        isIncludeAndroidResources = true
    }

    defaultConfig {
        applicationId = "com.vinted.preferx.examples"
        minSdkVersion(Versions.minSdkVersion)
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        map { it.java.srcDir("src/main/kotlin") }
    }
}

dependencies {
    implementation(project(":prefeRx"))
    implementation(Libs.kotlin_stdlib)
    implementation(Libs.app_compat_v7)
    implementation(Libs.constraint_layout)
    implementation(Libs.dagger)
    implementation(Libs.rx_java_2)
    implementation(Libs.gson)
    kapt(Libs.dagger_compiler)
    testImplementation(Libs.junit)
    testImplementation(Libs.robolectric)
    testImplementation(Libs.mockito_kotlin)
    kaptTest(Libs.dagger_compiler)
}
