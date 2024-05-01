package Test;

import Main.Movie;
import Main.MovieListing;
import Main.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for MovieListing class
class MovieListingTest {
    private MovieListing movieListing;

    // Set up the test environment
    @BeforeEach
    void setUp() {
        movieListing = new MovieListing();
        movieListing.addMoviesDefault();
    }

    // Test registering a user
    @Test
    void testRegisterUser() {
        String email = "test@example.com";
        movieListing.registerUser(email);
        assertNotNull(movieListing.getUserDetails(email));
    }

    // Test searching for movies
    @Test
    void testSearchMovies() {
        List<Movie> results = movieListing.searchMovies("the", "title");
        assertEquals(3, results.size());

        results = movieListing.searchMovies("Tim Robbins", "cast");
        assertEquals(1, results.size());
        assertEquals("The Shawshank Redemption", results.get(0).getTitle());

        results = movieListing.searchMovies("Crime", "category");
        assertEquals(2, results.size());
    }

    // Test getting details of a movie
    @Test
    void testGetMovieDetails() {
        Movie movie = movieListing.getMovieDetails("The Godfather");
        assertNotNull(movie);
        assertEquals("The Godfather", movie.getTitle());
        assertEquals(Arrays.asList("Marlon Brando", "Al Pacino"), movie.getCast());
        assertEquals("Crime", movie.getCategory());
        assertEquals(LocalDate.of(1972, 3, 24), movie.getReleaseDate());
        assertEquals(6000000, movie.getBudget());
    }

    // Test adding a movie to favorites
    @Test
    void testAddToFavorites() {
        String email = "test@example.com";
        String movieTitle = "The Shawshank Redemption";
        movieListing.registerUser(email);
        movieListing.addToFavorites(email, movieTitle);
        User user = movieListing.getUserDetails(email);
        assertNotNull(user);
        assertEquals(1, user.getFavorites().size());
        assertTrue(user.getFavorites().stream()
                .anyMatch(movie -> movie.getTitle().equals(movieTitle)));
    }

    // Test removing a movie from favorites
    @Test
    void testRemoveFromFavorites() {
        String email = "test@example.com";
        movieListing.registerUser(email);
        movieListing.addToFavorites(email, "The Shawshank Redemption");
        movieListing.removeFromFavorites(email, "The Shawshank Redemption");
        User user = movieListing.getUserDetails(email);
        assertNotNull(user);
        assertTrue(user.getFavorites().isEmpty());
    }

    // Test getting user details
    @Test
    void testGetUserDetails() {
        String email = "test@example.com";
        movieListing.registerUser(email);
        User user = movieListing.getUserDetails(email);
        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertTrue(user.getFavorites().isEmpty());
    }

    // Test getting all movies
    @Test
    void testGetAllMovies() {
        List<Movie> movies = movieListing.getAllMovies();
        assertEquals(4, movies.size());
        assertEquals("Pulp Fiction", movies.get(0).getTitle());
    }

    // Test searching favorites
    @Test
    void testSearchFavorites() {
        String email = "test@example.com";
        movieListing.registerUser(email);
        movieListing.addToFavorites(email, "The Shawshank Redemption");
        movieListing.addToFavorites(email, "The Godfather");

        List<Movie> results = movieListing.searchFavorites(email, "the", "title");
        assertEquals(2, results.size());

        results = movieListing.searchFavorites(email, "Marlon Brando", "cast");
        assertEquals(1, results.size());
        assertEquals("The Godfather", results.get(0).getTitle());
    }

    // Test adding a new movie
    @Test
    void testAddMovie() {
        String title = "New Movie";
        List<String> cast = Arrays.asList("Actor1", "Actor2");
        String category = "Comedy";
        LocalDate releaseDate = LocalDate.of(2023, 5, 1);
        double budget = 50000000;

        movieListing.addMovie(title, cast, category, releaseDate, budget);
        List<Movie> movies = movieListing.getAllMovies();
        assertEquals(5, movies.size());
        Movie newMovie = movies.get(0);
        assertEquals(title, newMovie.getTitle());
        assertEquals(cast, newMovie.getCast());
        assertEquals(category, newMovie.getCategory());
        assertEquals(releaseDate, newMovie.getReleaseDate());
        assertEquals(budget, newMovie.getBudget());
    }
}
