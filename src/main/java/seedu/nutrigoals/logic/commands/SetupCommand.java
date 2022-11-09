package seedu.nutrigoals.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_IDEAL_WEIGHT;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.nutrigoals.logic.commands.exceptions.CommandException;
import seedu.nutrigoals.model.Model;
import seedu.nutrigoals.model.user.User;


/**
 * Sets up user profile in NutriGoals
 */
public class SetupCommand extends Command {

    public static final String COMMAND_WORD = "setup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets up the users profile for NutriGoals. \n"
            + "Parameters: "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_WEIGHT + "WEIGHT "
            + PREFIX_IDEAL_WEIGHT + "IDEAL_WEIGHT "
            + PREFIX_AGE + "AGE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GENDER + "M "
            + PREFIX_HEIGHT + "170 "
            + PREFIX_WEIGHT + "70 "
            + PREFIX_IDEAL_WEIGHT + "65 "
            + PREFIX_AGE + "20";

    public static final String MESSAGE_SUCCESS = "Successfully set up user profile\n %s";

    private final User user;

    /**
     * Creates a SetupCommand for the user
     * @param user
     */
    public SetupCommand(User user) {
        requireNonNull(user);
        this.user = user;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setUserDetails(user);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getUserDetails())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SetupCommand
                && this.user.equals(((SetupCommand) other).user));
    }
}
