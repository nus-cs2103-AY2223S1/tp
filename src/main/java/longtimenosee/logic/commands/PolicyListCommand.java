package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.model.Model.PREDICATE_SHOW_ALL_POLICIES;

import longtimenosee.model.Model;

/**
 * Lists all Clients in the LTNS to the user.
 */
public class PolicyListCommand extends Command {

    public static final String COMMAND_WORD = "allPolicies";

    public static final String MESSAGE_SUCCESS = "Listed all policies in LTNS.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPolicyList(PREDICATE_SHOW_ALL_POLICIES);
        return new CommandResult(MESSAGE_SUCCESS, true, false, false);
    }
}
