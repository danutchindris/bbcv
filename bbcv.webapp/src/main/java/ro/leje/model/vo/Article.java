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

    private String title;

    private String text;

    public Article() {
    }

    public Article(long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("text", text)
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
                && Objects.equal(this.title, other.title)
                && Objects.equal(this.text, other.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.title, this.text);
    }

    @Override
    public int compareTo(Article o) {
        return ComparisonChain.start()
                .compare(this.title, o.title)
                .compare(this.text, o.text)
                .result();
    }
}
