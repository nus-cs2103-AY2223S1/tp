package seedu.modquik.logic.commands.consultation;
import static java.util.Objects.requireNonNull;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.consultation.Consultation;

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
            + PREFIX_NAME + "Review past year paper with Anna "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_VENUE + "COM1-0203 "
            + PREFIX_DATE_DAY + "2022-12-31 "
            + PREFIX_TIME + "15:00-18:00 "
            + PREFIX_DESCRIPTION + "AY2019-2020 Question 3,6,8 and AY2020-2021 Question 3,5,16";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation already exists in ModQuik";
    public static final String MESSAGE_CLASH_CONSULTATION =
            "There exists a consultation with overlapping timeslot in ModQuik";

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
