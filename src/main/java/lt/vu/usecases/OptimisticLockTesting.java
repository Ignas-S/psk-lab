package lt.vu.usecases;

import lt.vu.entities.MovieTheatre;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.MovieTheatreDao;
import lt.vu.services.AsyncTheatreUpdater;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class OptimisticLockTesting implements Serializable {

    private String theatreId = "theatre2"; // Hardcoded since this is only for testing / demo

    @Inject
    private AsyncTheatreUpdater updater;

    private CompletableFuture update1Future = null;
    private CompletableFuture update2Future = null;

    @Inject
    MovieTheatreDao theatreDao;

    @LoggedInvocation
    public String test() {
        MovieTheatre mt = theatreDao.getById(this.theatreId);
        // Wait 1000 ms then update
        update1Future = CompletableFuture.supplyAsync(() -> updater.update(theatreId,"rename1", 1000));
        // Wait 100 ms then update, first update arrives and throws Optimistic Lock exception
        update2Future = CompletableFuture.supplyAsync(() -> updater.update(theatreId,"rename2", 100));
        return "/optlocktest.xhtml?faces-redirect=true";
    }

    public boolean isUpdate1Running() {
        return this.update1Future != null && !this.update1Future.isDone();
    }
    public boolean isUpdate2Running() {
        return this.update2Future != null && !this.update2Future.isDone();
    }

    public String getUpdate1Status() throws ExecutionException, InterruptedException {
        if (update1Future == null) {
            return null;
        } else if (isUpdate1Running()) {
            return "Update 1 in progress...";
        }
        return "Update1 Status: " + update1Future.get();
    }
    public String getUpdate2Status() throws ExecutionException, InterruptedException {
        if (update1Future == null) {
            return null;
        } else if (isUpdate2Running()) {
            return "Update 2 in progress...";
        }
        return "Update2 Status: " + update2Future.get();
    }
}
