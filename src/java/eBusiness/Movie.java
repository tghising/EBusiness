package eBusiness;

/*
    This entity class is used to represent an Movie object. 
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
    , @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title")})
public class Movie extends Item {

    // Attributes
    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private String specialFeatures;

    //Constructors
    public Movie() {
        super();
    }

    public Movie(String title, String description, String company, String platform, String classification, Float price, int stockNumber, int duration, String specialFeatures) {
        super(title, description, company, platform, classification, price, stockNumber);
        this.duration = duration;
        this.specialFeatures = specialFeatures;
    }

    // setters
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    // getters
    public int getDuration() {
        return duration;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    // toString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Movie");
        sb.append("{Id=").append(id);
        sb.append(", Title='").append(super.getTitle()).append('\'');
        sb.append(", Description='").append(super.getDescription()).append('\'');
        sb.append(", Company='").append(super.getCompany()).append('\'');
        sb.append(", Platform='").append(super.getPlatform()).append('\'');
        sb.append(", Classification='").append(super.getClassification()).append('\'');
        sb.append(", Price=").append(super.getPrice());
        sb.append(", Stock Number=").append(super.getStockNumber());
        sb.append(", Duration=").append(duration);
        sb.append(", Special Features=").append(specialFeatures);
        sb.append('}');
        return sb.toString();
    }
}
