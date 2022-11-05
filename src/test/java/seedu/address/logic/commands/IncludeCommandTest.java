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
import static seedu.address.logic.commands.SocialTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.SocialTestUtil.VALID_INSTAGRAM;
import static seedu.address.logic.commands.SocialTestUtil.VALID_INSTAGRAM_AMY;
import static seedu.address.logic.commands.SocialTestUtil.VALID_TELEGRAM;
import static seedu.address.logic.commands.SocialTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.SocialTestUtil.VALID_WHATSAPP;
import static seedu.address.logic.commands.SocialTestUtil.VALID_WHATSAPP_AMY;
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
 * Contains integration tests and unit test for IncludeCommand.
 */
public class IncludeCommandTest {

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        IncludeCommand includeCommand = new IncludeCommand(outOfBoundIndex, VALID_WHATSAPP, VALID_WHATSAPP_AMY);

        assertCommandFailure(includeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validSocials() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        IncludeCommand includeCommand = new IncludeCommand(INDEX_FIRST_PERSON, INVALID_SOCIALS, VALID_WHATSAPP_AMY);
        assertCommandFailure(includeCommand, model, IncludeCommand.MESSAGE_WRONG_SOCIAL);
    }

    @Test
    public void execute_equalTest() throws CommandException {
        Model model = new ModelManager(getSocialAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getSocialAddressBook(), new UserPrefs());
        Person editedPerson = new PersonBuilder().withOccupation(VALID_OCCUPATION_AMY).withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(CommandTestUtil.VALID_EMAIL_AMY).withTutorial(VALID_TUTORIAL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
        IncludeCommand includeWhatsapp = new IncludeCommand(INDEX_FIRST_PERSON, VALID_WHATSAPP, VALID_WHATSAPP_AMY);
        IncludeCommand includeTelegram = new IncludeCommand(INDEX_FIRST_PERSON, VALID_TELEGRAM, VALID_TELEGRAM_AMY);
        IncludeCommand includeEmail = new IncludeCommand(INDEX_FIRST_PERSON, VALID_EMAIL, VALID_EMAIL_AMY);
        IncludeCommand includeInstagram = new IncludeCommand(INDEX_FIRST_PERSON, VALID_INSTAGRAM, VALID_INSTAGRAM_AMY);
        model.setPerson(expectedModel.getFilteredPersonList().get(0), editedPerson); // Remove social from first person in model

        CommandResult commandResult = includeWhatsapp.execute(model);
        assertEquals(String.format(IncludeCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        commandResult = includeTelegram.execute(model);
        assertEquals(String.format(IncludeCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        commandResult = includeEmail.execute(model);
        assertEquals(String.format(IncludeCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        commandResult = includeInstagram.execute(model);
        assertEquals(String.format(IncludeCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        assertEquals(model, expectedModel);
    }
}
