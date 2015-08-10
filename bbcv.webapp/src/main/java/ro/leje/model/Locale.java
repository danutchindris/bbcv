package ro.leje.model;

/**
 * @author Danut Chindris
 * @since July 21, 2015
 */
public class Locale {

    private String code;
    private String name;
    private boolean soon;

    public Locale() {
    }

    public Locale(String code, String name, boolean soon) {
        this.code = code;
        this.name = name;
        this.soon = soon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSoon() {
        return soon;
    }

    public void setSoon(boolean soon) {
        this.soon = soon;
    }
}
