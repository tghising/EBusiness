package eBusiness;

/*
    This entity class is used to represent an Item object, which is the super class for both the Game and Movie class. 
*/

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false)
    protected String title;

    @Column(nullable = false)
    protected String description;

    @Column(nullable = false)
    protected String company;

    @Column(nullable = false)
    protected String platform;

    @Column(nullable = false)
    protected String classification;

    @Column(nullable = false)
    protected Float price;

    @Column(nullable = false)
    protected int stockNumber;

    public Item() {
    }

    public Item(String title, String description, String company, String platform, String classification, Float price, int stockNumber) {
        this.title = title;
        this.description = description;
        this.company = company;
        this.platform = platform;
        this.classification = classification;
        this.price = price;
        this.stockNumber = stockNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

}
