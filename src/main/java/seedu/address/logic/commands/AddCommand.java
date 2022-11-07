package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNAVAILABLE_DATE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient/nurse to the address book. "
            + "Parameters: "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_NAME + "NAME "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]... \n"
            + "If add patient, you can choose to add details:  "
            + PREFIX_DATE_AND_SLOT + "HOME_VISIT_DATE_AND_SLOT \n"
            + "If add nurse, you can choose to add details: "
            + PREFIX_UNAVAILABLE_DATE + "UNAVAILABLE_DATE_TO_HOME_VISIT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "P "
            + PREFIX_NAME + "John Doe "
            + PREFIX_GENDER + "M "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_DATE_AND_SLOT + "2022-11-11,2";

    public static final String MESSAGE_INVALID_FIELD_NURSE = "The person to be added is a nurse, "
            + "should not have date and slot.";
    public static final String MESSAGE_INVALID_FIELD_PATIENT = "The person to be added is a patient, "
            + "should not have unavailable date.";

    public static final String MESSAGE_SUCCESS = "New %1$s added: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This %1$s already exists in the address book";
    public static final String MESSAGE_SIMILAR_PERSON = "This %1$s is similar someone we found"
            + " in the address book: %2$s. We have added them anyways.";
    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, toAdd.getCategoryIndicator()));
        }

        if (model.findSimilarPerson(toAdd).isPresent()) {
            model.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_SIMILAR_PERSON, toAdd.getCategoryIndicator(),
                    model.findSimilarPerson(toAdd).get()));
        }
        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getCategoryIndicator(), toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && toAdd.equals(((AddCommand) other).toAdd));
    }

}
