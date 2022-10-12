package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_CERTIFICATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_OWNER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_VACCINATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_WEIGHT;

public class AddPetCommand {
    public static final String COMMAND_WORD = "add-p";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a standalone pet or an order affiliated to an order or a person. "
            + "Parameters: "
            + PREFIX_PET_OWNER + "OWNER "
            + PREFIX_PET_NAME + "NAME "
            + PREFIX_PET_DATE_OF_BIRTH + "DATE_OF_BIRTH "
            + PREFIX_PET_COLOR + "COLOR "
            + PREFIX_PET_COLOR_PATTERN + "COLOR_PATTERN "
            + PREFIX_PET_HEIGHT + "HEIGHT "
            + PREFIX_PET_SPECIES + "SPECIES "
            + PREFIX_PET_VACCINATION_STATUS + "VACCINATION_STATUS "
            + PREFIX_PET_WEIGHT + "WEIGHT "
            + "[" + PREFIX_PET_CERTIFICATE + "CERTIFICATE]...\n"
            + "[" + PREFIX_PET_TAG + "TAG]...\n"

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PET_OWNER + "Felix Huang "
            + PREFIX_PET_NAME + "Wu Lezheng "
            + PREFIX_PET_DATE_OF_BIRTH + "2001-11-20 "
            + PREFIX_PET_COLOR + "red "
            + PREFIX_PET_COLOR_PATTERN + "stripped "
            + PREFIX_PET_HEIGHT + "39.5 "
            + PREFIX_PET_SPECIES + "chihuahua "
            + PREFIX_PET_VACCINATION_STATUS + "true "
            + PREFIX_PET_WEIGHT + "15.3 "
            + PREFIX_PET_CERTIFICATE + "Good-Dog Cert."
            + PREFIX_PET_CERTIFICATE + "Royal Blood Cert."
            + PREFIX_PET_TAG + "naughty"
            + PREFIX_PET_TAG + "sold\n";
}
