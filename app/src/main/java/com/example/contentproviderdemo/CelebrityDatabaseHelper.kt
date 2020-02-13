package com.example.contentproviderdemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CelebrityDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(CREATE_CELEBRITY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }

    fun insertCelebritiy(celebrity: Celebrity) {
        val database = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_FIRST_NAME, celebrity.firstName)
        contentValues.put(COL_LAST_NAME, celebrity.lastName)
        contentValues.put(COL_JOB, celebrity.job)
        contentValues.put(COL_FAVORITE, celebrity.favorite)
        contentValues.put(COL_ID, celebrity.id)

        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    fun getOnePersonFromDatabase(ssn : String) : Celebrity? {
        val database = readableDatabase
        var celebrity : Celebrity? = null
        val cursor = database
            .rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_ID = '$ssn'",
                null)

        if(cursor.moveToFirst()) {

            val firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME))
            val job = cursor.getString(cursor.getColumnIndex(COL_JOB))
            val favorite = cursor.getString(cursor.getColumnIndex(COL_FAVORITE))
            val id = cursor.getString(cursor.getColumnIndex(COL_ID))
            celebrity = Celebrity(firstName, lastName, job, favorite, id)


        }
        cursor.close()
        database.close()
        return celebrity
    }


    fun getAllCelebrities(): ArrayList<Celebrity> {
        val database = readableDatabase
        var celebList: ArrayList<Celebrity> = ArrayList()
        val cursor = database
            .rawQuery(
                "SELECT * FROM $TABLE_NAME",
                null
            )

        if (cursor.moveToFirst()) {
            do {
                val firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME))
                val lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME))
                val job = cursor.getString(cursor.getColumnIndex(COL_JOB))
                val favorite = cursor.getString(cursor.getColumnIndex(COL_FAVORITE))

                val id = cursor.getString(cursor.getColumnIndex(COL_ID))
                val celebrity = Celebrity(firstName, lastName, job, favorite, id)
                celebList.add(celebrity)
            } while (cursor.moveToNext())
        }

        cursor.close()
        database.close()
        return celebList
    }

    fun updateCeleb(celebrity: Celebrity) {
        val database = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(COL_FIRST_NAME, celebrity.firstName)
        contentValues.put(COL_LAST_NAME, celebrity.lastName)
        contentValues.put(COL_JOB, celebrity.job)
        contentValues.put(COL_FAVORITE, celebrity.favorite)
        contentValues.put(COL_ID, celebrity.id)

        database.update(TABLE_NAME, contentValues, "$COL_ID = ?", arrayOf(celebrity.id))
        database.close()

    }

    fun removePersonFromDatabase(id: String) {
        val database = writableDatabase
        database.delete(TABLE_NAME, "$COL_ID = ?", arrayOf(id))
        database.close()
    }

}