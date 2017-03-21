package ro.leje.model.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * An entity which describes an image.
 *
 * @author Danut Chindris
 * @since September 24, 2016
 */
@Entity
@Table(name = "IMAGE")
public class ImageEntity {

    @Id
    @Column(name = "IMAGE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "FILENAME")
    private String fileName;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ARTICLE_ID")
    private ArticleEntity article;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    public ImageEntity() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("fileName", fileName)
                .toString();
    }
}
