package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientTypePredicate;

/**
 * Lists all outpatients in the address book to the user.
 */
public class GetOutpatientCommand extends GetCommand {

    public static final String OUTPATIENT_PREFIX = "/outp";

    private final PatientTypePredicate predicate = new PatientTypePredicate(PatientType.PatientTypes.OUTPATIENT);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
