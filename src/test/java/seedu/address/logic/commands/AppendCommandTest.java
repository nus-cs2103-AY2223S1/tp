package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AppendCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AppendCommand(null, Collections.emptySet(), Collections.emptySet()));
        assertThrows(NullPointerException.class, () ->
                new AppendCommand(Index.fromZeroBased(0), null, Collections.emptySet()));
        assertThrows(NullPointerException.class, () ->
                new AppendCommand(Index.fromZeroBased(0), Collections.emptySet(), null));
    }

    @Test
    public void execute_singleSurveyAppended_success() {
        Set<Survey> surveys = new HashSet<Survey>();
        surveys.add(new Survey("Environment Survey"));
        surveys.add(new Survey("Appended Survey"));
        Person appendedPerson = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withGender("female").withBirthdate("1998-12-28")
                .withRace("White American").withReligion("Christian").withSurveys(surveys)
                .withTags("friends").build();
        Set<Survey> newSurveys = new HashSet<Survey>();
        newSurveys.add(new Survey("Appended Survey"));
        Set<Tag> newTags = Collections.emptySet();
        AppendCommand appendCommand = new AppendCommand(INDEX_FIRST_PERSON, newSurveys, newTags);

        String expectedMessage = String.format(AppendCommand.MESSAGE_APPEND_SUCCESS, appendedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), appendedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(appendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleSurveysAppended_success() {
        Set<Survey> surveys = new HashSet<Survey>();
        surveys.add(new Survey("Environment Survey"));
        surveys.add(new Survey("Appended Survey"));
        surveys.add(new Survey("Appended Survey2"));
        Person appendedPerson = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withGender("female").withBirthdate("1998-12-28")
                .withRace("White American").withReligion("Christian").withSurveys(surveys)
                .withTags("friends").build();
        Set<Survey> newSurveys = new HashSet<Survey>();
        newSurveys.add(new Survey("Appended Survey"));
        newSurveys.add(new Survey("Appended Survey2"));
        Set<Tag> newTags = Collections.emptySet();
        AppendCommand appendCommand = new AppendCommand(INDEX_FIRST_PERSON, newSurveys, newTags);

        String expectedMessage = String.format(AppendCommand.MESSAGE_APPEND_SUCCESS, appendedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), appendedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(appendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleSurveysAndTagsAppended_success() {
        Set<Survey> surveys = new HashSet<Survey>();
        surveys.add(new Survey("Environment Survey"));
        surveys.add(new Survey("Appended Survey"));
        surveys.add(new Survey("Appended Survey2"));
        Person appendedPerson = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withGender("female").withBirthdate("1998-12-28")
                .withRace("White American").withReligion("Christian").withSurveys(surveys)
                .withTags("friends", "neighbours", "student").build();
        Set<Survey> newSurveys = new HashSet<Survey>();
        newSurveys.add(new Survey("Appended Survey"));
        newSurveys.add(new Survey("Appended Survey2"));
        Set<Tag> newTags = new HashSet<Tag>();
        newTags.add(new Tag("neighbours"));
        newTags.add(new Tag("student"));
        AppendCommand appendCommand = new AppendCommand(INDEX_FIRST_PERSON, newSurveys, newTags);

        String expectedMessage = String.format(AppendCommand.MESSAGE_APPEND_SUCCESS, appendedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), appendedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(appendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_existingSurveyAppended_failure() {
        Set<Survey> newSurveys = new HashSet<Survey>();
        newSurveys.add(new Survey("Environment Survey"));
        Set<Tag> newTags = Collections.emptySet();
        AppendCommand appendCommand = new AppendCommand(INDEX_FIRST_PERSON, newSurveys, newTags);

        String expectedMessage = String.format(AppendCommand.MESSAGE_SURVEY_FOUND);

        assertCommandFailure(appendCommand, model, expectedMessage);
    }

    @Test
    public void execute_existingTagAppended_failure() {
        Set<Survey> newSurveys = Collections.emptySet();
        Set<Tag> newTags = new HashSet<Tag>();
        newTags.add(new Tag("friends"));
        AppendCommand appendCommand = new AppendCommand(INDEX_FIRST_PERSON, newSurveys, newTags);

        String expectedMessage = String.format(AppendCommand.MESSAGE_TAG_FOUND);

        assertCommandFailure(appendCommand, model, expectedMessage);
    }

    @Test
    public void execute_existingSurveyAndTagAppended_failure() {
        Set<Survey> newSurveys = new HashSet<Survey>();
        newSurveys.add(new Survey("Environment Survey"));
        Set<Tag> newTags = new HashSet<Tag>();
        newTags.add(new Tag("friends"));
        AppendCommand appendCommand = new AppendCommand(INDEX_FIRST_PERSON, newSurveys, newTags);

        // NOTE: Survey error message takes priority.
        String expectedMessage = String.format(AppendCommand.MESSAGE_SURVEY_FOUND);

        assertCommandFailure(appendCommand, model, expectedMessage);
    }
}
