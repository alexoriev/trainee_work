plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.aston_trainee_work"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aston_trainee_work"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"

            )
            manifestPlaceholders["usesCleartextTraffic"] = false
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"90db65ef652f461fa0871930eb104e37\"")
            buildConfigField("String", "PAGE_SIZE", "\"20\"")
            buildConfigField("Long", "TWO_WEEKS", "14L")
        }
        debug {
            manifestPlaceholders["usesCleartextTraffic"] = true
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "API_KEY", "\"90db65ef652f461fa0871930eb104e37\"")
            buildConfigField("String", "PAGE_SIZE", "\"20\"")
            buildConfigField("Long", "TWO_WEEKS", "14L")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Fragments
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    //MVP Moxy
    implementation("com.github.moxy-community:moxy:2.2.2")
    implementation("com.github.moxy-community:moxy-androidx:2.2.2")
    kapt("com.github.moxy-community:moxy-compiler:2.2.2")

    //Cicerone
    implementation("com.github.terrakok:cicerone:7.1")

    //Dagger
    implementation("com.google.dagger:dagger:2.50")
    implementation("com.google.dagger:dagger-android-support:2.50")
    kapt("com.google.dagger:dagger-android-processor:2.50")
    kapt("com.google.dagger:dagger-compiler:2.50")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    //RxJava
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")

    //RxAndroid
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    //Lottie
    implementation("com.airbnb.android:lottie:6.3.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}