package seedu.address.logic.commands.profile;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.Profile;

/**
 * Adds a profile to the NUScheduler.
 */
public class AddProfileCommand extends ProfileCommand {

    public static final String COMMAND_OPTION = "a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Adds a profile to the NUScheduler.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE_NUMBER "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM_USERNAME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@u.nus.edu "
            + PREFIX_TELEGRAM + "johndoe "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New profile added:\n%1$s";
    public static final String MESSAGE_HELP = "Adds a profile to NUScheduler.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM USERNAME] "
            + "[" + PREFIX_TAG + "TAG]...";

    private final Profile toAdd;

    /**
     * Creates an AddProfileCommand to add the specified {@code Profile}
     */
    public AddProfileCommand(Profile profile) {
        requireNonNull(profile);
        toAdd = profile;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEmail(toAdd)) {
            throw new CommandException(MESSAGE_SIMILAR_EMAIL);
        }

        if (model.hasPhone(toAdd)) {
            throw new CommandException(MESSAGE_SIMILAR_PHONE);
        }

        if (model.hasTelegram(toAdd)) {
            throw new CommandException(MESSAGE_SIMILAR_TELEGRAM);
        }

        model.addProfile(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProfileCommand // instanceof handles nulls
                && toAdd.equals(((AddProfileCommand) other).toAdd));
    }
}
