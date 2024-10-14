//
//  ViewModel.swift
//  iosApp
//
//  Created by Hermann Friesen on 14.10.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared


@MainActor
class BirdViewModel: ObservableObject {
    
    @Published var images: [BirdImage] = []
    
    init() {
        fetchData()
    }
    
    func fetchData() {
        Task {
            do {
                self.images = try await BirdRepository().getImages()
            } catch {
                print("Request failed with error: \(error)")
            }
        }
    }
}
