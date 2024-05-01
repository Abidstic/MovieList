package Main;

import java.util.HashSet;
import java.util.Set;

// Represents a user with an email and a set of favorite movies.
public class User {
    private String email;
    private Set<Movie> favorites;

    // Constructs a User object with the specified email.
    User(String email) {
        this.email = email;
        this.favorites = new HashSet<>();
    }

    // Gets the email of the user.
    public String getEmail() {
        return email;
    }

    // Gets the set of favorite movies of the user.
    public Set<Movie> getFavorites() {
        return favorites;
    }

    // Adds a movie to the user's favorites.
    public void addToFavorites(Movie movie) {
        favorites.add(movie);
    }

    // Removes a movie from the user's favorites.
    public void removeFromFavorites(Movie movie) {
        favorites.remove(movie);
    }
}
