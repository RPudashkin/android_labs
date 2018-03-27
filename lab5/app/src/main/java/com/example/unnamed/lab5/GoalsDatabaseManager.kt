package com.example.unnamed.lab5

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class GoalsDatabaseManager(context: Context) {
    private val dbName             = "GoalsDB"
    private val dbTable            = "Goals"
    private val dbVersion          = 1

    private val COLUMN_ID          = "Id"
    private val COLUMN_DESCRIPTION = "Description"
    private val COLUMN_DONE_FLAG   = "Done"

    private val CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS $dbTable ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_DESCRIPTION TEXT, $COLUMN_DONE_FLAG TEXT);"

    private var db: SQLiteDatabase? = null

    init {
        var dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
    }

    fun insert(values: ContentValues): Long {
        val ID = db!!.insert(dbTable, "", values)
        return ID
    }

    fun queryAll(): Cursor {
        return db!!.rawQuery("select * from " + dbTable, null)
    }

    fun delete(selection: String, selectionArgs: Array<String>): Int {
        val count = db!!.delete(dbTable, selection, selectionArgs)
        return count
    }

    fun update(values: ContentValues, selection: String, selectionargs: Array<String>): Int {
        val count = db!!.update(dbTable, values, selection, selectionargs)
        return count
    }

    inner class DatabaseHelper: SQLiteOpenHelper {
        var context: Context? = null

        constructor(context: Context): super(context, dbName, null, dbVersion) {
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(CREATE_TABLE_SQL)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS " + dbTable)
        }
    }
}