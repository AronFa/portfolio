package eu.itswc.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "TOKEN_PASSED")
public class ThankYouTokenPassed {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "TOKEN_ID")
    private String tokenId;

    // TODO: catch and solve the case of descriptions longer 500 char.
    @Column(name = "DESCRIPTION")
    @Size(max = 500)
    private String description;

    @Column(name = "DATE")
    private String date;

    @Column(name = "COORDINATES_ID")
    private Long coordinatesId;

    public ThankYouTokenPassed(){
        initializeDate();
    }

    private void initializeDate(){
        setDate(DateStuff.getTodayUTC());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCoordinatesId() {
        return coordinatesId;
    }

    public void setCoordinatesId(Long coordinatesId) {
        this.coordinatesId = coordinatesId;
    }

}
