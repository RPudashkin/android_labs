import android.graphics.Bitmap

class Planet(name:            String,
             image:           Bitmap,
             distanceFromSun: Int,
             url:             String,
             lifeIcon:        Bitmap? = null)
{
    private val mName            = name
    private val mImage           = image
    private val mDistanceFromSun = distanceFromSun
    private val mUrl             = url
    private val mLifeIcon        = lifeIcon

    fun name            () = mName
    fun image           () = mImage
    fun distanceFromSun () = mDistanceFromSun
    fun url             () = mUrl
    fun lifeIcon        () = mLifeIcon
    fun hasLife         () = mLifeIcon != null
}