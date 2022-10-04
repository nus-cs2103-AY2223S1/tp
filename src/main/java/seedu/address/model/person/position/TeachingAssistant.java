package seedu.address.model.person.position;

public class TeachingAssistant extends Position {

    public TeachingAssistant() {
        super(1);
    }

    @Override
    public String toString() {
        return "Teaching Assistant";
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashcode() {
        return 0;
    }
}
