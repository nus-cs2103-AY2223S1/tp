package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;

/**
 * Deletes a moduleClass identified using it's className from TA-Assist.
 */
public class DeletecCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class(es) identified by the class name(s).\n"
            + "Parameters: "
            + PREFIX_MODULE_CLASS + "CLASS_NAME... (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CLASS + "CS1101S" + " "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_DELETE_MODULE_CLASS_SUCCESS = "Deleted class(es): %1$s";

    private final Set<ModuleClass> moduleClasses;

    public DeletecCommand(Set<ModuleClass> moduleClasses) {
        this.moduleClasses = moduleClasses;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasModuleClasses(moduleClasses)) {
            throw new CommandException(String.format(MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        List<Student> students = model.getTaAssist().getStudentList();
        for (Student student : students) {
            if (!Collections.disjoint(student.getModuleClasses(), moduleClasses)) {
                Set<ModuleClass> assignedClasses = new HashSet<>();
                assignedClasses.addAll(student.getModuleClasses());
                assignedClasses.removeAll(moduleClasses);
                Student editedStudent = new Student(student.getName(), student.getPhone(), student.getEmail(),
                        student.getAddress(), assignedClasses);
                model.setStudent(student, editedStudent);
            }
        }

        model.deleteModuleClasses(moduleClasses);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_CLASS_SUCCESS, moduleClasses));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletecCommand // instanceof handles nulls
                && moduleClasses.equals(((DeletecCommand) other).moduleClasses)); // state check
    }
}

