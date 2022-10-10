package seedu.rc4hdb.logic.commands.modelcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalPersons.ALICE;
import static seedu.rc4hdb.testutil.TypicalPersons.AMY;
import static seedu.rc4hdb.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


import seedu.rc4hdb.logic.commands.modelcommands.FilterCommand.FilterPersonDescriptor;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.person.AttributesMatchKeywordsPredicate;
import seedu.rc4hdb.testutil.FilterPersonDescriptorBuilder;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(new FilterPersonDescriptorBuilder(ALICE).build());
        FilterCommand command = new FilterCommand(new FilterPersonDescriptorBuilder(ALICE).build());
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        FilterPersonDescriptor descriptor = new FilterPersonDescriptorBuilder().withName(ALICE.getName())
                .withPhone(ALICE.getPhone()).build();
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        AttributesMatchKeywordsPredicate predicate =
                new AttributesMatchKeywordsPredicate(descriptor);
        FilterCommand command = new FilterCommand(descriptor);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }


    @Test
    public void equals() {
        FilterCommand.FilterPersonDescriptor firstDescriptor = new FilterPersonDescriptorBuilder(AMY).build();
        FilterCommand.FilterPersonDescriptor secondDescriptor = new FilterPersonDescriptorBuilder(ALICE).build();

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

