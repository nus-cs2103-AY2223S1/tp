package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MODULEONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MODULETWO;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertTasksHaveSameExamSuccess;
import static seedu.address.logic.commands.EditModuleCommand.MESSAGE_MODULE_NOT_EDITED;
import static seedu.address.logic.commands.EditModuleCommand.MESSAGE_TASKS_EXAMS_RELATED_MODIFIED;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_MODULE_UNRELATED_TO_ANY_TASK_OR_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditModuleCommand.
 */
public class EditModuleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListWithModuleUnrelatedToAnyTaskAndExam_success() {
        Module editedModule = new ModuleBuilder(new Module(new ModuleCode("CS3213"),
                new ModuleName("Final Examinations"), new ModuleCredit(4))).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand =
                new EditModuleCommand(INDEX_MODULE_UNRELATED_TO_ANY_TASK_OR_EXAM, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(model.getFilteredModuleList()
                .get(INDEX_MODULE_UNRELATED_TO_ANY_TASK_OR_EXAM.getZeroBased()), editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListWithModuleUnrelatedToAnyTaskAndExam_success() {
        Module editedModule = new ModuleBuilder().withModuleCode("CS3213").withModuleName("Final Examinations").build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand =
                new EditModuleCommand(INDEX_MODULE_UNRELATED_TO_ANY_TASK_OR_EXAM, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(model.getFilteredModuleList()
                .get(INDEX_MODULE_UNRELATED_TO_ANY_TASK_OR_EXAM.getZeroBased()), editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    public void linkHelper(Model model, Index examIndex, Index taskIndex) throws CommandException {
        LinkExamCommand linkExamCommand = new LinkExamCommand(examIndex, taskIndex);
        linkExamCommand.execute(model);
    }

    @Test
    public void execute_allFieldsSpecifiedWithModuleRelatedToTasksNotExams_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_FOURTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleCode("CS3213")
                .withModuleName("Programming").withModuleCredit(5).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FOURTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit())
                + MESSAGE_TASKS_EXAMS_RELATED_MODIFIED;
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);
        expectedModel.updateModuleFieldForTask(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedWithModuleRelatedToTasksNotExams_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_FOURTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleCode("CS3213")
                .withModuleName("Programming").build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FOURTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit())
                + MESSAGE_TASKS_EXAMS_RELATED_MODIFIED;
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);
        expectedModel.updateModuleFieldForTask(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_moduleCodeFieldUnspecifiedWithModuleRelatedToTasksNotExams_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_FOURTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleName("Programming").withModuleCredit(5).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FOURTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedWithModuleRelatedToExamsNotTasks_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_FIFTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleCode("CS3213")
                .withModuleName("Programming").withModuleCredit(5).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIFTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit())
                + MESSAGE_TASKS_EXAMS_RELATED_MODIFIED;
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);
        expectedModel.updateModuleFieldForExam(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedWithModuleRelatedToExamsNotTasks_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_FIFTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleCode("CS3213")
                .withModuleName("Programming").build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIFTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit())
                + MESSAGE_TASKS_EXAMS_RELATED_MODIFIED;
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);
        expectedModel.updateModuleFieldForExam(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_moduleCodeUnspecifiedWithModuleRelatedToExamsNotTasks_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_FIFTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleName("Programming").withModuleCredit(5).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIFTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedWithModuleRelatedToTasksAndExams_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_SIXTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleCode("CS3213")
                .withModuleName("Programming").withModuleCredit(5).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_SIXTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit())
                + MESSAGE_TASKS_EXAMS_RELATED_MODIFIED;
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);
        expectedModel.updateModuleFieldForTask(moduleToEdit, editedModule);
        expectedModel.updateModuleFieldForExam(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedWithModuleRelatedToTasksAndExams_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_SIXTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleCode("CS3213").withModuleCredit(5).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_SIXTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit())
                + MESSAGE_TASKS_EXAMS_RELATED_MODIFIED;
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);
        expectedModel.updateModuleFieldForTask(moduleToEdit, editedModule);
        expectedModel.updateModuleFieldForExam(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    @Test
    public void execute_moduleCodeFieldUnspecifiedWithModuleRelatedToTasksAndExams_success() {
        Module moduleToEdit = model.getFilteredModuleList().get(INDEX_SIXTH_MODULE.getZeroBased());
        Module editedModule = new ModuleBuilder(moduleToEdit).withModuleName("Programming").withModuleCredit(5).build();
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_SIXTH_MODULE, descriptor);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS,
                editedModule.getModuleCode(), editedModule.getModuleName(), editedModule.getModuleCredit());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.replaceModule(moduleToEdit, editedModule);

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
        assertTasksHaveSameExamSuccess(model, expectedModel);
    }

    // edited module is a module in the module list unrelated to any tasks and exams
    @Test
    public void execute_duplicateModuleUnrelatedToAnyTasksAndExams_failure() {
        Module module = model.getFilteredModuleList().get(INDEX_MODULE_UNRELATED_TO_ANY_TASK_OR_EXAM.getZeroBased());
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(module).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE, descriptor);
        assertCommandFailure(editModuleCommand, model, MESSAGE_DUPLICATE_MODULE);
    }

    // edited module is a module in the module list related to tasks and exams
    @Test
    public void execute_duplicateModuleRelatedToTasksAndExams_failure() {
        Module module = model.getFilteredModuleList().get(INDEX_SIXTH_MODULE.getZeroBased());
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(module).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE, descriptor);
        assertCommandFailure(editModuleCommand, model, MESSAGE_DUPLICATE_MODULE);
    }


    // no fields change
    @Test
    public void execute_noFieldChangeUnfilteredList_failure() {
        Module module = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        EditModuleCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(module).build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE, descriptor);
        assertCommandFailure(editModuleCommand, model, MESSAGE_MODULE_NOT_EDITED);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        int index = model.getFilteredModuleList().size() + 1;
        Index outOfBoundIndex = Index.fromOneBased(index);
        EditModuleCommand.EditModuleDescriptor descriptor =
                new EditModuleDescriptorBuilder().withModuleName("Programming Methodology I").build();
        EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editModuleCommand, model,
                String.format(Messages.MESSAGE_INVALID_MODULE_INDEX_TOO_LARGE, index));
    }

    @Test
    public void equals() {
        final EditModuleCommand command =
                new EditModuleCommand(INDEX_SECOND_MODULE, DESC_MODULEONE);

        // same values -> returns true
        EditModuleCommand.EditModuleDescriptor copyDescriptor =
                new EditModuleCommand.EditModuleDescriptor(DESC_MODULEONE);
        EditModuleCommand commandWithSameValues =
                new EditModuleCommand(INDEX_SECOND_MODULE, copyDescriptor);
        assertTrue(command.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(command.equals(command));

        // null -> returns false
        assertFalse(command.equals(null));

        // different types -> returns false
        assertFalse(command.equals(10));

        // different index -> returns false
        assertFalse(command.equals(new EditModuleCommand(INDEX_THIRD_MODULE, DESC_MODULEONE)));

        // different descriptor -> returns false
        assertFalse(command.equals(new EditModuleCommand(INDEX_SECOND_MODULE, DESC_MODULETWO)));
    }
}
