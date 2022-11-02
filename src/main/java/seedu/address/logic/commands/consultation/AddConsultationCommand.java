package seedu.address.logic.commands.consultation;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelType;
import seedu.address.model.consultation.Consultation;

/**
 * Adds a consultation to ModQuik.
 */
public class AddConsultationCommand extends Command {
    public static final String COMMAND_WORD = "add consultation";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation to ModQuik.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_VENUE + "VENUE "
            + PREFIX_DATE_DAY + "DAY "
            + PREFIX_TIME + "TIMESLOT "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "JakeLim "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_VENUE + "COM1-0203 "
            + PREFIX_DATE_DAY + "2022-12-31 "
            + PREFIX_TIME + "1500-1800 "
            + PREFIX_DESCRIPTION + "Review past year paper";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation already exists in ModQuik";
    public static final String MESSAGE_CLASH_CONSULTATION =
            "There exists a consultation with overlapping timeslot in ModQuik";
    public static final String MESSAGE_NON_EXISTING_MODULE =
            "The module does not exist in Modquik";

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
        if (model.hasClashingConsultation(toAdd)) {
            throw new CommandException(MESSAGE_CLASH_CONSULTATION);
        }

        if (!model.hasModuleCode(toAdd.getModule())) {
            throw new CommandException(MESSAGE_NON_EXISTING_MODULE);
        }

        model.addConsultation(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ModelType.CONSULTATION);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddConsultationCommand // instanceof handles nulls
                && toAdd.equals(((AddConsultationCommand) other).toAdd));
    }
}
