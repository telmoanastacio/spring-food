package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 11.05.2020.
 */

@Entity
@Table(name = "users_roles")
public class UsersRoles implements Serializable
{
    private static final long serialVersionUID = 42865132230002L;

    @EmbeddedId
    private UsersRolesId usersRolesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private Role role;

    private UsersRoles() {}

    public UsersRoles(User user, Role role)
    {
        this.user = user;
        this.role = role;
        this.usersRolesId = new UsersRolesId(user.getId(), role.getId());
    }

    public UsersRolesId getUsersRolesId()
    {
        return usersRolesId;
    }

    public User getUser()
    {
        return user;
    }

    public Role getRole()
    {
        return role;
    }

    public void setUsersRolesId(UsersRolesId usersRolesId)
    {
        this.usersRolesId = usersRolesId;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        UsersRoles that = (UsersRoles) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(user, role);
    }

    @Override
    public String toString()
    {
        return "UsersRoles{" +
                "usersRolesId=" + usersRolesId +
                '}';
    }
}