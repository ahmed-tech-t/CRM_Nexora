import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}
val localProperties = gradleLocalProperties(rootDir, providers)

android {
    namespace = "com.example.crm_nexora"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.crm_nexora"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://api.example.com/\"")
    }
//    signingConfigs {
//        create("release") {
//            storeFile = file(localProperties.getProperty("RELEASE_STORE_FILE"))
//            storePassword = localProperties.getProperty("RELEASE_STORE_PASSWORD")
//            keyAlias = localProperties.getProperty("RELEASE_KEY_ALIAS")
//            keyPassword = localProperties.getProperty("RELEASE_KEY_PASSWORD")
//        }
//    }

    buildTypes {
        release {
        //    signingConfig = signingConfigs.getByName("release")
            isDebuggable = false // Add this line
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.example.com/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.example.com/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.exifinterface)
    implementation(libs.okhttp)
    implementation(libs.libphonenumber)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
