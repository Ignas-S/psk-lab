package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;
import lt.vu.persistence.MovieDao;
import lt.vu.persistence.MovieTheatreDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Model
@RequestScoped
public class Theatre implements Serializable {
    @Inject
    MovieDao movieDao;
    @Inject
    MovieTheatreDao theatreDao;

    @Getter
    private MovieTheatre theatre;
    @Getter
    private List<Movie> otherMovies;
    @Getter
    private String theatreId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.theatreId = requestParameters.get("tId");
        if (theatreId != null) {
            this.refresh();
        }
    }

    // All methods that need to run upon data change
    private void refresh() {
        this.loadTheatre();
        this.loadOtherMovies();
    }

    private void loadTheatre() {
        this.theatre = theatreDao.getById(theatreId);
    }

    private void loadOtherMovies() {
        if (theatre.getMovies().size() > 0) {
            List<String> exclude = this.theatre.getMovies()
                    .stream()
                    .map(m -> m.getEidr())
                    .collect(Collectors.toList());
            this.otherMovies = movieDao.getOtherMovies(exclude);
        } else {
            this.otherMovies = this.movieDao.loadAll();
        }
    }

    @Transactional
    public String addMovieToTheatre(String eidr, String theatreId) {
        this.theatreDao.addMovieToTheatre(eidr, theatreId);
        String go = "/theatre?faces-redirect=true&tId=" + theatreId;
        return go;
    }
}
