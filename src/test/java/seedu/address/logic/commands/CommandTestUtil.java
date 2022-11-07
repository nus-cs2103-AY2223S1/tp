package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_COMPLETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IS_LINKED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditExamDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_MODULE_1 = "CS2001";
    public static final String VALID_MODULE_2 = "CS2002";

    public static final String VALID_TASK_DESCRIPTION_1 = "description 1";
    public static final String VALID_TASK_DESCRIPTION_2 = "description 2";
    public static final String VALID_DESCRIPTION_EXAMONE = "Exam one";
    public static final String VALID_DESCRIPTION_EXAMTWO = "Exam two";
    public static final String VALID_MODULE_EXAMONE = "CS2030S";
    public static final String VALID_MODULE_EXAMTWO = "CS2040S";
    public static final String VALID_DATE_EXAMONE = "20-08-2023";
    public static final String VALID_DATE_EXAMTWO = "20-10-2023";
    public static final String INVALID_MODULE = " " + PREFIX_MODULE + "2001";

    public static final String INVALID_EXAM_DESCRIPTION = " " + PREFIX_EXAM_DESCRIPTION + " ";
    public static final String INVALID_FORMAT_EXAM_DATEONE = " " + PREFIX_EXAM_DATE + "2022-08-20";
    public static final String INVALID_FORMAT_EXAM_DATETWO = " " + PREFIX_EXAM_DATE + "20-008-2024";
    public static final String INVALID_FORMAT_EXAM_DATETHREE = " " + PREFIX_EXAM_DATE + "20/08/2024";
    public static final String INVALID_FORMAT_EXAM_DATEFOUR = " " + PREFIX_EXAM_DATE + "20-13-2024";
    public static final String INVALID_FORMAT_EXAM_DATEFIVE = " " + PREFIX_EXAM_DATE + "32-01-2024";
    public static final String INVALID_FORMAT_EXAM_DATESIX = " " + PREFIX_EXAM_DATE + "00-01-2024";
    public static final String INVALID_FORMAT_EXAM_DATESEVEN = " " + PREFIX_EXAM_DATE + "aa-bb-cccc";

    public static final String INVALID_EXAM_DATEONE = " " + PREFIX_EXAM_DATE + "29-02-2023";
    public static final String INVALID_EXAM_DATETWO = " " + PREFIX_EXAM_DATE + "31-11-2024";

    public static final String INVALID_PAST_EXAMDATEONE = " " + PREFIX_EXAM_DATE + "20-01-2021";
    public static final String INVALID_PAST_EXAMDATETWO = " " + PREFIX_EXAM_DATE + "29-10-2022";






    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    public static final EditExamCommand.EditExamDescriptor DESC_EXAMONE;
    public static final EditExamCommand.EditExamDescriptor DESC_EXAMTWO;

    static {
        DESC_EXAMONE = new EditExamDescriptorBuilder().withModule(VALID_MODULE_EXAMONE)
                .withDescription(VALID_DESCRIPTION_EXAMONE).withDate(VALID_DATE_EXAMONE).build();
        DESC_EXAMTWO = new EditExamDescriptorBuilder().withModule(VALID_MODULE_EXAMTWO)
                .withDescription(VALID_DESCRIPTION_EXAMTWO).withDate(VALID_DATE_EXAMTWO).build();

    }

    public static final String VALID_MODULE_CS2030 = "cs2030";
    public static final String VALID_MODULE_CS2040 = "cs2040";
    public static final String VALID_DESCRIPTION_DO_TUTORIAL = "do tutorial";
    public static final String VALID_DESCRIPTION_WATCH_LECTURE = "watch lecture";
    public static final String VALID_STATUS_YES = "y";
    public static final String VALID_STATUS_NO = "n";
    public static final String INVALID_STATUS_TRUE = "true";
    public static final String INVALID_MODULE_ABSENT_GEA1000 = "gea1000";

    public static final String MODULE_DESC_CS2030 = " " + PREFIX_MODULE + "cs2030";
    public static final String MODULE_DESC_CS2040 = " " + PREFIX_MODULE + "cs2040";
    public static final String DESCRIPTION_DESC_DO_TUTORIAL = " " + PREFIX_DESCRIPTION + "do tutorial";
    public static final String DESCRIPTION_DESC_WATCH_LECTURE = " " + PREFIX_DESCRIPTION + "watch lecture";
    public static final String COMPLETION_STATUS_DESC_YES = " " + PREFIX_IS_COMPLETE + VALID_STATUS_YES;
    public static final String COMPLETION_STATUS_DESC_NO = " " + PREFIX_IS_COMPLETE + VALID_STATUS_NO;
    public static final String INVALID_COMPLETION_STATUS_DESC = " " + PREFIX_IS_COMPLETE + INVALID_STATUS_TRUE;
    public static final String LINK_STATUS_DESC_YES = " " + PREFIX_IS_LINKED + VALID_STATUS_YES;
    public static final String LINK_STATUS_DESC_NO = " " + PREFIX_IS_LINKED + VALID_STATUS_NO;
    public static final String INVALID_LINK_STATUS_DESC = " " + PREFIX_IS_LINKED + INVALID_STATUS_TRUE;
    public static final String DESCRIPTION_DESC_TASK_A = " " + PREFIX_DESCRIPTION + "Task A";

    // module codes should be at least 6 characters long
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + "cs";
    // empty string not allowed for task descriptions
    public static final String INVALID_TASK_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + " ";

    public static final String EXAMMODULEONE = " " + PREFIX_MODULE + VALID_MODULE_EXAMONE;
    public static final String EXAMMODULETWO = " " + PREFIX_MODULE + VALID_MODULE_EXAMTWO;
    public static final String EXAMDESCRIPTIONONE = " " + PREFIX_EXAM_DESCRIPTION + VALID_DESCRIPTION_EXAMONE;
    public static final String EXAMDESCRIPTIONTWO = " " + PREFIX_EXAM_DESCRIPTION + VALID_DESCRIPTION_EXAMTWO;
    public static final String EXAMDATEONE = " " + PREFIX_EXAM_DATE + VALID_DATE_EXAMONE;
    public static final String EXAMDATETWO = " " + PREFIX_EXAM_DATE + VALID_DATE_EXAMTWO;



    public static final EditTaskCommand.EditTaskDescriptor DESC_TUTORIAL;
    public static final EditTaskCommand.EditTaskDescriptor DESC_LECTURE;

    static {
        DESC_TUTORIAL = new EditTaskDescriptorBuilder().withModule(VALID_MODULE_CS2030)
            .withDescription(VALID_DESCRIPTION_DO_TUTORIAL).build();
        DESC_LECTURE = new EditTaskDescriptorBuilder().withModule(VALID_MODULE_CS2040)
            .withDescription(VALID_DESCRIPTION_WATCH_LECTURE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }


    //must do after model itself executes.
    /**
     * Returns true when the tasks in the model has the same exams as the tasks in expected model
     */
    public static void assertTasksHaveSameExamSuccess(Model actualModel, Model expectedModel) {
        ObservableList<Task> filteredList = expectedModel.getFilteredTaskList();
        for (int i = 0; i < filteredList.size(); i++) {
            Task expectedModelTask = expectedModel.getFilteredTaskList().get(i);
            Task modelTask = actualModel.getFilteredTaskList().get(i);
            assertTrue(expectedModelTask.isLinked() == modelTask.isLinked());
            if (expectedModelTask.isLinked()) {
                assertTrue(expectedModelTask.getExam().equals(modelTask.getExam()));
            }
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Exam> expectedFilteredListForExams = new ArrayList<>(actualModel.getFilteredExamList());
        List<Task> expectedFilteredListForTasks = new ArrayList<>(actualModel.getFilteredTaskList());
        List<Module> expectedFilteredListForModules = new ArrayList<>(actualModel.getFilteredModuleList());
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredListForExams, actualModel.getFilteredExamList());
        assertEquals(expectedFilteredListForTasks, actualModel.getFilteredTaskList());
        assertEquals(expectedFilteredListForModules, actualModel.getFilteredModuleList());

    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] description = {task.getDescription().description.toLowerCase()};
        model.updateFilteredTaskList(new DescriptionContainsKeywordsPredicate(Arrays.asList(description)));

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Updates {@code model}'s filtered module list to show only the module at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showModuleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleList().size());

        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());
        final String[] moduleCode = {module.getModuleCode().moduleCode.toLowerCase()};
        model.updateFilteredModuleList(new ModuleCodeContainsKeywordsPredicate(Arrays.asList(moduleCode)));

        assertEquals(1, model.getFilteredModuleList().size());
    }

}
