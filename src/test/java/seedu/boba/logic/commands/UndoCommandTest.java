package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.REWARD_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.TAG_DESC_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.TAG_DESC_MEMBER;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_MEMBER;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.boba.logic.commands.EditCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.boba.logic.commands.RedoCommand.MESSAGE_REDO_SUCCESS;
import static seedu.boba.logic.commands.UndoCommand.MESSAGE_UNDO_SUCCESS;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;
import static seedu.boba.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.boba.testutil.TypicalEmails.EMAIL_SECOND_PERSON;
import static seedu.boba.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBot;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Name;
import seedu.boba.model.customer.NameContainsKeywordsPredicate;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.exceptions.PreviousStateNotFoundException;
import seedu.boba.testutil.CustomerBuilder;
import seedu.boba.testutil.EditCustomerDescriptorBuilder;

import java.util.Arrays;


public class UndoCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

    @Test
    public void execute_undoWhenInitialised_throwsPreviousStateNotFoundException() {
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterExitCommand_throwsPreviousStateNotFoundException() {
        CommandResult commandResult = new ExitCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterListCommand_throwsPreviousStateNotFoundException() {
        CommandResult commandResult = new ListCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterCalculateCommand_throwsPreviousStateNotFoundException() throws CommandException {
        CommandResult commandResult = new CalculateCommand("1+1*9").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterHelpCommand_throwsPreviousStateNotFoundException() {
        CommandResult commandResult = new HelpCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterCalculatorGuiCommand_throwsPreviousStateNotFoundException() {
        CommandResult commandResult = new CalculatorGuiCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterFindCommandViaPhone_throwsPreviousStateNotFoundException() throws CommandException {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setPhone(new Phone("94351253"));
        CommandResult commandResult = new FindCommand(findPersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterFindCommandViaEmail_throwsPreviousStateNotFoundException() throws CommandException {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setEmail(new Email("heinz@example.com"));
        CommandResult commandResult = new FindCommand(findPersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterFindCommandMultiSearch_throwsPreviousStateNotFoundException() throws CommandException {
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        CommandResult commandResult = new FindCommand(predicate).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    @Test
    public void execute_undoAfterClearCommand_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        CommandResult commandResult = new ClearCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterAddCommand_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        CommandResult commandResult = new AddCommand(new CustomerBuilder().build()).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterEditCommandViaEmail_success() throws CommandException, ParseException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        EditCommand.EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withReward(VALID_REWARD_AMY)
                .withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();
        CommandResult commandResult = new EditCommand(EMAIL_SECOND_PERSON, descriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterEditCommandViaPhone_success() throws CommandException, ParseException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        EditCommand.EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withReward(VALID_REWARD_AMY)
                .withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();
        CommandResult commandResult = new EditCommand(EMAIL_SECOND_PERSON, descriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterIncreaseCommandViaPhone_success() throws ParseException, CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        CommandResult commandResult = new IncreaseCommand(PHONE_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterIncreaseCommandViaEmail_success() throws ParseException, CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        CommandResult commandResult = new IncreaseCommand(EMAIL_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterDecreaseCommandViaPhone_success() throws ParseException, CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        CommandResult commandResult = new DecreaseCommand(PHONE_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterDecreaseCommandViaEmail_success() throws ParseException, CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        CommandResult commandResult = new DecreaseCommand(EMAIL_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterDeleteCommandViaPhone_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("94351253"));
        CommandResult commandResult = new DeleteCommand(deletePersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterDeleteCommandViaEmail_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setEmail(new Email("heinz@example.com"));
        CommandResult commandResult = new DeleteCommand(deletePersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoAfterRedoCommand_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        CommandResult commandResult = new ClearCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterClearCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());

        CommandResult expectedUndoCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);
        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedUndoCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);
        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterClearCommand);
    }

    @Test
    public void execute_multipleUndoCommand_success() throws CommandException, ParseException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        //Decrease ALICE rewards
        CommandResult commandResult = new DecreaseCommand(EMAIL_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel expectedModelAfterDecrease = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());

        //Delete ALICE
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("94351253"));
        CommandResult commandResult1 = new DeleteCommand(deletePersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel expectedModelAfterDeletingAlice = new BobaBotModelManager(bobaBotModel.getBobaBot(),
                new UserPrefs());


        //Delete CARL
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor1 = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor1.setEmail(new Email("heinz@example.com"));
        CommandResult commandResult2 = new DeleteCommand(deletePersonDescriptor1).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();

        CommandResult expectedUndoCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        //First undo will return to state after deleting ALICE
        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedUndoCommandResult,
                expectedModelAfterDeletingAlice);

        //Second undo will return to state after decrease command
        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedUndoCommandResult, expectedModelAfterDecrease);

        //Third undo will return to the initialised state
        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedUndoCommandResult, expectedModel);

        //Fourth undo will result in a PreviousStateNotFoundException being thrown since it is
        // already at the initialised state
        assertCommandFailure(new UndoCommand(), bobaBotModel, new PreviousStateNotFoundException().getMessage());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
