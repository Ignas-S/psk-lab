package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Movie;
import lt.vu.persistence.MovieDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Movies {
    @Inject
    MovieDao movieDao;

    @Getter
    private List<Movie> allMovies;

    @PostConstruct
    public void init(){
        loadAllMovies();
    }

    private void loadAllMovies(){
        this.allMovies = movieDao.loadAll();
    }

    @Getter @Setter
    private Movie movieToCreate = new Movie();

    @Transactional
    public void addTheatreToMovie(String movieEidr, String theatreId) {
        movieDao.addTheatreToMovie(movieEidr, theatreId);
        return;
    }

    @Transactional
    public void createMovie() {
        this.movieDao.persist(movieToCreate);
        this.loadAllMovies();
        this.movieToCreate = new Movie();
        return;
    }
}
