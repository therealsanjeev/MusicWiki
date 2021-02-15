# MusicWiki

App demonstrate a good experience of music library with artist and album details categorised on the basis of genre. This App uses apis from https://www.last.fm/api. you just need to create your account and paste your api key in build.gradle file and you are good to go.

## Screenshots:
<img src="https://raw.githubusercontent.com/therealsanjeev/MusicWiki/master/media/homeScreen.png" width="230"> <img src="https://raw.githubusercontent.com/therealsanjeev/MusicWiki/master/media/genreScreen.png" width="230"> 
<img src="https://raw.githubusercontent.com/therealsanjeev/MusicWiki/master/media/albumScreen.png" width="230"> 
<img src="https://raw.githubusercontent.com/therealsanjeev/MusicWiki/master/media/artistScreen.png" width="230"> 

## Features :
● Displays the list of genres available. The list will initially contain top 10 genres and when the user clicks on the expand button the entire list is shown in the same screen.

● Clicking on the genre it takes the user to a page which contains information regarding it. It contains genre as the title and also has a description of the genre.In the page it lists top albums, top tracks and top artists as different sections.

        ○ Each item listed under the album has the cover image if available or a
        default image, the title and artist.
        
        ○ Each item listed under the artists,has the cover image, if available or a
        default image and the name
        
        ○ Each item listed under tracks has the cover image if available or a
        default image, the title and artist name.
● Clicking on the item

  ○ Albums

        ■ It displays the cover image, title and the artist information. It
        also contains the description and the genres. Clicking on the genre it
        displays the details about the genre, similar to the flow in the first screen.

  ○ Artists

        ■ It displays the image, title ,play count and followers, description, a
        list of top tracks and top albums and the genres. Clicking on the genre it
        displays the details about the genre, similar to the flow in the first
        screen.
        ■ Clicking on the album shows its information.

### API Reference
https://www.last.fm/api

### Design pattern : MVVM
### Glide & coil - Image Loading and Cache
### Android Architecture Components 

- LiveData - Data objects that notify views when the underlying database changes.
- ViewModel - Stores UI-related data that isn't destroyed on UI changes.
- ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- Retrofit - A type-safe HTTP client for Android and Java.
- Material Components for Android - Modular and customizable Material Design UI components for Android.

