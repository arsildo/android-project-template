import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.arsildo.template.core.designsystem.R

private val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val googleFont = GoogleFont(name = "Inter")

private val fontFamily = FontFamily(Font(googleFont = googleFont, fontProvider = fontProvider))

private val typography = Typography()

internal val applicationTypography = Typography(
    displayLarge = typography.displayLarge.copy(fontFamily = fontFamily),
    displayMedium = typography.displayMedium.copy(fontFamily = fontFamily),
    displaySmall = typography.displaySmall.copy(fontFamily = fontFamily),
    bodyLarge = typography.bodyLarge.copy(fontFamily = fontFamily),
    bodyMedium = typography.bodyMedium.copy(fontFamily = fontFamily),
    labelLarge = typography.labelLarge.copy(fontFamily = fontFamily),
    labelMedium = typography.labelMedium.copy(fontFamily = fontFamily),
    labelSmall = typography.labelSmall.copy(fontFamily = fontFamily),
    headlineLarge = typography.headlineLarge.copy(fontFamily = fontFamily),
    headlineMedium = typography.headlineMedium.copy(fontFamily = fontFamily),
    headlineSmall = typography.headlineSmall.copy(fontFamily = fontFamily),
    titleLarge = typography.titleLarge.copy(fontFamily = fontFamily),
    titleMedium = typography.titleMedium.copy(fontFamily = fontFamily),
    titleSmall = typography.titleSmall.copy(fontFamily = fontFamily),
)
