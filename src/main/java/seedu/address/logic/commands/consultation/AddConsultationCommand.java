package seedu.address.logic.commands.consultation;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tutorial.AddTutorialCommand;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

public class AddConsultationCommand extends Command {
    public static final String COMMAND_WORD = "add consult";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation to the ModQuik. "
            + "Parameters: "
            + PREFIX_TIMESLOT + "TIMESLOT "
            + PREFIX_VENUE + "VENUE "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TIMESLOT + "1500-1800 "
            + PREFIX_VENUE + "COM1-0203 "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_NAME + "JakeLim"
            + PREFIX_DESCRIPTION + "Review past year paper";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation already exists in the ModQuik";
    public static final String MESSAGE_CLASH_CONSULTATION =
            "There exists a consultation having same venue and timeslot in the ModQuik";

    private final Consultation toAdd;

    /**
     * Creates an AddConsultationCommand to add the specified {@code Consultation}
     */
    public AddConsultationCommand(Consultation consultation) {
        requireNonNull(consultation);
        toAdd = consultation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasConsultation(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }
        if (model.hasConsultationClashingWith(toAdd)) {
            throw new CommandException(MESSAGE_CLASH_CONSULTATION);
        }
        model.addConsultation(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddConsultationCommand // instanceof handles nulls
                && toAdd.equals(((AddConsultationCommand) other).toAdd));
    }
}
