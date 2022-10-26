package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_CERTIFICATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_VACCINATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_WEIGHT;

/**
 * Adds a pet to the address book.
 */
public class AddPetCommand extends Command {
    public static final String COMMAND_WORD = "add-pt";

    public static final String COMMON_PARAMETERS =
            PREFIX_PET_NAME + "NAME "
                    + PREFIX_PET_DATE_OF_BIRTH + "DATE_OF_BIRTH "
                    + PREFIX_PET_COLOR + "COLOR "
                    + PREFIX_PET_COLOR_PATTERN + "COLOR_PATTERN "
                    + PREFIX_PET_HEIGHT + "HEIGHT "
                    + PREFIX_PET_SPECIES + "SPECIES "
                    + PREFIX_PET_VACCINATION_STATUS + "VACCINATION_STATUS "
                    + PREFIX_PET_WEIGHT + "WEIGHT "
                    + PREFIX_PET_PRICE + "PRICE"
                    + "[" + PREFIX_PET_CERTIFICATE + "CERTIFICATE]...\n"
                    + "[" + PREFIX_PET_TAG + "TAG]...\n"

                    + "Example: " + COMMAND_WORD + " ";

    public static final String COMMON_SAMPLE_PARAMETERS = PREFIX_PET_NAME + "Wu Lezheng "
            + PREFIX_PET_DATE_OF_BIRTH + "2001-11-20 "
            + PREFIX_PET_COLOR + "red "
            + PREFIX_PET_COLOR_PATTERN + "stripped "
            + PREFIX_PET_HEIGHT + "39.5 "
            + PREFIX_PET_SPECIES + "chihuahua "
            + PREFIX_PET_VACCINATION_STATUS + "true "
            + PREFIX_PET_WEIGHT + "15.3 "
            + PREFIX_PET_PRICE + "20"
            + PREFIX_PET_CERTIFICATE + "Good-Dog Cert. "
            + PREFIX_PET_CERTIFICATE + "Royal Blood Cert. "
            + PREFIX_PET_TAG + "naughty "
            + PREFIX_PET_TAG + "sold\n";

    public static final String MESSAGE_USAGE_EXISTING_SUPPLIER = COMMAND_WORD
            + ": Adds a new pet to an existing supplier. "
            + "Parameters: "
            + PREFIX_INDEX + "INDEX "
            + COMMON_PARAMETERS
            + PREFIX_INDEX + "1 "
            + COMMON_SAMPLE_PARAMETERS;

    public static final String MESSAGE_USAGE_NEW_SUPPLIER = COMMAND_WORD
            + ": Adds a pet when adding a supplier. "
            + "Parameters: "
            + COMMON_PARAMETERS
            + COMMON_SAMPLE_PARAMETERS;


    public static final String MESSAGE_DUPLICATE_PET = "This pet already exists in the buyer list";

}
