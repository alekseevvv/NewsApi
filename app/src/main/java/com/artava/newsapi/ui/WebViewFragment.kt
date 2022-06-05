package com.artava.newsapi.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.artava.newsapi.R
import com.artava.newsapi.databinding.FragmentWebViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class WebViewFragment : Fragment() {
    val Url = "ya.ru"
    lateinit var binding: FragmentWebViewBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(Url)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar2.isVisible = true
        val webView = binding.webView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.setSupportZoom(true)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        webView.webViewClient = WebViewClient()

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, weburl: String) {
                binding.progressBar2.isVisible = false

            }
        }

        webView.setOnClickListener {

        }
        webView.loadUrl(url!!)

        val nav_view = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        nav_view.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(Url, url)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        val view = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)

        view.visibility = View.VISIBLE
    }
}