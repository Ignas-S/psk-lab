package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.MovieTheatre;
import lt.vu.persistence.MovieDao;
import lt.vu.persistence.MovieTheatreDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
@SessionScoped
public class OptimisticLockTesting implements Serializable {

    private String theatreId = "theatre2";

    @Inject
    MovieTheatreDao theatreDao;

    public void test() {
        MovieTheatre t1 = this.theatreDao.getById(this.theatreId);
        MovieTheatre t2 = this.theatreDao.getById(this.theatreId);
        t1.setName("t1");
        t2.setName("t2");
        update(t1);
        update(t2);
    }

    @Transactional
    public void update(MovieTheatre mt) {
        try {
            this.theatreDao.persist(mt);
        } catch (OptimisticLockException e) {
            System.out.println("Optimistic lock in update2 triggered");
            update(mt);
        }
    }

    @Transactional
    public void update1() {
        try {
            MovieTheatre theatre = this.theatreDao.getById(this.theatreId);
            theatre.setName("update1");
            this.theatreDao.persist(theatre);
        } catch (OptimisticLockException e) {
            System.out.println("Optimistic lock in update2 triggered");
            update1();
        }
    }

    @Transactional
    public void update2() {
        try {
            MovieTheatre theatre = this.theatreDao.getById(this.theatreId);
            theatre.setName("update2");
            this.theatreDao.persist(theatre);
        } catch (OptimisticLockException e) {
            System.out.println("Optimistic lock in update2 triggered");
            update2();
        }
    }

}
