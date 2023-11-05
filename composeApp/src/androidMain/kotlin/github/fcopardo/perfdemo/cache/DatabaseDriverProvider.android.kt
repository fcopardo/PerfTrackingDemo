package github.fcopardo.perfdemo.cache

import android.content.Context
//import app.cash.sqldelight.db.SqlDriver
//import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverProvider(private val context : Context) {
    /*actual fun getDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "test.db")
    }*/
}