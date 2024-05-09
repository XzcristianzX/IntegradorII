plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.integradorii"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.integradorii"
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation ("io.socket:socket.io-client:2.0.0") {
        exclude("org.json","json")
    }
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("org.postgresql:postgresql:42.2.24")


    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")




    implementation ("com.github.bumptech.glide:glide:4.16.0")


    // Material Components for Android. Replace the version with the latest version of Material Components library.
    implementation ("com.google.android.material:material:1.5.0")

    // Circle Indicator (To fix the xml preview "Missing classes" error)
    implementation ("me.relex:circleindicator:2.1.6")

    implementation ("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")

    //noinspection UseTomlInstead
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    //noinspection UseTomlInstead
    implementation ("com.squareup.retrofit2:converter-gson:2.6.4")
    //noinspection UseTomlInstead
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //noinspection UseTomlInstead
    implementation("com.airbnb.android:lottie:3.4.0")



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.cronet.embedded)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}