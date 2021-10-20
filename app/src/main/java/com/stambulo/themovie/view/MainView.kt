package com.stambulo.themovie.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainView: MvpView {
    fun showMovie(title: String)
}
