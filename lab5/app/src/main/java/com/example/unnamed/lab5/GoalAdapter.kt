import android.content.ContentValues
import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.unnamed.lab5.R
import com.example.unnamed.lab5.Goal
import com.example.unnamed.lab5.GoalsDatabaseManager
import kotlinx.android.synthetic.main.goal_layout.view.*
import me.thanel.swipeactionview.SwipeActionView
import me.thanel.swipeactionview.SwipeGestureListener


class GoalAdapter(context: Context, goals: ArrayList<Goal>): RecyclerView.Adapter<GoalAdapter.ViewHolder>() {
    private val mGoals = goals
    private val mContext = context

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val goal = mGoals[position]
        holder?.description?.text = goal.description()
        holder?.description?.paintFlags = goal.done
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.goal_layout, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount() = mGoals.size

    private fun removeItem(position: Int) {
        val dbManager = GoalsDatabaseManager(mContext)
        dbManager.delete("Id=?", arrayOf(mGoals[position].id().toString()))
        mGoals.removeAt(position)
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mGoals.size);
    }

    fun addItem(goal: Goal) {
        mGoals.add(goal)
        notifyItemInserted(mGoals.size - 1)
        notifyDataSetChanged()

        // Write goal to DB
        val dbManager = GoalsDatabaseManager(mContext)
        var values = ContentValues()
        values.put("Id", goal.id().toString())
        values.put("Description", goal.description())
        values.put("Done", goal.done.toString())
        dbManager.insert(values)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description = view.goalDescription
        val swipeView   = view.swipeView
        var singleLine  = false

        init {
            swipeView.setOnLongClickListener {
                description.paintFlags = description.paintFlags xor Paint.STRIKE_THRU_TEXT_FLAG
                val goal = mGoals[adapterPosition]
                goal.done = description.paintFlags

                val dbManager = GoalsDatabaseManager(mContext)
                val values = ContentValues()
                values.put("Done", goal.done.toString())
                dbManager.update(values,"Id=?", arrayOf(goal.id().toString()))
                true
            }

            swipeView.setOnClickListener {
                description.setSingleLine(singleLine)
                singleLine = !singleLine
            }

            swipeView.swipeGestureListener = object : SwipeGestureListener {
                override fun onSwipedLeft(swipeActionView: SwipeActionView): Boolean {
                    description.paintFlags = 0
                    removeItem(adapterPosition)
                    return true
                }

                override fun onSwipedRight(swipeActionView: SwipeActionView): Boolean {
                    description.paintFlags = 0
                    removeItem(adapterPosition)
                    return true
                }
            }
        } // init
    } // ViewHolder
}