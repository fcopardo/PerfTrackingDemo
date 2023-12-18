import os.signpost
import Foundation

@objc public class Tracer : NSObject {
    private static var logs  : [String : OSLog] = [:]

    @objc public class func logStart(id : String, data : String, description : String, category : String) {

        var osLog  : OSLog? = nil
        if let activeLog = logs[id]{
            osLog = activeLog
        } else {
            let myLog = OSLog(subsystem: id, category: category)
            logs[id] = myLog
            osLog = myLog
        }

        var signPost = OSSignpostID(log : osLog!)
        os_signpost(.begin, log: osLog!, name: "trace", signpostID: signPost, "perf")
    }

    @objc public class func logEnd(id : String, data : String, description : String, category : String) {
        var osLog  : OSLog? = nil
        if let activeLog = logs[id]{
            osLog = activeLog
        } else {
            let myLog = OSLog(subsystem: id, category: category)
            logs[id] = myLog
            osLog = myLog
        }
        var signPost = OSSignpostID(log : osLog!)
        os_signpost(.end, log: osLog!, name: "trace", signpostID: signPost, "perf")
    }
}
