package lt.vu.persistence;
import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class MovieTheatreDao {

    @Inject @Default
    private EntityManager em;

    public void persist(MovieTheatre theatre) {
        em.persist(theatre);
    }

    public MovieTheatre getById(String id){
        return em.find(MovieTheatre.class, id);
    }

    public MovieTheatre update(MovieTheatre player){
        return em.merge(player);
    }

    public List<MovieTheatre> loadAll() {
        return em.createNamedQuery("MovieTheatre.allTheatres", MovieTheatre.class).getResultList();
    }

    public void addMovieToTheatre(String eidr, String theatreId) {
        Movie movie = em.find(Movie.class, eidr);
        MovieTheatre theatre = this.getById(theatreId);
        theatre.addMovie(movie);
        this.update(theatre);
    }

}
