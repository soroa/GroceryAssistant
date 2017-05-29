package com.example.asoro.healthygroceryassistant

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.asoro.healthygroceryassistant.dagger.SearchComponent
import com.example.asoro.healthygroceryassistant.ui.search.SearchLifecycleFragment
import com.example.asoro.healthygroceryassistant.ui.search_results.SearchResultsLifecycleFragment


class MainActivity() : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SearchLifecycleFragment.OnRecipeSearchEvent {

    //TODO Recipe repo from dagger




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        getSupportActionBar()?.hide()



        loadFragment(SearchLifecycleFragment())
    }


    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    fun loadFragment(f: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
        fragmentTransaction.replace(R.id.frag_container_search, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.search) {
            // Handle the camera action
        } else if (id == R.id.favorites) {

        } else if (id == R.id.shopping_list) {

        } else if (id == R.id.intermittent_fasting) {

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onRecipeSearchEvent() {
        loadFragment(SearchResultsLifecycleFragment())
    }


}
