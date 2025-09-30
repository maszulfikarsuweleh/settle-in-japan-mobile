import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinIOS.shared.doInit()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
