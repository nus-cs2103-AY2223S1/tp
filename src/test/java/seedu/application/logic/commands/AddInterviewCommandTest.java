package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;
import static seedu.application.testutil.TypicalApplicationsWithInterview.getTypicalApplicationBookWithInterview;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.application.testutil.TypicalInterviews.INTERVIEW_SHOPEE;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.Messages;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.application.Application;
import seedu.application.model.application.interview.Interview;
import seedu.application.testutil.InterviewBuilder;

public class AddInterviewCommandTest {
    private static final String INTERVIEW_DATE_BEFORE_APPLICATION = "1988-01-09";
    private Model model = new ModelManager(getTypicalApplicationBookWithInterview(), new UserPrefs());
    private Model modelWithoutInterview = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void constructor_nullInterview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInterviewCommand(null, null));
    }

    @Test
    public void execute_interviewAcceptedByModel_addSuccessful() throws Exception {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application lastApplication = model.getFilteredApplicationList().get(indexLastApplication.getZeroBased());

        CommandResult commandResult = new AddInterviewCommand(indexLastApplication, INTERVIEW_SHOPEE)
                .execute(model);

        assertEquals(String.format(AddInterviewCommand.MESSAGE_SUCCESS, lastApplication),
                commandResult.getFeedbackToUser());
    }


    @Test
    public void execute_duplicateInterview_throwsCommandException() throws Exception {
        Interview validInterview = new InterviewBuilder().build();
        CommandResult addCommandFirst = new AddInterviewCommand(INDEX_FIRST_APPLICATION, validInterview).execute(model);
        AddInterviewCommand addCommandSecond = new AddInterviewCommand(INDEX_SECOND_APPLICATION, validInterview);

        assertThrows(CommandException.class,
                AddInterviewCommand.MESSAGE_DUPLICATE_INTERVIEW, () -> addCommandSecond.execute(model));
    }

    @Test
    public void execute_duplicateInterviewInModelWithoutInterview_throwsCommandException() throws Exception {
        Interview validInterview = new InterviewBuilder().build();
        CommandResult addCommandFirst = new AddInterviewCommand(INDEX_FIRST_APPLICATION, validInterview)
                .execute(modelWithoutInterview);
        AddInterviewCommand addCommandSecond = new AddInterviewCommand(INDEX_SECOND_APPLICATION, validInterview);

        assertThrows(CommandException.class,
                AddInterviewCommand.MESSAGE_DUPLICATE_INTERVIEW, () -> addCommandSecond.execute(modelWithoutInterview));
    }

    @Test
    public void execute_invalidApplication_throwsCommandException() {
        Interview validInterview = new InterviewBuilder().build();
        AddInterviewCommand addCommand = new AddInterviewCommand(Index.fromZeroBased(Integer.MAX_VALUE),
                validInterview);

        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX, () -> addCommand.execute(model));
    }

    @Test
    public void execute_invalidInterview_throwsCommandException() throws Exception {
        Interview invalidInterview = new InterviewBuilder().withInterviewDate(INTERVIEW_DATE_BEFORE_APPLICATION)
                .build();
        AddInterviewCommand addCommandSecond = new AddInterviewCommand(INDEX_SECOND_APPLICATION, invalidInterview);

        assertThrows(CommandException.class,
                AddInterviewCommand.MESSAGE_INVALID_INTERVIEW, () -> addCommandSecond.execute(model));
    }


    @Test
    public void equals() {
        Interview googleInterview = new InterviewBuilder().withRound("Technical interview 1").build();
        Interview shopeeInterview = new InterviewBuilder().withRound("Online assessment").build();
        AddInterviewCommand addGoogleCommand = new AddInterviewCommand(INDEX_FIRST_APPLICATION, googleInterview);
        AddInterviewCommand addShopeeCommand = new AddInterviewCommand(INDEX_FIRST_APPLICATION, shopeeInterview);

        // same object -> returns true
        assertTrue(addGoogleCommand.equals(addGoogleCommand));

        // same values -> returns true
        AddInterviewCommand addGoogleCommandCopy = new AddInterviewCommand(INDEX_FIRST_APPLICATION, googleInterview);
        assertTrue(addGoogleCommand.equals(addGoogleCommandCopy));

        // different types -> returns false
        assertFalse(addGoogleCommand.equals(1));

        // null -> returns false
        assertFalse(addGoogleCommand.equals(null));

        // different interview -> returns false
        assertFalse(addGoogleCommand.equals(addShopeeCommand));
    }


}
