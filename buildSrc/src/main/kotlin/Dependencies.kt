object Versions {
    const val android_gradle_plugin = "4.2.0"
    const val kotlin = "1.5.10"
    const val app_compat = "1.3.0"
    const val constraint_layout = "2.0.4"
    const val rx_java_2 = "2.2.19"
    const val junit = "4.12"
    const val robolectric = "4.5.1"
    const val mockito_kotlin = "3.2.0"
    const val dagger = "2.35.1"
    const val gson = "2.8.4"

    const val compileSdkVersion = 30
    const val minSdkVersion = 16
    const val targetSdkVersion = 30

    private const val major = 1
    private const val minor = 3
    private const val patch = 0

    const val versionString: String = "$major.$minor.$patch"
}

object Libs {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val rx_java_2 = "io.reactivex.rxjava2:rxjava:${Versions.rx_java_2}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val mockito_kotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito_kotlin}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}
