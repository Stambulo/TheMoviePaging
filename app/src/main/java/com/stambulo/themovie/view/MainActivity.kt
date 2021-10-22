package com.stambulo.themovie.view

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stambulo.themovie.MovieApplication
import com.stambulo.themovie.R
import com.stambulo.themovie.databinding.ActivityMainBinding
import com.stambulo.themovie.presenter.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity(): MvpAppCompatActivity(R.layout.activity_main), MainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private lateinit var binding: ActivityMainBinding


    val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply {
            MovieApplication.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MovieApplication.instance.appComponent.inject(this)
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
