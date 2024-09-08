# Photo Album App

The Photo Album app allows users to store and organize their photos with a clean, modern UI built using Jetpack Compose. Users can add, view, and delete photos, with data stored locally using Room Database. The app is built following the MVVM (Model-View-ViewModel) architecture, ensuring efficient data management and scalability.

## Features

- Add, view, and delete photos
- Local photo storage using Room Database
- Simple and responsive UI with Jetpack Compose
- MVVM architecture for separation of concerns and scalability
- Real-time updates with ViewModel, LiveData, and StateFlow

## Architecture

This app follows the **MVVM (Model-View-ViewModel)** pattern:
- **Model:** Handles photo data management using Room Database.
- **View:** Jetpack Compose is used for building the UI.
- **ViewModel:** Manages photo-related data and business logic using LiveData/StateFlow.

## Technologies Used

- **Jetpack Compose** - UI toolkit for building native Android UIs.
- **Room Database** - SQLite abstraction library for local photo storage.
- **MVVM Architecture** - For separation of concerns.
- **LiveData/StateFlow** - For observing changes in data and real-time UI updates.
- **Kotlin** - Language used for development.

## Installation

1. Clone this repository:
    ```bash
    git clone https://github.com/yourusername/photo-album-app.git
    ```
2. Open the project in Android Studio.
3. Build and run the app on an emulator or a physical device.

## Usage

- **Add Album:** Click the "Add Photo" button, select a photo from your gallery, and save.
- **View Album:** Tap on a photo to view it in full screen.
- **Delete Album:** Swipe left or tap the delete icon to remove a photo.
- **Update Album:** Swipe left or tap the delete icon to remove a photo.
![Screenshot_20240908_152833](https://github.com/user-attachments/assets/0367428c-f8b2-450f-b740-13314891302c)



