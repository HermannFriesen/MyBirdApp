import SwiftUI
import Shared

struct ContentView: View {
    
    @ObservedObject var viewModel = BirdViewModel()
    
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 8) {
                if viewModel.images.isEmpty {
                    ForEach(0..<10, id: \.self) { _ in
                        Text("Loading...")
                    }
                } else {
                    ForEach(viewModel.images, id: \.self) { image in
                        ImageCard(image: image)
                    }
                }
            }
            .padding(.horizontal)
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView(viewModel: ContentView.ViewModel())
//    }
//}
