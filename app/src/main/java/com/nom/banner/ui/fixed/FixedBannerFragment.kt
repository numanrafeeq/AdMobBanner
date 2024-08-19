package com.nom.banner.ui.fixed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nom.banner.R
import com.nom.banner.adconfig.BannerAdManager
import com.nom.banner.adconfig.utils.AdSizeUtil
import com.nom.banner.databinding.FragmentFixedBinding

class FixedBannerFragment : Fragment() {

    private var _binding: FragmentFixedBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFixedBinding.inflate(inflater, container, false)
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
            val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId

            val selectedType = when (selectedRadioButtonId) {
                R.id.radioBanner -> {
                    AdSizeUtil.FixedSize.BANNER
                }

                R.id.radioFullBanner -> {
                    AdSizeUtil.FixedSize.FULL_BANNER
                }

                R.id.radioLargeBanner -> {
                    AdSizeUtil.FixedSize.LARGE_BANNER
                }

                R.id.radioLeaderboard -> {
                    AdSizeUtil.FixedSize.LEADERBOARD
                }

                R.id.radioMediumRectangle -> {
                    AdSizeUtil.FixedSize.MEDIUM_RECTANGLE
                }

                R.id.radioSkyscraper -> {
                    AdSizeUtil.FixedSize.WIDE_SKYSCRAPER
                }

                else -> {
                    AdSizeUtil.FixedSize.BANNER
                }
            }

            manager.loadFixedBannerAd(requireActivity(),binding.bannerAdContainer,"ca-app-pub-3940256099942544/6300978111",selectedType)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}