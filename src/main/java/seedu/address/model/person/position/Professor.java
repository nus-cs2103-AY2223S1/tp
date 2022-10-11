package seedu.address.model.person.position;

import static java.util.Objects.requireNonNull;

/**
 * Represents the Professor position in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class Professor extends Position {
    /**
     * Roles of the Professor.
     */
    public enum Role {
        COORDINATOR,
        LECTURER,
        TUTOR,
        ADVISOR,
        UNASSIGNED;
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Role can only be Unassigned, Coordinator, Lecturer, Tutor, and/or Advisor (non case-sensitive)";

    private String roles;

    /**
     * Creates a professor. Its roles at initiation are empty.
     */
    public Professor() {
        super("Professor");
        roles = "Unassigned";

    }
    @Override
    public void setDetails(String roles) {
        requireNonNull(roles);
        this.roles = roles;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        for (Role role: Role.values()) {
            if (role.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Professor: " + roles;
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashcode() {
        return 0;
    }

    @Override
    public String getDetails() {
        return roles;
    }
}
