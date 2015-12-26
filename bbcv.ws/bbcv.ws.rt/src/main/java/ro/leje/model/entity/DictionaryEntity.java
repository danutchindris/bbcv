package ro.leje.model.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;

/**
 * An entity which describes a translation entry. It is used for translating items such as articles.
 *
 * @author Danut Chindris
 * @since December 8, 2015
 */
@Entity
@Table(name = "DICTIONARY")
public class DictionaryEntity {

    @Id
    @Column(name = "DICTIONARY_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // fully qualified name of the entity it refers to; e.g. ro.leje.model.entity.ArticleEntity
    @Column(name = "OBJECT_TYPE")
    private String objectType;

    // the id of the entity it refers to
    @Column(name = "OBJECT_ID")
    private Long objectId;

    // the semantic category for the dictionary entry; e.g. title or body
    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "EN")
    private String en;

    @Column(name = "RO")
    private String ro;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getRo() {
        return ro;
    }

    public void setRo(String ro) {
        this.ro = ro;
    }

    public DictionaryEntity() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("en", en)
                .add("ro", ro)
                .toString();
    }
}
