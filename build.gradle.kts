plugins {
    // Versions synchronized with spec
    kotlin("android") version "1.9.20" apply false
    id("com.android.application") version "8.5.2" apply false
    id("com.android.library") version "8.5.2" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    kotlin("kapt") version "1.9.20" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
