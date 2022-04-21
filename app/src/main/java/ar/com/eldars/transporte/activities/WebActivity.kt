package ar.com.eldars.transporte.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import ar.com.eldars.transporte.R
import java.util.zip.Inflater

class WebActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.web_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_call){
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:911"))
            startActivity(callIntent)
        }
        if (item.itemId == R.id.action_documents){
            val webPage= "https://www.eldars.com.ar/"
            val callIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webPage))
            startActivity(callIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = object : WebChromeClient(){}
        webView.webViewClient = object : WebViewClient(){}
        webView.loadUrl("file:///android_asset/help.html")
    }
}