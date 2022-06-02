                                    ----------
                                      MOVIES
                                    ----------
                            
**Movies** is an **android native application** that caches, fetches  and displays 
list of movies data from TMDB on the homepage and onclick on each movies,
it will fetch and display the details of each movies on a details screen. 

All the latest technological tools and libraries were employed to build this
app such as  room database, retrofit, dependency injection (Dagger Hilt), 
network observation using RxJava (Live data), Courotine, Jetpack navigation components,
espresso, junit, truth,  github, to mention a few. 

It was solely worked on by me so I 
dont see the need for continous integration and deployment.

This app is built to demonstrate expertise in these various libraries and technologies 
for an assessment with google. 

                          ----------------------------
                             SCREENSHOT OF THE APP
                          ----------------------------

---------------
POPULAR MOVIES
---------------


![popular_movies](https://user-images.githubusercontent.com/54009597/171627234-00db40c5-e23d-413b-880a-3bcb5388bed1.jpeg)


-------------------------------
POPULAR MOVIES WITHOUT NETWORK
-------------------------------


![popular_movies_without_network](https://user-images.githubusercontent.com/54009597/171627430-2a1365e0-72ba-43b4-aa6d-115cda092f93.jpeg)



----------------
UPCOMING MOVIES
----------------


![upcoming_movies_network_on](https://user-images.githubusercontent.com/54009597/171627812-6b13587a-9aa2-43d9-8e86-01568256eb34.jpeg)


-------------------------------
UPCOMING MOVIES WITHOUT NETWORK
-------------------------------


![upcoming_movies_without_network](https://user-images.githubusercontent.com/54009597/171627709-58783e19-3833-4755-8ed8-197781aa51e6.jpeg)

-------------------------------
LATEST MOVIES WITHOUT NETWORK
-------------------------------


![latest_movies_without_network](https://user-images.githubusercontent.com/54009597/171627524-29c6d1e0-aff4-4466-a959-28ecfe3dbf83.jpeg)


--------------
MOVIE DETAILS
--------------


![movie_details](https://user-images.githubusercontent.com/54009597/171627615-c7ef11c1-5178-4c30-aac6-7b8941c59c77.jpeg)

                        -----------------
                          API SOURCE
                        -----------------
                   TMDB : http://themoviedb.org/
                              
                              
                              
                          ------------
                            LANGUAGE 
                          ------------
                           Kotlin


                        -------------
                        ARCHITECTURE: 
                        -------------
Model View ViewModel, MVVM - Google recommendation but I've grown to love it better than MVC, MVP

                          -----------
                          CHALLENGES : 
                          -----------
Challenges faced during this little implementation is Scalability.
It is a broad term but sticking to best software development practices helps alot

                      ----------------------
                      WISH TO ADD FEATURES : 
                      ---------------------
I can always find improvement in all projects I have built everytime. But for this, 
Wish I had time to add search bar, filters  and more test coverage to the screens. But I will update it later
for the sake of young developers who crave for step by step guide of implementing such. 

* search bar
* filter
* more test coverage

                      -------------------
                      TECHNOLOGIES USED
                      -------------------
Android studio SDK, Jetpack Navigation Components, RxJava (LiveData), 
Dependency Injection (Dagger Hilt), Room database, Real-time Network Observation,
Coroutine, Retrofit, Gson Converter, Gradle, Github etc

                  -------------------------------------
                  ASSUMPTIONS FOR THIS LIBRARY CHOICES
                  -------------------------------------
**Android Studio SDk** is the fastest tool used to build the views and logic using 
kotlin for android devices.

**Android Jetpack Navigation Component**  is used to implement navigation, from simple 
button clicks to more complex patterns, such as app bars and the navigation drawer.
The Navigation component ensures a consistent and predictable user experience by a
dhering to an established set of principles.

**RxJava** RxJava is a Java library that allows **functional reactive programming** in 
Android development by raising the level of abstraction around threading in order to
simplify the implementation of complex concurrent behavior. LiveData was used to implement this.
There is **flow** also which you can use. The official documentation is the best way to learn the
latest best practices and learn more.

**Dependency Injection (Dagger Hilt)** Hilt is a dependency injection library for Android that 
reduces the boilerplate of doing manual dependency injection in your project.

**Hilt** provides a standard way to use DI in our application by providing containers for every 
Android class in our project and managing their lifecycles automatically. It is built on top 
of the popular **DI library Dagger** to benefit from the **compile-time correctness,** **runtime performance,**
**scalability**, and **Android Studio support that Dagger provides.**
You can see why I love Hilt

**Room database** Room database is one of the caching mechanism to persist our data in android development.
**The Room persistence library** provides an abstraction layer over SQLite to allow fluent database access
while harnessing the full power of **SQLite.** 

**It helps to improve UI / UX for our users. 
**It protect against configuration changes.
**It enhance scalability of our apps by accessing our data faster and even without network. So our app can 
  function property with little access to internet

In particular, Room provides the following benefits:

      * Compile-time verification of SQL queries.
      * Convenience annotations that minimize repetitive and error-prone boilerplate code.
      * Streamlined database migration paths.
      
NOTE: It is a recommended best practice to use room db in android instead of other SQLite APIs directly.
--------------------------------------------------------------------------------------------------------

**NETWORK OBSERVATION :** This is achieved using Rxjava (LiveData) to monitor our network in real time and 
as such provides good UI and UX for our users.


**Coroutine** is a **concurrency design pattern** that you can use on Android to simplify code that executes 
asynchronously. Coroutines were added to Kotlin in version 1.3 and are based on established concepts from
other languages. It aids to perform long operation off main thread ( so it doesnt block the main thread )
and manages its own lifecycle itself.

**Retrofit:** is used to call our data from the cloud.

**Gson Converter:** helps to convert the json response to kotlin-Java Object.

