package seedu.boba.logic.commands;

import static seedu.boba.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_REWARD_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_GOLD;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_TAG_MEMBER;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.boba.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.boba.logic.commands.RedoCommand.MESSAGE_REDO_SUCCESS;
import static seedu.boba.logic.commands.UndoCommand.MESSAGE_UNDO_SUCCESS;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;
import static seedu.boba.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.boba.testutil.TypicalEmails.EMAIL_SECOND_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_FIRST_PERSON;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.UserPrefs;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.NameContainsKeywordsPredicate;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.exceptions.NextStateNotFoundException;
import seedu.boba.model.exceptions.PreviousStateNotFoundException;
import seedu.boba.testutil.CustomerBuilder;
import seedu.boba.testutil.EditCustomerDescriptorBuilder;

public class RedoCommandTest {
    private BobaBotModel bobaBotModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

    @Test
    public void execute_redoWhenInitialised_throwsNextStateNotFoundException() {
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterExitCommand_throwsNextStateNotFoundException() {
        CommandResult commandResult = new ExitCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterListCommand_throwsNextStateNotFoundException() {
        CommandResult commandResult = new ListCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterCalculateCommand_throwsNextStateNotFoundException() throws CommandException {
        CommandResult commandResult = new CalculateCommand("1+1*9").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterHelpCommand_throwsNextStateNotFoundException() {
        CommandResult commandResult = new HelpCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterCalculatorGuiCommand_throwsNextStateNotFoundException() {
        CommandResult commandResult = new CalculatorGuiCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterFindCommandViaPhone_throwsNextStateNotFoundException() throws CommandException {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setPhone(new Phone("94351253"));
        CommandResult commandResult = new FindCommand(findPersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterFindCommandViaEmail_throwsNextStateNotFoundException() throws CommandException {
        FindCommand.FindPersonDescriptor findPersonDescriptor = new FindCommand.FindPersonDescriptor();
        findPersonDescriptor.setEmail(new Email("heinz@example.com"));
        CommandResult commandResult = new FindCommand(findPersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterFindCommandMultiSearch_throwsNextStateNotFoundException() throws CommandException {
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        CommandResult commandResult = new FindCommand(predicate).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterClearCommand_throwsNextStateNotFoundException() {
        CommandResult commandResult = new ClearCommand().execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterAddCommand_throwsNextStateNotFoundException() throws CommandException {
        CommandResult commandResult = new AddCommand(new CustomerBuilder().build()).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterEditCommand_throwsNextStateNotFoundException() throws CommandException,
            ParseException {
        EditCommand.EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withReward(VALID_REWARD_AMY)
                .withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();
        CommandResult commandResult = new EditCommand(EMAIL_SECOND_PERSON, descriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterIncreaseCommand_throwsNextStateNotFoundException() throws CommandException,
            ParseException {
        CommandResult commandResult = new IncreaseCommand(PHONE_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterDecreaseCommand_throwsNextStateNotFoundException() throws CommandException,
            ParseException {
        CommandResult commandResult = new DecreaseCommand(PHONE_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterDeleteCommandViaPhone_throwsNextStateNotFoundException() throws CommandException,
            ParseException {
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("94351253"));
        CommandResult commandResult = new DeleteCommand(deletePersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    @Test
    public void execute_redoAfterDeleteCommandViaEmail_throwsNextStateNotFoundException() throws CommandException,
            ParseException {
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setEmail(new Email("heinz@example.com"));
        CommandResult commandResult = new DeleteCommand(deletePersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }


    //----------------------------Start of Successful RedoCommands---------------------------
    @Test
    public void execute_redoAfterUndoClearCommand_success() {
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
    public void execute_redoAfterUndoAddCommand_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        CommandResult commandResult = new AddCommand(new CustomerBuilder().build()).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterAddCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterAddCommand);
    }

    @Test
    public void execute_redoAfterUndoEditCommandViaEmail_success() throws CommandException, ParseException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        EditCommand.EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withReward(VALID_REWARD_AMY)
                .withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();
        CommandResult commandResult = new EditCommand(EMAIL_SECOND_PERSON, descriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterEditCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterEditCommand);
    }

    @Test
    public void execute_redoAfterUndoEditCommandViaPhone_success() throws CommandException, ParseException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());

        EditCommand.EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withReward(VALID_REWARD_AMY)
                .withTags(VALID_TAG_GOLD, VALID_TAG_MEMBER).build();
        CommandResult commandResult = new EditCommand(EMAIL_SECOND_PERSON, descriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterEditCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterEditCommand);
    }

    @Test
    public void execute_redoAfterUndoIncreaseCommandViaPhone_success() throws ParseException, CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        CommandResult commandResult = new IncreaseCommand(PHONE_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterIncreaseCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterIncreaseCommand);
    }

    @Test
    public void execute_redoAfterUndoIncreaseCommandViaEmail_success() throws ParseException, CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        CommandResult commandResult = new IncreaseCommand(EMAIL_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterIncreaseCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterIncreaseCommand);
    }

    @Test
    public void execute_redoAfterUndoDecreaseCommandViaPhone_success() throws ParseException, CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        CommandResult commandResult = new DecreaseCommand(PHONE_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterDecreaseCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterDecreaseCommand);
    }

    @Test
    public void execute_redoAfterUndoDecreaseCommandViaEmail_success() throws ParseException, CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        CommandResult commandResult = new DecreaseCommand(EMAIL_FIRST_PERSON, "100").execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterDecreaseCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterDecreaseCommand);
    }

    @Test
    public void execute_redoAfterUndoDeleteCommandViaPhone_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("94351253"));
        CommandResult commandResult = new DeleteCommand(deletePersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterDeleteCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterDeleteCommand);
    }

    @Test
    public void execute_redoAfterUndoDeleteCommandViaEmail_success() throws CommandException {
        BobaBotModel expectedModel = new BobaBotModelManager(getTypicalBobaBot(), new UserPrefs());
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setEmail(new Email("heinz@example.com"));
        CommandResult commandResult = new DeleteCommand(deletePersonDescriptor).execute(bobaBotModel);
        bobaBotModel.commitBobaBot();
        BobaBotModel modelAfterDeleteCommand = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        assertCommandSuccess(new UndoCommand(), bobaBotModel, expectedCommandResult, expectedModel);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, modelAfterDeleteCommand);
    }

    @Test
    public void execute_multipleRedoCommand_success() throws CommandException, ParseException {
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
        BobaBotModel expectedModelAfterDeletingCarl = new BobaBotModelManager(bobaBotModel.getBobaBot(),
                new UserPrefs());

        CommandResult expectedUndoCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS, false,
                false, true, false, false);

        CommandResult expectedRedoCommandResult = new CommandResult(MESSAGE_REDO_SUCCESS, false,
                false, false, true, false);

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

        //First redo will return to state after decreasing ALICE rewards
        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult, expectedModelAfterDecrease);

        //Second redo will return to state after deleting ALICE
        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult,
                expectedModelAfterDeletingAlice);

        //Third redo will return to state after deleting CARL
        assertCommandSuccess(new RedoCommand(), bobaBotModel, expectedRedoCommandResult,
                expectedModelAfterDeletingCarl);

        //Fourth redo will result in a NextStateNotFoundException being thrown since it is
        //already in the most updated state
        assertCommandFailure(new RedoCommand(), bobaBotModel, new NextStateNotFoundException().getMessage());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

