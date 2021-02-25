package lt.vu.persistence;

import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@ApplicationScoped
public class MovieDao {

    @Inject
    private EntityManager em;

    public void persist(Movie movie) {
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

    public List<Movie> getOtherMovies(List<String> exclude) {
        Query qr = em.createNamedQuery("Movie.otherMovies", Movie.class);
        qr.setParameter("exclude", exclude);
        return qr.getResultList();
    }


}
