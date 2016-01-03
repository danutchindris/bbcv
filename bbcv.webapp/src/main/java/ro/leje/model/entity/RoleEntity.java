package ro.leje.model.entity;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(targetEntity = PermissionEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = {
            @JoinColumn(name = "ROLE_ID", nullable = false)
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "PERMISSION_ID")
            })
    private Set<PermissionEntity> permissions;

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

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }
}
