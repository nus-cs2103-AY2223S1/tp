package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.HiddenPredicateSingleton;

/**
 * Hides all persons and their appointments in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class HideAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "hide";
    public static final String DESCRIPTOR_WORD = "appts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Hides all appointments which reason or tags"
            + " contain any of the specified keywords (case-insensitive) and displays\n"
            + "the appointments which are not hidden. Also able to hide by marked/unmarked appointments\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + DESCRIPTOR_WORD + " " + PREFIX_TAG + "ear";

    private Predicate<Appointment> predicate;

    public HideAppointmentsCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Appointment> combinedPredicate = HiddenPredicateSingleton.combineWithApptPredicate(predicate);
        model.updateFilteredAppointmentList(combinedPredicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_RESULTS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size(),
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HideAppointmentsCommand)) {
            return false;
        }

        HideAppointmentsCommand otherCommand = (HideAppointmentsCommand) other;
        return otherCommand.predicate.equals(this.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predicate);
    }
}
