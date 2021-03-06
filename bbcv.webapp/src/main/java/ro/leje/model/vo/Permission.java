package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Danut Chindris
 * @since September 23, 2015
 */
public class Permission implements Serializable, Comparable<Permission> {

    private static final long serialVersionUID = 1L;

    private long id;

    @Size(max = 50, message = "{error.name.size}")
    @NotEmpty(message = "{error.name.empty}")
    private String name;

    public Permission() {}

    public Permission(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
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
        final Permission other = (Permission) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.id, this.name);
    }

    @Override
    public int compareTo(Permission o) {
        return ComparisonChain.start()
                .compare(this.name, o.name)
                .result();
    }
}
