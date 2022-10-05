package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;

/**
 * Assign students to a class.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assign students to a class. "
            + "Parameters: INDEX... (must be positive integers) "
            + PREFIX_MODULE_CLASS + "CLASS_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 2 3 "
            + PREFIX_MODULE_CLASS + "CS1231S";

    public static final String MESSAGE_SUCCESS = "Students with %1$s %2$s are assigned to: %3$s";
    public static final String MESSAGE_DUPLICATE_MODULE_CLASS = "Some students are already assigned with this class";

    private final List<Index> indices;
    private final ModuleClass moduleClassToAssign;

    /**
     * Creates an AssignCommand to assign the given {@Code ModuleClass} to students at the given {@Code Indices}.
     */
    public AssignCommand(List<Index> indices, ModuleClass moduleClassToAssign) {
        requireNonNull(indices);
        requireNonNull(moduleClassToAssign);
        this.indices = indices;
        this.moduleClassToAssign = moduleClassToAssign;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModuleClass(moduleClassToAssign)) {
            throw new CommandException(Messages.MESSAGE_MODULE_CLASS_DOES_NOT_EXIST);
        }

        List<Student> lastShownList = model.getFilteredStudentList();
        for (Index index : indices) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            Student student = lastShownList.get(index.getZeroBased());
            if (student.getModuleClasses().contains(moduleClassToAssign)) {
                throw new CommandException(MESSAGE_DUPLICATE_MODULE_CLASS);
            }
        }
        for (Index index : indices) {
            Student student = lastShownList.get(index.getZeroBased());
            Set<ModuleClass> moduleClasses = student.getModuleClasses();
            Set<ModuleClass> newModuleClasses = new HashSet<>();
            newModuleClasses.addAll(moduleClasses);
            newModuleClasses.add(moduleClassToAssign);

            Student editedStudent = new Student(
                    student.getName(),
                    student.getPhone(),
                    student.getEmail(),
                    student.getAddress(),
                    newModuleClasses);
            model.setStudent(student, editedStudent);
        }

        String indexOrIndices;
        if (indices.size() == 1) {
            indexOrIndices = "index";
        } else {
            indexOrIndices = "indices";
        }

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
