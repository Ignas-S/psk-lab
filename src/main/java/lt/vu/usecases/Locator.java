package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.MovieTheatre;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.MovieTheatreDao;
import lt.vu.services.ILocator;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class Locator implements Serializable {

    @Inject
    MovieTheatreDao theatreDao;

    @Inject
    ILocator locator;

    private CompletableFuture<String> locatorTask = null;

    @Getter @Setter
    private String theatreId;

    @LoggedInvocation
    public String locateTheatre() {
        MovieTheatre mt = theatreDao.getById(this.theatreId);
        locatorTask = CompletableFuture.supplyAsync(() -> locator.locateTheatre(mt));
        return "/theatre.xhtml?faces-redirect=true&tId=" + this.theatreId;
    }

    public boolean isAddressLocatorRunning() {
        return this.locatorTask != null && !this.locatorTask.isDone();
   }

    public String getAddressLocatorStatus() throws ExecutionException, InterruptedException {
        if (locatorTask == null) {
            return null;
        } else if (isAddressLocatorRunning()) {
            return "Address location in progress...";
        }
        return "Located: " + locatorTask.get();
    }
}
