package com.nom.banner.adconfig

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.nom.banner.adconfig.utils.AdSizeUtil
import com.nom.banner.adconfig.utils.Constant

class BannerAdManager {


    fun loadCustomSizeBannerAd(
        activity: Activity, adContainer: ViewGroup, adUnitId: String, width: Int, height: Int
    ) {


        // Get the ad size and add the ad view to the layout
        val adSize = AdSizeUtil.getCustomInlineAdSize(width, height)

        val adView = AdView(activity)
        adView.adUnitId = adUnitId
        adView.setAdSize(adSize)


        // Load an ad
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)


        adView.adListener = initListener(adContainer, adView)

    }


    fun loadAdaptiveBannerAd(
        activity: Activity, adContainer: ViewGroup, adUnitId: String, adCategory: AdSizeUtil.AdCategory, orientation: AdSizeUtil.Orientation
    ) {


        // Get the ad size and add the ad view to the layout
        val adSize = when (adCategory) {
            AdSizeUtil.AdCategory.INLINE -> AdSizeUtil.getInlineAdSize(activity, adContainer, orientation)
            AdSizeUtil.AdCategory.ANCHORED -> AdSizeUtil.getAnchoredAdSize(activity, adContainer, orientation)
            AdSizeUtil.AdCategory.INTERSCROLLER -> AdSizeUtil.getInterscrollerAdSize(activity, adContainer, orientation)
        }

        val adView = AdView(activity)
        adView.adUnitId = adUnitId
        adView.setAdSize(adSize)


        // Load an ad
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)


        adView.adListener = initListener(adContainer, adView)

    }

    fun loadFixedBannerAd(activity: Activity, adContainer: ViewGroup, adUnitId: String, adSize: AdSizeUtil.FixedSize) {


        // Get the ad size and add the ad view to the layout

        val adType = when (adSize) {
            AdSizeUtil.FixedSize.BANNER -> AdSize.BANNER
            AdSizeUtil.FixedSize.FULL_BANNER -> AdSize.FULL_BANNER
            AdSizeUtil.FixedSize.LARGE_BANNER -> AdSize.LARGE_BANNER
            AdSizeUtil.FixedSize.LEADERBOARD -> AdSize.LEADERBOARD
            AdSizeUtil.FixedSize.MEDIUM_RECTANGLE -> AdSize.MEDIUM_RECTANGLE
            AdSizeUtil.FixedSize.WIDE_SKYSCRAPER -> AdSize.WIDE_SKYSCRAPER
        }

        val adView = AdView(activity)
        adView.adUnitId = adUnitId
        adView.setAdSize(adType)


        // Load an ad
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)


        adView.adListener = initListener(adContainer, adView)


    }

    fun loadCollapsibleBannerAd(activity: Activity, adContainer: ViewGroup, adUnitId: String, direction: AdSizeUtil.Collapsible) {


        // Get the ad size and add the ad view to the layout
        val adSize = AdSizeUtil.getAnchoredAdSize(activity, adContainer, AdSizeUtil.Orientation.CURRENT)
        val adView = AdView(activity)
        adView.adUnitId = adUnitId
        adView.setAdSize(adSize)

        val extras = Bundle()

        when (direction) {
            AdSizeUtil.Collapsible.TOP -> extras.putString(Constant.AD_COLLAPSIBLE, Constant.AD_COLLAPSIBLE_TOP)
            AdSizeUtil.Collapsible.BOTTOM -> extras.putString(Constant.AD_COLLAPSIBLE, Constant.AD_COLLAPSIBLE_BOTTOM)
        }

        // Load an ad
        val adRequest = AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter::class.java, extras).build()

        adView.loadAd(adRequest)


        adView.adListener = initListener(adContainer, adView)


    }

    private fun initListener(adContainer: ViewGroup, adView: AdView) = object : AdListener() {

        override fun onAdLoaded() {
            // Code to be executed when an ad finishes loading.
            Log.d(Constant.TAG, " : Ad Loaded Successfully ")
            // Add the AdView to the container
            adContainer.visibility = View.VISIBLE
            adContainer.removeAllViews()
            adContainer.addView(adView)

        }

        override fun onAdFailedToLoad(adError: LoadAdError) {
            // Code to be executed when an ad request fails.
            Log.d(Constant.TAG, " : Ad Load Failed : ${adError.message}")

        }


    }
}