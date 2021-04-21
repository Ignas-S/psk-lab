package lt.vu.persistence;

import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@ApplicationScoped
public class MovieDao {

    @Inject @Default
    private EntityManager em;

    public void persist(Movie movie) {
        em.persist(movie);
    }

    public Movie getByEidr(String eidr){
        return em.find(Movie.class, eidr);
    }

    public Movie update(Movie movie){
        return em.merge(movie);
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
