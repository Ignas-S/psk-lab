package lt.vu.services;

import lombok.var;
import lt.vu.entities.Movie;
import lt.vu.persistence.MovieDao;
import lt.vu.rest.contracts.MovieDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class MovieService implements IMovieService {
    @Inject
    MovieDao movieDao;

    public void save(Movie movie) {
        movieDao.persist(movie);
    }
    public Movie get(String eidr) throws NotFoundException {
        var m = movieDao.getByEidr(eidr);
        if (m == null) {
            throw new NotFoundException("Unable to find movie with eidr: " + eidr);
        }
        return m;
    }
    public void update(Movie movie) throws NotFoundException {
        var m = movieDao.getByEidr(movie.getEidr());
        if (m == null) {
            throw new NotFoundException("Unable to find movie with eidr: " + movie.getEidr());
        }
        movieDao.update(movie);
    }

    public void updateFromDTO(MovieDTO movieDTO) {
        var m = movieDao.getByEidr(movieDTO.getEidr());
        if (m == null) {
            throw new NotFoundException("Unable to find movie with eidr: " + movieDTO.getEidr());
        }
        m.setGenres(movieDTO.getGenres());
        m.setDirector(movieDTO.getDirector());
        m.setDuration(movieDTO.getDuration());
        m.setYear(movieDTO.getYear());
        m.setName(movieDTO.getName());
        movieDao.update(m);
    }

    @Override
    public void saveFromDTO(MovieDTO data) {
        var m = new Movie(
                data.getEidr(),
                data.getDirector(),
                data.getName(),
                data.getYear(),
                data.getGenres(),
                data.getDuration()
        );
        movieDao.persist(m);
    }
}
