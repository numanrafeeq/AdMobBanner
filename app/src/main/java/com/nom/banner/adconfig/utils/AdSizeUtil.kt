@file:Suppress("DEPRECATION")

package com.nom.banner.adconfig.utils

import android.app.Activity
import android.util.DisplayMetrics
import android.view.ViewGroup
import com.google.android.gms.ads.AdSize


object AdSizeUtil {

    enum class AdCategory {
        ANCHORED, INLINE, INTERSCROLLER
    }

    enum class Orientation {
        CURRENT, LANDSCAPE, PORTRAIT
    }

    enum class Collapsible {
        TOP, BOTTOM
    }

    enum class FixedSize {
        BANNER, FULL_BANNER, LARGE_BANNER, LEADERBOARD, MEDIUM_RECTANGLE, WIDE_SKYSCRAPER
    }


    fun getCustomInlineAdSize(adWidth: Int, adHeight: Int): AdSize {

        return AdSize.getInlineAdaptiveBannerAdSize(adWidth, adHeight)
    }

    fun getInlineAdSize(activity: Activity, adContainer: ViewGroup, orientation: Orientation): AdSize {
        return when (orientation) {
            Orientation.CURRENT -> AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(activity, getAdWidth(activity, adContainer))
            Orientation.LANDSCAPE -> AdSize.getLandscapeInlineAdaptiveBannerAdSize(activity, getAdWidth(activity, adContainer))
            Orientation.PORTRAIT -> AdSize.getPortraitInlineAdaptiveBannerAdSize(activity, getAdWidth(activity, adContainer))
        }
    }


    fun getAnchoredAdSize(activity: Activity, adContainer: ViewGroup, orientation: Orientation): AdSize {
        return when (orientation) {
            Orientation.CURRENT -> AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, getAdWidth(activity, adContainer))
            Orientation.LANDSCAPE -> AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(activity, getAdWidth(activity, adContainer))
            Orientation.PORTRAIT -> AdSize.getPortraitAnchoredAdaptiveBannerAdSize(activity, getAdWidth(activity, adContainer))
        }
    }



    fun getInterscrollerAdSize(activity: Activity, adContainer: ViewGroup, orientation: Orientation): AdSize {
        return when (orientation) {
            Orientation.CURRENT -> AdSize.getCurrentOrientationInterscrollerAdSize(activity, getAdWidth(activity, adContainer))
            Orientation.LANDSCAPE -> AdSize.getLandscapeInterscrollerAdSize(activity, getAdWidth(activity, adContainer))
            Orientation.PORTRAIT -> AdSize.getPortraitInterscrollerAdSize(activity, getAdWidth(activity, adContainer))
        }
    }


    private fun getAdWidth(activity: Activity, adContainer: ViewGroup): Int {

        val display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        var adWidthPixels = adContainer.width.toFloat()

        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }
        val adWidth = (adWidthPixels / density).toInt()
        return adWidth

    }


}