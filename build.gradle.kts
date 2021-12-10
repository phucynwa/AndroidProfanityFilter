buildscript {
    val composeVersion by extra("1.1.0-beta04")
}

plugins {
    id("com.android.application") version "7.0.3" apply false
    id("com.android.library") version "7.0.3" apply false
    kotlin("android") version "1.6.0" apply false
    id("com.github.dcendents.android-maven") version "2.1" apply false
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
