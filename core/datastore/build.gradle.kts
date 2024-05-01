plugins {
    alias(libs.plugins.template.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android { namespace = "com.template.core.datastore" }

dependencies {
    implementation(libs.androidx.datastore.preferences)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
}