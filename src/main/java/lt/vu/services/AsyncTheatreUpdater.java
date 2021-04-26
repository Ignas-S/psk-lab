package lt.vu.services;

import lombok.var;
import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;
import lt.vu.persistence.Async;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class AsyncTheatreUpdater {
    @PersistenceContext
    @Async
    private EntityManager em;

    // Simulating big system load - changes to db delayed
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String update(String id, String name, long waitMillis, long prewaitMilis) {
        try {
            Thread.sleep(prewaitMilis);
            var theatre = em.find(MovieTheatre.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            System.out.println("updating with " + name + ", v: " + theatre.getV());
            Thread.sleep(waitMillis);
            theatre.setName(name);
            System.out.println("flushing with " + theatre.getName() + ", v: " + theatre.getV());
            em.flush();
        }
        catch (OptimisticLockException e) {
            System.out.println("OPTIMISTIC LOCK EXCEPTION IN AsyncTheatreUpdater"); //
            System.out.println(e);
            return "Optimistic Lock exception";
            // update(id, name, waitMillis);
        }
        catch (InterruptedException e) {}
        return "Update finished";
    }
}
