import org.bouncycastle.asn1.iana.IANAObjectIdentifiers.experimental
import org.gradle.internal.impldep.org.bouncycastle.asn1.iana.IANAObjectIdentifiers.experimental

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")

}

android {
    namespace = "com.example.gg"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gg"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true;
    }



}

dependencies {

    implementation("com.android.support:support-annotations:28.0.0")
    implementation("androidx.annotation:annotation:1.6.0")
    val nav_version = "2.7.2"
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.2")
    implementation("com.google.firebase:firebase-storage-ktx:20.2.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.7.1")


    implementation ("com.github.bumptech.glide:glide:4.13.0")
//    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.0")

    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation ("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")

    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")


    //room db
    val room_version = "2.5.2"

    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")

//    implementation ("androidx.room:room-runtime:2.4.3")
//    implementation ("androidx.room:room-ktx:2.4.3")
//    kapt ("androidx.room:room-compiler:2.4.3")

}