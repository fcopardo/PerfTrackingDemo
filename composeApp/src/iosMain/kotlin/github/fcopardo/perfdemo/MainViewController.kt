package github.fcopardo.perfdemo

import androidx.compose.ui.window.ComposeUIViewController
import github.fcopardo.perfdemo.tracing.EventTracer
import github.fcopardo.perfdemo.tracing.IosJsonWriter
import github.fcopardo.perfdemo.view.ImageLoader
import github.fcopardo.perfdemo.view.MainIosView
import github.fcopardo.perfdemo.view.PlatformBoundImageLoader
import github.fcopardo.perfdemo.viewmodels.MLSearchViewModel
import platform.UIKit.UIViewController

fun MainViewController() : UIViewController {
    ImageLoader.setPlatformLoader(PlatformBoundImageLoader())
    EventTracer.instance.jsonWriter = IosJsonWriter()
    return ComposeUIViewController { MainIosView.Render() }
}
