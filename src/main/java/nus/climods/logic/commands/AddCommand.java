package nus.climods.logic.commands;

import static java.util.Objects.requireNonNull;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.SemestersEnum;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;
import nus.climods.model.Model;
import nus.climods.model.module.LessonTypeEnum;
import nus.climods.model.module.Module;
import nus.climods.model.module.UserModule;

/**
 * Adds a module into the UserModule List
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " <Module Code> : Adds a module to module record. ";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in your list of modules";
    public static final String MESSAGE_MODULE_NOT_FOUND = ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE;
    public static final String MESSAGE_MODULE_NOT_OFFERED_IN_SEMESTER = "Module not offered in chosen semester";

    private final String toAdd;
    private final SemestersEnum semester;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddCommand(String toAdd, SemestersEnum semester) {
        requireNonNull(toAdd);
        this.toAdd = toAdd.trim();
        this.semester = semester;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String uppercaseToAdd = toAdd.toUpperCase();

        if (!model.isModuleOffered(uppercaseToAdd)) {
            throw new CommandException(String.format(MESSAGE_MODULE_NOT_FOUND, toAdd));
        }
        if (!model.isModuleOfferedInSemester(uppercaseToAdd, semester)) {
            throw new CommandException(MESSAGE_MODULE_NOT_OFFERED_IN_SEMESTER);
        }

        UserModule moduleToAdd = new UserModule(uppercaseToAdd, semester);
        if (model.hasUserModule(moduleToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        Module module = model.getListModule(uppercaseToAdd).get();

        try {
            module.loadMoreData();
        } catch (ApiException e) {
            throw new CommandException("Error 404 something went wrong");
        }

        for (LessonTypeEnum t : module.getUnselectableLessonTypeEnums(semester)) {
            String lessonId = module.getUnselectableLessonId(t, semester);
            moduleToAdd.addLesson(t, lessonId);
        }

        model.addUserModule(moduleToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, uppercaseToAdd), COMMAND_WORD);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
