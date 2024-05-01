package Main;

import java.time.LocalDate;
import java.util.List;

// Represents a movie with its details such as title, cast, category, release date, and budget.
public class Movie {
    private String title;
    private List<String> cast;
    private String category;
    private LocalDate releaseDate;
    private double budget;

    // Constructs a Movie object with the specified details.
    public Movie(String title, List<String> cast, String category, LocalDate releaseDate, double budget) {
        this.title = title;
        this.cast = cast;
        this.category = category;
        this.releaseDate = releaseDate;
        this.budget = budget;
    }

    // Gets the title of the movie.
    public String getTitle() {
        return title;
    }

    // Gets the list of cast members.
    public List<String> getCast() {
        return cast;
    }

    // Gets the category of the movie.
    public String getCategory() {
        return category;
    }

    // Gets the release date of the movie.
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    // Gets the budget of the movie.
    public double getBudget() {
        return budget;
    }
}
