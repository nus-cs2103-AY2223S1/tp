package seedu.address.logic.commands.commission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_COMMISSIONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCommissions.ALICE_CAT;
import static seedu.address.testutil.TypicalCommissions.ALICE_DEER;
import static seedu.address.testutil.TypicalCommissions.CARL_ELEPHANT;
import static seedu.address.testutil.TypicalCommissions.CARL_SHARK;
import static seedu.address.testutil.TypicalCommissions.DANIEL_WHALE;
import static seedu.address.testutil.TypicalCommissions.DANIEL_ZEBRA;
import static seedu.address.testutil.TypicalCommissions.getTypicalAddressBookWithCommissions;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommissionCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.commission.CompositeCommissionPredicate;
import seedu.address.model.tag.Tag;

public class FindCommissionCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithCommissions(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithCommissions(), new UserPrefs());

    @Test
    public void equals() {
        CompositeCommissionPredicate firstPredicate =
                new CompositeCommissionPredicate(Collections.singleton("first"),
                        Collections.singleton(new Tag("inking")),
                        Collections.singleton(new Tag("traditional")));
        CompositeCommissionPredicate secondPredicate =
                new CompositeCommissionPredicate(Collections.singleton("second"),
                        Collections.singleton(new Tag("big")),
                        Collections.singleton(new Tag("acrylic")));

        FindCommissionCommand findFirstCommand = new FindCommissionCommand(firstPredicate);
        FindCommissionCommand findSecondCommand = new FindCommissionCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommissionCommand findFirstCommandCopy = new FindCommissionCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different  -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noCommissionFound() {
        String expectedMessage = String.format(MESSAGE_COMMISSIONS_LISTED_OVERVIEW, 0);
        String[] foreignKeywords = new String[]{"knife", "spear"};
        Tag[] foreignMustTags = new Tag[]{new Tag("animal"), new Tag("fantasy")};
        Tag[] foreignAnyTags = new Tag[]{new Tag("anime"), new Tag("gothic")};
        Tag[] emptyTags = new Tag[]{};
        String[] emptyKeywords = new String[]{};

        // Get all commissions.
        expectedModel.selectCustomer(null);
        model.selectCustomer(null);

        // No commission contains any of the given keywords
        CompositeCommissionPredicate predicate = preparePredicate(foreignKeywords, emptyTags, emptyTags);
        FindCommissionCommand command = new FindCommissionCommand(predicate);
        expectedModel.updateFilteredCommissionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCommissionList());

        expectedModel.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        // No commission contains all the must tags.
        predicate = preparePredicate(emptyKeywords, foreignMustTags, emptyTags);
        command = new FindCommissionCommand(predicate);
        expectedModel.updateFilteredCommissionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCommissionList());

        expectedModel.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        // No commission contains any of the optional tags.
        predicate = preparePredicate(emptyKeywords, emptyTags, foreignAnyTags);
        command = new FindCommissionCommand(predicate);
        expectedModel.updateFilteredCommissionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCommissionList());

        expectedModel.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        // No commission meets all the composite requirements.
        predicate = preparePredicate(foreignKeywords, foreignMustTags, foreignAnyTags);
        command = new FindCommissionCommand(predicate);
        expectedModel.updateFilteredCommissionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCommissionList());

        expectedModel.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
    }

    @Test
    public void execute_commissionsFound() {
        String[] keywords = new String[]{"great", "garfield", "african"};
        Tag[] mustTags = new Tag[]{new Tag("mammal"), new Tag("animal")};
        Tag[] anyTags = new Tag[]{new Tag("herbivore"), new Tag("land"), new Tag("insect")};
        Tag[] emptyTags = new Tag[]{};
        String[] emptyKeywords = new String[]{};

        // Get all commissions.
        expectedModel.selectCustomer(null);
        model.selectCustomer(null);

        // Multiple commissions contain at least one of the given keyword
        String expectedMessage = String.format(MESSAGE_COMMISSIONS_LISTED_OVERVIEW, 5);
        CompositeCommissionPredicate predicate = preparePredicate(keywords, emptyTags, emptyTags);
        FindCommissionCommand command = new FindCommissionCommand(predicate);
        expectedModel.updateFilteredCommissionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new HashSet<>(Arrays.asList(CARL_SHARK, DANIEL_WHALE, ALICE_CAT, DANIEL_ZEBRA, CARL_ELEPHANT)),
                new HashSet<>(model.getFilteredCommissionList()));

        expectedModel.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        // Multiple commissions all the must-have tags.
        expectedMessage = String.format(MESSAGE_COMMISSIONS_LISTED_OVERVIEW, 3);
        predicate = preparePredicate(emptyKeywords, mustTags, emptyTags);
        command = new FindCommissionCommand(predicate);
        expectedModel.updateFilteredCommissionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new HashSet<>(Arrays.asList(DANIEL_ZEBRA, DANIEL_WHALE, ALICE_DEER)),
                new HashSet<>(model.getFilteredCommissionList()));

        expectedModel.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        // Multiple commissions contain at least one of the optional tags.
        expectedMessage = String.format(MESSAGE_COMMISSIONS_LISTED_OVERVIEW, 3);
        predicate = preparePredicate(emptyKeywords, emptyTags, anyTags);
        command = new FindCommissionCommand(predicate);
        expectedModel.updateFilteredCommissionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new HashSet<>(Arrays.asList(CARL_ELEPHANT, DANIEL_ZEBRA, ALICE_DEER)),
                new HashSet<>(model.getFilteredCommissionList()));

        expectedModel.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        // One commission meet all three criteria.
        expectedMessage = String.format(MESSAGE_COMMISSIONS_LISTED_OVERVIEW, 1);
        predicate = preparePredicate(keywords, mustTags, anyTags);
        command = new FindCommissionCommand(predicate);
        expectedModel.updateFilteredCommissionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(new HashSet<>(Arrays.asList(DANIEL_ZEBRA)),
                new HashSet<>(model.getFilteredCommissionList()));

        expectedModel.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
    }

    /**
     * Combines keywords, must-have tags and optional tags into a {@code CompositeCustomerPredicate}.
     */
    public static CompositeCommissionPredicate preparePredicate(String[] keywords, Tag[] mustTags, Tag[] optionalTags) {
        return new CompositeCommissionPredicate(new HashSet<>(Arrays.asList(keywords)),
                new HashSet<>(Arrays.asList(mustTags)), new HashSet<>(Arrays.asList(optionalTags)));
    }
}
