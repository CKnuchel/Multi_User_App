package com.wiss.m223.Config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wiss.m223.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * UserDetailsImpl ist eine Implementierung des UserDetails-Interfaces von Spring Security.
 * Es enthält Details über den Benutzer, die für die Authentifizierung und Autorisierung benötigt werden.
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Konstruktor für UserDetailsImpl.
     * @param id - die ID des Benutzers.
     * @param username - der Benutzername des Benutzers.
     * @param email - die E-Mail des Benutzers.
     * @param password - das Passwort des Benutzers.
     * @param authorities - die Rollen des Benutzers.
     */
    public UserDetailsImpl(Long id, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Erstellt ein UserDetailsImpl-Objekt aus einem User-Objekt.
     * @param user - das User-Objekt.
     * @return UserDetailsImpl - das erstellte UserDetailsImpl-Objekt.
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                (long) user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Überprüft, ob das übergebene Objekt gleich dem aktuellen Objekt ist.
     * @param o - das zu vergleichende Objekt.
     * @return boolean - true, wenn die Objekte gleich sind, sonst false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}