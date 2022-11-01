package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.List;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.parser.ParserStudentIndexUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.moduleclass.exceptions.ModuleClassNotFoundException;
import seedu.taassist.model.student.Student;

/**
 * Unassigns students from a module class.
 */
public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = "> Unassigns students from a class.\n"
            + "Parameters: INDEX... "
            + PREFIX_MODULE_CLASS + "CLASS_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 2 3 "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "Students unassigned from [ %1$s ]:\n[ %2$s ]";

    private final List<Index> indices;
    private final ModuleClass moduleClassToUnassign;

    /**
     * Creates an UnassignCommand to unassign the given {@code ModuleClass} from students at the given {@code Indices}.
     */
    public UnassignCommand(List<Index> indices, ModuleClass moduleClassToUnassign) {
        requireAllNonNull(indices);
        requireNonNull(moduleClassToUnassign);
        this.indices = indices;
        this.moduleClassToUnassign = moduleClassToUnassign;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModuleClass existingModuleClass;

        try {
            existingModuleClass = model.getModuleClassWithSameName(moduleClassToUnassign);
        } catch (ModuleClassNotFoundException mcnfe) {
            throw new CommandException(String.format(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        List<Student> lastShownList = model.getFilteredStudentList();
        List<Student> studentsToUnassign;
        try {
            studentsToUnassign = ParserStudentIndexUtil.parseStudentsFromIndices(indices, lastShownList);
        } catch (ParseException pe) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        studentsToUnassign.forEach(s -> model.setStudent(s, s.removeModuleClass(existingModuleClass)));

        return new CommandResult(getSuccessMessage(studentsToUnassign, existingModuleClass));
    }

    public static String getSuccessMessage(List<Student> students, ModuleClass moduleClass) {
        String studentNames = commaSeparate(students, student -> student.getName().toString());
        return String.format(MESSAGE_SUCCESS, moduleClass, studentNames);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignCommand // instanceof handles nulls
                && indices.equals(((UnassignCommand) other).indices))
                && moduleClassToUnassign.equals(((UnassignCommand) other).moduleClassToUnassign);
    }
}
