package seedu.address.logic.commands;

/**
 * Sorts the pet list.
 */
public class SortPetCommand {

    public static final String MESSAGE_SUCCESS = "pet list has been sorted successfully";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting pet list \n%2$s";
    public static final String MESSAGE_USAGE =
            "Acceptable order attributes are name, color, colorpattern, birthdate, species,"
                    + "vaccinationstatus, price, characteristics, certificate";
}
