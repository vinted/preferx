object Versions {
    const val kotlin = "1.2.30"
    const val build_tools = "3.0.1"
    const val app_compat = "26.1.0"
    const val constraint_layout = "1.0.2"
    const val rx_java_2 = "2.1.10"
    const val junit = "4.12"
    const val robolectric = "3.7.1"
    const val mockito = "2.13.0"
    const val mockito_kotlin = "1.5.0"
    const val dagger = "2.14.1"
    const val gson = "2.8.4"

    const val compileSdkVersion = 27
    const val minSdkVersion = 16
    const val targetSdkVersion = 27

    private const val major = 1
    private const val minor = 2
    private const val patch = 0

    const val versionCode: Int = (major * 10000) + (minor * 100) + patch
    const val versionString: String = "$major.$minor.$patch"
}

object Libs {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val app_compat_v7 = "com.android.support:appcompat-v7:${Versions.app_compat}"
    const val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraint_layout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val rx_java_2 = "io.reactivex.rxjava2:rxjava:${Versions.rx_java_2}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val mockito_kotlin = "com.nhaarman:mockito-kotlin:${Versions.mockito_kotlin}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}
