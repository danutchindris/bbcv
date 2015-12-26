package ro.leje.model.entity;

import javax.persistence.*;

/**
 * An entity which describes a link that is displayed in the Links page.
 *
 * @author Danut Chindris
 * @since December 8, 2015
 */
@Entity
@Table(name = "LINK")
public class LinkEntity {

    @Id
    @Column(name = "LINK_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "URL")
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
