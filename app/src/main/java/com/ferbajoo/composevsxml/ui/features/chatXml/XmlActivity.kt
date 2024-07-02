package com.ferbajoo.composevsxml.ui.features.chatXml

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferbajoo.composevsxml.R
import com.ferbajoo.composevsxml.ui.features.chatCompose.compose.FilterMenu

class XmlActivity : AppCompatActivity(){

    var all : CardView? = null
    var unread : CardView? = null
    var group : CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xml_activity_view)

        val toolbar = findViewById<Toolbar>(R.id.whatsapp_toolbar)
        setSupportActionBar(toolbar)

        findViewById<ComposeView>(R.id.container_header).setContent {
            FilterMenu()
        }

        all = findViewById(R.id.cv_all)
        unread = findViewById(R.id.cv_unread)
        group = findViewById(R.id.cv_group)

        fillRecyclerView()

        headerButtonsAction()
        paintHeaders(1)

    }

    private fun headerButtonsAction() {
        all?.setOnClickListener {
            paintHeaders(all?.tag.toString().toInt())
        }
        unread?.setOnClickListener {
            paintHeaders(unread?.tag.toString().toInt())
        }
        group?.setOnClickListener {
            paintHeaders(group?.tag.toString().toInt())
        }
    }

    private fun paintHeaders(selectedIndex: Int){
        val activeColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.tertiary_color))
        val disableColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray))
        all?.backgroundTintList = if (all?.tag.toString().toInt() == selectedIndex) activeColor else disableColor
        unread?.backgroundTintList = if (unread?.tag.toString().toInt() == selectedIndex) activeColor else disableColor
        group?.backgroundTintList = if (group?.tag.toString().toInt() == selectedIndex) activeColor else disableColor
    }

    private fun fillRecyclerView() {
        val recycler = findViewById<RecyclerView>(R.id.rv_chat_list)
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter()
        recycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu_toolbar, menu)

        val toolbar = findViewById<Toolbar>(R.id.whatsapp_toolbar)
        toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener true
        }
        return true
    }

}