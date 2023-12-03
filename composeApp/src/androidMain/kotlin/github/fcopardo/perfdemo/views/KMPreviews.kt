package github.fcopardo.perfdemo.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import github.fcopardo.perfdemo.view.composables.MainViewWidgets

class KMPreviews {
    @Composable
    @Preview
    private fun myApp(){
        MainViewWidgets.MyApp(onSearch = {}) {
        }
    }
}