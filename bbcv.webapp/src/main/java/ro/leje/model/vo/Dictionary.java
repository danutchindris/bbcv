package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Danut Chindris
 * @since December 21, 2015
 */
public class Dictionary implements Serializable, Comparable<Dictionary> {

    private static final long serialVersionUID = 1L;

    private long id;

    @NotNull(message = "{error.object.null}")
    private String objectType;

    @NotNull(message = "{error.object.null}")
    private Long objectId;

    @NotNull(message = "{error.object.null}")
    private String category;

    private String en;

    private String ro;

    public Dictionary() {
    }

    public Dictionary(long id, String objectType, Long objectId, String category, String en, String ro) {
        this.id = id;
        this.objectType = objectType;
        this.objectId = objectId;
        this.category = category;
        this.en = en;
        this.ro = ro;
    }

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("objectType", objectType)
                .add("objectId", objectId)
                .add("category", category)
                .add("en", en)
                .add("ro", ro)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dictionary other = (Dictionary) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.objectType, other.objectType)
                && Objects.equal(this.objectId, other.objectId)
                && Objects.equal(this.category, other.category)
                && Objects.equal(this.en, other.en)
                && Objects.equal(this.ro, other.ro);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.objectType, this.objectId, this.category, this.en, this.ro);
    }

    @Override
    public int compareTo(Dictionary o) {
        return ComparisonChain.start()
                .compare(this.objectType, o.objectType)
                .compare(this.objectId, o.objectId)
                .compare(this.category, o.category)
                .compare(this.en, o.en)
                .compare(this.ro, o.ro)
                .result();
    }
}
