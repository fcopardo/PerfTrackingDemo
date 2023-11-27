package github.fcopardo.perfdemo

import androidx.compose.ui.window.ComposeUIViewController
import github.fcopardo.perfdemo.App
import github.fcopardo.perfdemo.view.MainIosView

fun MainViewController() = ComposeUIViewController { MainIosView.Render() }
