package eu.itswc.model;

import javax.persistence.*;

@Entity
@Table(name = "MEDIA_DESCRIPTOR")
public class MediaDescriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    private String id;

    @Column(name = "TOKEN_PASSED_ID")
    private String thankYouTokenPassedId;

    @Column (name = "ACCESS_PATH")
    private String path;

    @Column (name = "FILE_NAME")
    private String fileName;

    @Column (name = "EXTENSION")
    private Enum<MediaType> mediaType;

    @Column (name = "DESCRIPTION")
    private String description;

    //TODO: implement the support for media files
    public MediaDescriptor() {
    }

    public MediaDescriptor(String id, String thankYouTokenPassedId, Enum<MediaType> mediaType, String fileName, String description) {
        this.id = id;
        this.thankYouTokenPassedId = thankYouTokenPassedId;
        this.mediaType = mediaType;
        this.fileName = fileName;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThankYouTokenPassedId() {
        return thankYouTokenPassedId;
    }

    public void setThankYouTokenPassedId(String thankYouTokenPassedId) {
        this.thankYouTokenPassedId = thankYouTokenPassedId;
    }

    public Enum<MediaType> getMediaType() {
        return mediaType;
    }

    public void setMediaType(Enum<MediaType> mediaType) {
        this.mediaType = mediaType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
