package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.Application;

/**
 * Adds an application to CinternS.
 */
public class ApplicationAddCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an application to CinternS. "
            + "Parameters: "
            + PREFIX_COMPANY + "COMPANY"
            + PREFIX_CONTACT + "CONTACT "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_POSITION + "POSITION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "ByteDance "
            + PREFIX_CONTACT + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_POSITION + "Software Engineer (Front-End) "
            + PREFIX_TAG + "first choice "
            + PREFIX_TAG + "expected salary: 6000";

    public static final String MESSAGE_SUCCESS = "New application added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "This application is already exists in CinternS";

    private final Application toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Application}
     */
    public ApplicationAddCommand(Application application) {
        requireNonNull(application);
        toAdd = application;
    }

    @Override
    public CommandResult execute(ApplicationModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplication(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.addApplication(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicationAddCommand // instanceof handles nulls
                && toAdd.equals(((ApplicationAddCommand) other).toAdd));
    }
}
