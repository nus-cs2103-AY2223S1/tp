package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUBUSERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICEHOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.StringJoiner;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Professor;

/**
 * Adds a Professor to the address book.
 */
public class ProfCommand extends Command {

    public static final String COMMAND_WORD = "prof";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a professor to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + "[" + PREFIX_SPECIALISATION + "SPECIALISATION] "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GENDER + "GENDER "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_GITHUBUSERNAME + "GITHUB_USERNAME] "
            + "[" + PREFIX_RATING + "RATING]"
            + "[" + PREFIX_OFFICEHOUR + "OFFICE_HOUR]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Wong Tin Lok "
            + PREFIX_MODULE_CODE + "CS1231S "
            + PREFIX_SPECIALISATION + "Discrete Math "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "wongTK@example.com "
            + PREFIX_GENDER + "M "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_LOCATION + "COM2 LT4 "
            + PREFIX_GITHUBUSERNAME + "WongWong "
            + PREFIX_RATING + "5 "
            + PREFIX_OFFICEHOUR + "2-12:00-2";

    public static final String MESSAGE_DUPLICATE_PERSON = "This Professor already exists in the address book";

    public static final String MESSAGE_SUCCESS = "New professor added: %1$s";

    public static final String PROF_TEMPLATE = new StringJoiner(" ").add(COMMAND_WORD)
        .add(PREFIX_NAME.getPrefix())
        .add(PREFIX_MODULE_CODE.getPrefix())
        .add(PREFIX_SPECIALISATION.getPrefix())
        .add(PREFIX_PHONE.getPrefix())
        .add(PREFIX_EMAIL.getPrefix())
        .add(PREFIX_GENDER.getPrefix())
        .add(PREFIX_TAG.getPrefix())
        .add(PREFIX_LOCATION.getPrefix())
        .add(PREFIX_GITHUBUSERNAME.getPrefix())
        .add(PREFIX_RATING.getPrefix())
        .add(PREFIX_OFFICEHOUR.getPrefix())
        .toString();

    private final Person toAdd;

    /**
     * Creates a ProfCommand to add the specified {@code Professor}
     */
    public ProfCommand(Professor toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        model.updatePieChart();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ProfCommand // instanceof handles nulls
            && toAdd.equals(((ProfCommand) other).toAdd));
    }
}
