package eBusiness;

/*
    This entity class is used to represent an Game object. 
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g")
    , @NamedQuery(name = "Game.findByTitle", query = "SELECT g FROM Game g WHERE g.title = :title")})
public class Game extends Item {

    // Attributes
    @Column(nullable = false)
    private String hdVideoOutput;

    @Column(nullable = false)
    private int hardDriveSpace;

    @Column(nullable = false)
    private int numberOfPlayers;

    //Constructors
    public Game() {
        super();
    }

    public Game(String title, String description, String company, String platform, String classification, Float price, int stockNumber, String hdVideoOutput, int hardDriveSpace, int numberOfPlayers) {
        super(title, description, company, platform, classification, price, stockNumber);
        this.hdVideoOutput = hdVideoOutput;
        this.hardDriveSpace = hardDriveSpace;
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getHdVideoOutput() {
        return hdVideoOutput;
    }

    public void setHdVideoOutput(String hdVideoOutput) {
        this.hdVideoOutput = hdVideoOutput;
    }

    public int getHardDriveSpace() {
        return hardDriveSpace;
    }

    public void setHardDriveSpace(int hardDriveSpace) {
        this.hardDriveSpace = hardDriveSpace;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    // toString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Game");
        sb.append("{Id=").append(id);
        sb.append(", Title='").append(super.getTitle()).append('\'');
        sb.append(", Description='").append(super.getDescription()).append('\'');
        sb.append(", Company='").append(super.getCompany()).append('\'');
        sb.append(", Platform='").append(super.getPlatform()).append('\'');
        sb.append(", Classification='").append(super.getClassification()).append('\'');
        sb.append(", Price=").append(super.getPrice());
        sb.append(", Stock Number=").append(super.getStockNumber());
        sb.append(", HD Video Output=").append(hdVideoOutput);
        sb.append(", Hard Drive Space=").append(hardDriveSpace);
        sb.append(", Number Of Players=").append(numberOfPlayers);
        sb.append('}');
        return sb.toString();
    }
}
