package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * @author Danut Chindris
 * @since September 24, 2016
 */
public class Image implements Serializable, Comparable<Image> {

    private static final long serialVersionUID = 1L;

    private long id;

    private String language;

    @NotEmpty(message = "{error.file.name.empty}")
    private String fileName;

    @NotEmpty(message = "{error.title.empty}")
    private String title;

    private Boolean cover;

    public Image() {
    }

    public Image(final long id, final String language, final String fileName, final String title, final Boolean cover) {
        this.id = id;
        this.language = language;
        this.fileName = fileName;
        this.title = title;
        this.cover = cover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCover() {
        return cover;
    }

    public void setCover(Boolean cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("language", language)
                .add("fileName", fileName)
                .add("title", title)
                .add("cover", cover)
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
        final Image other = (Image) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.language, other.language)
                && Objects.equal(this.fileName, other.fileName)
                && Objects.equal(this.title, other.title)
                && Objects.equal(this.cover, other.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.language, this.fileName, this.title, this.cover);
    }

    @Override
    public int compareTo(Image o) {
        return ComparisonChain.start()
                .compare(this.language, o.language)
                .compare(this.fileName, o.fileName)
                .compare(this.title, o.title)
                .compare(this.cover, o.cover)
                .result();
    }
}
