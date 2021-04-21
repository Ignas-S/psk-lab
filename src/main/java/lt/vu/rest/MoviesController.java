package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.var;
import lt.vu.entities.Movie;
import lt.vu.persistence.MovieDao;
import lt.vu.rest.contracts.MovieDTO;
import lt.vu.services.MovieService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/movies")
public class MoviesController {

    @Inject
    @Setter
    @Getter
    MovieService movieService;

    @Path("/{eidr}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByEidr(@PathParam("eidr") final String eidr) {
        try {
            var m = movieService.get(eidr);
            var res = new MovieDTO(
                    m.getEidr(),
                    m.getDirector(),
                    m.getName(),
                    m.getYear(),
                    m.getGenres(),
                    m.getDuration()
            );
            return Response.ok(res).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @Path("/")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(MovieDTO data) {
        try {
            movieService.updateFromDTO(data);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(MovieDTO data) {
        try {
            movieService.saveFromDTO(data);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (EntityExistsException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}

