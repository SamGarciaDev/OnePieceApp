package edu.samgarcia.onepieceapp.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import edu.samgarcia.onepieceapp.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    @DrawableRes
    val imageDark: Int = image,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
) {
    object First: OnboardingPage(
        image = R.drawable.luffy_waving,
        title = R.string.first_onboarding_page_text,
        description = R.string.first_onboarding_page_desc
    )

    object Second: OnboardingPage(
        image = R.drawable.op_ship,
        imageDark = R.drawable.op_ship_dark,
        title = R.string.second_onboarding_page_title,
        description = R.string.second_onboarding_page_desc
    )

    object Third: OnboardingPage(
        image = R.drawable.chopper_happy,
        title = R.string.third_onboarding_page_title,
        description = R.string.third_onboarding_page_desc
    )
}
