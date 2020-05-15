package com.tsilva.springFood.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 11.05.2020.
 */

@Embeddable
public class UsersRolesId implements Serializable
{
    private static final long serialVersionUID = 42865132230001L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    private UsersRolesId() {}

    public UsersRolesId(Long userId, Long roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
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
        UsersRolesId that = (UsersRolesId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, roleId);
    }

    @Override
    public String toString()
    {
        return "UsersRolesId{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
