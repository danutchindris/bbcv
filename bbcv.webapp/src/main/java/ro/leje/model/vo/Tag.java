package ro.leje.model.vo;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * @author Danut Chindris
 * @since May 3, 2017
 */
public class Tag implements Serializable {

    private long id;

    @NotEmpty(message = "{error.text.empty}")
    private String text;

    public Tag() {}

    public Tag(final long id, final String text) {
        this.id = id;
        this.text = text;
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
}
