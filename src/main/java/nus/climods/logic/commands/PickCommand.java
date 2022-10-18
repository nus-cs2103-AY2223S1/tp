package nus.climods.logic.commands;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.module.UserModule;

import java.util.Locale;

import static java.util.Objects.requireNonNull;

/**
 * Allow users to pick lesson slots
 */
public class PickCommand extends Command {
    public static final String COMMAND_WORD = "pick";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <Module Code> <Lesson Type> <Class No> " +
            ": adds Lesson into selected module.";

    public static final String MESSAGE_SUCCESS = "New lesson added: %1$s";
    public static final String MESSAGE_MODULE_MISSING = "This module is not in your module list";

    private final String toPick;
    private final String lessonType;
    private final String classNo;

    /**
     * Creates an PickCommand to add the specified lesson in Module
     */
    public PickCommand(String toPick, String lessonType, String classNo) {
        requireNonNull(toPick);
        this.toPick = toPick;
        this.lessonType = lessonType;
        this.classNo = classNo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        UserModule moduleToPick = new UserModule(toPick, model);

        if (!model.filteredListhasUserModule(moduleToPick)) {
            throw new CommandException(MESSAGE_MODULE_MISSING);
        }

        model.getFilteredModuleList();
        String addedDetails = String.format("%s %s %s", toPick, lessonType, classNo);
        return new CommandResult(String.format(MESSAGE_SUCCESS, addedDetails.toUpperCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PickCommand // instanceof handles nulls
                && toPick.equals(((PickCommand) other).toPick));
    }
}
