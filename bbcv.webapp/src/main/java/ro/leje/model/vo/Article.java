package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

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

    @NotEmpty(message = "{error.motto.empty}")
    private String motto;

    @NotEmpty(message = "{error.body.empty}")
    private String body;

    private String status;

    private Date date;

    private String formattedDate;

    public Article() {
    }

    public Article(final long id, final String language, final String title, final String motto,
                   final String body, final String status, final Date date) {
        this.id = id;
        this.language = language;
        this.title = title;
        this.motto = motto;
        this.body = body;
        this.status = status;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("language", language)
                .add("title", title)
                .add("motto", motto)
                .add("body", body)
                .add("status", status)
                .add("date", date)
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
                && Objects.equal(this.motto, other.motto)
                && Objects.equal(this.body, other.body)
                && Objects.equal(this.status, other.status)
                && Objects.equal(this.date, other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.language, this.title, this.motto, this.body, this.status, this.date);
    }

    @Override
    public int compareTo(Article o) {
        return ComparisonChain.start()
                .compare(this.language, o.language)
                .compare(this.title, o.title)
                .compare(this.motto, o.motto)
                .compare(this.body, o.body)
                .compare(this.status, o.status)
                .compare(this.date, o.date)
                .result();
    }
}
