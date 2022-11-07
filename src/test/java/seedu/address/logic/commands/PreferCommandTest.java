package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OCCUPATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.SocialTestUtil.INVALID_SOCIALS;
import static seedu.address.logic.commands.SocialTestUtil.VALID_EMAIL;
import static seedu.address.logic.commands.SocialTestUtil.VALID_INSTAGRAM;
import static seedu.address.logic.commands.SocialTestUtil.VALID_TELEGRAM;
import static seedu.address.logic.commands.SocialTestUtil.VALID_WHATSAPP;
import static seedu.address.logic.commands.SocialTestUtil.getAmySocial;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getSocialAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests and unit test for PreferCommand.
 */
public class PreferCommandTest {
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PreferCommand preferCommand = new PreferCommand(outOfBoundIndex, VALID_WHATSAPP);

        assertCommandFailure(preferCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validSocials() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        PreferCommand preferCommand = new PreferCommand(INDEX_FIRST_PERSON, INVALID_SOCIALS);
        assertCommandFailure(preferCommand, model, PreferCommand.MESSAGE_WRONG_SOCIAL);
    }

    @Test
    public void execute_equalTest() throws CommandException {
        Model model = new ModelManager(getSocialAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getSocialAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder().withOccupation(VALID_OCCUPATION_AMY).withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(CommandTestUtil.VALID_EMAIL_AMY).withTutorial(VALID_TUTORIAL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).withSocial(getAmySocial()).build();
        PreferCommand preferWhatsapp = new PreferCommand(INDEX_FIRST_PERSON, VALID_WHATSAPP);
        PreferCommand preferTelegram = new PreferCommand(INDEX_FIRST_PERSON, VALID_TELEGRAM);
        PreferCommand preferEmail = new PreferCommand(INDEX_FIRST_PERSON, VALID_EMAIL);
        PreferCommand preferInstagram = new PreferCommand(INDEX_FIRST_PERSON, VALID_INSTAGRAM);

        CommandResult commandResult = preferWhatsapp.execute(model);
        editedPerson.getSocial().prefer(VALID_WHATSAPP);
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), editedPerson);
        assertEquals(String.format(preferWhatsapp.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        assertEquals(model, expectedModel);

        commandResult = preferTelegram.execute(model);
        editedPerson.getSocial().prefer(VALID_TELEGRAM);
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), editedPerson);
        assertEquals(String.format(preferTelegram.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        assertEquals(model, expectedModel);

        commandResult = preferEmail.execute(model);
        editedPerson.getSocial().prefer(VALID_EMAIL);
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), editedPerson);
        assertEquals(String.format(preferEmail.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        assertEquals(model, expectedModel);

        commandResult = preferInstagram.execute(model);
        editedPerson.getSocial().prefer(VALID_INSTAGRAM);
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), editedPerson);
        assertEquals(String.format(preferInstagram.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        assertEquals(model, expectedModel);
    }
}
