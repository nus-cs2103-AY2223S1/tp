package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagMatchesQueryPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FilterListCommandTest {
    private static final String COMMA = "\\s*,\\s*";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        FilterListCommand findFirstCommand = new FilterListCommand();
        FilterListCommand findSecondCommand = new FilterListCommand();

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different comand -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }


    @Test
    public void execute_list() {
        Set<NameContainsKeywordsPredicate> namePredicates = prepareNamePredicate("Alice");
        Set<TagMatchesQueryPredicate> tagPredicates = prepareTagPredicate("owesMoney");

        FilterListCommand command = new FilterListCommand();
        String expectedMessage1 = Messages.MESSAGE_NO_FILTERS_APPLIED;
        assertCommandSuccess(command, model, expectedMessage1, expectedModel);


        FilterCommandPredicate predicate1 = new FilterCommandPredicate(namePredicates, null);
        expectedModel.addNewFilterToFilteredPersonList(predicate1);
        model.addNewFilterToFilteredPersonList(predicate1);
        String expectedMessage2 = "Name filters: [alice]\n";
        assertCommandSuccess(command, model, expectedMessage2, expectedModel);

        expectedModel.clearFiltersInFilteredPersonList();
        model.clearFiltersInFilteredPersonList();

        FilterCommandPredicate predicate2 = new FilterCommandPredicate(null, tagPredicates);
        expectedModel.addNewFilterToFilteredPersonList(predicate2);
        model.addNewFilterToFilteredPersonList(predicate2);
        String expectedMessage3 = "Tag filters: [owesMoney]";
        assertCommandSuccess(command, model, expectedMessage3, expectedModel);

        expectedModel.clearFiltersInFilteredPersonList();
        model.clearFiltersInFilteredPersonList();

        FilterCommandPredicate predicate3 = new FilterCommandPredicate(namePredicates, tagPredicates);
        expectedModel.addNewFilterToFilteredPersonList(predicate3);
        model.addNewFilterToFilteredPersonList(predicate3);
        String expectedMessage4 = expectedMessage2 + expectedMessage3;
        assertCommandSuccess(command, model, expectedMessage4, expectedModel);
    }

    /**
     * Parses {@code userInput} into a set of {@code NameContainsKeywordsPredicate}.
     */
    private Set<NameContainsKeywordsPredicate> prepareNamePredicate(String userInput) {
        return Stream.of(userInput.split(COMMA)).map(name -> new NameContainsKeywordsPredicate(name))
                .collect(Collectors.toSet());
    }

    /**
     * Parses {@code userInput} into a set of {@code TagMatchesQueryPredicate}.
     */
    private Set<TagMatchesQueryPredicate> prepareTagPredicate(String userInput) {
        return Stream.of(userInput.split(COMMA)).map(input -> new Tag(input))
                .map(tag -> new TagMatchesQueryPredicate(tag)).collect(Collectors.toSet());
    }
}
