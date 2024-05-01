plugins {
    alias(libs.plugins.template.android.feature)
}

android {
    namespace = "com.template.feature.feature"
}

dependencies {
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
}