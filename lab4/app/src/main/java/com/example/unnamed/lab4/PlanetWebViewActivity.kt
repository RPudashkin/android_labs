package com.example.unnamed.lab4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_planet_web_view.*


class PlanetWebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet_web_view)

        val bunble = intent.extras
        if (bunble != null) {
            this.title = bunble!!.getString("planetName")
            this.planetWebView.setWebViewClient(WebViewClient())
            this.planetWebView.loadUrl(bunble!!.getString("planetUrl"))
        }
    }
}
