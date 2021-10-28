package com.glushko.rentateamtesttask.presentation_layer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.glushko.rentateamtesttask.R
import com.glushko.rentateamtesttask.presentation_layer.vm.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var model: UserViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        progressBar = findViewById(R.id.toolbar_progress_bar)
        setSupportActionBar(toolbar)
        val bottomNavigate = findViewById<BottomNavigationView>(R.id.mainButtonNavigation)
        val navController =
            (supportFragmentManager.findFragmentById(R.id.containerGuestView) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavigate, navController)
        model = ViewModelProvider(this).get(UserViewModel::class.java)

        model.liveDataAPI.observe(this, Observer {
            progressBar.visibility = View.INVISIBLE
            if(it == false){
                Snackbar.make(bottomNavigate, getString(R.string.snackbar_users_list_text),Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.snackbar_users_list_action)){
                        model.getUsers()
                        progressBar.visibility= View.VISIBLE
                    }.show()
            }
        })

        progressBar.visibility = View.VISIBLE
        model.getUsers()
    }
}