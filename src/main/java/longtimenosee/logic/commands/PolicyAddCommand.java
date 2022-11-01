package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMPANY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COVERAGES;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TITLE;

import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.policy.Policy;

/**
 * Adds a policy to the address book.
 */
public class PolicyAddCommand extends Command {
    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a policy to the LTNS. \n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            //Todo: Replace message with a command that lists out different companies
            + PREFIX_COMPANY + "COMPANY_CODE"
            + PREFIX_COMMISSION + "Year1% Year2% Remaining%"
            + "[" + PREFIX_COVERAGES + "COVERAGE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "PruShield "
            + PREFIX_COMPANY + "PRU "
            + PREFIX_COMMISSION + "15% 7.5% 1% "
            //Todo: Replace message with a command that lists out different coverages
            + PREFIX_COVERAGES + "HEALTH "
            + PREFIX_COVERAGES + "LIFE";

    public static final String MESSAGE_SUCCESS = "New policy added: %1$s";
    public static final String MESSAGE_DUPLICATE_POLICY = "This policy already exists in the address book";

    final Policy toAdd;

    /**
     * Creates an PolicyAddCommand to add the specified {@code Policy}
     *
     * @param policy
     */
    public PolicyAddCommand(Policy policy) {
        requireNonNull(policy);
        toAdd = policy;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPolicy(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_POLICY);
        }

        model.addPolicy(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyAddCommand // instanceof handles nulls
                && toAdd.equals(((PolicyAddCommand) other).toAdd));
    }
}
