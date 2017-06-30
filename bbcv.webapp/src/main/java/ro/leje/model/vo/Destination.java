package ro.leje.model.vo;

/**
 * @author Danut Chindris
 * @since June 29, 2017
 */
public class Destination {

    private long id;

    private String text;

    private int card;

    private String fileName;

    public Destination(final long id, final String text, final String fileName) {
        this.id = id;
        this.text = text;
        this.card = 4;
        this.fileName = fileName;
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

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
