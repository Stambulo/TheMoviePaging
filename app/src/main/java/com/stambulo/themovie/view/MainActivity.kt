package com.stambulo.themovie.view

import android.os.Bundle
import android.widget.TextView
import com.stambulo.themovie.R
import com.stambulo.themovie.presenter.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter

class MainActivity() : MvpAppCompatActivity(R.layout.activity_main), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv)

        presenter.requestMovie(88)
    }

    override fun showMovie(title: String) {
        tv.text = title
    }
}
