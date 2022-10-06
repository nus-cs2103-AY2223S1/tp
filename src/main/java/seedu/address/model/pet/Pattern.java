package seedu.address.model.pet;

import seedu.address.model.person.Person;

public class Pattern {

    private final String pattern;

    public Pattern(String pattern) {
        if (pattern == null) {
            this.pattern = "";
        } else {
            this.pattern = pattern;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Person) {
            Pattern otherPattern = (Pattern) other;
            return pattern.equals(otherPattern.pattern);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return pattern.hashCode();
    }

    @Override
    public String toString() {
        return pattern;
    }

}
