package scam.utils;

public interface Constants {
    String FIND_USER_BY_USERNAME_MESSAGE = "FINDING USER with USERNAME: [%s].";
    String CREATE_USER_MESSAGE = "CREATING USER with USERNAME: [%s].";
    String UPDATE_USER_BY_USERNAME_MESSAGE = "UPDATING USER with USERNAME: [%s].";
    String DELETE_USER_BY_USERNAME_MESSAGE = "DELETING USER with USERNAME: [%s].";
    String GET_ALL_USERS_MESSAGE = "GETTING ALL USERS.";
    String LOGIN_USER_MESSAGE="USER WITH NAME [%s] IS LOGGING.";
    String LOGOUT_USER_MESSAGE="USER LOGGING OUT";

    // RESTAURANT CONSTANTS
    String FIND_POST_BY_ID_MESSAGE = "FINDING POST with ID: [%s].";
    String CREATE_POST_MESSAGE = "CREATING POST with OWNER_USERNAME: [%s].";
    String UPDATE_POST_BY_ID_MESSAGE = "UPDATING POST with ID: [%s].";
    String DELETE_POST_BY_ID_MESSAGE = "DELETING POST with ID: [%s].";
    String GET_ALL_POSTS_MESSAGE = "GETTING ALL POST.";
    String GET_ALL_POSTS_WITH_CATEGORY_MESSAGE = "GETTING ALL POST WITH CATEGORY [%s].";

    // PRODUCTS CONSTANTS
    String FIND_PIC_BY_ID_MESSAGE = "FINDING PIC with ID: [%s].";
    String CREATE_PIC_MESSAGE = "CREATING PIC: WITH POST ID [%s].";
    String UPDATE_PIC_BY_ID_MESSAGE = "UPDATING PIC with ID: [%s].";
    String DELETE_PIC_BY_ID_MESSAGE = "DELETING PIC with ID: [%s].";
    String GET_ALL_PIC_MESSAGE = "GETTING ALL PICS.";

    // ORDER CONSTANTS
    String FIND_THUMBNAIL_BY_ID_MESSAGE = "FINDING THUMBNAIL with ID: [%s].";
    String CREATE_THUMBNAIL_MESSAGE = "CREATING THUMBNAIL WITH POST ID: [%s]";
    String UPDATE_THUMBNAIL_BY_ID_MESSAGE = "UPDATING THUMBNAIL with ID: [%s].";
    String DELETE_THUMBNAIL_BY_ID_MESSAGE = "DELETING THUMBNAIL with ID: [%s].";
    String GET_ALL_THUMBNAILS_MESSAGE = "GETTING ALL ORDERS.";

    // SEARCH CONSTANTS
    String FIND_COMMENT_BY_ID_MESSAGE = "FINDING COMMENT with ID: [%s].";
    String CREATE_COMMENT_MESSAGE = "CREATING COMMENT with USERNAME: [%s].";
    String UPDATE_COMMENT_BY_ID_MESSAGE = "CREATING COMMENT with ID: [%s].";
    String DELETE_COMMENT_BY_ID_MESSAGE = "DELETING COMMENT with ID: [%s].";
    String GET_ALL_COMMENTS_MESSAGE = "GETTING ALL COMMENTS.";


    // TRIP EXCEPTIONS CONSTANTS
    String POST_NOT_FOUND_MESSAGE = "Cannot find POST with ID [%s].";

    // USER EXCEPTIONS CONSTANTS
    String USER_NOT_FOUND_MESSAGE="Cannot find USER with USERNAME [%s].";

    // CAR EXCEPTIONS CONSTANTS
    String PIC_NOT_FOUND_MESSAGE="Cannot find PIC WITH ID [%s].";

    //IMAGE EXCEPTIONS CONSTANTS
    String THUMBNAIL_NOT_FOUND_EXCEPTION="Cannot find THUMBNAIL with ID [%s].";

    //IMAGE EXCEPTIONS CONSTANTS
    String COMMENT_NOT_FOUND_EXCEPTION="Cannot find COMMENT with ID [%s].";

    String PICTURES_WITH_SAME_URL_EXCEPTION_MESSAGE="CANNOT ADD PICTURES WITH SAME URLS";
    String CREATE_FACEBOOK_USER_MESSAGE="Creating facebook USER with USERNAME [%s]";

    String CREATE_GUEST_USER_MESSAGE = "Creating guest user";

    String DATABASE_ERROR_MESSAGE = "Database error occurred.";
    String NOT_FOUND_MESSAGE = "Resource was not found";
    String BAD_REQUEST_MESSAGE = "Invalid input was given";
    String CONFLICT_CREATE_MESSAGE = "Conflict while creating entity";
    String EXISTING_RESOURCE_MESSAGE = "Resource already exists.";
    String CONFLICT_DELETE_MESSAGE = "Entity  not allowed to be deleted";
    String UNAUTHORIZED_MESSAGE= "Unauthorized request was given.";
    String CONFIRM_PASS_DOES_NOT_MATCH_MESSAGE="Confirm pass does not match user password.";
    String INVALID_CREDENTIALS_MESSAGE="Invalid credentials.";
    String USERNAME_NOT_EQUAL_MESSAGE="Username cannot be changed.";
    String BORN_DATE_NOT_EQAL_MESSAGE="Born Date cannot be changed.";
    String EMAIL_NOT_EQUAL_MESSAGE="Email cannot be changed.";
    String ROLE_NOT_EQUAL_MESSAGE="Role cannot be changed.";

    String EXISTING_EMAIL_MESSAGE="Email [%s] already exists.";
    String EXISTING_USERNAME_MESSAGE="Username [%s] already exists.";

    String PASSWORD_RESET_MESSAGE="Resetting password on user with email [%s]";

    String EMAIL_NOT_FOUND_MESSAGE = "Email [%s] was not found";

    String RESTAURANT_NOT_FOUND_MESSAGE="Restaurant with id [%s] was not found.";
    String ORDER_NOT_FOUND_MESSAGE="Order with id [%s] was not found.";

    String RESET_PASSWORD_SENT_MESSAGE="Sending new password TOKEN [%s] to USER [%s] with EMAIL [%s]";
}
