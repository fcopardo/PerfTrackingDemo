package github.fcopardo.perfdemo

import androidx.compose.ui.window.ComposeUIViewController
import github.fcopardo.perfdemo.view.ImageLoader
import github.fcopardo.perfdemo.view.MainIosView
import github.fcopardo.perfdemo.view.PlatformBoundImageLoader
import platform.UIKit.UIViewController

fun MainViewController() : UIViewController {
    ImageLoader.setPlatformLoader(PlatformBoundImageLoader())
    return ComposeUIViewController { MainIosView.Render() }
}
