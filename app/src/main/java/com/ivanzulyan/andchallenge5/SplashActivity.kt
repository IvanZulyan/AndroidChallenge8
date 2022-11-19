package com.ivanzulyan.andchallenge5

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ivanzulyan.andchallenge5.viewmodel.LoginViewModel
import com.ivanzulyan.andchallenge5.viewmodel.ViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var pref: com.ivanzulyan.andchallenge5.datastore.LoginDataStoreManager
    private lateinit var viewModelLoginPref: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = this.getSharedPreferences(
            "datauser",
            Context.MODE_PRIVATE
        )


        pref = com.ivanzulyan.andchallenge5.datastore.LoginDataStoreManager(this)
        viewModelLoginPref = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]
        supportActionBar?.hide()

        Handler().postDelayed({
            viewModelLoginPref.getUser().observe(this, {
                if (it.username == "" && it.password == "") {
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            })
        }, 3000)
    }
}