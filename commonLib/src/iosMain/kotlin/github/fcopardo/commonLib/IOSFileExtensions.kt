package github.fcopardo.commonLib

import kotlinx.cinterop.*
import kotlinx.coroutines.yield
import platform.Foundation.*
import platform.posix.memcpy

//copied from https://github.com/JetBrains/compose-multiplatform/blob/c09b344c0345ce82742af6c6614ae1db4ae630b4/examples/imageviewer/shared/src/iosMain/kotlin/example/imageviewer/storage/FileExtensions.kt

@OptIn(ExperimentalForeignApi::class)
val NSFileManager.DocumentDirectory
    get() = URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        create = true,
        appropriateForURL = null,
        error = null
    )!!

@Suppress("FunctionName")
fun File(dir: NSURL, child: String) =
    dir.URLByAppendingPathComponent(child)!!

@OptIn(ExperimentalForeignApi::class)
val NSURL.isDirectory: Boolean
    get() {
        return memScoped {
            val isDirectory = alloc<BooleanVar>()
            val fileExists = NSFileManager.defaultManager.fileExistsAtPath(path!!, isDirectory.ptr)
            fileExists && isDirectory.value
        }
    }

@OptIn(ExperimentalForeignApi::class)
fun NSURL.mkdirs() {
    NSFileManager.defaultManager.createDirectoryAtURL(this, true, null, null)
}

@OptIn(ExperimentalForeignApi::class)
fun NSURL.listFiles(filter: (NSURL, String) -> Boolean) =
    NSFileManager.defaultManager.contentsOfDirectoryAtPath(path!!, null)
        ?.map { it.toString() }
        ?.filter { filter(this, it) }
        ?.map { File(this, it) }
        ?.toTypedArray()

@OptIn(ExperimentalForeignApi::class)
fun NSURL.delete() {
    NSFileManager.defaultManager.removeItemAtURL(this, null)
}

suspend fun NSURL.readData(): NSData {
    while (true) {
        val data = NSData.dataWithContentsOfURL(this)
        if (data != null)
            return data
        yield()
    }
}

@OptIn(ExperimentalForeignApi::class)
suspend fun NSURL.readBytes(): ByteArray =
    with(readData()) {
        ByteArray(length.toInt()).apply {
            usePinned {
                memcpy(it.addressOf(0), bytes, length)
            }
        }
    }

@OptIn(ExperimentalForeignApi::class)
fun NSURL.readText(): String =
    NSString.stringWithContentsOfURL(
        url = this,
        encoding = NSUTF8StringEncoding,
        error = null,
    ) as String

@OptIn(ExperimentalForeignApi::class)
fun NSURL.writeText(text: String) {
    (text as NSString).writeToURL(
        url = this,
        atomically = true,
        encoding = NSUTF8StringEncoding,
        error = null
    )
}