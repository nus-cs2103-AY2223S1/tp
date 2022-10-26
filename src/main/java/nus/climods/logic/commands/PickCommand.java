package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import nus.climods.model.module.LessonTypeEnum;
import org.openapitools.client.model.SemestersEnum;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.module.UserModule;

/**
 * Allow users to pick lesson slots
 */
public class PickCommand extends Command {
    public static final String COMMAND_WORD = "pick";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <Module Code> <Lesson Type> <Class No> "
            + ": adds Lesson into selected module.";

    public static final String MESSAGE_SUCCESS = "New lesson added: %1$s";
    public static final String MESSAGE_MODULE_MISSING = "This module is not in your module list";
    public static final String MESSAGE_INVALID_CLASSNO = "This class is not offered or an invalid one";
    public static final String MESSAGE_DUPLICATE_CLASSNO = "This class already exist in your current module";

    private final String toPick;
    private final LessonTypeEnum lessonType;
    private final String classNo;

    /**
     * Creates an PickCommand to add the specified lesson in Module
     */
    public PickCommand(String toPick, LessonTypeEnum lessonType, String classNo) {
        requireNonNull(toPick);
        this.toPick = toPick.toUpperCase();
        this.lessonType = lessonType;
        this.classNo = classNo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<UserModule> toUpdate= model.getUserModule(toPick);

        if (toUpdate.isEmpty()) {
            throw new CommandException(MESSAGE_MODULE_MISSING);
        }

        toUpdate.get().setLessons(lessonType, classNo);

        //TODO: Update lesson details for correct UserModule in the list
        String addedDetails = String.format("%s %s %s", toPick, lessonType, classNo);
        return new CommandResult(String.format(MESSAGE_SUCCESS, addedDetails.toUpperCase()),
                COMMAND_WORD, model);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PickCommand // instanceof handles nulls
                && toPick.equals(((PickCommand) other).toPick));
    }
}
