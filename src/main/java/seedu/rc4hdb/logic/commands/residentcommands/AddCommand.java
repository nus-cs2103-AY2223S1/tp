package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;

/**
 * Adds a person to RC4HDB .
 */
public class AddCommand implements ModelCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a resident to RC4HDB. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ROOM + "ROOM "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_HOUSE + "HOUSE "
            + PREFIX_MATRIC_NUMBER + "MATRIC_NUMBER "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ROOM + "03-08 "
            + PREFIX_GENDER + "M "
            + PREFIX_HOUSE + "D "
            + PREFIX_MATRIC_NUMBER + "A0000000A "
            + PREFIX_TAG + "BlockHead ";

    public static final String MESSAGE_SUCCESS = "New resident added: %1$s";
    public static final String MESSAGE_DUPLICATE_RESIDENT = "This resident already exists in RC4HDB";

    private final Resident toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Resident}
     */
    public AddCommand(Resident resident) {
        requireNonNull(resident);
        toAdd = resident;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasResident(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RESIDENT);
        }

        model.addResident(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
