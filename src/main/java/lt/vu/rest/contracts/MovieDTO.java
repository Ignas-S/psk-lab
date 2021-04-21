package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
    private String eidr;
    private String director;
    private String name;
    private int year;
    private String genres;
    private int duration;

    public MovieDTO() {}
    public MovieDTO(String eidr, String director, String name, int year, String genres, int duration) {
        this.eidr = eidr;
        this.director = director;
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.duration = duration;
    }
}
