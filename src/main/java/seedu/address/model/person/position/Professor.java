package seedu.address.model.person.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

import seedu.address.model.tag.Tag;

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

    private String role;

    /**
     * Creates a professor. Its roles at initiation are empty.
     */
    public Professor() {
        super("Professor");
        role = "Unassigned";
    }

    /**
     * Creates a professor with the given details.
     * @param role of the professor
     */
    public Professor(String role) {
        super("Professor");
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);

        this.role = role;
    }

    @Override
    public void setDetails(String role) {
        requireNonNull(role);
        this.role = role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        requireNonNull(test);
        for (Role role: Role.values()) {
            if (role.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toShow() {
        return "Role: " + role;
    }

    @Override
    public String toString() {
        return "Professor: " + role;
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
        return role;
    }

    @Override
    public void setFilePath(Set<Tag> modelTags) {

    }
}
