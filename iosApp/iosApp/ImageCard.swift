//
//  ImageCard.swift
//  iosApp
//
//  Created by Hermann Friesen on 14.10.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ImageCard: View {
    
    let image: BirdImage
    
    var body: some View {
        AsyncImage(url: URL(string:"https://sebi.io/demo-image-api/\(image.path)")) { image in
            image
                .resizable()
        } placeholder: {
            Image(systemName: "photo")
                .resizable()
        }
        .frame(width: .infinity, height: 200)
        .cornerRadius(16)
    }
}

