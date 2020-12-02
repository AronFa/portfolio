package eu.itswc.model;

import javax.persistence.*;


@Entity
@Table(name = "TOKEN_PASSED")
public class ThankYouTokenPassed {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "TOKEN_ID")
    private String tokenId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TIME")
    private String timeOfInteraction;

    @Column(name = "LOCATION_ID")
    private String locationIdOfInteraction;

    public ThankYouTokenPassed() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeOfInteraction() {
        return timeOfInteraction;
    }

    public void setTimeOfInteraction(String timeOfInteraction) {
        this.timeOfInteraction = timeOfInteraction;
    }

    public String getLocationIdOfInteraction() {
        return locationIdOfInteraction;
    }

    public void setLocationIdOfInteraction(String locationIdOfInteraction) {
        this.locationIdOfInteraction = locationIdOfInteraction;
    }

}
