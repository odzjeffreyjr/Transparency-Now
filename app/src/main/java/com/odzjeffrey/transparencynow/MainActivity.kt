package com.odzjeffrey.transparencynow

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

private lateinit var mDrawerLayout: DrawerLayout
private lateinit var viewPager: ViewPager
private lateinit var tabs: TabLayout
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDrawerLayout = findViewById(R.id.drawerLayout)

        val ivToChats: ImageView = findViewById<ImageView>(R.id.ivToChats)
        ivToChats.setOnClickListener {
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }

        val ivNav = findViewById<ImageView>(R.id.ivNav)
        ivNav.setOnClickListener{
            mDrawerLayout.openDrawer(GravityCompat.START)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = sectionsPagerAdapter
        tabs = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }
}