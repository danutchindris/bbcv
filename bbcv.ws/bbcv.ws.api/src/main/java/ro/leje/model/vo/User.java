package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.io.Serializable;

/**
 * Value object that should be accessible to the outside world.
 * We don't expose entities as they pertain to the data access layer.
 * <br />
 * This is the reference for implementing value objects across the application.
 * Following there is the checklist for implementation requirements:
 *
 * <ul>
 * <li>Always implement java.io.Serializable</li>
 * <li>Always populate all fields of a value object</li>
 * <li>Always type fields accurately</li>
 * <li>Check dependence on third-party classes in value objects</li>
 * <li>Always override method {@link Object#toString()}</li>
 * <li>Consider overriding methods {@link Object#equals(Object)} and {@link Object#hashCode()}</li>
 * <li>Consider implementing {@link java.lang.Comparable}</li>
 * </ul>
 *
 * @author Danut Chindris
 * @see "The Java EE Architect's Handbook, Second Edition, Derek C. Ashmore"
 * @since July 29, 2015
 */
public class User implements Serializable, Comparable<User> {

    private static final long serialVersionUID = 0L;

    private long id;

    private String firstName;

    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
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
        final User other = (User) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.firstName, other.firstName)
                && Objects.equal(this.lastName, other.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.id, this.firstName, this.lastName);
    }

    @Override
    public int compareTo(User o) {
        return ComparisonChain.start()
                .compare(this.firstName, o.firstName)
                .compare(this.lastName, o.lastName)
                .result();
    }
}
