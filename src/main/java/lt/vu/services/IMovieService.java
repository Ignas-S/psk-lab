package lt.vu.services;

import lt.vu.entities.Movie;
import lt.vu.entities.MovieTheatre;
import lt.vu.rest.contracts.MovieDTO;

public interface IMovieService {
    void save(Movie movie);
    void saveFromDTO(MovieDTO movie);
    Movie get(String eidr);
    void update(Movie movie);
    void updateFromDTO(MovieDTO movieDTO);
}
