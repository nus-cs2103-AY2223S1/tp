package seedu.address.logic.commands.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.predicates.IssueContainsKeywordsPredicate;
import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;


public class FindIssueCommandTest {
    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_findIssueTitle_success() {
        Model actualModel = new ModelManager();
        Issue issueOne = new Issue(new Title("Fix some bugs"),
                new Deadline("2022-02-02"), Urgency.MEDIUM,
                new Status(false), new Project(new Name("Stub")), new IssueId(1),
                new Pin(false));
        Issue issueTwo = new Issue(new Title("Fix the UG"),
                new Deadline("2022-01-02"), Urgency.HIGH,
                new Status(false), new Project(new Name("Stub")), new IssueId(2),
                new Pin(false));
        Issue issueThree = new Issue(new Title("Fix the DG"),
                new Deadline("2022-01-02"), Urgency.HIGH,
                new Status(false), new Project(new Name("Stub")), new IssueId(3),
                new Pin(false));

        actualModel.addIssue(issueOne);
        actualModel.addIssue(issueTwo);
        actualModel.addIssue(issueThree);

        CommandResult expectedCommandResultName = new CommandResult(
                String.format(Messages.MESSAGE_ISSUES_LISTED_OVERVIEW,
                        actualModel.getFilteredIssueList().size()));

        Model expectedModel = new ModelManager();
        expectedModel.addIssue(issueOne);
        expectedModel.addIssue(issueTwo);
        expectedModel.addIssue(issueThree);

        List<String> title = Arrays.asList("fix");
        List<String> empty = new ArrayList<>();


        FindIssueCommand findTitle = new FindIssueCommand(new IssueContainsKeywordsPredicate(title, empty, empty,
                empty, empty, empty));

        assertCommandSuccess(findTitle, actualModel, expectedCommandResultName, expectedModel, stubUi);

    }

    @Test
    public void execute_findIssueStatus_success() {
        Model actualModel = new ModelManager();
        Issue issueOne = new Issue(new Title("Fix some bugs"),
                new Deadline("2022-02-02"), Urgency.MEDIUM,
                new Status(true), new Project(new Name("Stub")), new IssueId(1),
                new Pin(false));
        Issue issueTwo = new Issue(new Title("Fix the UG"),
                new Deadline("2022-01-02"), Urgency.HIGH,
                new Status(false), new Project(new Name("Stub")), new IssueId(2),
                new Pin(false));
        Issue issueThree = new Issue(new Title("Fix the DG"),
                new Deadline("2022-01-02"), Urgency.HIGH,
                new Status(false), new Project(new Name("Stub")), new IssueId(3),
                new Pin(false));

        actualModel.addIssue(issueOne);
        actualModel.addIssue(issueTwo);
        actualModel.addIssue(issueThree);

        Model expectedModel = new ModelManager();
        expectedModel.addIssue(issueOne);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_ISSUES_LISTED_OVERVIEW,
                        expectedModel.getFilteredIssueList().size()));

        List<String> status = Arrays.asList("completed");
        List<String> empty = new ArrayList<>();


        FindIssueCommand find = new FindIssueCommand(new IssueContainsKeywordsPredicate(empty, status, empty,
                empty, empty, empty));

        assertCommandSuccess(find, actualModel, expectedCommandResult, expectedModel, stubUi);

    }

    @Test
    public void execute_findUrgencyStatus_success() {
        Model actualModel = new ModelManager();
        Issue issueOne = new Issue(new Title("Fix some bugs"),
                new Deadline("2022-02-02"), Urgency.MEDIUM,
                new Status(true), new Project(new Name("Stub")), new IssueId(1),
                new Pin(false));
        Issue issueTwo = new Issue(new Title("Fix the UG"),
                new Deadline("2022-01-02"), Urgency.HIGH,
                new Status(false), new Project(new Name("Stub")), new IssueId(2),
                new Pin(false));
        Issue issueThree = new Issue(new Title("Fix the DG"),
                new Deadline("2022-01-02"), Urgency.HIGH,
                new Status(false), new Project(new Name("Stub")), new IssueId(3),
                new Pin(false));

        actualModel.addIssue(issueOne);
        actualModel.addIssue(issueTwo);
        actualModel.addIssue(issueThree);

        Model expectedModel = new ModelManager();
        expectedModel.addIssue(issueTwo);
        expectedModel.addIssue(issueThree);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_ISSUES_LISTED_OVERVIEW,
                        expectedModel.getFilteredIssueList().size()));

        List<String> urgency = Arrays.asList("high");
        List<String> empty = new ArrayList<>();


        FindIssueCommand find = new FindIssueCommand(new IssueContainsKeywordsPredicate(empty, empty, urgency,
                empty, empty, empty));

        assertCommandSuccess(find, actualModel, expectedCommandResult, expectedModel, stubUi);

    }

    @Test
    public void testEquals() {

        List<String> issueTitle = Arrays.asList("This is an issue title", "another title");
        List<String> projectName = Arrays.asList("DevEnable", "AB3");
        List<String> urgency = Arrays.asList("NONE", "HIGH", "MEDIUM");
        List<String> status = Arrays.asList("completed", "Incomplete");
        List<String> issueId = Arrays.asList("7", "8");
        List<String> projectId = Arrays.asList("1", "3");
        List<String> empty = new ArrayList<>();

        FindIssueCommand findIssueCommandOne = new FindIssueCommand(
                new IssueContainsKeywordsPredicate(issueTitle, status, urgency, projectName, projectId, issueId));
        FindIssueCommand findIssueCommandTwo = new FindIssueCommand(
                new IssueContainsKeywordsPredicate(issueTitle, status, urgency, empty, empty, issueId));
        FindIssueCommand findIssueCommandOneCopy = new FindIssueCommand(
                new IssueContainsKeywordsPredicate(issueTitle, status, urgency, projectName, projectId, issueId));

        // same object -> returns true
        assertTrue(findIssueCommandOne.equals(findIssueCommandOne));

        // same values -> returns true
        assertTrue(findIssueCommandOne.equals(findIssueCommandOneCopy));

        // different types -> returns false
        assertFalse(findIssueCommandOne.equals(1));

        // null -> returns false
        assertFalse(findIssueCommandOne.equals(null));

        // different person -> returns false
        assertFalse(findIssueCommandOne.equals(findIssueCommandTwo));
    }
}
