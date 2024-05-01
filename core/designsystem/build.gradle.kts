plugins {
    alias(libs.plugins.template.android.library)
    alias(libs.plugins.jetbrains.compose.compiler)
}

android {
    namespace = "com.arsildo.template.core.designsystem"
    buildFeatures { compose = true }
}

dependencies {
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.text.google.fonts)
}