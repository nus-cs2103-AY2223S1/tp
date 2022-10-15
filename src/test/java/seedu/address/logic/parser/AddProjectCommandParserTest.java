package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REPOSITORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.REPOSITORY_DESC_REPOSITORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOSITORY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.AddProjectCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.client.Client;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;

class AddProjectCommandParserTest {
    private ProjectCommandParser parser = new ProjectCommandParser();

    @Test
    public void parse_compulsoryAndOptionalFieldsPresent_success() {
        Project project = new Project(new Name(VALID_NAME_BOB), new Repository(VALID_REPOSITORY),
                new Deadline(VALID_DEADLINE), Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(),
                new ProjectId(HasIntegerIdentifier.generateNextID(AddressBook.get().getProjectList())));
        //compulsory and optional fields
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG, NAME_DESC_BOB + REPOSITORY_DESC_REPOSITORY
                + DEADLINE_DESC_DEADLINE, new AddProjectCommand(project));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Project project = new Project(new Name(VALID_NAME_BOB), Repository.EmptyRepository.EMPTY_REPOSITORY,
                Deadline.EmptyDeadline.EMPTY_DEADLINE, Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(),
                new ProjectId(HasIntegerIdentifier.generateNextID(AddressBook.get().getProjectList())));
        // only name
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG, NAME_DESC_BOB,
                new AddProjectCommand(project));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddProjectCommand.MESSAGE_ADD_PROJECT_USAGE);

        // missing name prefix
        assertParseFailure(parser, AddProjectCommand.COMMAND_FLAG, VALID_NAME_BOB
                + REPOSITORY_DESC_REPOSITORY + DEADLINE_DESC_DEADLINE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, AddProjectCommand.COMMAND_FLAG, INVALID_NAME_DESC
                + REPOSITORY_DESC_REPOSITORY + DEADLINE_DESC_DEADLINE, Name.MESSAGE_CONSTRAINTS);

        // invalid repository
        assertParseFailure(parser, AddProjectCommand.COMMAND_FLAG, NAME_DESC_AMY
                + INVALID_REPOSITORY_DESC + DEADLINE_DESC_DEADLINE, Repository.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, AddProjectCommand.COMMAND_FLAG, NAME_DESC_AMY
                + REPOSITORY_DESC_REPOSITORY + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);
    }
}
