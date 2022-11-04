package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Student;

/**
 * Adds a person to profNus.
 */
public class AddStuCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to profNus. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]..."
            + PREFIX_ID + "ID "
            + PREFIX_HANDLE + "HANDLE "
            + "[" + PREFIX_MODULE_CODE + "MODULE]..."
            + "[" + PREFIX_STUDENT_TA + "TA]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_ID + "A0123456G "
            + PREFIX_HANDLE + "@good_student "
            + PREFIX_MODULE_CODE + "CS2030S "
            + PREFIX_STUDENT_TA + "CS1101S "
            + PREFIX_CLASS_GROUP + "CS2030S:Tut07";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in profNus";

    private final Student toAdd;

    /**
     * Creates an AddStuCommand to add the specified {@code Person}
     */
    public AddStuCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        //check if module exists
        if (toAdd.getStudentModuleInfo().size() > 0 || toAdd.isTeachingAssistant()) {
            List<Module> moduleList = model.getFilteredModuleList();
            List<ModuleCode> studentModules = new ArrayList<>();
            studentModules.addAll(toAdd.getStudentModuleInfo());
            studentModules.addAll(toAdd.getTeachingAssistantInfo());
            ModuleCode lastViewedCode = null;
            for (ModuleCode moduleCode : studentModules) {
                boolean isValid = false;
                for (Module module : moduleList) {
                    if (module.getCode().equals(moduleCode)) {
                        isValid = true;
                    } else {
                        lastViewedCode = moduleCode;
                    }
                }
                if (!isValid) {
                    throw new CommandException(Messages.MESSAGE_MODULE_DOES_NOT_EXIST
                            + " Module not found: " + lastViewedCode + ".\nPlease create the module first using "
                            + "the madd command or specify an existing module!");
                }
            }
        }

        model.addPerson(toAdd);

        if (toAdd instanceof Student && (toAdd.isTeachingAssistant())) {
            if (model.hasTutor(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
            } else {
                model.addTutor(toAdd);
            }
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                false, false, false,
                true, false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStuCommand // instanceof handles nulls
                && toAdd.equals(((AddStuCommand) other).toAdd));
    }
}
