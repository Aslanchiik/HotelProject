plugins {
    id(Plugins.AGP.library)
    kotlin(Plugins.Kotlin.android)

    // Kotlin Symbol Processing
    id(Plugins.KSP.ksp) version Versions.KSP
}

android {
    namespace = "com.example.data"
    compileSdk = AndroidConfigs.compileSdk

    defaultConfig {
        minSdk = AndroidConfigs.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://run.mocky.io/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://run.mocky.io/\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))

    // Retrofit
    implementation(Libraries.Retrofit.retrofit)
    implementation(Libraries.Retrofit.converterGson)

    // OkHttp
    implementation(Libraries.OkHttp.bom)
    implementation(Libraries.OkHttp.okHttp)
    implementation(Libraries.OkHttp.loggingInterceptor)

    // Room
    ksp(Libraries.Room.compiler)
    api(Libraries.Room.ktx)
}