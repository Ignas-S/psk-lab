package lt.vu.services;
import lombok.var;
import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;
import lt.vu.persistence.MovieDao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.List;

@ApplicationScoped
@Alternative
public class TheatreLocator implements ILocator {
    public String locateTheatre(MovieTheatre mt) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
        return mt.getAddress();
    }
}
