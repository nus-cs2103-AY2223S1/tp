package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;

/**
 * Adds an application to CinternS.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an application to CinternS. "
            + "Parameters: "
            + PREFIX_COMPANY + "COMPANY"
            + PREFIX_CONTACT + "CONTACT "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_DATE + "DATE"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "ByteDance "
            + PREFIX_CONTACT + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_POSITION + "Software Engineer (Front-End) "
            + PREFIX_DATE + "2022-10-09";

    public static final String MESSAGE_SUCCESS = "New application added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application already exists in CinternS";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview has clashed with another interview "
            + "exists in CinternS";


    private final Application toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Application}
     */
    public AddCommand(Application application) {
        requireNonNull(application);
        toAdd = application;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplication(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        } else if (model.hasSameInterviewTime(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        model.addApplication(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
