package github.fcopardo.perfdemo

import androidx.compose.ui.window.ComposeUIViewController
import github.fcopardo.perfdemo.view.ImageLoader
import github.fcopardo.perfdemo.view.MainIosView
import github.fcopardo.perfdemo.view.PlatformBoundImageLoader
import github.fcopardo.perfdemo.viewmodels.MLSearchViewModel
import platform.UIKit.UIViewController

fun MainViewController() : UIViewController {
    ImageLoader.setPlatformLoader(PlatformBoundImageLoader())
    val model = MLSearchViewModel()
    return ComposeUIViewController { MainIosView.Render() }
}
