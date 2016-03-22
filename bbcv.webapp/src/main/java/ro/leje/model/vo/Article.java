package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.io.Serializable;

/**
 * @author Danut Chindris
 * @since December 8, 2015
 */
public class Article implements Serializable, Comparable<Article> {

    private static final long serialVersionUID = 1L;

    private long id;

    private String language;

    private String title;

    private String body;

    public Article() {
    }

    public Article(long id, String language, String title, String body) {
        this.id = id;
        this.language = language;
        this.title = title;
        this.body = body;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("language", language)
                .add("title", title)
                .add("body", body)
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
                && Objects.equal(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.language, this.title, this.body);
    }

    @Override
    public int compareTo(Article o) {
        return ComparisonChain.start()
                .compare(this.language, o.language)
                .compare(this.title, o.title)
                .compare(this.body, o.body)
                .result();
    }
}
