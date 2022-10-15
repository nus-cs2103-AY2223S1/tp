package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Person;
import seedu.address.model.student.TagContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;


public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    public Model mockDatabase() {
        Person john = new PersonBuilder().withName("John").withTags("friends").build();
        Person cena = new PersonBuilder().withName("Cena").withTags("friends").build();
        AddressBook ab = new AddressBook();
        ab.addPerson(john);
        ab.addPerson(cena);
        Model model = new ModelManager(ab, new UserPrefs());
        return model;
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.filterPersonListWithTag(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleKeyword_multiplePersonsFound() {
        Model initialModel = mockDatabase();
        Model outputModel = mockDatabase();
        Person john = outputModel.getAddressBook().getPersonList().get(0);
        Person cena = outputModel.getAddressBook().getPersonList().get(1);
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = preparePredicate("friends");
        FilterCommand command = new FilterCommand(predicate);
        outputModel.filterPersonListWithTag(predicate);
        assertCommandSuccess(command, initialModel, expectedMessage, outputModel);
        assertEquals(Arrays.asList(john, cena), outputModel.getFilteredPersonList());


    }

    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
