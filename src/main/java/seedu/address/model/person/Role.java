package seedu.address.model.person;

public class Role {
    private final String role;
    public static final String MESSAGE_CONSTRAINTS =
            "Roles should only contain alphanumeric characters and spaces, and it should not be blank";
    
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static boolean isValidRole(String roleString) {
        return roleString.matches(VALIDATION_REGEX);
    }
}
