package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.List;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.commands.result.CommandResult;
import seedu.taassist.logic.parser.ParserStudentIndexUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.moduleclass.exceptions.ModuleClassNotFoundException;
import seedu.taassist.model.student.Student;

/**
 * Assigns students to a module class.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = "> Assigns students to a class.\n"
            + "Parameters: INDEX... "
            + PREFIX_MODULE_CLASS + "CLASS_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 2 3 "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "Students assigned to [ %1$s ]:\n[ %2$s ]";

    private final List<Index> indices;
    private final ModuleClass moduleClassToAssign;

    /**
     * Creates an AssignCommand to assign the given {@code ModuleClass} to students at the given {@code Indices}.
     */
    public AssignCommand(List<Index> indices, ModuleClass moduleClassToAssign) {
        requireAllNonNull(indices);
        requireNonNull(moduleClassToAssign);
        this.indices = indices;
        this.moduleClassToAssign = moduleClassToAssign;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModuleClass existingModuleClass;

        try {
            existingModuleClass = model.getModuleClassWithSameName(moduleClassToAssign);
        } catch (ModuleClassNotFoundException mcnfe) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        List<Student> lastShownList = model.getFilteredStudentList();
        List<Student> studentsToAssign;
        try {
            studentsToAssign = ParserStudentIndexUtil.parseStudentsFromIndices(indices, lastShownList);
        } catch (ParseException pe) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        studentsToAssign.forEach(s -> model.setStudent(s, s.addModuleClass(existingModuleClass)));

        return new CommandResult(getSuccessMessage(studentsToAssign, existingModuleClass));
    }

    public static String getSuccessMessage(List<Student> students, ModuleClass moduleClass) {
        String studentNames = commaSeparate(students, student -> student.getName().toString());
        return String.format(MESSAGE_SUCCESS, moduleClass, studentNames);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                && indices.equals(((AssignCommand) other).indices))
                && moduleClassToAssign.equals(((AssignCommand) other).moduleClassToAssign);
    }
}
