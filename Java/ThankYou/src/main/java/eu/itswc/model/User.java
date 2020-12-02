package eu.itswc.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @Column(name = "userName", updatable = false, nullable = false)
    private String userName;

    private User(){
    }

    // TODO: is the empty constructor sufficient?
    private User(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
