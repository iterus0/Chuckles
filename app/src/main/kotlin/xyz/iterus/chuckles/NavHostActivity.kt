package xyz.iterus.chuckles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import xyz.iterus.chuckles.databinding.ActivityNavHostBinding

class NavHostActivity : AppCompatActivity(R.layout.activity_nav_host) {

    private lateinit var binding: ActivityNavHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomMenu()
    }

    private fun setupBottomMenu() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        binding.bottomMenu.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
    }
}
