package lt.vu.usecases;

import lombok.Getter;
import lt.vu.mybatis.dao.MovieTheatreMapper;
import lt.vu.mybatis.model.MovieTheatre;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
@RequestScoped
public class TheatreMyBatis implements Serializable {
    @Inject
    private MovieTheatreMapper mtm;

    @Getter
    private MovieTheatre theatre;
    @Getter
    private String theatreId;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.theatreId = requestParameters.get("tId");
        if (theatreId != null) {
            this.refresh();
        }
    }

    // All methods that need to run upon data change
    private void refresh() {
        this.loadTheatre();
    }
    private void loadTheatre() {
        this.theatre = mtm.selectByPrimaryKey(this.theatreId);
    }
}
