package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.AppointmentContainsKeywordsPredicate;
import seedu.address.model.person.AppointmentOfFilteredPersonsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons and their appointments in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "filter";
    public static final String DESCRIPTOR_WORD = "appts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all appointments of a specific tag "
            + "or appointments which are in a specific date"
            + "Parameters: " + PREFIX_DATE + "DATE\n"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + DESCRIPTOR_WORD + " alice bob charlie";

    private final AppointmentContainsKeywordsPredicate predicate;

    public FilterAppointmentsCommand(AppointmentContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredAppointmentList(predicate);
//        List<Person> validPersons = model.getFilteredPersonList();
//        AppointmentOfFilteredPersonsPredicate appointmentPredicate =
//                new AppointmentOfFilteredPersonsPredicate(validPersons);
//        model.updateFilteredAppointmentList(appointmentPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_RESULTS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(),
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterAppointmentsCommand // instanceof handles nulls
                && predicate.equals(((FilterAppointmentsCommand) other).predicate)); // state check
    }
}
