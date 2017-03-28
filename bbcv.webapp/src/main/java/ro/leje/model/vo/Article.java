package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * @author Danut Chindris
 * @since December 8, 2015
 */
public class Article implements Serializable, Comparable<Article> {

    private static final long serialVersionUID = 1L;

    private long id;

    private String language;

    @NotEmpty(message = "{error.title.empty}")
    private String title;

    @NotEmpty(message = "{error.body.empty}")
    private String body;

    private String status;

    public Article() {
    }

    public Article(final long id, final String language, final String title,
                   final String body, final String status) {
        this.id = id;
        this.language = language;
        this.title = title;
        this.body = body;
        this.status = status;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("language", language)
                .add("title", title)
                .add("body", body)
                .add("status", status)
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
        final Article other = (Article) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.language, other.language)
                && Objects.equal(this.title, other.title)
                && Objects.equal(this.body, other.body)
                && Objects.equal(this.status, other.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.language, this.title, this.body, this.status);
    }

    @Override
    public int compareTo(Article o) {
        return ComparisonChain.start()
                .compare(this.language, o.language)
                .compare(this.title, o.title)
                .compare(this.body, o.body)
                .compare(this.status, o.status)
                .result();
    }
}
