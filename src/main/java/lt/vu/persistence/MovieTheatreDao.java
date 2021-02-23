package lt.vu.persistence;

import lt.vu.entities.MovieTheatre;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;


@ApplicationScoped
public class MovieTheatreDao {

    @Inject
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

}
