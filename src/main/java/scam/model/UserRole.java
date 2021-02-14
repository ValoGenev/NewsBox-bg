package scam.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static scam.model.UserPermission.*;

public enum UserRole {

    USER(new HashSet<>(Arrays.asList(
            USER_GET_ONE,
            USER_GET_ALL,
            USER_UPDATE,
            POST_GET_ONE,
            COMMENT_CREATE,
            COMMENT_DELETE,
            COMMENT_UPDATE
    ))),
    ADMIN(new HashSet<>(Arrays.asList(
            USER_GET_ONE,
            USER_GET_ALL,
            USER_DELETE,
            USER_CREATE,
            USER_UPDATE,

            COMMENT_GET_ONE,
            COMMENT_GET_ALL,
            COMMENT_CREATE,
            COMMENT_DELETE,
            COMMENT_UPDATE,

            POST_GET_ALL,
            POST_GET_ONE,
            POST_DELETE,
            POST_CREATE,
            POST_UPDATE,

            THUMBNAIL_CREATE,
            THUMBNAIL_DELETE,
            THUMBNAIL_GET_ALL,
            THUMBNAIL_UPDATE,
            THUMBNAIL_GET_ONE,

            PIC_CREATE,
            PIC_UPDATE,
            PIC_GET_ALL,
            PIC_GET_ONE,
            PIC_DELETE
    )));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return grantedAuthorities;
    }
}