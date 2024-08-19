package com.nom.banner.ui.adaptive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nom.banner.R
import com.nom.banner.adconfig.BannerAdManager
import com.nom.banner.adconfig.utils.AdSizeUtil
import com.nom.banner.databinding.FragmentAdaptiveBinding


class AdaptiveBannerFragment : Fragment() {

    private var _binding: FragmentAdaptiveBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdaptiveBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAds()
    }

    private fun initAds() {

        val manager = BannerAdManager()


        binding.btnLoadAndShowBanner.setOnClickListener {
            val selectedOrientationRadioButtonId = binding.radioOrientation.checkedRadioButtonId
            val selectedCategoryRadioButtonId = binding.radioCategory.checkedRadioButtonId

            val selectedOrientation = when (selectedOrientationRadioButtonId) {

                R.id.radioCurrent -> {
                    AdSizeUtil.Orientation.CURRENT
                }

                R.id.radioLandscape -> {
                    AdSizeUtil.Orientation.LANDSCAPE
                }

                R.id.radioPortrait -> {
                    AdSizeUtil.Orientation.PORTRAIT
                }

                else -> {
                    AdSizeUtil.Orientation.CURRENT
                }

            }

            val selectedCategory = when (selectedCategoryRadioButtonId) {
                R.id.radioAnchored -> {
                    AdSizeUtil.AdCategory.ANCHORED
                }

                R.id.radioInline -> {
                    AdSizeUtil.AdCategory.INLINE
                }

                R.id.radioInterscroller -> {
                    AdSizeUtil.AdCategory.INTERSCROLLER
                }

                else -> {
                    AdSizeUtil.AdCategory.ANCHORED
                }
            }

            manager.loadAdaptiveBannerAd(requireActivity(), binding.bannerAdContainer,"ca-app-pub-3940256099942544/9214589741", selectedCategory, selectedOrientation)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}