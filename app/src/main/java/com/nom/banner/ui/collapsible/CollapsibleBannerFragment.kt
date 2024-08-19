package com.nom.banner.ui.collapsible

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nom.banner.R
import com.nom.banner.adconfig.BannerAdManager
import com.nom.banner.adconfig.utils.AdSizeUtil
import com.nom.banner.databinding.FragmentCollapsibleBinding


class CollapsibleBannerFragment : Fragment() {

    private var _binding: FragmentCollapsibleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCollapsibleBinding.inflate(inflater, container, false)
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

            when (selectedRadioButtonId) {
                R.id.radioTop -> {

                    manager.loadCollapsibleBannerAd(
                        requireActivity(),
                        binding.bannerAdContainerTop,
                        "ca-app-pub-3940256099942544/9214589741",
                        AdSizeUtil.Collapsible.TOP
                    )

                }

                R.id.radioBottom -> {

                    manager.loadCollapsibleBannerAd(
                        requireActivity(),
                        binding.bannerAdContainerBottom,
                        "ca-app-pub-3940256099942544/9214589741",
                        AdSizeUtil.Collapsible.BOTTOM
                    )

                }


                else -> {
                    manager.loadCollapsibleBannerAd(
                        requireActivity(),
                        binding.bannerAdContainerTop,
                        "ca-app-pub-3940256099942544/9214589741",
                        AdSizeUtil.Collapsible.TOP
                    )

                }
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}