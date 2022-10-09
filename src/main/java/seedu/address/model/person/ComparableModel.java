package seedu.address.model.person;

/**
 * Represents category which values can be compared
 */
public abstract class ComparableModel {

    /**
     * Compares the values of the category
     */
    public abstract int compareTo(ComparableModel o);

}
