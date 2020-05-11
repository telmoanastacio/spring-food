package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Telmo Silva on 11.05.2020.
 */

@Entity
@Table(name = "users_roles")
public class UserRole
{
    @EmbeddedId
    private UserRoleId userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private Role role;

    private UserRole() {}

    public UserRole(User user, Role role)
    {
        this.user = user;
        this.role = role;
        this.userRoleId = new UserRoleId(user.getId(), role.getId());
    }

    public UserRoleId getUserRoleId()
    {
        return userRoleId;
    }

    public User getUser()
    {
        return user;
    }

    public Role getRole()
    {
        return role;
    }

    public void setUserRoleId(UserRoleId userRoleId)
    {
        this.userRoleId = userRoleId;
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
        UserRole that = (UserRole) o;
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
        return "UserRole{" +
                "userRoleId=" + userRoleId +
                '}';
    }
}