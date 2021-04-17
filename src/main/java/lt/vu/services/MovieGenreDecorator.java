package lt.vu.services;

import lt.vu.entities.Movie;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class MovieGenreDecorator implements IMovieService {
    @Inject
    @Delegate
    @Any
    IMovieService movieSaver;
    public void save(Movie movie) {
        String[] genres = movie.getGenres().split(",");
        if (genres.length > 3) {
            System.out.println("Movie genre decorator activated, " + movie.getName() + " has more than 3 genres, snipping.");
            movie.setGenres(genres[0] + ',' + genres[1] + ',' + genres[2]);
        }
        movieSaver.save(movie);
    }
}
