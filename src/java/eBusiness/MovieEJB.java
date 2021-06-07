package eBusiness;

/*
    This EJB class is used to persist and retrieve Movie objects to and from the entity manager.
*/

import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateful
public class MovieEJB {

    // Attributes             
    @PersistenceContext(unitName = "EBusinessPU")
    private EntityManager em;

    public List<Movie> findMovies() {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
        return query.getResultList();
    }

    public List<Movie> findMovieByTitle(String title) {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTitle", Movie.class).setParameter("title", title);
        return query.getResultList();
    }

    public Movie findMovieById(Long id) {
        return em.find(Movie.class, id);
    }

    public Movie createMovie(Movie movie) {
        em.persist(movie);
        return movie;
    }

    public Movie updateMovie(Movie movie) {
        return em.merge(movie);
    }
}
