package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;
import lt.vu.persistence.MovieDao;
import lt.vu.persistence.MovieTheatreDao;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Index {
    @Inject
    MovieDao movieDao;

    @Inject
    MovieTheatreDao theatreDao;

    @Getter
    private List<MovieTheatre> allTheatres;

    @PostConstruct
    public void init(){
        loadAllTheatres();
    }
    private void loadAllTheatres() { this.allTheatres = this.theatreDao.loadAll(); }
}
