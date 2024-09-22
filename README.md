## App Overview
This Android app demonstrates a dynamic media experience by leveraging modern technologies like **Jetpack Compose**, **Kotlin**, and **Retrofit**. The app interacts with an external API to fetch media content and displays it in a series of scrollable carousels on the **MainScreen**. Each carousel represents different categories of media, such as movies or TV shows, and users can navigate through them smoothly using intuitive scroll functionality.

## Features

### MainScreen with Scrollable Carousels
- The **MainScreen** showcases a list of scrollable carousels, each filled with media content fetched from an API.
- The carousels can be scrolled both vertically (for different categories) and horizontally (for media items within each category).
- Each item in the carousel is clickable and leads to detailed information about that specific media content.

### DetailScreen
- Upon selecting an item from any carousel, the user is navigated to the **DetailScreen**, where basic information about the chosen media is displayed.
- The detailed information includes the title, description, and a prominent image of the media item.
- If the selected media is a movie or TV show, a "Play" button is shown to simulate a media player experience.

### PlayerScreen
- For media items of type video or TV show, the user can click the "Play" button, which leads to the **PlayerScreen**.
- The **PlayerScreen** is a simulated media player that displays the main image of the selected content in full-screen landscape mode.
- This feature gives users a simulated playback experience, enhancing the interactivity of the app.

## What's Being Used
- **Jetpack Compose**: For building a modern and responsive UI with declarative programming.
- **Kotlin**: The primary language for the entire app, ensuring clear and concise code.
- **Retrofit**: To make API calls and fetch media content dynamically.
- **Coroutines**: Used to handle asynchronous tasks like API calls.
- **Flow**: For managing data streams from the repository.
- **Unit Testing with MockK**: To test the repository, use cases, and other components of the app, ensuring robust and reliable functionality.
Yo
