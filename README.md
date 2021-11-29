<h1 align="center">GitHub Users App</h1>

<p align="center">
GitHub Users App is a sample GitHub Users application based on modern Android application tech-stacks and MVVM architecture.<br>This project is for focusing especially on the new library Hilt of implementing dependency injection.<br>
Also fetching data from the network and integrating persisted data in the database via repository pattern.
</p>

## Feature
1. Display list of GitHub User
<img width="300" height="600" src="https://github.com/aptx113/github-users-app/blob/add_readme/preview/display%20users.gif"/>

2. Display the last Recipe list when offline through cache.
<img width="300" height="600" src="https://github.com/aptx113/github-users-app/blob/add_readme/preview/display%20cache.gif"/>

## Tech stack & Open-source libraries
- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For writing Gradle build scripts using Kotlin.
- Jetpack
  - [Databinding](https://developer.android.com/topic/libraries/data-binding) - Bind UI components in the layouts to data sources in the app.
  - [Hilt](https://dagger.dev/hilt/) - For dependency injection.
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Build lifecycle-aware components, like ViewModel and LiveData that can automatically adjust their behavior based on the current lifecycle state of an activity or fragment.
  - [Recyclerview](https://developer.android.com/guide/topics/ui/layout/recyclerview) - Use to efficiently display large sets of data.
  - [Room](https://developer.android.com/training/data-storage/room) - Construct a database using the abstract layer for offline cache.
  - [Test](https://developer.android.com/jetpack/androidx/releases/test) - For unit tests and instrumented tests
  - [Truth](https://truth.dev/) - For tests' assertions
- Third Party Library
  - [Coil](https://github.com/coil-kt/coil) - Loading images.
  - [Mockk](https://mockk.io/) - For mock object in tests
  - [Moshi](https://github.com/square/moshi) - Parse and convert a JSON object into Kotlin objects.
  - [OkHttp3](https://github.com/square/okhttp) - Log the outgoing request and the incoming response.
  - [Retrofit2](https://github.com/square/retrofit) - Send requests to API and receive response.
  - [Timber](https://github.com/JakeWharton/timber) - For logging.
- [Material](https://github.com/material-components/material-components-android) - Help to build material components like bottom navigation bar, floating action button.
- [detekt](https://github.com/detekt/detekt) - Use static code analysis tool for the Kotlin to improve code smell.
- [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - To make code follow Kotlin official code style.
- Architectural and Design pattern
  - MVVM
  - Observer
  - Adapter
  - Dependency Injection
  - Singleton

## Open API

GitHub Users App using the [GitHub API](https://docs.github.com/en/rest) for obtaining remote data.<br>

## Contact
<danteyu.studio@gmail.com>

Dante Yu
