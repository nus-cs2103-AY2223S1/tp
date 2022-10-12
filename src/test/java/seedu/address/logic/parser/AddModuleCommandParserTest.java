package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNMENT_DETAILS_HARD;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNMENT_DETAILS_NORMAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSIGNMENT_DETAILS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_DETAILS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_DETAILS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ZOOM_LINK;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_DETAILS_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_DETAILS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_DETAILS_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_DETAILS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DETAILS_HARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DETAILS_NORMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_DETAILS_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_DETAILS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_DETAILS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.ZOOM_LINK_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.ZOOM_LINK_CS2103T;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.assignmentdetails.AssignmentDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleCommandParserTest {
    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(CS2103T)
            .withAssignmentDetails(VALID_ASSIGNMENT_DETAILS_NORMAL).build();

        // whitespace only preamble
        assertParseSuccess(parser,
            PREAMBLE_WHITESPACE + MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T
                + ZOOM_LINK_CS2103T + ASSIGNMENT_DETAILS_NORMAL, new AddModuleCommand(expectedModule));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser,
            MODULE_CODE_CS2100 + MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T
                + ZOOM_LINK_CS2103T + ASSIGNMENT_DETAILS_NORMAL, new AddModuleCommand(expectedModule));

        // multiple lectures - last lecture details accepted
        assertParseSuccess(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2100 + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T
                + ZOOM_LINK_CS2103T + ASSIGNMENT_DETAILS_NORMAL, new AddModuleCommand(expectedModule));

        // multiple tutorials - last tutorial details accepted
        assertParseSuccess(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2100 + TUTORIAL_DETAILS_CS2103T
                + ZOOM_LINK_CS2103T + ASSIGNMENT_DETAILS_NORMAL, new AddModuleCommand(expectedModule));

        // multiple zoom links - last zoom link accepted
        assertParseSuccess(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + ZOOM_LINK_CS2100
                + ZOOM_LINK_CS2103T + ASSIGNMENT_DETAILS_NORMAL, new AddModuleCommand(expectedModule));

        // multiple asssignments - all accepted
        Module expectedModuleMultipleTags =
            new ModuleBuilder(CS2103T).withAssignmentDetails(VALID_ASSIGNMENT_DETAILS_HARD,
                VALID_ASSIGNMENT_DETAILS_NORMAL).build();
        assertParseSuccess(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + ZOOM_LINK_CS2103T
                + ASSIGNMENT_DETAILS_HARD + ASSIGNMENT_DETAILS_NORMAL,
            new AddModuleCommand(expectedModuleMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero assignments
        Module expectedModule = new ModuleBuilder(CS2100).withAssignmentDetails().build();
        assertParseSuccess(parser,
            MODULE_CODE_CS2100 + LECTURE_DETAILS_CS2100 + TUTORIAL_DETAILS_CS2100 + ZOOM_LINK_CS2100,
            new AddModuleCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);

        // missing module code prefix
        assertParseFailure(parser,
            VALID_MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + ZOOM_LINK_CS2103T,
            expectedMessage);

        // missing lecture prefix
        assertParseFailure(parser,
            MODULE_CODE_CS2103T + VALID_LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + ZOOM_LINK_CS2103T,
            expectedMessage);

        // missing tutorial prefix
        assertParseFailure(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + VALID_TUTORIAL_DETAILS_CS2103T + ZOOM_LINK_CS2103T,
            expectedMessage);

        // missing zoom link prefix
        assertParseFailure(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + VALID_ZOOM_LINK_CS2103T,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_MODULE_CODE_CS2103T + VALID_LECTURE_DETAILS_CS2100 + VALID_TUTORIAL_DETAILS_CS2103T
                + VALID_ZOOM_LINK_CS2103T, expectedMessage);
    }

    @Test
    public void parse_invalidModuleValue_failure() {
        // invalid module code
        assertParseFailure(parser,
            INVALID_MODULE_CODE + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + ZOOM_LINK_CS2103T
                + ASSIGNMENT_DETAILS_HARD, ModuleCode.MESSAGE_CONSTRAINTS);

        // invalid lecture
        assertParseFailure(parser,
            MODULE_CODE_CS2103T + INVALID_LECTURE_DETAILS + TUTORIAL_DETAILS_CS2103T + ZOOM_LINK_CS2103T
                + ASSIGNMENT_DETAILS_HARD, LectureDetails.MESSAGE_CONSTRAINTS);

        // invalid tutorial
        assertParseFailure(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + INVALID_TUTORIAL_DETAILS + ZOOM_LINK_CS2103T
                + ASSIGNMENT_DETAILS_HARD, TutorialDetails.MESSAGE_CONSTRAINTS);

        // invalid zoom link
        assertParseFailure(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + INVALID_ZOOM_LINK
                + ASSIGNMENT_DETAILS_HARD, ZoomLink.MESSAGE_CONSTRAINTS);

        // invalid assignment
        assertParseFailure(parser,
            MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + ZOOM_LINK_CS2103T
                + INVALID_ASSIGNMENT_DETAILS, AssignmentDetails.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
            INVALID_MODULE_CODE + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + INVALID_ZOOM_LINK,
            ModuleCode.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
            PREAMBLE_NON_EMPTY + MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T
                + ZOOM_LINK_CS2103T + ASSIGNMENT_DETAILS_HARD + ASSIGNMENT_DETAILS_NORMAL,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
    }
}
