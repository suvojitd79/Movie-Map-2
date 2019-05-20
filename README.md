# Movie-Map(Stage-2)

This app is made as a part of Udacity [Android Developer Nanodegree program](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801)

This project is just the continuation of [Movie-Map](https://github.com/suvojitd79/Movie-Map)


## Objective
Most of us can relate to kicking back on the couch and enjoying a movie with friends and family. In this project, you’ll build an app to allow users to discover the most popular movies playing. We will split the development of this app in two stages. First, let's talk about stage 1.

## Core features

* Presenting the user with a grid arrangement of movie posters upon launch.
* Allowing the user to change sort order via a setting:
* The sort order can be by most popular or by highest-rated
* Allowing the user to tap on a movie poster and transition to a details screen with additional information such as:
* original title
* movie poster image thumbnail
* A plot synopsis (called overview in the api)
* user rating (called vote_average in the api)
* release date

## New features
* users can view and play trailers (either in the youtube app or a web browser).
* users can read reviews of a selected movie.
* users can mark a movie as a favorite in the details view by tapping a button (star).
* used Android Architecture Components (Room, LiveData, ViewModel and Lifecycle) to create a robust an * efficient application.
* created a database using Room to store the names and ids of the user's favorite movies (and optionally, the rest of the information needed to display their favorites collection while offline).
* modified the existing sorting criteria for the main view to include an additional pivot to show their favorites collection.

## Additional features
* movie recommendations functionality is included 



### Getting Started
Clone or download the project and run it using Android Studio(>=3.x.x)

## How do I use Movie-Map?

* Get an Api-key from [themoviedb.org](https://www.themoviedb.org/)

* Copy your own api key in string.xml(```app->src->main->res->values->string.xml```)

```
<resources>
    <string name="api_key">paste your key here</string>
</resources>
```

## Built with
* [Retrofit ](https://github.com/square/retrofit)- Type-safe HTTP client for Android and Java.
* [Espresso ](https://developer.android.com/training/testing/espresso)- The Espresso test framework. Espresso is a testing framework for Android to make it easy to write reliable user interface tests
* [Glide ](https://github.com/bumptech/glide)- An image loading and caching library for Android focused on smooth scrolling
* [RippleEffect ](https://github.com/traex/RippleEffect) - Implementation of Ripple effect from Material Design for Android API 9+
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Android architecture components are a collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.





#### Contributing

If you want to contribute to this project and make it better, your help is very welcome.


#### Acknowledgments

* Hat tip to anyone whose code was used

