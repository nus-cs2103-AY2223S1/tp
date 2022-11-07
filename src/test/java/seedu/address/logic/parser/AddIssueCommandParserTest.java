package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_URGENCY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.URGENCY_DESC_URGENCY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.IssueCliSyntax.PREFIX_PROJECT_ID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.issue.IssueWithoutModel;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;

class AddIssueCommandParserTest {

    private IssueCommandParser parser = new IssueCommandParser();

    @Test
    public void parse_compulsoryAndOptionalFieldsPresent_success() {
        Project projectStub = new Project(new Name("Stub"));
        ProjectId stubId = projectStub.getProjectId();
        String descProject = " " + PREFIX_PROJECT_ID + Integer.toString(projectStub.getProjectId().getIdInt());

        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(
                new Title(VALID_TITLE), new Deadline(VALID_DEADLINE), Urgency.LOW, Status.EmptyStatus.EMPTY_STATUS,
                stubId, new Pin(false));
        //compulsory and optional fields
        assertParseSuccess(parser, AddIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + DEADLINE_DESC_DEADLINE
                + URGENCY_DESC_URGENCY + descProject, new AddIssueCommand(issueWithoutModel, stubId));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Project projectStub = new Project(new Name("Stub"));
        ProjectId stubId = projectStub.getProjectId();
        String descProject = " " + PREFIX_PROJECT_ID + Integer.toString(projectStub.getProjectId().getIdInt());

        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(
                new Title(VALID_TITLE), Deadline.EmptyDeadline.EMPTY_DEADLINE, Urgency.NONE,
                Status.EmptyStatus.EMPTY_STATUS, stubId, new Pin(false));
        //only title and project
        assertParseSuccess(parser, AddIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + descProject,
                new AddIssueCommand(issueWithoutModel, stubId));
    }

    @Test
    public void parse_optionalDeadlineMissing_success() {
        Project projectStub = new Project(new Name("Stub"));
        ProjectId stubId = projectStub.getProjectId();
        String descProject = " " + PREFIX_PROJECT_ID + Integer.toString(projectStub.getProjectId().getIdInt());

        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(
                new Title(VALID_TITLE), Deadline.EmptyDeadline.EMPTY_DEADLINE, Urgency.LOW,
                Status.EmptyStatus.EMPTY_STATUS, stubId, new Pin(false));
        //title, project and urgency
        assertParseSuccess(parser, AddIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + descProject
                + URGENCY_DESC_URGENCY, new AddIssueCommand(issueWithoutModel, stubId));
    }

    @Test
    public void parse_optionalUrgencyMissing_success() {
        Project projectStub = new Project(new Name("Stub"));
        ProjectId stubId = projectStub.getProjectId();
        String descProject = " " + PREFIX_PROJECT_ID + Integer.toString(projectStub.getProjectId().getIdInt());

        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(
                new Title(VALID_TITLE), new Deadline(VALID_DEADLINE), Urgency.NONE,
                Status.EmptyStatus.EMPTY_STATUS, stubId, new Pin(false));
        //title, project and deadline
        assertParseSuccess(parser, AddIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + descProject
                + DEADLINE_DESC_DEADLINE, new AddIssueCommand(issueWithoutModel, stubId));
    }

    @Test
    public void parse_fieldsInAnyOrder_success() {
        Project projectStub = new Project(new Name("Stub"));
        ProjectId stubId = projectStub.getProjectId();
        String descProject = " " + PREFIX_PROJECT_ID + Integer.toString(projectStub.getProjectId().getIdInt());

        IssueWithoutModel issueWithoutModel = new IssueWithoutModel(
                new Title(VALID_TITLE), new Deadline(VALID_DEADLINE), Urgency.LOW,
                Status.EmptyStatus.EMPTY_STATUS, stubId, new Pin(false));

        assertParseSuccess(parser, AddIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + descProject
                + DEADLINE_DESC_DEADLINE + URGENCY_DESC_URGENCY, new AddIssueCommand(issueWithoutModel, stubId));

        assertParseSuccess(parser, AddIssueCommand.COMMAND_FLAG, descProject + TITLE_DESC_TITLE
                + DEADLINE_DESC_DEADLINE + URGENCY_DESC_URGENCY, new AddIssueCommand(issueWithoutModel, stubId));

        assertParseSuccess(parser, AddIssueCommand.COMMAND_FLAG, DEADLINE_DESC_DEADLINE + descProject
                + TITLE_DESC_TITLE + URGENCY_DESC_URGENCY, new AddIssueCommand(issueWithoutModel, stubId));

        assertParseSuccess(parser, AddIssueCommand.COMMAND_FLAG, URGENCY_DESC_URGENCY
                + DEADLINE_DESC_DEADLINE + descProject + TITLE_DESC_TITLE,
                new AddIssueCommand(issueWithoutModel, stubId));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        Project projectStub = new Project(new Name("Stub"));
        ProjectId stubId = projectStub.getProjectId();
        String descProject = " " + PREFIX_PROJECT_ID + Integer.toString(projectStub.getProjectId().getIdInt());

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIssueCommand.MESSAGE_USAGE);

        //missing title prefix
        assertParseFailure(parser, AddIssueCommand.COMMAND_FLAG, VALID_TITLE + descProject
                + DEADLINE_DESC_DEADLINE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        Project projectStub = new Project(new Name("Stub"));
        ProjectId stubId = projectStub.getProjectId();
        String descProject = " " + PREFIX_PROJECT_ID + Integer.toString(projectStub.getProjectId().getIdInt());

        //invalid title
        assertParseFailure(parser, AddIssueCommand.COMMAND_FLAG, INVALID_TITLE_DESC + descProject
                + DEADLINE_DESC_DEADLINE, Title.MESSAGE_CONSTRAINTS);

        //invalid deadline
        assertParseFailure(parser, AddIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + descProject
                + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        //invalid urgency
        assertParseFailure(parser, AddIssueCommand.COMMAND_FLAG, TITLE_DESC_TITLE + descProject
                + INVALID_URGENCY_DESC, Urgency.MESSAGE_CONSTRAINTS);
    }

}
