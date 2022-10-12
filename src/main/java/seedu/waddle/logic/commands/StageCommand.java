package seedu.waddle.logic.commands;

import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.Stages;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class StageCommand extends Command {
    public static final String COMMAND_WORD = "stage";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Switches to another planning stage according "
            + "to the specified planning stage.\n"
            + "Current available stages are \"wish\" and \"schedule\"\n"
            + "Parameters: PLANNING_STAGE\n"
            + "Example: " + COMMAND_WORD + " s/wish";
    public static final String MESSAGE_STAGE_SWITCH_SUCCESS = "Waddled to the %1$s stage";
    public static final String MESSAGE_ALREADY_AT_STAGE_SUCCESS = "Already waddling in the %1$s stage";
    private final Stages selectedStage;

    public StageCommand(Stages selectedStage) {
        this.selectedStage = selectedStage;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StageManager stageManager = StageManager.getInstance();

        // if already at home page, do nothing and tell user
        if (stageManager.isCurrentStage(selectedStage)) {
            return new CommandResult(String.format(MESSAGE_ALREADY_AT_STAGE_SUCCESS,
                    selectedStage.toString().toLowerCase()));
        }

        // change to home stage in stage manager
        stageManager.switchStage(selectedStage);

        // return command result with stage change to selectedStage
        return new CommandResult(String.format(MESSAGE_STAGE_SWITCH_SUCCESS, selectedStage.toString().toLowerCase()),
                selectedStage);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof HomeCommand;

    }
}
