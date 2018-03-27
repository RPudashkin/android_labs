package com.example.unnamed.lab5

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import GoalAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList
import android.app.AlertDialog
import android.text.InputType
import android.widget.EditText


class MainActivity: AppCompatActivity() {
    var mGoalAdapter = GoalAdapter(this, ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Creating a new goal")

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.setSingleLine(false)
            builder.setView(input)

            builder.setPositiveButton("OK", {
                dialog, which -> val description = input.text.toString()

                if (description.isNotEmpty()) {
                    val goal = Goal(description)
                    mGoalAdapter.addItem(goal)
                }
            })

            builder.setNegativeButton("Cancel", {
                dialog, which -> dialog.cancel()
            })

            builder.show()
        }

        mGoalAdapter = GoalAdapter(this,  loadGoalsFromDB())
        this.todoListView.setHasFixedSize(true)
        this.todoListView.layoutManager = LinearLayoutManager(this)
        this.todoListView.adapter = mGoalAdapter
    }

    private fun loadGoalsFromDB(): ArrayList<Goal> {
        val dbManager = GoalsDatabaseManager(this)
        val cursor = dbManager.queryAll()
        val goals  = ArrayList<Goal>()

        while (cursor.moveToNext()) {
            val description = cursor.getString(cursor.getColumnIndex("Description"))
            val done = cursor.getInt(cursor.getColumnIndex("Done"))
            val goal = Goal(description)
            goal.done = done
            goals.add(goal)
        }
        return goals
    }
}