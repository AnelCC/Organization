# Organization


Project name: **OrganizationList**

### Android
This is a mobile app using Auth Github Auth api. Develop in android over Clean Architecture, MVVM, Kotlin, etc. 
This use XML views


### Instruction
1. To compile you just need download the zip or clone the repo. 
2. To login in the app you will need have a GitHub account to login with the GitHub authentication. 
3. 

### Package Structure

```
com.anelcc.organizations      # Root Package
.
│
├── core               
│   └── API            # Call list to Repository.        
│
├── data              
│   └── Network info   # Use about to provide Repository.  
│
├── di                 # Use dependency injection 
│   └── NetworkModule  # we declare the modules from the app
│
├── domain             # We have been implementation Modules
│   └── UserCase       # The user case are using to comunications 
│
├── presentation       # Presentiation contain all views
│   ├── detail         
│   ├── list           
│   └── login 
│
└── OrganizationsApp.kt         # name application

```
### Preview 🎉


<img src="https://raw.githubusercontent.com/AnelCC/Organization/main/images/1loginLogin.png" width="150" height="300"/><img src="https://raw.githubusercontent.com/AnelCC/Organization/main/images/2loginLoading.png" width="150" height="300"/><img src="https://raw.githubusercontent.com/AnelCC/Organization/main/images/3loginAuth.png" width="150" height="300"/><img src="https://raw.githubusercontent.com/AnelCC/Organization/main/images/4loginAuthAppr.png" width="150" height="300"/>


<img src="https://raw.githubusercontent.com/AnelCC/Organization/main/images/5loginAuthRedirected.png" width="150" height="300"/><img src="https://raw.githubusercontent.com/AnelCC/Organization/main/images/6loginDashboard.png" width="150" height="300"/><img src="https://raw.githubusercontent.com/AnelCC/Organization/main/images/7loginSearch.png" width="150" height="300"/><img src="https://raw.githubusercontent.com/AnelCC/Organization/main/images/8loginDetail.png" width="150" height="300"/>



### Library References

1. Github Authentication [Read here](https://docs.github.com/en/rest/guides/getting-started-with-the-rest-api#authenticating)
2. Clea architecture [Read here](https://developer.android.com/topic/architecture)
3. Android Components Navigation [Read here](https://developer.android.com/jetpack/docs/guide)
4. Kotlin [Read here](https://developer.android.com/kotlin/ktx)
5. MVVM [Read here](https://blog.mindorks.com/mvc-mvp-mvvm-architecture-in-android)
6. View Models [Read here](https://developer.android.com/topic/libraries/architecture/viewmodel)
7. DataModel [Read here](https://developer.android.com/topic/libraries/architecture/viewmodel)
8. Coroutines [Read here](https://developer.android.com/topic/libraries/architecture/coroutines)
9. JUnitTest [Read here](https://developer.android.com/training/testing/local-tests)
