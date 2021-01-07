package com.flowz.linkedinexpressoessentialcourse

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sqisland.android.espresso.recyclerview.NumberedAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var greetingsView: TextView
    private lateinit var greetButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(getString(R.string.title))

        greetingsView = findViewById(R.id.greeting)
        greetButton = findViewById(R.id.greet_button)


        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        val footer = findViewById<TextView>(R.id.footer)
        footer.setBackgroundColor(Color.LTGRAY)
        footer.visibility = View.GONE

//        val listView = findViewById<ListView>(R.id.list)
//
//        val items = arrayOfNulls<Item>(COUNT)
//        for (i in 0 until COUNT) {
//            items[i] = Item(i)
//        }
//        val adapter = ArrayAdapter<Item>(
//            this,
//            android.R.layout.simple_list_item_1, items
//        )
//        listView.adapter = adapter
//
//        listView.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id ->
//                footer.text = items[position].toString()
//                footer.visibility = View.VISIBLE
//            }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NumberedAdapter(30, object : NumberedAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                footer.text = position.toString()
                footer.visibility = View.VISIBLE
            }
        })
    }

    companion object {
        private val COUNT = 30
    }

    fun greet(v: View) {
        greetButton.isEnabled = false
        greetingsView.setText(R.string.hello_2021)
    }

    class Item(private val value: Int) {
        override fun toString(): String {
            return value.toString()
        }

    }
}

