package shell.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;


public class Role implements GrantedAuthority {


    private Long id;

    private String role;


    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
    ///////
//    public Set<User> getUser() {
//        return user;
//    }
//
//    public void setUser(Set<User> user) {
//        this.user = user;
//    }
}
