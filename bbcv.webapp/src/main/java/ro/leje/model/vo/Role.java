package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.io.Serializable;

/**
 * @author Danut Chindris
 * @since August 31, 2015
 */
public class Role implements Serializable, Comparable<Role> {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    public Role() {}

    public Role(long id, String name) {
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
        final Role other = (Role) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.id, this.name);
    }

    @Override
    public int compareTo(Role o) {
        return ComparisonChain.start()
                .compare(this.name, o.name)
                .result();
    }
}
