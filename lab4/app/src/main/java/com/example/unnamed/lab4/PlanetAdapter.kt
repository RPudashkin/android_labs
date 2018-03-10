import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.unnamed.lab4.PlanetWebViewActivity
import com.example.unnamed.lab4.R
import kotlinx.android.synthetic.main.item_layout.view.*


class PlanetAdapter(context: Context, planets: ArrayList<Planet>): RecyclerView.Adapter<PlanetAdapter.ViewHolder>() {
    private val mPlanets = planets
    private val mContext = context

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val planet = mPlanets[position]

        holder?.planetName?.text     = planet.name()
        holder?.planetUrl            = planet.url()
        holder?.distaceFromSun?.text = planet.distanceFromSun().toString()  + " million km. from the Sun"
        holder?.planetImage?.setImageBitmap(planet.image())


        if (planet.hasLife()) {
            val icon = planet.lifeIcon()!!
            val w = icon.width / 2
            val h = icon.height / 2

            if ((w !=0) and (h != 0)) {
                holder?.lifeStr?.text = " (" + mContext.getString(R.string.there_are_life) + ") "
                holder?.lifeIcon?.setImageBitmap(Bitmap.createScaledBitmap(icon, w, h, true))
            }
        }
        else {
            holder?.lifeStr?.text = ""
            holder?.lifeIcon?.setImageResource(android.R.color.transparent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount() = mPlanets.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mainLayout     = view.mainLayout
        val planetName     = view.planetName
        val planetImage    = view.planetImage
        val distaceFromSun = view.distaceFromSun
        var planetUrl      = ""
        val lifeStr        = view.lifeStr
        val lifeIcon       = view.lifeIcon

        init {
            mainLayout.setOnClickListener {
                val intent = Intent(view.context, PlanetWebViewActivity::class.java)
                intent.putExtra("planetName", planetName.text)
                intent.putExtra("planetUrl",  planetUrl)
                view.context.startActivity(intent)
            }
        }
    }
}