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

    @Column(name = "INITIAL_LOCATION")
    private String initialLocationId;

    public ThankYouToken(){};

    public ThankYouToken(String id){
        this.id = id;
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

    public String getInitialLocationId() {
        return initialLocationId;
    }

    public void setInitialLocationId(String initialLocationId) {
        this.initialLocationId = initialLocationId;
    }
}
