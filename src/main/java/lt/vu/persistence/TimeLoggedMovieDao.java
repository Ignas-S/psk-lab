package lt.vu.persistence;

import lombok.var;
import lt.vu.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@ApplicationScoped
@Specializes
public class TimeLoggedMovieDao extends MovieDao {
    private static final String output = "[MovieDao] method <%s> elapsed %f ms";

    private double _calcMs(long start, long end) {
        return ((double) end - start)/1000000;
    }

    public void persist(Movie movie) {
        long startTime = System.nanoTime();
        super.persist(movie);
        long endTime = System.nanoTime();
        System.out.printf((output) + "%n", "persist", _calcMs(startTime, endTime));
    }

    public Movie getByEidr(String eidr){
        long startTime = System.nanoTime();
        Movie m = super.getByEidr(eidr);
        long endTime = System.nanoTime();
        System.out.printf((output) + "%n", "getByEidr", _calcMs(startTime, endTime));
        return m;
    }

    public Movie update(Movie movie){
        long startTime = System.nanoTime();
        Movie m = super.update(movie);
        long endTime = System.nanoTime();
        System.out.printf((output) + "%n", "update", _calcMs(startTime, endTime));
        return m;
    }

    public List<Movie> loadAll() {
        long startTime = System.nanoTime();
        List<Movie> m = super.loadAll();
        long endTime = System.nanoTime();
        System.out.printf((output) + "%n", "loadAll", _calcMs(startTime, endTime));
        return m;
    }

    public List<Movie> getOtherMovies(List<String> exclude) {
        long startTime = System.nanoTime();
        List<Movie> m = super.getOtherMovies(exclude);
        long endTime = System.nanoTime();
        System.out.printf((output) + "%n", "getOtherMovies", _calcMs(startTime, endTime));
        return m;
    }
}
