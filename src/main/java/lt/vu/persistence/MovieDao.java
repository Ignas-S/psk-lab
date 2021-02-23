package lt.vu.persistence;

import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


@ApplicationScoped
public class MovieDao {

    @Inject
    private EntityManager em;

    public void persist(Movie movie) {
        em.persist(movie);
    }

    public void addTheatreToMovie(String movieEidr, String theatreId) {
        Movie movie = getByEidr(movieEidr);
        MovieTheatre theatre = em.find(MovieTheatre.class, theatreId);
        movie.addTheatre(theatre);
        em.persist(movie);
    }

    public Movie getByEidr(String eidr){
        return em.find(Movie.class, eidr);
    }

    public Movie update(Movie player){
        return em.merge(player);
    }

    public List<Movie> loadAll() {
        return em.createNamedQuery("Movie.allMovies", Movie.class).getResultList();
    }


}
