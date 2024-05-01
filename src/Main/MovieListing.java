package Main;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MovieListing {
    private List<Movie> movies;
    private Map<String, User> users;

    public MovieListing() {
        movies = new ArrayList<>();
        users = new HashMap<>();
    }

    public void registerUser(String email) {
        users.put(email, new User(email));
    }

    public List<Movie> searchMovies(String query, String searchType) {
        return movies.stream()
                .filter(movie -> {
                    switch (searchType) {
                        case "title":
                            return movie.getTitle().toLowerCase().contains(query.toLowerCase());
                        case "cast":
                            return movie.getCast().stream()
                                    .anyMatch(cast -> cast.toLowerCase().contains(query.toLowerCase()));
                        case "category":
                            return movie.getCategory().toLowerCase().contains(query.toLowerCase());
                        default:
                            return false;
                    }
                })
                .sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());
    }

    public Movie getMovieDetails(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public void addToFavorites(String email, String movieTitle) {
        User user = users.get(email);
        if (user != null) {
            Movie movie = getMovieDetails(movieTitle);
            if (movie != null) {
                user.addToFavorites(movie);
            }
        }
    }

    public void removeFromFavorites(String email, String movieTitle) {
        User user = users.get(email);
        if (user != null) {
            Movie movie = getMovieDetails(movieTitle);
            if (movie != null) {
                user.removeFromFavorites(movie);
            }
        }
    }

    public User getUserDetails(String email) {
        return users.get(email);
    }
    public List<Movie> getAllMovies() {
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getTitle)) // Sort movies by title
                .collect(Collectors.toList());
    }

    public List<Movie> searchFavorites(String email, String query, String searchType) {
        User user = users.get(email);
        if (user != null) {
            return user.getFavorites().stream()
                    .filter(movie -> {
                        switch (searchType) {
                            case "title":
                                return movie.getTitle().toLowerCase().contains(query.toLowerCase());
                            case "cast":
                                return movie.getCast().stream()
                                        .anyMatch(cast -> cast.toLowerCase().contains(query.toLowerCase()));
                            case "category":
                                return movie.getCategory().toLowerCase().contains(query.toLowerCase());
                            default:
                                return false;
                        }
                    })
                    .sorted(Comparator.comparing(Movie::getTitle))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    // Helper method to add some initial movies
    public void addMoviesDefault() {
        movies.add(new Movie("The Shawshank Redemption", Arrays.asList("Tim Robbins", "Morgan Freeman"), "Drama", LocalDate.of(1994, 9, 23), 25000000));
        movies.add(new Movie("The Godfather", Arrays.asList("Marlon Brando", "Al Pacino"), "Crime", LocalDate.of(1972, 3, 24), 6000000));
        movies.add(new Movie("The Dark Knight", Arrays.asList("Christian Bale", "Heath Ledger"), "Action", LocalDate.of(2008, 7, 18), 185000000));
        movies.add(new Movie("Pulp Fiction", Arrays.asList("John Travolta", "Samuel L. Jackson"), "Crime", LocalDate.of(1994, 10, 14), 8000000));
    }
    public void addMovie(String title, List<String> cast, String category, LocalDate releaseDate, double budget) {
        Movie movie = new Movie(title, cast, category, releaseDate, budget);
        movies.add(movie);
    }
}