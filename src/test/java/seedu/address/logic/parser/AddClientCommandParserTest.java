package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESC_EMPTY_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_ID;
import static seedu.address.logic.commands.client.AddClientCommand.MESSAGE_ADD_CLIENT_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.client.AddClientCommand;
import seedu.address.logic.commands.project.AddProjectCommand;
import seedu.address.model.Name;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientPhone;
import seedu.address.model.client.ClientWithoutModel;
import seedu.address.model.project.ProjectId;


public class AddClientCommandParserTest {
    private ClientCommandParser parser = new ClientCommandParser();

    @Test
    public void parse_compulsoryAndOptionalFieldsPresent_success() {

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(
                new Name(VALID_NAME_AMY), new ClientPhone(VALID_PHONE_AMY),
                new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>());

        //compulsory and optional fields
        assertParseSuccess(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT, new AddClientCommand(clientWithoutModel,
                    new ProjectId.EmptyProjectId()));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(
                new Name(VALID_NAME_AMY), ClientPhone.EmptyClientPhone.EMPTY_PHONE,
                ClientEmail.EmptyEmail.EMPTY_EMAIL, new ArrayList<>());

        // only name
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG, NAME_DESC_AMY + PROJECT_DESC_EMPTY_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG, NAME_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));

    }

    @Test
    public void parse_optionalEmailMissing_success() {

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(
                new Name(VALID_NAME_AMY), new ClientPhone(VALID_PHONE_AMY),
                ClientEmail.EmptyEmail.EMPTY_EMAIL, new ArrayList<>());

        // email missing
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + PHONE_DESC_AMY + PROJECT_DESC_EMPTY_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + PHONE_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));

    }

    @Test
    public void parse_optionalPhoneMissing_success() {

        ClientWithoutModel clientWithoutModel =
                new ClientWithoutModel(new Name(VALID_NAME_AMY), ClientPhone.EmptyClientPhone.EMPTY_PHONE,
                        new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>());

        // phone missing
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_EMPTY_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));

    }

    @Test
    public void parse_fieldsInAnyOrder_success() {

        ClientWithoutModel clientWithoutModel =
                new ClientWithoutModel(new Name(VALID_NAME_AMY), new ClientPhone(VALID_PHONE_AMY),
                        new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>());


        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY + PROJECT_DESC_EMPTY_PROJECT,
                new AddClientCommand(clientWithoutModel, new ProjectId.EmptyProjectId()));

        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, new ProjectId.EmptyProjectId()));

        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                PHONE_DESC_AMY + NAME_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, new ProjectId.EmptyProjectId()));

        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                PROJECT_DESC_PROJECT + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY,
                new AddClientCommand(clientWithoutModel, new ProjectId.EmptyProjectId()));

    }


    @Test
    public void parse_clientAdded_success() {
        // all valid fields
        String expected = "No project with this project Id";
        ClientWithoutModel clientWithoutModel =
                new ClientWithoutModel(new Name(VALID_NAME_AMY), new ClientPhone(VALID_PHONE_AMY),
                        new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>());

        //        ProjectWithoutModel projectWithoutModel =
        //                new ProjectWithoutModel(new Name(VALID_NAME_BOB), new Repository(VALID_REPOSITORY),
        //                new Deadline(VALID_DEADLINE), new ClientId.EmptyClientId(), new ArrayList<>());

        assertParseSuccess(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                        + PHONE_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, new ProjectId(Integer.parseInt(VALID_PROJECT_ID))));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_ADD_CLIENT_USAGE);

        // invalid argument number and invalid name
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, INVALID_NAME_DESC
                + PHONE_DESC_AMY + EMAIL_DESC_AMY, expected);

        // invalid argument number and invalid phone
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                + INVALID_PHONE_DESC + EMAIL_DESC_AMY, expected);

        // invalid argument number and invalid email
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                + PHONE_DESC_AMY + INVALID_EMAIL_DESC, expected);

        // invalid argument number and all valid fields
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY, expected);

    }

    @Test
    public void parse_invalidFields_failure() {
        // invalid name
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, INVALID_NAME_DESC + PROJECT_DESC_PROJECT,
                Name.MESSAGE_CONSTRAINTS);

        //invalid email
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG,
                NAME_DESC_AMY + INVALID_EMAIL_DESC + PROJECT_DESC_PROJECT,
                ClientEmail.MESSAGE_CONSTRAINTS);

        //invalid phone
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG,
                NAME_DESC_AMY + INVALID_PHONE_DESC + PROJECT_DESC_PROJECT,
                ClientPhone.MESSAGE_CONSTRAINTS);

    }

}
