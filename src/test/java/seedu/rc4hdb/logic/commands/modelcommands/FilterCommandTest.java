package seedu.rc4hdb.logic.commands.modelcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_RESIDENTS_LISTED_OVERVIEW;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.AMY;
import static seedu.rc4hdb.testutil.TypicalResidents.BENSON;
import static seedu.rc4hdb.testutil.TypicalResidents.DANIEL;
import static seedu.rc4hdb.testutil.TypicalResidents.ELLE;
import static seedu.rc4hdb.testutil.TypicalResidents.FIONA;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;
import static seedu.rc4hdb.testutil.TypicalSpecifiers.ALL_SPECIFIER;
import static seedu.rc4hdb.testutil.TypicalSpecifiers.ANY_SPECIFIER;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchAllKeywordsPredicate;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchAnyKeywordPredicate;
import seedu.rc4hdb.testutil.ResidentStringDescriptorBuilder;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalResidentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalResidentBook(), new UserPrefs());


    @Test
    public void execute_allFieldsSpecifiedUnfilteredListForAll_success() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder(ALICE).build());
        FilterCommand command = new FilterCommand(new ResidentStringDescriptorBuilder(ALICE).build(), ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder().withName(ALICE.getName().fullName)
                .withPhone(ALICE.getPhone().value).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_nameSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withName(ALICE.getName().fullName).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_phoneSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withPhone(ALICE.getPhone().value).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_emailSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withEmail(ALICE.getEmail().value).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }


    @Test
    public void execute_matricNumberSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withMatricNumber(ALICE.getMatricNumber().matricNumber).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_houseSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withHouse(ALICE.getHouse().house).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 2);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_genderSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withGender(ALICE.getGender().gender).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 3);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_tagsSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withTags(ALICE.getTags()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 3);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredResidentList());
    }

    @Test
    public void execute_roomSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withRoom(ALICE.getRoom().room).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ALL_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListForAny_success() {
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 5);
        AttributesMatchAnyKeywordPredicate predicate = new AttributesMatchAnyKeywordPredicate(
                new ResidentStringDescriptorBuilder(ALICE).build());
        FilterCommand command = new FilterCommand(new ResidentStringDescriptorBuilder(ALICE).build(), ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, ELLE, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder().withName(ALICE.getName().fullName)
                .withPhone(ALICE.getPhone().value).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), expectedModel.getFilteredResidentList());
    }

    @Test
    public void execute_nameSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withName(ALICE.getName().fullName).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_phoneSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withPhone(ALICE.getPhone().value).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_emailSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withEmail(ALICE.getEmail().value).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }


    @Test
    public void execute_matricNumberSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withMatricNumber(ALICE.getMatricNumber().matricNumber).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }

    @Test
    public void execute_houseSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withHouse(ALICE.getHouse().house).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 2);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_genderSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withGender(ALICE.getGender().gender).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 3);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, FIONA), model.getFilteredResidentList());
    }

    @Test
    public void execute_tagsSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withTags(ALICE.getTags()).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 3);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredResidentList());
    }

    @Test
    public void execute_roomSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withRoom(ALICE.getRoom().room).build();
        String expectedMessage = String.format(MESSAGE_RESIDENTS_LISTED_OVERVIEW, 1);
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor, ANY_SPECIFIER);
        expectedModel.updateFilteredResidentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredResidentList());
    }


    @Test
    public void equals() {
        ResidentStringDescriptor firstDescriptor = new ResidentStringDescriptorBuilder(AMY).build();
        ResidentStringDescriptor secondDescriptor = new ResidentStringDescriptorBuilder(ALICE).build();

        FilterCommand filterFirstCommand = new FilterCommand(firstDescriptor, ALL_SPECIFIER);
        FilterCommand filterSecondCommand = new FilterCommand(secondDescriptor, ALL_SPECIFIER);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstDescriptor, ALL_SPECIFIER);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

}

