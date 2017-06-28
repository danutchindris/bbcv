package ro.leje.model.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity which describes an image.
 *
 * @author Danut Chindris
 * @since September 24, 2016
 */
@Entity
@Table(name = "IMAGE")
public class ImageEntity {

    @Id
    @Column(name = "IMAGE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "FILENAME")
    private String fileName;

    @Column(name = "COVER")
    private Boolean cover;

    // fully qualified name of the entity it refers to; e.g. ro.leje.model.entity.ArticleEntity
    @Column(name = "OBJECT_TYPE")
    private String objectType;

    // the id of the entity it refers to
    @Column(name = "OBJECT_ID")
    private Long objectId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getCover() {
        return cover;
    }

    public void setCover(Boolean cover) {
        this.cover = cover;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public ImageEntity() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("fileName", fileName)
                .add("cover", cover)
                .add("objectType", objectType)
                .add("objectId", objectId)
                .toString();
    }
}
