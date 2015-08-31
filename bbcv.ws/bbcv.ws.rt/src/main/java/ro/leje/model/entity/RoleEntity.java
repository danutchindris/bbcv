package ro.leje.model.entity;

import javax.persistence.*;

/**
 * An entity which describes a role that a {@link UserEntity} user can have.
 * Users can have several of these roles.
 *
 * @author Danut Chindris
 * @since August 2, 2015
 */
@Entity
@Table(name = "ROLE")
public class RoleEntity {

    @Id
    @Column(name = "ROLE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String name;

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
}
