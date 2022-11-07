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
     * Creates an UnassignCommand to unassign the students at the given {@code indices} from
     * {@code moduleClassToUnassign}.
     *
     * @param indices List of {@code Index} objects specifying which Student objects to unassign from
     *     {@code moduleClassToUnassign}.
     * @param moduleClassToUnassign Module class to unassign the students from.
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
        requireModuleClassExists(moduleClassToUnassign, model);

        List<Student> lastShownList = model.getFilteredStudentList();
        List<Student> studentsToUnassign;

        try {
            studentsToUnassign = IndexUtil.getAtIndices(lastShownList, indices);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        studentsToUnassign.forEach(s -> model.setStudent(s, s.removeModuleClass(moduleClassToUnassign)));

        return new CommandResult(getCommandMessage(studentsToUnassign, moduleClassToUnassign));
    }

    /**
     * Returns the command message on successful execution of the command.
     *
     * @param students Student objects that were unassigned from {@code moduleClass}.
     * @param moduleClass Module class the students were assigned from.
     * @return Command message showing which Student objects were unassigned from {@code moduleClass}.
     */
    public static String getCommandMessage(List<Student> students, ModuleClass moduleClass) {
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
