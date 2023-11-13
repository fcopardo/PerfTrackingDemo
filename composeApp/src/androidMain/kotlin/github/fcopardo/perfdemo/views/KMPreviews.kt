package github.fcopardo.perfdemo.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import github.fcopardo.perfdemo.view.MainView

class KMPreviews {
    @Composable
    @Preview
    private fun myApp(){
        MainView.MyApp()
    }
}