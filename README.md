# Gradle for Android and Java Final Project

In this project, you will create an app with multiple flavors that uses
multiple libraries and Google Cloud Endpoints. The finished app will consist
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

# Required Components
Project contains a Java library for supplying jokes
Project contains an Android library with an activity that displays jokes passed to it as intent extras.
Project contains a Google Cloud Endpoints module that supplies jokes from the Java library. Project loads jokes from GCE module via an async task. Note that this GCE module doesn't need to be deployed to a server. Local testing works fine.
Project contains connected tests to verify that the async task is indeed loading jokes.
Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.

# Required Behavior
App retrieves jokes from Google Cloud Endpoints module and displays them via an Activity from the Android Library.

# Nice to have Components
Make the free app variant display interstitial ads between the main activity and the joke-displaying activity.
Have the app display a loading indicator while the joke is being fetched from the server.
Write a Gradle task that starts the GCE dev server, runs all the Android tests, and shuts down the dev server.