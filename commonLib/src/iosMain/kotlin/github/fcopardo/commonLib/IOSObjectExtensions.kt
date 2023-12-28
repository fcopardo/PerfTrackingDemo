package github.fcopardo.commonLib

import platform.Foundation.NSMutableDictionary
import platform.Foundation.setValue

fun <T, X> Map<T, X>.toDictionary(): NSMutableDictionary {
    val dictionary = NSMutableDictionary()
    entries.forEach {
        dictionary.setValue(it.key, it.value.toString())
    }
    return dictionary
}