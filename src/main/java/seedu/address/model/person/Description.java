package seedu.address.model.person;

public class Description {

    public String description;

    public Description(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
