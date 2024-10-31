
# Updatify

Updatify is an Android news application built with Jetpack Compose, implementing a clean architecture and MVVM pattern. It provides a modern and personalized reading experience with features like bookmarks, search functionality, and a detailed view for articles.


![Logo](https://raw.githubusercontent.com/sobhandeep/Updatify/refs/heads/master/Screenshots/logo.png)


## Features

- Home Feed: Displays a curated list of news articles with options to navigate to details.
- **Bookmarking**: Save articles for offline access and reading later.
- **Room Database Integration**: Stores bookmarked articles locally for offline access.
- **Search Functionality**: Search for news articles by keywords.
- **Themed Toast Messages**: Displays toast messages with colors that adapt to the device's theme.
- **Smooth Navigation**: Uses a bottom navigation bar for seamless navigation between Home, Search, and Bookmark screens.
- Light/dark mode toggle


## Project Structure

The app is structured using Jetpack Compose and adheres to a clean architecture pattern. Key modules include:

- **Presentation Layer**: Contains all composables and UI-related code.
- **Domain Layer**: Defines business logic and data models.
- **Data Layer**: Manages data sources and repositories.
## API Reference
```https
  Base URL https://newsapi.org/v2/
```
#### Get all news


```GET everything/```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |
| `page` | `string` | **Optional**. Default is 1 |
| `sources` | `string` | **Optional**. Default is all |
| `language` | `string` | **Optional**. Default is English |

#### Get news by category

```GET top-headlines/```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |
| `category` | `string` | **Required**. Your news category|

#### Get top headlines

```GET top-headlines/```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |
| `sources` | `string` | **Optional**. Default is all |

#### Search news

```GET everything/```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |
| `q` | `string` | **Required**. Your search query |
| `page` | `string` | **Optional**. Default is 1 |
| `sources` | `string` | **Optional**. Default is all |
| `language` | `string` | **Optional**. Default is English |
## Screenshots

Onboarding Screen
![Onboarding Screenshot](https://raw.githubusercontent.com/sobhandeep/Updatify/refs/heads/master/Screenshots/onboarding_screen.png)


Home Screen
![Home Screenshot](https://raw.githubusercontent.com/sobhandeep/Updatify/refs/heads/master/Screenshots/home_screen.png)


Search Screen
![Search Screenshot](https://raw.githubusercontent.com/sobhandeep/Updatify/refs/heads/master/Screenshots/search_screen.jpg)


Boomark Screen 
![Boomark Screenhot](https://raw.githubusercontent.com/sobhandeep/Updatify/refs/heads/master/Screenshots/bookmark_screen.jpg)

Details Screen
![Details Screenshot](https://raw.githubusercontent.com/sobhandeep/Updatify/refs/heads/master/Screenshots/details_screen.jpg)## Getting Started

To get a local copy of this project up and running, follow these steps:

### Prerequisites

Ensure you have the following installed:
- **Android Studio**: Recommended version Ladybug | 2024.2.1 or newer.
- **Android SDK**: Minimum SDK level 24.
- **Kotlin**: Version 2.0.21 or newer.
- **Gradle**: Version 8.10.2 or newer.
- **JDK**: Ensure JVM version is set to 21.

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/sobhandeep/Updatify.git
## Usage
### Navigation
Updatify uses a bottom navigation bar to switch between the main tabs:

- **Home**: Displays a feed of the latest articles.
- **Search**: Allows users to search for news articles.
- **Bookmark**: Lists all saved articles.
### Bookmarking
- To bookmark an article, tap the bookmark icon on the top bar in the details screen.
- The state of each bookmarked article is saved across sessions, ensuring a persistent reading list.
### Navigation Functions
- `navigateTab()`: Handles navigation between tabs, ensuring only a single instance of each tab.
- `navigateToDetails()`: Navigates to the details screen, passing the selected article information.
### Customization
- **Theme Colors**: Modify MaterialTheme colors to adjust the appearance of the app.
- **Icons and Images**: Place custom icons in the res/drawable folder and update references in BottomNavigator.kt.
## FAQ

##### 1. **What is the base URL for the API?**
   - The base URL for all API endpoints is:
     ```
     https://newsapi.org/v2/
     ```

##### 2. **How do I get an API key?**
   - You can obtain an API key by signing up at [NewsAPI.org](https://newsapi.org). Once signed up, your API key will be available in your account dashboard.

##### 3. **What is the default language for news articles? Can I change it?**
   - The default language for news articles is English (`"en"`). You can specify other languages by passing the appropriate language code (e.g., `"es"` for Spanish) in the `language` query parameter.

##### 4. **What categories can I use for filtering top headlines?**
   - Common categories include `"business"`, `"entertainment"`, `"general"`, `"health"`, `"science"`, `"sports"`, and `"technology"`. Be sure to check the official [NewsAPI documentation](https://newsapi.org/docs/endpoints/top-headlines) for a full list of categories.

##### 5. **Is pagination supported, and how do I use it?**
   - Yes, pagination is supported. To paginate results, use the `page` parameter in your requests. Start with `page=1` and increment as needed to fetch additional pages of results.

##### 6. **What happens if I exceed my API request limit?**
   - If you exceed your rate limit, the API will return a 429 status code with an error message. Consider upgrading to a higher tier plan if you need more requests.

##### 7. **Can I get news articles from multiple sources in a single request?**
   - Yes, you can specify multiple sources by separating them with commas in the `sources` parameter. Example: `sources=bbc-news,cnn`.

##### 8. **What data is returned for each news article?**
   - Each news article typically includes fields like `title`, `description`, `author`, `url`, `source`, `publishedAt`, and `content`. Refer to the API response format for full details.

##### 9. **Does the API support searching for specific keywords?**
   - Yes, you can search for specific keywords using the `q` parameter in the `/everything` endpoint. This will return articles matching the specified query.

##### 10. **Who can I contact for support?**
   - For support, please reach out via the [NewsAPI support page](https://newsapi.org/docs/get-started).

