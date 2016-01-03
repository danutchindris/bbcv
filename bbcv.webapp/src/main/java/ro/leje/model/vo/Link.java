package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.io.Serializable;

/**
 * @author Danut Chindris
 * @since December 6, 2015
 */
public class Link implements Serializable, Comparable<Link> {

    private static final long serialVersionUID = 1L;

    private long id;

    private String text;

    private String url;

    public Link() {
    }

    public Link(long id, String text, String url) {
        this.id = id;
        this.text = text;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                .add("text", text)
                .add("url", url)
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
        final Link other = (Link) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.text, other.text);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.text, this.url);
    }

    @Override
    public int compareTo(Link o) {
        return ComparisonChain.start()
                .compare(this.text, o.text)
                .compare(this.url, o.url)
                .result();
    }
}
