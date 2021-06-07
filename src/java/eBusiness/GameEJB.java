package eBusiness;

/*
    This EJB class is used to persist and retrieve Game objects to and from the entity manager.
*/

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.ejb.Stateful;

@Stateful
public class GameEJB {

    // Attributes             
    @PersistenceContext(unitName = "EBusinessPU")
    private EntityManager em;

    public List<Game> findGames() {
        TypedQuery<Game> query = em.createNamedQuery("Game.findAll", Game.class);
        return query.getResultList();
    }

    public List<Game> findGameByTitle(String title) {
        TypedQuery query = em.createNamedQuery("Game.findByTitle", Game.class).setParameter("title", title);
        return query.getResultList();
    }

    public Game findGameById(Long id) {
        return em.find(Game.class, id);
    }

    public Game createGame(Game game) {
        em.persist(game);
        return game;
    }
    
    public Game updateGame(Game game) {
    return em.merge(game);
    }
}
