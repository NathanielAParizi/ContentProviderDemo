package com.example.contentproviderdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CelebrityCallback {

    var celebList = ArrayList<Celebrity>()


    val db by lazy { CelebrityDatabaseHelper(this) }
    val adapter by lazy { CelebrityAdapter(db.getAllCelebrities(), this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        populateCelebs()
        initRecyclerView()

        adapter.updateList(db.getAllCelebrities())

    }

    fun initRecyclerView() {

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.layoutManager = layoutManager
        rv.adapter = adapter

    }


    fun populateCelebs() {


        celebList.add(Celebrity("Brad", "Pitt", "Actor", "no", "3294"))
        celebList.add(Celebrity("Jimi", "Hendrix", "Artist", "yes", "32494"))
        celebList.add(Celebrity("Seth", "Rogan", "Actor", "no", "32924"))
        celebList.add(Celebrity("Tyler", "Durdan", "Actor", "yes", "321494"))
        celebList.add(Celebrity("Peter", "Parker", "Spiderman", "no", "326394"))
        celebList.add(Celebrity("Bruce", "Lee", "Actor", "yes", "323294"))
        celebList.add(Celebrity("Mr", "T", "Actor", "yes", "329994"))
        celebList.add(Celebrity("Gordon", "Ramsey", "Actor", "yes", "3288094"))
        celebList.add(Celebrity("Salvador", "Dali", "Artist", "yes", "32987594"))
        celebList.add(Celebrity("Isaac", "Newton", "Scientist", "no", "32998826594"))
        celebList.add(Celebrity("Charles", "Mingus", "Artist", "yes", "326678894"))

        for (i in 0..celebList.size - 1) {
            db.insertCelebritiy(celebList.get(i))
            Log.v("TAG", celebList.get(i).toString())

        }
        Log.v("TAG", db.getAllCelebrities().toString())


    }

    fun onClick(view: View) {

        var firstName = etFirstName.text.toString()
        var lastName = etLastName.text.toString()
        var job = etJob.text.toString()
        var favorite = etFav.text.toString()
        var id = etId.text.toString()

        db.insertCelebritiy(Celebrity(firstName, lastName, job, favorite, id))
        adapter.updateList(db.getAllCelebrities())

    }

    fun onContentProviderClicked(view: View) {
        val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
        val personList = ArrayList<Celebrity>()
        if (cursor!!.moveToFirst()) {
            do {

                val firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME)) + " CONTENT"
                val lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME)) + " CONTENT"
                val job = cursor.getString(cursor.getColumnIndex(COL_JOB)) + " CONTENT"
                val favorite = cursor.getString(cursor.getColumnIndex(COL_FAVORITE)) + " CONTENT"
                val id = cursor.getString(cursor.getColumnIndex(COL_ID)) + " CONTENT"

                val person = Celebrity(firstName, lastName, job, favorite, id)
                personList.add(person)

            } while (cursor.moveToNext())
        }

        cursor.close()
        adapter.updateList(personList)
    }
}

