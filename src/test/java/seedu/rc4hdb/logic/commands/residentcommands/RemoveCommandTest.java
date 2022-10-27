package seedu.rc4hdb.logic.commands.residentcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.logic.commands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.ALICE;
import static seedu.rc4hdb.testutil.TypicalResidents.AMY;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;
import static seedu.rc4hdb.testutil.TypicalSpecifiers.ALL_SPECIFIER;
import static seedu.rc4hdb.testutil.TypicalSpecifiers.ANY_SPECIFIER;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchAllKeywordsPredicate;
import seedu.rc4hdb.model.resident.predicates.AttributesMatchAnyKeywordPredicate;
import seedu.rc4hdb.testutil.ResidentStringDescriptorBuilder;
import seedu.rc4hdb.testutil.TypicalResidents;

public class RemoveCommandTest {
    private Model model = new ModelManager(getTypicalResidentBook(), new VenueBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalResidentBook(), new VenueBook(), new UserPrefs());

    /**
     * Deletes the residents that pass the predicate from expected model.
     * @param predicate object used to test the resident data
     * @return number of residents deleted
     */
    private int deleteResidents(Predicate<Resident> predicate) {
        List<Resident> residents = TypicalResidents.getTypicalResidents();
        int deleted = 0;
        for (int i = 0; i < residents.size(); i++) {
            if (predicate.test(residents.get(i))) {
                expectedModel.deleteResident(residents.get(i));
                deleted++;
            }
        }
        return deleted;
    }


    @Test
    public void execute_allFieldsSpecifiedUnfilteredListForAll_success() {
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(new ResidentStringDescriptorBuilder(ALICE).build());
        RemoveCommand command = new RemoveCommand(new ResidentStringDescriptorBuilder(ALICE).build(), ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder().withName(ALICE.getName().value)
                .withPhone(ALICE.getPhone().value).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nameSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withName(ALICE.getName().value).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_phoneSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withPhone(ALICE.getPhone().value).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emailSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withEmail(ALICE.getEmail().value).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_matricNumberSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withMatricNumber(ALICE.getMatricNumber().value).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_houseSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withHouse(ALICE.getHouse().value).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_genderSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withGender(ALICE.getGender().value).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagsSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withTags(ALICE.getTags()).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_roomSpecifiedUnfilteredListForAll_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withRoom(ALICE.getRoom().value).build();
        AttributesMatchAllKeywordsPredicate predicate =
                new AttributesMatchAllKeywordsPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ALL_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredListForAny_success() {
        AttributesMatchAnyKeywordPredicate predicate = new AttributesMatchAnyKeywordPredicate(
                new ResidentStringDescriptorBuilder(ALICE).build());
        RemoveCommand command = new RemoveCommand(new ResidentStringDescriptorBuilder(ALICE).build(), ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder().withName(ALICE.getName().value)
                .withPhone(ALICE.getPhone().value).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nameSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withName(ALICE.getName().value).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_phoneSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withPhone(ALICE.getPhone().value).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emailSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withEmail(ALICE.getEmail().value).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_matricNumberSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withMatricNumber(ALICE.getMatricNumber().value).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_houseSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withHouse(ALICE.getHouse().value).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_genderSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withGender(ALICE.getGender().value).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagsSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withTags(ALICE.getTags()).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_roomSpecifiedUnfilteredListForAny_success() {
        ResidentStringDescriptor descriptor = new ResidentStringDescriptorBuilder()
                .withRoom(ALICE.getRoom().value).build();
        AttributesMatchAnyKeywordPredicate predicate =
                new AttributesMatchAnyKeywordPredicate(descriptor);
        RemoveCommand command = new RemoveCommand(descriptor, ANY_SPECIFIER);
        int deleted = deleteResidents(predicate);
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVED_SUCCESS, deleted);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void equals() {
        ResidentStringDescriptor firstDescriptor = new ResidentStringDescriptorBuilder(AMY).build();
        ResidentStringDescriptor secondDescriptor = new ResidentStringDescriptorBuilder(ALICE).build();

        RemoveCommand removeFirstCommand = new RemoveCommand(firstDescriptor, ALL_SPECIFIER);
        RemoveCommand removeSecondCommand = new RemoveCommand(secondDescriptor, ALL_SPECIFIER);

        // same object -> returns true
        assertTrue(removeFirstCommand.equals(removeFirstCommand));

        // same values -> returns true
        RemoveCommand removeFirstCommandCopy = new RemoveCommand(firstDescriptor, ALL_SPECIFIER);
        assertTrue(removeFirstCommand.equals(removeFirstCommandCopy));

        // different types -> returns false
        assertFalse(removeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(removeFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(removeFirstCommand.equals(removeSecondCommand));
    }

}


