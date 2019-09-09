# Album Search

## This app helps the users to search anhy particular album they want and see the details for the same.

### Setup

You can clone the project from https://github.com/PuneetTest52/project-album-search.

### API details

API used to fetch the albums - http://ws.audioscrobbler.com/
Query Parameters required -
1. Method - ex. "album.search"
2. Keyword - The user input
3. API Key - unique key generated
4. Format - ex. json

### Tech Stack used

- [x] MVVM: MVVM used as the pattern along with ViewModel and LiveData to maintain updated state of UI.
- [x] Retrofit : For Network calls.
- [x] Dagger : Dependency injector.
- [x] RxJava : To fetch the response from API.
- [x] Mockito : For Unit tests. Unit tests are covered for ViewModel, Repository and Utilities.
- [x] Espresso : Used for UI test cases.
- [x] ButterKnife : Used for View Binding.
- [x] Glide : Used for loading Images.
