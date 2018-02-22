package com.example.unnamed.lab3

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : AppCompatActivity() {
    private var m_photoUri  = ""
    private var m_photoName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)
    }

    private fun loadPhoto() {
        if (m_photoUri.isEmpty() )
            m_photoUri = intent.getStringExtra("photoUri")

        if (m_photoName.isEmpty())
            m_photoName = intent.getStringExtra("photoName")

        val targetHeight = this.imageView.height
        val targetWidth  = this.imageView.width
        if ((targetHeight == 0) or (targetWidth == 0)) return

        val bitmapOptions = BitmapFactory.Options()

        bitmapOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(m_photoUri, bitmapOptions)

        val photoHeight = bitmapOptions.outHeight
        val photoWidth  = bitmapOptions.outWidth
        val scaleFactor = Math.min(photoHeight / targetHeight, photoWidth / targetWidth)

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize       = scaleFactor;

        this.imageView.setImageBitmap(BitmapFactory.decodeFile(m_photoUri, bitmapOptions))
        this@ImageViewActivity.title = m_photoName
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("photoUri",  m_photoUri)
        outState.putString("photoName", m_photoName)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            m_photoUri  = savedInstanceState.getString("photoUri")
            m_photoName = savedInstanceState.getString("photoName")
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) loadPhoto()
    }
}