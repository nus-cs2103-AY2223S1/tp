package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.AppointmentOfFilteredPersonsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons and their appointments in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterPatientsCommand extends Command {

    public static final String COMMAND_WORD = "filter";
    public static final String DESCRIPTOR_WORD = "patients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons whose names or tags contain any of "
            + "the specified keywords (case-insensitive) and displays them and their appointments as 2 lists with "
            + "index numbers. Also able to filter by dates and \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + DESCRIPTOR_WORD + " alice bob charlie";

    private Predicate<Person> predicate;

    public FilterPatientsCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        List<Person> validPersons = model.getFilteredPersonList();
        AppointmentOfFilteredPersonsPredicate appointmentPredicate =
                new AppointmentOfFilteredPersonsPredicate(validPersons);
        model.updateFilteredAppointmentList(appointmentPredicate);


        return new CommandResult(
                String.format(Messages.MESSAGE_RESULTS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(),
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPatientsCommand // instanceof handles nulls
                && predicate.equals(((FilterPatientsCommand) other).predicate)); // state check
    }
}
