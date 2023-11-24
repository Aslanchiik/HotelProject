plugins {
    id("java-library")
    kotlin(Plugins.Kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Javax Inject
    api(Libraries.Javax.inject)

    // Kotlin
    api(Libraries.Coroutines.core)
}
