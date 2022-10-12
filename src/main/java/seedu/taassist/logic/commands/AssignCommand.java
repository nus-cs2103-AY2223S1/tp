package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.ArrayList;
import java.util.List;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.parser.ParserStudentIndexUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentModuleData;

/**
 * Assigns students to a class.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign students to a class. "
            + "Parameters: INDEX... (must be positive integers) "
            + PREFIX_MODULE_CLASS + "CLASS_NAME (case sensitive)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 2 3 "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "Students with %1$s %2$s are assigned to: %3$s";

    private final List<Index> indices;
    private final ModuleClass moduleClassToAssign;

    /**
     * Creates an AssignCommand to assign the given {@Code ModuleClass} to students at the given {@Code Indices}.
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

        if (!model.hasModuleClass(moduleClassToAssign)) {
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

        for (Student student : studentsToAssign) {
            List<StudentModuleData> newModuleData = new ArrayList<>(student.getModuleData());
            newModuleData.add(new StudentModuleData(moduleClassToAssign));
            Student editedStudent = new Student(
                    student.getName(),
                    student.getPhone(),
                    student.getEmail(),
                    student.getAddress(),
                    newModuleData);
            model.setStudent(student, editedStudent);
        }

        String indexOrIndices = indices.size() == 1 ? "index" : "indices";
        return new CommandResult(String.format(MESSAGE_SUCCESS, indexOrIndices, indices, moduleClassToAssign));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                && indices.equals(((AssignCommand) other).indices))
                && moduleClassToAssign.equals(((AssignCommand) other).moduleClassToAssign);
    }
}
