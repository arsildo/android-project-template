plugins {
    alias(libs.plugins.template.android.library)
}

android {
    namespace = "com.arsildo.template.core.network"
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)

    implementation(libs.timber)

    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.okhttp.logging)
    implementation(libs.slf4j)
    implementation(libs.androidx.datastore.preferences)
}