# U-Haul-Coding-Challenge
Here is the prompt given:

The following exercises provide an opportunity to demonstrate design and programming skills. Although you may not complete all sections in time, it is much more important to demonstrate good software design and coding technique than to finish the entire activity. Feel free to advance to subsequent tasks once you feel you have sufficiently demonstrated your skills in a particular section. You may revisit partially-completed sections later if there is time.

Before you begin, please create a private repository on GitHub for your project and add (U-Haul) as a collaborator.
Please commit your code at the 4 hour mark, but feel free to continue working on the project up to 24 hours.

For these exercises you will be utilizing the mock web service found at http://jsonplaceholder.typicode.com. You will create an Android application (please use Kotlin) to perform the following tasks. You have free rein to design the UI and UX of the application however you wish, provided it performs the required functions. However, keep in mind that this is intended to be a test of technical skills rather than design aesthetic.

You may use third-party libraries to perform some or all of the functions listed below, but be prepared to explain your choices.

1. Create a UI to display a list of users obtained from the web service (GET http://jsonplaceholder.typicode.com/users). You should show username and full address for each user. The app should query the service for a new list of users each time it is launched.

2. Add functionality so that the user can select a user from the list and view all posts by that user (GET http://jsonplaceholder.typicode.com/posts?userId={userId}). You should show the title and body for each post.

3. Add functionality so that the user can create a new post by the user they have selected (POST http://jsonplaceholder.typicode.com/posts).

4. Add functionality so that the user can edit or delete a post (PUT or PATCH, DELETE http://jsonplaceholder.typicode.com/posts/{postId}).

5. Add functionality to allow the user to sort the list of posts in ascending and descending alphabetical order by title.
