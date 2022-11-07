package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.commands.CommandUtil.requireModuleClassExists;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;

import java.util.List;

import seedu.taassist.commons.core.Messages;
import seedu.taassist.commons.core.index.Index;
import seedu.taassist.commons.core.index.IndexUtil;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
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
     * Creates an AssignCommand to assign the students at the given {@code indices} to {@code moduleClassToAssign}.
     *
     * @param indices List of {@code Index} objects specifying which Student objects to assign to
     *     {@code moduleClassToAssign}.
     * @param moduleClassToAssign Module class to assign the students to.
     */
    public AssignCommand(List<Index> indices, ModuleClass moduleClassToAssign) {
        requireAllNonNull(indices, moduleClassToAssign);
        this.indices = indices;
        this.moduleClassToAssign = moduleClassToAssign;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireModuleClassExists(moduleClassToAssign, model);

        List<Student> lastShownList = model.getFilteredStudentList();
        List<Student> studentsToAssign;
        try {
            studentsToAssign = IndexUtil.getAtIndices(lastShownList, indices);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        studentsToAssign.forEach(s -> model.setStudent(s, s.addModuleClass(moduleClassToAssign)));

        return new CommandResult(getCommandMessage(studentsToAssign, moduleClassToAssign));
    }

    /**
     * Returns the command message on successful execution of the command.
     *
     * @param students Student objects that were assigned to {@code moduleClass}.
     * @param moduleClass Module class the students were assigned to.
     * @return Command message showing which Student objects were assigned to {@code moduleClass}.
     */
    public static String getCommandMessage(List<Student> students, ModuleClass moduleClass) {
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
