package seedu.address.model.person;

/**
 * Represents the possible fields of a {@code Person} to sort by.
 */
public enum PersonSortFieldType {
    NAME("name"),
    DATE_OF_BIRTH("date of birth"),
    GENDER("gender"),
    NO_FIELD("no field");


    private final String value;


    private PersonSortFieldType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
