buildscript {
    val composeVersion by extra("1.1.0-beta04")
}

plugins {
    id("com.android.application") version "7.0.4" apply false
    id("com.android.library") version "7.0.4" apply false
    kotlin("android") version "1.6.0" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
