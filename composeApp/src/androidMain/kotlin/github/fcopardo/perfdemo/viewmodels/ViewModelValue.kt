package github.fcopardo.perfdemo.viewmodels

sealed class ViewModelResult<T> {
    data class Success<T>(val value: T) : ViewModelResult<T>()
    data class Error<T>(val message : String) : ViewModelResult<T>()
}

data class ViewModelValue<T>(var data:T, var success : Boolean = true, var message : String = "")