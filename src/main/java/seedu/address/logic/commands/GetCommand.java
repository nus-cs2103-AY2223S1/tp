package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.getcommands.GetFloorNumberCommand.FLOOR_NUMBER_PREFIX;
import static seedu.address.logic.commands.getcommands.GetHospitalWingCommand.HOSPITAL_WING_PREFIX;
import static seedu.address.logic.commands.getcommands.GetInpatientCommand.INPATIENT_PREFIX;
import static seedu.address.logic.commands.getcommands.GetNameCommand.NAME_PREFIX;
import static seedu.address.logic.commands.getcommands.GetOutpatientCommand.OUTPATIENT_PREFIX;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all filtered persons in the address book according to the given condition prefix
 */
public class GetCommand extends Command {

    public static final String COMMAND_WORD = "get";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all persons whose information corresponds to the "
            + "query prefix.\n"
            + "Prefixes to choose from: "
            + OUTPATIENT_PREFIX + ", "
            + INPATIENT_PREFIX + ", "
            + FLOOR_NUMBER_PREFIX + " FLOOR NUMBER, "
            + HOSPITAL_WING_PREFIX + " HOSPITAL WING\n"
            + "Examples: "
            + COMMAND_WORD + " " + OUTPATIENT_PREFIX + ", "
            + COMMAND_WORD + " " + INPATIENT_PREFIX + ", "
            + COMMAND_WORD + " " + FLOOR_NUMBER_PREFIX + " 3 5 9, "
            + COMMAND_WORD + " " + HOSPITAL_WING_PREFIX + " south"
            + COMMAND_WORD + " " + NAME_PREFIX + " alice bob charlie";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Only subclasses of GetCommand can be executed
        throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetCommand.MESSAGE_USAGE));
    }
}
