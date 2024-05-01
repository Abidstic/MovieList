package Main;

import java.time.LocalDate;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Scanner;

    public class Main {
        private static MovieListing movieListing;
        private static Scanner scanner;
        private static String userEmail;
        private static boolean reg=false;
        private static int choice;

        public static void main(String[] args) {
            movieListing = new MovieListing();
            scanner = new Scanner(System.in);
            userEmail = null;

            // Add some initial movies
            movieListing.addMoviesDefault();

            boolean exit = false;
            while (!exit) {
                if(reg)
                {
                    dashboard();
                    int choiceFun=scanner.nextInt();
                    scanner.nextLine();// consume newline
                    switch (choiceFun) {
                        case 1:
                            searchMovies();
                            break;
                        case 2:
                            getMovieDetails();
                            break;
                        case 3:
                            addToFavorites();
                            break;
                        case 4:
                            removeFromFavorites();
                            break;
                        case 5:
                            getUserDetails();
                            break;
                        case 6:
                            searchFavorites();
                            break;

                        case 7:
                            showAllMovies();
                            break;
                        case 8:
                            addMovie();
                            break;

                        case 9:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
                else{
                    displayMenu();
                    choice = scanner.nextInt(); // Read the user's choice first
                    scanner.nextLine();
                    switch(choice){
                        case 1:
                            dashboard();
                            break;
                        case 2:
                            registerUser();
                            reg=true;
                            break;
                    }
                }




            }
        }



        private static void displayMenu() {
            System.out.println("\nAre You Registered?");
            System.out.println("1.Yes\n2.No");

        }

        public static void dashboard() {
            if (userEmail == null) {
                System.out.println("Please register an account first.");
                reg=false;
                return;
            }
            System.out.println("\n***Main.Movie Listing Application***");
            System.out.println("\n===Dashboard===");
            System.out.println("1. Search Movies");
            System.out.println("2. Get Main.Movie Details");
            System.out.println("3. Add Main.Movie to Favorites");
            System.out.println("4. Remove Main.Movie from Favorites");
            System.out.println("5. Get Main.User Details");
            System.out.println("6. Search Favorites");
            System.out.println("7. Show all Movies");
            System.out.println("8. Add Movies");
            System.out.println("9. Exit\n");
            System.out.print("Enter your choice: ");
            System.out.println(" ");

        }

        private static void registerUser() {
            System.out.print("Enter your email: ");
            userEmail = scanner.nextLine();
            movieListing.registerUser(userEmail);
            System.out.println("Main.User registered successfully.");

        }

        private static void searchMovies() {
            System.out.print("Enter search query: ");
            String query = scanner.nextLine();
            System.out.print("Search by (title/cast/category): ");
            String searchType = scanner.nextLine();

            List<Movie> results = movieListing.searchMovies(query, searchType);
            if (results.isEmpty()) {
                System.out.println("No movies found.");
            } else {
                System.out.println("Search results:");
                for (Movie movie : results) {
                    System.out.println(movie.getTitle());
                }
            }
        }

        private static void getMovieDetails() {
            System.out.print("Enter movie title: ");
            String title = scanner.nextLine();

            Movie movie = movieListing.getMovieDetails(title);
            if (movie != null) {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Cast: " + String.join(", ", movie.getCast()));
                System.out.println("Category: " + movie.getCategory());
                System.out.println("Release Date: " + movie.getReleaseDate());
                System.out.println("Budget: $" + movie.getBudget());
            } else {
                System.out.println("Main.Movie not found.");
            }
        }

        private static void addToFavorites() {
            if (userEmail == null) {
                reg=false;
                System.out.println("Please register an account first.");

                return;
            }

            System.out.print("Enter movie title: ");
            String movieTitle = scanner.nextLine();

            movieListing.addToFavorites(userEmail, movieTitle);
            System.out.println("Main.Movie added to favorites.");
        }

        private static void removeFromFavorites() {
            if (userEmail == null) {
                reg=false;
                System.out.println("Please register an account first.");
                return;
            }

            System.out.print("Enter movie title: ");
            String movieTitle = scanner.nextLine();

            movieListing.removeFromFavorites(userEmail, movieTitle);
            System.out.println("Main.Movie removed from favorites.");
        }

        private static void getUserDetails() {
            if (userEmail == null) {
                reg=false;
                System.out.println("Please register an account first.");
                return;
            }

            User user = movieListing.getUserDetails(userEmail);
            if (user != null) {
                System.out.println("Email: " + user.getEmail());
                System.out.println("Favorite Movies:");
                for (Movie movie : user.getFavorites()) {
                    System.out.println("- " + movie.getTitle());
                }
            } else {
                System.out.println("Main.User not found.");
                reg=false;
            }
        }
        private static void showAllMovies() {
            System.out.println("Here are the movies:");
            List<Movie> movies = movieListing.getAllMovies();
            if (movies.isEmpty()) {
                System.out.println("No movies found.");
            } else {
                for (Movie movie : movies) {
                    System.out.println("Title: " + movie.getTitle());
                    System.out.println("Cast: " + String.join(", ", movie.getCast()));
                    System.out.println("Category: " + movie.getCategory());
                    System.out.println("Release Date: " + movie.getReleaseDate());
                    System.out.println("Budget: $" + movie.getBudget());
                    System.out.println();
                }
            }
        }

        private static void searchFavorites() {
            if (userEmail == null) {
                reg=false;
                System.out.println("Please register an account first.");
                return;
            }

            System.out.print("Enter search query: ");
            String query = scanner.nextLine();
            System.out.print("Search by (title/cast/category): ");
            String searchType = scanner.nextLine();

            List<Movie> results = movieListing.searchFavorites(userEmail, query, searchType);
            if (results.isEmpty()) {
                System.out.println("No movies found in favorites.");
            } else {
                System.out.println("Search results in favorites:");
                for (Movie movie : results) {
                    System.out.println(movie.getTitle());
                }
            }
        }
        private static void addMovie() {
            System.out.print("Enter movie title: ");
            String title = scanner.nextLine();

            System.out.print("Enter cast members (separated by commas): ");
            String castInput = scanner.nextLine();
            List<String> cast = Arrays.asList(castInput.split(",\\s*"));

            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            System.out.print("Enter release date (yyyy-mm-dd): ");
            String releaseDateInput = scanner.nextLine();
            LocalDate releaseDate = LocalDate.parse(releaseDateInput);

            System.out.print("Enter budget: ");
            double budget = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            movieListing.addMovie(title, cast, category, releaseDate, budget);
            System.out.println("Main.Movie added successfully.");
        }
    }