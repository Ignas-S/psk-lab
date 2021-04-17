package lt.vu.services;

import lt.vu.entities.MovieTheatre;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class TheatreLocatorFormatted implements ILocator {
    public String locateTheatre(MovieTheatre mt) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        return "[Address] " + mt.getAddress();
    }
}
