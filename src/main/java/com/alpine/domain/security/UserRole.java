package com.alpine.domain.security;

import com.alpine.domain.User;
import javax.persistence.*;

@Entity
@Table(name="user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    // The unique identifier for the user role.
    private Long userRoleId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    // The User entity associated with this user role.
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    // The Role entity associated with this user role.
    private Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
    public UserRole() {}

    // Getter and setters
    public Long getUserRoleId() {
        return userRoleId;
    }
    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}

