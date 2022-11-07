package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MOBILE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MOBILE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESC_EMPTY_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOBILE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_ID;
import static seedu.address.logic.commands.client.AddClientCommand.MESSAGE_ADD_CLIENT_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.client.AddClientCommand;
import seedu.address.logic.commands.project.AddProjectCommand;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.client.ClientWithoutModel;
import seedu.address.model.project.ProjectId;


public class AddClientCommandParserTest {
    private ClientCommandParser parser = new ClientCommandParser();

    @Test
    public void parse_compulsoryAndOptionalFieldsPresent_success() {

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(
                new Name(VALID_NAME_AMY), new ClientMobile(VALID_MOBILE_AMY),
                new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(), new Pin(false));

        //compulsory and optional fields
        assertParseSuccess(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                + MOBILE_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT, new AddClientCommand(clientWithoutModel,
                    new ProjectId.EmptyProjectId()));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(
                new Name(VALID_NAME_AMY), ClientMobile.EmptyClientMobile.EMPTY_MOBILE,
                ClientEmail.EmptyEmail.EMPTY_EMAIL, new ArrayList<>(), new Pin(false));

        // only name
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG, NAME_DESC_AMY + PROJECT_DESC_EMPTY_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG, NAME_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));

    }

    @Test
    public void parse_optionalEmailMissing_success() {

        ClientWithoutModel clientWithoutModel = new ClientWithoutModel(
                new Name(VALID_NAME_AMY), new ClientMobile(VALID_MOBILE_AMY),
                ClientEmail.EmptyEmail.EMPTY_EMAIL, new ArrayList<>(), new Pin(false));

        // email missing
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + MOBILE_DESC_AMY + PROJECT_DESC_EMPTY_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));
        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + MOBILE_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, ProjectId.EmptyProjectId.EMPTY_PROJECT_ID));

    }

    @Test
    public void parse_optionalMobileMissing_success() {

        ClientWithoutModel clientWithoutModel =
                new ClientWithoutModel(new Name(VALID_NAME_AMY), ClientMobile.EmptyClientMobile.EMPTY_MOBILE,
                        new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(), new Pin(false));

        // mobile missing
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
                new ClientWithoutModel(new Name(VALID_NAME_AMY), new ClientMobile(VALID_MOBILE_AMY),
                        new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(), new Pin(false));

        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + EMAIL_DESC_AMY + MOBILE_DESC_AMY + PROJECT_DESC_EMPTY_PROJECT,
                new AddClientCommand(clientWithoutModel, new ProjectId.EmptyProjectId()));

        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                NAME_DESC_AMY + MOBILE_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, new ProjectId.EmptyProjectId()));

        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                MOBILE_DESC_AMY + NAME_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, new ProjectId.EmptyProjectId()));

        assertParseSuccess(parser, AddProjectCommand.COMMAND_FLAG,
                PROJECT_DESC_PROJECT + MOBILE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY,
                new AddClientCommand(clientWithoutModel, new ProjectId.EmptyProjectId()));

    }


    @Test
    public void parse_clientAdded_success() {
        // all valid fields
        String expected = "No project with this project Id";
        ClientWithoutModel clientWithoutModel =
                new ClientWithoutModel(new Name(VALID_NAME_AMY), new ClientMobile(VALID_MOBILE_AMY),
                        new ClientEmail(VALID_EMAIL_AMY), new ArrayList<>(), new Pin(false));

        //        ProjectWithoutModel projectWithoutModel =
        //                new ProjectWithoutModel(new Name(VALID_NAME_BOB), new Repository(VALID_REPOSITORY),
        //                new Deadline(VALID_DEADLINE), new ClientId.EmptyClientId(), new ArrayList<>());

        assertParseSuccess(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                        + MOBILE_DESC_AMY + EMAIL_DESC_AMY + PROJECT_DESC_PROJECT,
                new AddClientCommand(clientWithoutModel, new ProjectId(Integer.parseInt(VALID_PROJECT_ID))));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_ADD_CLIENT_USAGE);

        // invalid argument number and invalid name
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, INVALID_NAME_DESC
                + MOBILE_DESC_AMY + EMAIL_DESC_AMY, expected);

        // invalid argument number and invalid mobile
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                + INVALID_MOBILE_DESC + EMAIL_DESC_AMY, expected);

        // invalid argument number and invalid email
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                + MOBILE_DESC_AMY + INVALID_EMAIL_DESC, expected);

        // invalid argument number and all valid fields
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG, NAME_DESC_AMY
                + MOBILE_DESC_AMY + EMAIL_DESC_AMY, expected);

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

        //invalid mobile
        assertParseFailure(parser, AddClientCommand.COMMAND_FLAG,
                NAME_DESC_AMY + INVALID_MOBILE_DESC + PROJECT_DESC_PROJECT,
                ClientMobile.MESSAGE_CONSTRAINTS);

    }

}
