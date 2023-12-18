import Foundation
import UIKit

public class ImageCache : NSObject {
    private var cache: [URL: UIImage] = [:]

    func cacheImage(_ image: UIImage, for url: URL) {
        cache[url] = image
    }

    func image(for url: URL) -> UIImage? {
        return cache[url]
    }
}