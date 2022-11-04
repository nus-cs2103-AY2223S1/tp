package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Remark;
import seedu.address.model.tag.Tag;

/**
 * Filters and lists patients by name, phone, email, address, remark, and/or tag(s) according to the prefix input(s)
 * by the user.
 */
public class FindPatientCommand extends Command {

    public static final CommandWord COMMAND_WORD =
            new CommandWord("findpatient", "fp");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients whose name/phone/email/address"
            + "/tag/remark (according to prefix input) contains any of the specified inputs and displays them as "
            + "a list with index numbers.\n"
            + "Multiple prefixes can be used at once to filter patients, but only the tag prefix (t/) can be used "
            + "more than once in one command.\n"
            + "Inputs can have letters, numbers and/or special characters depending on constraints of a field.\n"
            + "Parameters: prefix(s), INPUT [MORE_INPUTS]...\n"
            + "Example: " + COMMAND_WORD + " n/alex \n"
            + "Example: " + COMMAND_WORD + " p/91234567 \n"
            + "Example: " + COMMAND_WORD + " e/example \n"
            + "Example: " + COMMAND_WORD + " a/Blk 123 \n"
            + "Example: " + COMMAND_WORD + " t/friends \n"
            + "Example: " + COMMAND_WORD + " t/friends t/colleagues \n"
            + "Example: " + COMMAND_WORD + " r/swim \n"
            + "Example: " + COMMAND_WORD + " n/alex p/91234567 e/example a/Blk 123 t/friends r/swim";

    private final Predicate<Patient> predicate;
    private Optional<Predicate<Name>> namePredicate = null;
    private Optional<Predicate<Phone>> phonePredicate = null;
    private Optional<Predicate<Email>> emailPredicate = null;
    private Optional<Predicate<Address>> addressPredicate = null;
    private Optional<Predicate<Remark>> remarkPredicate = null;
    private Optional<Predicate<Set<Tag>>> tagPredicate = null;

    /**
     * Creates a FindPatientCommand to find patients(s) according to the prefix input(s).
     *
     * @param namePredicate Optional predicate to filter patients by name.
     * @param phonePredicate Optional predicate to filter patients by phone.
     * @param emailPredicate Optional predicate to filter patients by email.
     * @param addressPredicate Optional predicate to filter patients by address.
     * @param tagPredicate Optional predicate to filter patients by tag(s).
     * @param remarkPredicate Optional predicate to filter patients by remark.
     */
    public FindPatientCommand(Optional<Predicate<Name>> namePredicate, Optional<Predicate<Phone>> phonePredicate,
                              Optional<Predicate<Email>> emailPredicate, Optional<Predicate<Address>> addressPredicate,
                              Optional<Predicate<Set<Tag>>> tagPredicate, Optional<Predicate<Remark>> remarkPredicate) {
        this.namePredicate = namePredicate;
        this.phonePredicate = phonePredicate;
        this.emailPredicate = emailPredicate;
        this.addressPredicate = addressPredicate;
        this.tagPredicate = tagPredicate;
        this.remarkPredicate = remarkPredicate;
        this.predicate = patient -> namePredicate.orElse(x -> true).test(patient.getName())
                && phonePredicate.orElse(x -> true).test(patient.getPhone())
                && emailPredicate.orElse(x -> true).test(patient.getEmail())
                && addressPredicate.orElse(x -> true).test(patient.getAddress())
                && tagPredicate.orElse(x -> true).test(patient.getTags())
                && remarkPredicate.orElse(x -> true).test(patient.getRemark());
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPatientCommand // instanceof handles nulls
                && namePredicate.equals(((FindPatientCommand) other).namePredicate)
                && phonePredicate.equals(((FindPatientCommand) other).phonePredicate)
                && emailPredicate.equals(((FindPatientCommand) other).emailPredicate)
                && addressPredicate.equals(((FindPatientCommand) other).addressPredicate)
                && remarkPredicate.equals(((FindPatientCommand) other).remarkPredicate)
                && tagPredicate.equals(((FindPatientCommand) other).tagPredicate)); // state check
    }

    public Predicate<Patient> getPredicate() {
        return predicate;
    }
}
