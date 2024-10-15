import SwiftUI
import Shared
import KMPObservableViewModelSwiftUI

struct ContentView: View {
    
    @ObservedViewModel var viewModel = BirdsViewModel()
    
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 8) {
                if viewModel.uiState.value.images.isEmpty {
                    ForEach(0..<10, id: \.self) { _ in
                        Text("Loading...")
                    }
                } else {
                    ForEach(viewModel.uiState.value.images, id: \.self) { image in
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
