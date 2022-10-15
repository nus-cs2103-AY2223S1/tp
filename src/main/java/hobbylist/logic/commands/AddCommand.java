package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.logic.parser.CliSyntax;
import hobbylist.model.Model;
import hobbylist.model.activity.Activity;

/**
 * Adds an activity to the HobbyList.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an activity to the HobbyList. "
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "And Then There Were None "
            + CliSyntax.PREFIX_DESCRIPTION + "mystery book by Agatha Christie "
            + CliSyntax.PREFIX_TAG + "book "
            + CliSyntax.PREFIX_TAG + "entertainment"
            + CliSyntax.PREFIX_DATE + "2003-03-03";;

    public static final String MESSAGE_SUCCESS = "New activity added: %1$s";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "This activity already exists in the HobbyList";

    private final Activity toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Activity}
     */
    public AddCommand(Activity activity) {
        requireNonNull(activity);
        toAdd = activity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasActivity(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
        }

        model.addActivity(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
