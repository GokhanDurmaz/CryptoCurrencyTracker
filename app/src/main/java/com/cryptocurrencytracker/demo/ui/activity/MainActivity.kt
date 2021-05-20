package com.cryptocurrencytracker.demo.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.cryptocurrencytracker.demo.R
import com.cryptocurrencytracker.demo.databinding.ActivityMainBinding
import com.cryptocurrencytracker.demo.ui.base.BaseActivity

/**
 * Created by gokhan on 2/6/21.
 */

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getActivityViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView()
    }

    private fun setView() {
        setSupportActionBar(activityBinding.header.appbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = resources.getText(R.string.app_title)
        }
        activityBinding.sideMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.user -> {

                }
                R.id.favorite -> {
                    findNavController(R.id.nav_host_fragment).let { navController ->
                        navController.navigate(navController.graph.startDestination)
                        navController.navigate(R.id.action_mainFragment_to_coinFavorite)
                    }
                }
                R.id.settings -> {
                    findNavController(R.id.nav_host_fragment).let { navController ->
                        navController.navigate(navController.graph.startDestination)
                        navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                    }
                }
                R.id.version -> {

                }
                else -> Log.d("MainActivity", "${it.itemId}")
            }
            activityBinding.drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.options -> activityBinding.drawer.openDrawer(GravityCompat.START)
            android.R.id.home -> {
                activityBinding.drawer.closeDrawer(GravityCompat.START)
                findNavController(R.id.nav_host_fragment).let { navController ->
                    navController.navigate(navController.graph.startDestination)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
