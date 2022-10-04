package seedu.address.model.person.position;

public abstract class Position {

    public final int value;

    public static final String MESSAGE_CONSTRAINTS =
            "Positions should only contain integers from 0 to 2 inclusive";

    public Position(int value) {
        this.value = value;
    }

    public static boolean isValidPosition(int test) {
        return (test >= 0) && (test <= 2);
    }

    public static Position buildPosition(Integer position) {
        switch(position) {
            case 0:
                return new Student();
            case 1:
                return new TeachingAssistant();
            default:
                return new Professor();
        }
    }

    public abstract String toString();

    public abstract boolean equals(Object other);

    public abstract int hashcode();
}
