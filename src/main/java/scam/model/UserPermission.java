package scam.model;


public enum UserPermission {

    USER_GET_ALL("user:get:all"),
    USER_GET_ONE("user:get:one"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),

    THUMBNAIL_GET_ALL("THUMBNAIL:get:all"),
    THUMBNAIL_GET_ONE("THUMBNAIL:get:one"),
    THUMBNAIL_CREATE("THUMBNAIL:create"),
    THUMBNAIL_UPDATE("THUMBNAIL:delete"),
    THUMBNAIL_DELETE("THUMBNAIL:delete"),

    POST_GET_ALL("POST:get:all"),
    POST_GET_ONE("POST:get:one"),
    POST_CREATE("POST:create"),
    POST_UPDATE("POST:delete"),
    POST_DELETE("POST:delete"),

    COMMENT_GET_ALL("COMMENT:get:all"),
    COMMENT_GET_ONE("COMMENT:get:one"),
    COMMENT_CREATE("COMMENT:create"),
    COMMENT_UPDATE("COMMENT:delete"),
    COMMENT_DELETE("COMMENT:delete"),

    PIC_GET_ALL("PIC:get:all"),
    PIC_GET_ONE("PIC:get:one"),
    PIC_CREATE("PIC:create"),
    PIC_UPDATE("PIC:delete"),
    PIC_DELETE("PIC:delete");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
