package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMPANY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COVERAGES;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_EMAIL;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_INCOME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PHONE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_RISK_APPETITE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TAG;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.core.Messages;
import longtimenosee.model.Model;
import longtimenosee.model.person.Person;

/**
 * Finds and lists all Clients in address book who matches the given metrics.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "findClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients that match the given metrics and "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]… "
            + "[" + PREFIX_BIRTHDAY + "BIRTHDAY] "
            + "[" + PREFIX_INCOME + "INCOME] "
            + "[" + PREFIX_RISK_APPETITE + "RISK_APPETITE] "
            + "[" + PREFIX_TITLE + "POLICY_TITLE] "
            + "[" + PREFIX_COVERAGES + "POLICY_COVERAGE]… "
            + "[" + PREFIX_COMPANY + "POLICY_COMPANY]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "alice "
            + PREFIX_PHONE + "12341234";

    private final List<Predicate<Person>> predicates;

    /**
     * Creates a FindCommand object.
     *
     * @param predicates check if the specified metrics match any contact.
     */
    public FindCommand(List<Predicate<Person>> predicates) {
        assert predicates.size() > 0;
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Person> finalPredicate = predicates.get(0);
        for (Predicate<Person> predicate : predicates) {
            if (!predicate.equals(predicates.get(0))) {
                finalPredicate = finalPredicate.and(predicate);
            }
        }
        model.updateFilteredPersonList(finalPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
