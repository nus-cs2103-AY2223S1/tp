package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.taassist.commons.core.Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.ArrayList;
import java.util.List;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.parser.ParserStudentIndexUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.moduleclass.StudentModuleData;
import seedu.taassist.model.student.Student;

/**
 * Unassigns students from a class.
 */
public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unassigns students from a class. "
            + "Parameters: INDEX... (must be positive integers) "
            + PREFIX_MODULE_CLASS + "CLASS_NAME (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 2 3 "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "Students with %1$s %2$s are unassigned from: %3$s";

    private final List<Index> indices;
    private final ModuleClass moduleClassToUnassign;

    /**
     * Creates an UnassignCommand to unassign the given {@Code ModuleClass} from students at the given {@Code Indices}.
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

        if (!model.hasModuleClass(moduleClassToUnassign)) {
            throw new CommandException(String.format(MESSAGE_MODULE_CLASS_DOES_NOT_EXIST,
                    model.getModuleClassList()));
        }

        List<Student> lastShownList = model.getFilteredStudentList();
        List<Student> studentsToUnassign;
        try {
            studentsToUnassign = ParserStudentIndexUtil.parseStudentsFromIndices(indices, lastShownList);
        } catch (ParseException pe) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        for (Student student : studentsToUnassign) {
            List<StudentModuleData> newModuleData = student.getModuleDataList().stream()
                    .filter(moduleData -> !moduleData.getModuleClass().isSame(moduleClassToUnassign))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            Student editedStudent = new Student(
                    student.getName(),
                    student.getPhone(),
                    student.getEmail(),
                    student.getAddress(),
                    newModuleData);
            model.setStudent(student, editedStudent);
        }

        String indexOrIndices = indices.size() == 1 ? "index" : "indices";
        return new CommandResult(String.format(MESSAGE_SUCCESS, indexOrIndices, indices, moduleClassToUnassign));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnassignCommand // instanceof handles nulls
                && indices.equals(((UnassignCommand) other).indices))
                && moduleClassToUnassign.equals(((UnassignCommand) other).moduleClassToUnassign);
    }
}
