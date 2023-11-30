import Foundation
import UIKit

@objc public class KImage : NSObject {
    private static let imageCache = ImageCache()

    @objc class func downloadImageData(from url: URL) async throws -> Data {
        let request = URLRequest(url: url)
        let (data, _) = try await URLSession.shared.data(for: request)
        return data
    }

    @objc class func downloadImage(from url: URL) async throws -> UIImage {
        if let cachedImage = imageCache.image(for: url) {
            return cachedImage
        }

        let data = try await downloadImageData(from: url)
        guard let image = UIImage(data: data) else {
            throw NSError(domain: "InvalidImageData", code: 0, userInfo: nil)
        }

        imageCache.cacheImage(image, for: url)
        return image
    }

    @objc class func getDocumentDirectoryPath() -> URL {
        let arrayPaths = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDirectoryPath = arrayPaths[0]
        return docDirectoryPath
    }

    @objc class func getCacheDirectoryPath() -> URL {
        let arrayPaths = FileManager.default.urls(for: .cachesDirectory, in: .userDomainMask)
        let cacheDirectoryPath = arrayPaths[0]
        return cacheDirectoryPath
    }

    @objc class func getTempDirectoryPath() -> URL {
        let tempDirectoryPath = URL(fileURLWithPath: NSTemporaryDirectory(), isDirectory: true)
        return tempDirectoryPath
    }
}