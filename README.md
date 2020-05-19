# card-wallet-kotlin
Android project using Clean Architecture based on MVVM pattern.

### Sample app overview

A simple app for showing and adding credit card info. The aim of the app is to test Firebase ML Kit recognition features to
allow the user to fill card's info.

The application is written entirely in Kotlin.

Android Jetpack is used as an Architecture glue including but not limited to ViewModel, LiveData, Lifecycles, Navigation and Room.

Kotlin Coroutines manage background threads with simplified code and reducing needs for callbacks.

Koin is used for dependency injection.

Camera X and Firebase ML Kit to scan and analyse card info.