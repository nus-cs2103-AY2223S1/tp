package seedu.address.model.person;

/**
 * Enum to represent the type of person someone is
 */
public enum PersonType {
    PATIENT("Patient"), NURSE("Nurse");

    private String personTypeName;

    private PersonType(String name) {
        this.personTypeName = name;
    }

    @Override
    public String toString() {
        return personTypeName;
    }

}
