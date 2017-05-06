package ro.leje.model.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * An entity which describes a tag.
 *
 * @author Danut Chindris
 * @since April 23, 2017
 */
@Entity
@Table(name = "TAG")
public class TagEntity {

    @Id
    @Column(name = "TAG_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "TEXT")
    private String text;

    @ManyToMany(targetEntity = ArticleEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ARTICLE_TAG", joinColumns = {
            @JoinColumn(name = "TAG_ID", nullable = false)
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "ARTICLE_ID")
            })
    private Set<ArticleEntity> articles;

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

    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    public TagEntity() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("text", text)
                .toString();
    }
}
