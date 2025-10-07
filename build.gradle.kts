plugins {
    id("com.android.application") version "8.2.1" apply false
    id("com.android.library") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.25" apply false
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}