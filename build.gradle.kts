// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.4" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1" apply false
    id("com.github.ben-manes.versions") version "0.50.0" apply false
}

// Dependency versions
object Versions {
    // Core
    const val kotlin = "1.9.22"
    const val coroutines = "1.7.3"
    
    // AndroidX
    const val coreKtx = "1.12.0"
    const val appCompat = "1.6.1"
    const val material = "1.10.0"
    const val constraintLayout = "2.1.4"
    const val lifecycle = "2.7.0"
    const val navigation = "2.7.5"
    const val room = "2.6.0"
    const val datastore = "1.0.0"
    const val paging = "3.2.1"
    const val work = "2.9.0"
    
    // Compose
    const val composeBom = "2023.10.01"
    const val composeCompiler = "1.5.4"
    
    // Third-party
    const val hilt = "2.48"
    const val lottie = "6.1.0"
    const val gson = "2.10.1"
    const val coil = "2.5.0"
    const val timber = "5.0.1"
    
    // Testing
    const val junit = "4.13.2"
    const val testExt = "1.1.5"
    const val espresso = "3.5.1"
    const val mockk = "1.13.8"
    
    // Tools
    const val desugar = "2.0.4"
    const val leakCanary = "2.12"
}

// Configure subprojects
subprojects {
    afterEvaluate { project ->
        if (project.hasProperty("android")) {
            project.extensions.configure<com.android.build.gradle.BaseExtension>("android") {
                compileSdk = 34
                
                defaultConfig {
                    minSdk = 26
                    targetSdk = 34
                    
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                    isCoreLibraryDesugaringEnabled = true
                }
                
                kotlinOptions {
                    jvmTarget = "17"
                    freeCompilerArgs = freeCompilerArgs + listOf(
                        "-opt-in=kotlin.RequiresOptIn",
                        "-Xjvm-default=all",
                        "-Xstring-concat=inline"
                    )
                }
                
                buildFeatures {
                    viewBinding = true
                    buildConfig = true
                }
                
                // Build optimization
                buildTypes {
                    getByName("debug") {
                        isMinifyEnabled = false
                        isDebuggable = true
                        isTestCoverageEnabled = true
                    }
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        isDebuggable = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
                
                // Packaging options optimization
                packaging {
                    resources {
                        excludes += listOf(
                            "META-INF/AL2.0",
                            "META-INF/LGPL2.1",
                            "META-INF/DEPENDENCIES",
                            "META-INF/LICENSE",
                            "META-INF/LICENSE.txt",
                            "META-INF/NOTICE",
                            "META-INF/NOTICE.txt",
                            "**/kotlin/**",
                            "**/*.kotlin_module"
                        )
                        pickFirsts += listOf(
                            "**/libjsc.so",
                            "**/libc++_shared.so"
                        )
                    }
                }
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

// Performance optimization tasks
tasks.register("optimizeBuild") {
    group = "optimization"
    description = "Run all optimization tasks"
    
    dependsOn(tasks.named("clean"))
    dependsOn(tasks.named("lintDebug"))
    dependsOn(tasks.named("testDebugUnitTest"))
}

// Custom task for dependency updates
tasks.register("checkDependencyUpdates") {
    group = "optimization"
    description = "Check for dependency updates"
    
    doLast {
        println("Checking for dependency updates...")
        println("Run: ./gradlew dependencyUpdates")
    }
}

// Code quality tasks
tasks.register("codeQualityCheck") {
    group = "verification"
    description = "Run all code quality checks"
    
    dependsOn(tasks.named("lintDebug"))
    dependsOn(tasks.named("detekt"))
    dependsOn(tasks.named("ktlintCheck"))
}

// Build scan configuration (if using Gradle Enterprise)
gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        
        // Publish build scans for every build
        publishAlways()
    }
}

// Configure detekt for all modules
allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    
    detekt {
        toolVersion = "1.23.4"
        config = files("${project.rootDir}/detekt-config.yml")
        buildUponDefaultConfig = true
        allRules = false
        
        reports {
            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
            xml {
                enabled = false
            }
            txt {
                enabled = false
            }
        }
    }
}

// Configure ktlint for all modules
allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    
    ktlint {
        version = "0.50.0"
        debug = true
        verbose = true
        android = true
        outputToConsole = true
        outputColorName = "RED"
        ignoreFailures = false
        enableExperimentalRules = true
        
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

// Apply dependency updates plugin
apply(plugin = "com.github.ben-manes.versions")

dependencyUpdates {
    checkForGradleUpdate = true
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
    
    // Configure which dependencies to check
    resolutionStrategy {
        componentSelection {
            all {
                val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea")
                    .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-+]*") }
                    .any { it.matches(candidate.version) }
                if (rejected) {
                    reject("Release candidate")
                }
            }
        }
    }
}

// Build cache configuration for better performance
buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, "build-cache")
        removeUnusedEntriesAfterDays = 30
    }
}

// Parallel build execution
tasks.withType<JavaCompile>().configureEach {
    options.isIncremental = true
}

// Kotlin compilation optimization
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        apiVersion = "1.9"
        languageVersion = "1.9"
        allWarningsAsErrors = true
    }
}