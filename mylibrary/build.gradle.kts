
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.example.mylibrary"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.example.mylibrary"
                artifactId = "mylibrary"
                version = "1.0.0"
            }
        }

        repositories {
            maven {
                url = uri("$rootDir/local-maven-repo") // 💡 Local folder to act like a Maven repo
            }
        }
    }
}

//publishing {
//    publications {
//        create<MavenPublication>("release") {
//            afterEvaluate {
//                from(components["release"])
//            }
//
//            groupId = "com.github.nitinnegi267"
//            artifactId = "mylibrary"
//            version = "1.0.1"
//        }
//    }
//
//    repositories {
//        maven {
//            name = "GitHubPackages"
//            url = uri("https://maven.pkg.github.com/nitinnegi267/mylibrary1")
//            credentials {
//                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
//                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
//            }
//        }
//    }
//}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}