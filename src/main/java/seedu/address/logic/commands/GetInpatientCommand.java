package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PatientType;
import seedu.address.model.person.PatientTypePredicate;

/**
 * Lists all inpatients in the address book to the user.
 */
public class GetInpatientCommand extends Command {

    public static final String COMMAND_WORD = "get";

    private final PatientTypePredicate predicate = new PatientTypePredicate(PatientType.PatientTypes.INPATIENT);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
