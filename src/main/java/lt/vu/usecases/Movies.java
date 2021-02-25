package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;
import lt.vu.persistence.MovieDao;
import lt.vu.persistence.MovieTheatreDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model
@RequestScoped
public class Movies implements Serializable {
    @Inject
    MovieDao movieDao;

    @Getter
    private List<Movie> allMovies;


    @PostConstruct
    public void init(){
        this.loadMovies();
    }

    private void loadMovies() {
        this.allMovies = movieDao.loadAll();
    }

    @Getter @Setter
    private Movie movieToCreate = new Movie();

    @Transactional
    public void createMovie() {
        if (movieToCreate.getEidr() != null) {
            this.movieDao.persist(movieToCreate);
            this.movieToCreate = new Movie();
            this.loadMovies();
        }
        return;
    }
}
