package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class UnappendCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_singleSurveyUnappended_success() {
        Person unappendedPerson = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withGender("female").withBirthdate("1998-12-28")
                .withRace("White American").withSurveys(Collections.emptySet())
                .withReligion("Christian").withTags("friends").build();
        Set<Survey> surveysToRemove = new HashSet<Survey>();
        surveysToRemove.add(new Survey("Environment Survey"));
        Set<Tag> tagsToRemove = Collections.emptySet();
        UnappendCommand unappendCommand = new UnappendCommand(INDEX_FIRST_PERSON, surveysToRemove, tagsToRemove);

        String expectedMessage = String.format(UnappendCommand.MESSAGE_UNAPPEND_SUCCESS, unappendedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), unappendedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(unappendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_singleTagUnappended_success() {
        Person unappendedPerson = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withGender("female").withBirthdate("1998-12-28")
                .withRace("White American").withSurvey("Environment Survey", false)
                .withTags(Collections.emptySet()).withReligion("Christian").build();
        Set<Survey> surveysToRemove = Collections.emptySet();
        Set<Tag> tagsToRemove = new HashSet<Tag>();
        tagsToRemove.add(new Tag("friends"));
        UnappendCommand unappendCommand = new UnappendCommand(INDEX_FIRST_PERSON, surveysToRemove, tagsToRemove);

        String expectedMessage = String.format(UnappendCommand.MESSAGE_UNAPPEND_SUCCESS, unappendedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), unappendedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(unappendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_missingSurveyUnappended_failure() {
        Set<Survey> surveysToRemove = new HashSet<Survey>();
        surveysToRemove.add(new Survey("Random Survey"));
        Set<Tag> tagsToRemove = Collections.emptySet();
        UnappendCommand unappendCommand = new UnappendCommand(INDEX_FIRST_PERSON, surveysToRemove, tagsToRemove);

        String expectedMessage = String.format(UnappendCommand.MESSAGE_SURVEY_TAG_NOT_FOUND);

        assertCommandFailure(unappendCommand, model, expectedMessage);
    }

    @Test
    public void execute_missingTagUnappended_failure() {
        Set<Survey> surveysToRemove = Collections.emptySet();
        Set<Tag> tagsToRemove = new HashSet<Tag>();
        tagsToRemove.add(new Tag("RandomTag"));

        UnappendCommand unappendCommand = new UnappendCommand(INDEX_FIRST_PERSON, surveysToRemove, tagsToRemove);

        String expectedMessage = String.format(UnappendCommand.MESSAGE_SURVEY_TAG_NOT_FOUND);

        assertCommandFailure(unappendCommand, model, expectedMessage);
    }

    @Test
    public void execute_missingSurveysTagsUnappended_failure() {
        Set<Survey> surveysToRemove = new HashSet<Survey>();
        surveysToRemove.add(new Survey("Random Survey1"));
        surveysToRemove.add(new Survey("Random Survey2"));
        Set<Tag> tagsToRemove = new HashSet<Tag>();
        tagsToRemove.add(new Tag("RandomTag1"));
        tagsToRemove.add(new Tag("RandomTag2"));

        UnappendCommand unappendCommand = new UnappendCommand(INDEX_FIRST_PERSON, surveysToRemove, tagsToRemove);

        String expectedMessage = String.format(UnappendCommand.MESSAGE_SURVEY_TAG_NOT_FOUND);

        assertCommandFailure(unappendCommand, model, expectedMessage);
    }
}
