package ro.leje.model.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Value object that should be accessible to the outside world.
 * We don't expose entities as they pertain to the data access layer.
 * <br />
 * This is the reference for implementing value objects across the application.
 * Following there is the checklist for implementation requirements:
 * <p>
 * <ul>
 * <li>Always implement java.io.Serializable</li>
 * <li>Always populate all fields of a value object</li>
 * <li>Always type fields accurately</li>
 * <li>Check dependence on third-party classes in value objects</li>
 * <li>Always override method {@link Object#toString()}</li>
 * <li>Consider overriding methods {@link Object#equals(Object)} and {@link Object#hashCode()}</li>
 * <li>Consider implementing {@link Comparable}</li>
 * </ul>
 *
 * @author Danut Chindris
 * @see "The Java EE Architect's Handbook, Second Edition, Derek C. Ashmore"
 * @since July 29, 2015
 */
public class User implements Serializable, Comparable<User> {

    private static final long serialVersionUID = 1L;

    private long id;

    @Size(min = 3, max = 20, message = "{error.user.name.size}")
    @NotEmpty(message = "{error.user.name.empty}")
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    @Email(message = "{error.email}")
    private String email;

    private Boolean enabled = false;

    public User() {
    }

    public User(long id, String userName, String firstName, String lastName, String email, Boolean enabled) {
        this.id = id;
        this.userName = userName;
        this.password = "";
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
    }

    public User(long id, String userName, String password, String firstName, String lastName, String email, Boolean enabled) {
        this(id, userName, firstName, lastName, email, enabled);
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("userName", userName)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("enabled", enabled)
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
                && Objects.equal(this.userName, other.userName)
                && Objects.equal(this.firstName, other.firstName)
                && Objects.equal(this.lastName, other.lastName)
                && Objects.equal(this.email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.id, this.userName, this.firstName, this.lastName, this.email);
    }

    @Override
    public int compareTo(User o) {
        return ComparisonChain.start()
                .compare(this.userName, o.userName)
                .compare(this.firstName, o.firstName)
                .compare(this.lastName, o.lastName)
                .compare(this.email, o.email)
                .result();
    }
}
