package com.example.unnamed.lab4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import PlanetAdapter
import Planet
import android.graphics.BitmapFactory
import android.widget.Toast
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val planets = fillPlanetsList()

        this.planetsList.setHasFixedSize(true)
        this.planetsList.layoutManager = LinearLayoutManager(this)
        this.planetsList.adapter       = PlanetAdapter(this@MainActivity, planets)
    }

    private fun fillPlanetsList(): ArrayList<Planet> {
        val mercury       = getString(R.string.mercury)
        val venus         = getString(R.string.venus)
        val earth         = getString(R.string.earth)
        val mars          = getString(R.string.mars)
        val jupiter       = getString(R.string.jupiter)
        val saturn        = getString(R.string.saturn)
        val uranus        = getString(R.string.uranus)
        val neptune       = getString(R.string.neptune)
        val pluto         = getString(R.string.pluto)

        val mercuryUrl    = getString(R.string.mercury_url)
        val venusUrl      = getString(R.string.venus_url)
        val earthUrl      = getString(R.string.earth_url)
        val marsUrl       = getString(R.string.mars_url)
        val jupiterUrl    = getString(R.string.jupiter_url)
        val saturnUrl     = getString(R.string.saturn_url)
        val uranusUrl     = getString(R.string.uranus_url)
        val neptuneUrl    = getString(R.string.neptune_url)
        val plutoUrl      = getString(R.string.pluto_url)

        val mercuryBitmap = BitmapFactory.decodeResource(this.resources, R.drawable.mercury)
        val venusBitmap   = BitmapFactory.decodeResource(this.resources, R.drawable.venus)
        val earthBitmap   = BitmapFactory.decodeResource(this.resources, R.drawable.earth)
        val marsBitmap    = BitmapFactory.decodeResource(this.resources, R.drawable.mars)
        val jupiterBitmap = BitmapFactory.decodeResource(this.resources, R.drawable.jupiter)
        val saturnBitmap  = BitmapFactory.decodeResource(this.resources, R.drawable.saturn)
        val uranusBitmap  = BitmapFactory.decodeResource(this.resources, R.drawable.uranus)
        val neptuneBitmap = BitmapFactory.decodeResource(this.resources, R.drawable.neptune)
        val plutoBitmap   = BitmapFactory.decodeResource(this.resources, R.drawable.pluto)
        val lifeBitmap    = BitmapFactory.decodeResource(this.resources, R.drawable.cat)

        val planets       = ArrayList<Planet>()

        planets.add(Planet(mercury, mercuryBitmap, 58  , mercuryUrl))
        planets.add(Planet(venus  ,   venusBitmap, 108 , venusUrl))
        planets.add(Planet(earth  ,   earthBitmap, 150 , earthUrl, lifeBitmap))
        planets.add(Planet(mars   ,    marsBitmap, 228 , marsUrl))
        planets.add(Planet(jupiter, jupiterBitmap, 778 , jupiterUrl))
        planets.add(Planet(saturn ,  saturnBitmap, 1429, saturnUrl))
        planets.add(Planet(uranus ,  uranusBitmap, 2875, uranusUrl))
        planets.add(Planet(neptune, neptuneBitmap, 4497, neptuneUrl))
        planets.add(Planet(pluto  ,   plutoBitmap, 5913, plutoUrl))

        return planets
    }
}