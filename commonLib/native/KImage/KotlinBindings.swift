import Foundation
import UIKit

@objc public class ImageDownloader : NSObject {
    private static let imageCache = ImageCache()

    @objc public class func downloadImageData(from url: URL) async throws -> Data {
        let request = URLRequest(url: url)
        let (data, _) = try await URLSession.shared.data(for: request)
        return data
    }

    @objc public class func downloadImage(from url: URL) async throws -> UIImage {
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
}