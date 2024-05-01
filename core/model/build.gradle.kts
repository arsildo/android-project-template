plugins {
    alias(libs.plugins.template.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.arsildo.template.core.model"
}

dependencies {
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.serialization.kotlinx.json)
}