package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author Danut Chindris
 * @since March 27, 2017 - Azores
 */
public class HomeArticle {

    private static final long serialVersionUID = 1L;

    private long id;

    private String language;

    private String title;

    private String motto;

    private String titleInUrl;

    private String image;

    public HomeArticle() {
    }

    public HomeArticle(final long id, final String language, final String title,
                       final String motto, final String image) {
        this.id = id;
        this.language = language;
        this.title = title;
        this.motto = motto;
        this.image = image;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitleInUrl() {
        return titleInUrl;
    }

    public void setTitleInUrl(String titleInUrl) {
        this.titleInUrl = titleInUrl;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("language", language)
                .add("title", title)
                .add("motto", motto)
                .add("image", image)
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
        final HomeArticle other = (HomeArticle) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.language, other.language)
                && Objects.equal(this.title, other.title)
                && Objects.equal(this.motto, other.motto)
                && Objects.equal(this.image, other.image);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.language, this.title, this.motto, this.image);
    }
}
