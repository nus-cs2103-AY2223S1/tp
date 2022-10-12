package seedu.rc4hdb.logic.commands.modelcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_RESIDENTS_LISTED_OVERVIEW;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.AMY;
import static seedu.rc4hdb.testutil.TypicalResidents.DANIEL;
import static seedu.rc4hdb.testutil.TypicalResidents.ELLE;
import static seedu.rc4hdb.testutil.TypicalResidents.FIONA;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchKeywordsPredicate;
import seedu.rc4hdb.testutil.ResidentDescriptorBuilder;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalResidentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalResidentBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(new ResidentDescriptorBuilder(ALICE).build());
        FilterCommand command = new FilterCommand(new ResidentDescriptorBuilder(ALICE).build());
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withName(ALICE.getName())
                .withPhone(ALICE.getPhone()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_nameSpecifiedUnfilteredList_success() {
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withName(ALICE.getName()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_phoneSpecifiedUnfilteredList_success() {
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withPhone(ALICE.getPhone()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_emailSpecifiedUnfilteredList_success() {
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder().withEmail(ALICE.getEmail()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }


    @Test
    public void execute_matricNumberSpecifiedUnfilteredList_success() {
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder()
                .withMatricNumber(ALICE.getMatricNumber()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_houseSpecifiedUnfilteredList_success() {
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder()
                .withHouse(ALICE.getHouse()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 2);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_genderSpecifiedUnfilteredList_success() {
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder()
                .withGender(ALICE.getGender()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 3);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_tagsSpecifiedUnfilteredList_success() {
        ResidentDescriptor descriptor = new ResidentDescriptorBuilder()
                .withTags(ALICE.getTags()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 2);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, DANIEL), model.getFilteredResidentList());
    }


    @Test
    public void equals() {
        ResidentDescriptor firstDescriptor = new ResidentDescriptorBuilder(AMY).build();
        ResidentDescriptor secondDescriptor = new ResidentDescriptorBuilder(ALICE).build();

        FilterCommand filterFirstCommand = new FilterCommand(firstDescriptor);
        FilterCommand filterSecondCommand = new FilterCommand(secondDescriptor);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstDescriptor);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

}

