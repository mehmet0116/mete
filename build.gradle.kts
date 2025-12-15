// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1" apply false
}

// Dependency versions
ext {
    // Core
    kotlinVersion = "1.9.20"
    coroutinesVersion = "1.7.3"
    
    // AndroidX
    coreKtxVersion = "1.12.0"
    appCompatVersion = "1.6.1"
    materialVersion = "1.10.0"
    constraintLayoutVersion = "2.1.4"
    lifecycleVersion = "2.7.0"
    navigationVersion = "2.7.5"
    roomVersion = "2.6.0"
    datastoreVersion = "1.0.0"
    pagingVersion = "3.2.1"
    workVersion = "2.9.0"
    
    // Compose
    composeBomVersion = "2023.10.01"
    composeCompilerVersion = "1.5.4"
    
    // Third-party
    hiltVersion = "2.48"
    lottieVersion = "6.1.0"
    gsonVersion = "2.10.1"
    coilVersion = "2.5.0"
    timberVersion = "5.0.1"
    
    // Testing
    junitVersion = "4.13.2"
    testExtVersion = "1.1.5"
    espressoVersion = "3.5.1"
    mockkVersion = "1.13.8"
    
    // Tools
    desugarVersion = "2.0.4"
    leakCanaryVersion = "2.12"
}

// Configure subprojects
subprojects {
    afterEvaluate { project ->
        if (project.hasProperty("android")) {
            project.android {
                compileSdk = 34
                
                defaultConfig {
                    minSdk = 26
                    targetSdk = 34
                    
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
                
                kotlinOptions {
                    jvmTarget = "17"
                }
                
                buildFeatures {
                    viewBinding = true
                    buildConfig = true
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
    dependsOn(tasks.named("detekt")) // If using detekt
    dependsOn(tasks.named("ktlintCheck")) // If using ktlint
}

// Build scan configuration
buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    
    // Publish build scans for every build
    publishAlways()
}

// Configure detekt for all modules
allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    
    detekt {
        toolVersion = "1.23.1"
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

// Dependency updates plugin
plugins.apply("com.github.ben-manes.versions")

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