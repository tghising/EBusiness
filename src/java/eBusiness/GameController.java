package eBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/*
    This controller class is used to control the functionalities for the XHTML pages related to Game model. 
 */
@Named(value = "gameController")
@RequestScoped
public class GameController {

    // Attributes 
    @EJB
    private GameEJB gameEJB;
    private Game game;
    private List<Game> gameList;
    private List<Game> foundGameList;

    public GameController() {
        this.game = new Game();
        this.gameList = new ArrayList<>();
        this.foundGameList = new ArrayList<>();
    }

    public boolean isInstanceOfGame(Item item) {
        if (item instanceof Game) {
            return true;
        } else {
            return false;
        }
    }

    // Public Methods           
    public String createGame() {
        game = gameEJB.createGame(game);
        gameList = gameEJB.findGames();
        return "/game/list.xhtml?faces-redirect = true";
    }

    // method to search Game and display    
    public String searchGame() {
        this.foundGameList = gameEJB.findGameByTitle(game.getTitle());
        return "/game/found.xhtml?faces-redirect = true";
    }

    public String viewItem() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        String selectedGameId = params.get("selectedGameId");
        Game selectedGame = gameEJB.findGameById(Long.parseLong(selectedGameId));
        if (selectedGame != null) {
            this.foundGameList = gameEJB.findGameByTitle(selectedGame.getTitle());
        } else {
            System.out.println("Game : " + selectedGame.getTitle() + " not found.");
        }
        return "/game/found.xhtml?faces-redirect = true";

    }

    // getters and setters
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Game> getGameList() {
        gameList = gameEJB.findGames();
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public List<Game> getFoundGameList() {
        return foundGameList;
    }

    public void setFoundGameList(List<Game> foundGameList) {
        this.foundGameList = foundGameList;
    }

}
