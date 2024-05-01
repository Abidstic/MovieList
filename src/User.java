import java.util.HashSet;
import java.util.Set;

public class User {
    private String email;
    private Set<Movie> favorites;

    User(String email) {
        this.email = email;
        this.favorites = new HashSet<>();
    }

    public String getEmail() {
        return email;
    }

    public Set<Movie> getFavorites() {
        return favorites;
    }

    public void addToFavorites(Movie movie) {
        favorites.add(movie);
    }

    public void removeFromFavorites(Movie movie) {
        favorites.remove(movie);
    }
}