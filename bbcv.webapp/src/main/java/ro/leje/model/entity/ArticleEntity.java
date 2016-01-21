package ro.leje.model.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Set;

/**
 * An entity which describes an article.
 *
 * @author Danut Chindris
 * @since December 8, 2015
 */
@Entity
@Table(name = "ARTICLE")
public class ArticleEntity {

    @Id
    @Column(name = "ARTICLE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(targetEntity = UserEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ARTICLE_USER", joinColumns = {
            @JoinColumn(name = "ARTICLE_ID", nullable = false)
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "USER_ID")
            })
    private Set<UserEntity> authors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<UserEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<UserEntity> authors) {
        this.authors = authors;
    }

    public ArticleEntity() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}