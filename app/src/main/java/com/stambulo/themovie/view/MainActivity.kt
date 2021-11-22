package com.stambulo.themovie.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stambulo.themovie.MovieApplication
import com.stambulo.themovie.R
import com.stambulo.themovie.databinding.ActivityMainBinding
import com.stambulo.themovie.view.navigation.screens.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity(): AppCompatActivity(R.layout.activity_main){

    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var router: Router
    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MovieApplication.instance.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        router.replaceScreen(Screens.MovieListScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}
