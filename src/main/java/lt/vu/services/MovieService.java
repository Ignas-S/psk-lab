package lt.vu.services;

import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;
import lt.vu.persistence.MovieDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

@ApplicationScoped
public class MovieService implements IMovieService {
    @Inject
    MovieDao movieDao;

    public void save(Movie movie) {
        movieDao.persist(movie);
    }
}
