package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tuthub.logic.parser.CliSyntax.PREFIX_MODULE;
import static tuthub.logic.parser.CliSyntax.PREFIX_NAME;
import static tuthub.logic.parser.CliSyntax.PREFIX_PHONE;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tuthub.logic.parser.CliSyntax.PREFIX_TAG;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;
import static tuthub.logic.parser.CliSyntax.PREFIX_YEAR;

import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.tutor.Tutor;

/**
 * Adds a tutor to tuthub.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutor to tuthub. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_MODULE + "MODULE]... "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_STUDENTID + "STUDENT ID "
            + PREFIX_TEACHINGNOMINATION + "TEACHING NOMINATIONS "
            + PREFIX_RATING + "RATING "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "E1234567@u.nus.edu "
            + PREFIX_MODULE + "CS2100 "
            + PREFIX_YEAR + "3 "
            + PREFIX_STUDENTID + "A0123456X "
            + PREFIX_TEACHINGNOMINATION + "1 "
            + PREFIX_RATING + "5.0 "
            + PREFIX_TAG + "senior ";

    public static final String MESSAGE_SUCCESS = "New tutor added: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTOR = "This tutor already exists in tuthub";

    private final Tutor toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Tutor}
     */
    public AddCommand(Tutor tutor) {
        requireNonNull(tutor);
        toAdd = tutor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutor(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTOR);
        }

        model.addTutor(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
