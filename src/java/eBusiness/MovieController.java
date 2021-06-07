package eBusiness;

/*
    This controller class is used to control the functionalities for the XHTML pages related to Movie model. 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

@Named(value = "movieController")
@ManagedBean
@RequestScoped
public class MovieController {

    // Attributes   
    @EJB
    private MovieEJB movieEJB;

    private Movie movie;

    private List<Movie> movieList;
    private List<Movie> foundMovieList;

    public MovieController() {
        this.movie = new Movie();
        this.movieList = new ArrayList<>();
        this.foundMovieList = new ArrayList<>();
    }
    
     public boolean isInstanceOfMovie(Item item) {
        if (item instanceof Movie) {
            return true;
        } else {
            return false;
        }
    }

    // method to create movie          
    public String createMovie() {
        movie = movieEJB.createMovie(movie);
        movieList = movieEJB.findMovies();
        return "/movie/list.xhtml?faces-redirect = true";
    }

    // method to search movie and display    
    public String searchMovie() {
        this.foundMovieList = movieEJB.findMovieByTitle(movie.getTitle());
        return "/movie/found.xhtml?faces-redirect = true";
    }

    public String viewItem() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String selectedMovieId = params.get("selectedMovieId");

        Movie selectedMovie = movieEJB.findMovieById(Long.parseLong(selectedMovieId));
        if (selectedMovie != null) {
            this.foundMovieList = movieEJB.findMovieByTitle(selectedMovie.getTitle());
        } else {
            System.out.println("Movie : " + selectedMovie.getTitle() + " not found.");
        }
        return "/movie/found.xhtml?faces-redirect = true";
    }

    //Getters & Setters  
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Movie> getMovieList() {
        movieList = movieEJB.findMovies();
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getFoundMovieList() {
        return foundMovieList;
    }

    public void setFoundMovieList(List<Movie> foundMovieList) {
        this.foundMovieList = foundMovieList;
    }

}
