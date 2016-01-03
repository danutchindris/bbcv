package ro.leje.model.entity;

import javax.persistence.*;

/**
 * An entity which describes a permission that a {@link RoleEntity} can have.
 * Roles can have several of these permissions.
 *
 * @author Danut Chindris
 * @since September 23, 2015
 */
@Entity
@Table(name = "PERMISSION")
public class PermissionEntity {

    @Id
    @Column(name = "PERMISSION_ID", unique = true, nullable = false)
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
