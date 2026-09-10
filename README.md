# HungryBuddy

HungryBuddy is an Android recipe discovery application developed using
Kotlin and Jetpack Compose.

The application allows users to register and log in securely, search for
recipes using an online REST API, view recipe information, and manage basic
application settings.

---

##  Project Purpose

The purpose of HungryBuddy is to provide users with a simple and
user-friendly mobile application for discovering recipes.

The project demonstrates the practical use of:

- Android application development
- Kotlin programming
- Jetpack Compose
- REST API integration
- Firebase Authentication
- Repository and ViewModel architecture
- Input validation
- Unit testing
- Git and GitHub
- Automated software development practices

---

##  Features

###  User Authentication

HungryBuddy uses Firebase Authentication for:

- User registration
- User login
- Secure password handling
- Authentication state checking
- Logout functionality

Passwords are handled by Firebase Authentication rather than being
stored directly inside the application.

###  Recipe Search

Users can search for recipes by entering a recipe name.

The application communicates with TheMealDB REST API to retrieve recipe
information.

### Recipe Results

Search results display recipes retrieved from the REST API.

Users can select a recipe to view more information.

###  Recipe Details

The recipe details screen provides information such as:

- Recipe name
- Category
- Cuisine/area
- Instructions
- Recipe image
- Ingredients and measurements
- YouTube recipe information when available

###  Settings

The Settings screen provides basic application preferences, including:

- Logged-in user information
- Language preference
- Dark mode preference [yet to be fully functional]
- Logout

###  Input Validation

The application validates user input before authentication requests.

Validation includes:

- Empty email detection
- Email format validation
- Empty password detection
- Password length validation
- Password confirmation matching

---

##  Technologies Used

* Kotlin- Application programming language
* Jetpack Compose -User interface development
* Material 3 - UI components and styling
* Firebase Authentication -User registration and login
* Retrofit - REST API communication
* Gson - JSON response conversion
* Coil -Recipe image loading
* Navigation Compose - Screen navigation
* JUnit - Unit testing
* Git - Version control
* GitHub - Source code repository
* GitHub Actions - Continuous integration

---

##  REST API

HungryBuddy uses TheMealDB REST API to retrieve recipe information.

The application uses API operations for:

- Searching recipes by name
- Retrieving recipe details
- Retrieving recipes by category

The API responses are converted into Kotlin data models using Gson.

---

##  Application Architecture

HungryBuddy follows a simple layered architecture.

```
                 layered architecture
User Interface
      ↓
ViewModel
      ↓
Repository
      ↓
Retrofit API
      ↓
TheMealDB REST API


```
---

##  YouTube link

* https://youtu.be/E_hJ7RuuNDU
