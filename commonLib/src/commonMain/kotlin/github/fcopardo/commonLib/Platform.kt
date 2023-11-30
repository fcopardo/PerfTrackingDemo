package github.fcopardo.commonLib

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform