package eu.itswc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOKEN")
public class ThankYouToken {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "INITIAL_USER_ID")
    private String introductoryUserId;

    @Column (name = "INITIAL_DESCRIPTION")
    private String initialDescription;

    @Column (name = "INTRODUCED")
    private String initialDate;

    @Column(name = "INITIAL_COORDINATES_ID")
    private Long initialCoordinates;

    public ThankYouToken(){
        initializeDate();
    };

    public ThankYouToken(String id){
        this.id = id;
    }

    private void initializeDate(){
        setInitialDate(DateStuff.getTodayUTC());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroductoryUserId() {
        return introductoryUserId;
    }

    public void setIntroductoryUserId(String introductoryUserId) {
        this.introductoryUserId = introductoryUserId;
    }

    public String getInitialDescription() {
        return initialDescription;
    }

    public void setInitialDescription(String initialDescription) {
        this.initialDescription = initialDescription;
    }

    public String getDateIntroduced() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public Long getInitialCoordinatesId() {
        return initialCoordinates;
    }

    public void setInitialCoordinatesId(Long c) {
        this.initialCoordinates = c;
    }

}
