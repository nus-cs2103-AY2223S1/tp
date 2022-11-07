package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNMENT_DETAILS_HARD;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNMENT_DETAILS_NORMAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_DETAILS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LECTURE_ZOOM_LINK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_DETAILS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_ZOOM_LINK;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_DETAILS_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_DETAILS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_ZOOM_LINK_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.LECTURE_ZOOM_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_DETAILS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_ZOOM_LINK_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_ZOOM_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DETAILS_HARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_DETAILS_NORMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_DETAILS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_ZOOM_LINK_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_DETAILS_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_ZOOM_LINK_CS2103T;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MODULE;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.nusmodules.NusModulesParser;
import seedu.address.model.assignmentdetails.AssignmentDetails;
import seedu.address.model.module.LectureDetails;
import seedu.address.model.module.TutorialDetails;
import seedu.address.model.module.ZoomLink;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleCommandParserTest {

    private static final String ASSIGNMENT_EMPTY = " " + PREFIX_ASSIGNMENT;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE);

    private EditModuleCommandParser parser = new EditModuleCommandParser(new NusModulesParser());

    public EditModuleCommandParserTest() throws IOException, URISyntaxException {
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_MODULE_CODE_CS2103T, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditModuleCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + MODULE_CODE_CS2103T, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + MODULE_CODE_CS2103T, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_LECTURE_DETAILS, LectureDetails.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TUTORIAL_DETAILS, TutorialDetails.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_LECTURE_ZOOM_LINK, ZoomLink.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TUTORIAL_ZOOM_LINK, ZoomLink.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + INVALID_LECTURE_DETAILS
            + TUTORIAL_DETAILS_CS2103T, LectureDetails.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + LECTURE_DETAILS_CS2100
            + INVALID_LECTURE_DETAILS, LectureDetails.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Module} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + ASSIGNMENT_DETAILS_HARD
            + ASSIGNMENT_DETAILS_NORMAL + ASSIGNMENT_EMPTY, AssignmentDetails.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ASSIGNMENT_DETAILS_HARD
            + ASSIGNMENT_EMPTY + ASSIGNMENT_DETAILS_NORMAL, AssignmentDetails.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + ASSIGNMENT_EMPTY
            + ASSIGNMENT_DETAILS_HARD + ASSIGNMENT_DETAILS_NORMAL, AssignmentDetails.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_LECTURE_DETAILS + INVALID_TUTORIAL_DETAILS
            + VALID_MODULE_CODE_CS2100 + VALID_ASSIGNMENT_DETAILS_HARD, LectureDetails.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T
            + TUTORIAL_DETAILS_CS2103T + LECTURE_ZOOM_LINK_CS2103T + TUTORIAL_ZOOM_LINK_CS2103T
            + ASSIGNMENT_DETAILS_NORMAL;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS2103T)
            .withLectureDetails(VALID_LECTURE_DETAILS_CS2103T).withLectureZoomLink(VALID_LECTURE_ZOOM_LINK_CS2103T)
            .withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2103T).withTutorialZoomLink(VALID_TUTORIAL_ZOOM_LINK_CS2103T)
            .withAssignmentDetails(VALID_ASSIGNMENT_DETAILS_NORMAL).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_CS2103T + LECTURE_DETAILS_CS2103T
            + TUTORIAL_DETAILS_CS2103T;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS2103T)
            .withLectureDetails(VALID_LECTURE_DETAILS_CS2103T)
            .withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2103T).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // module code
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_CS2103T;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
            .withModuleCode(VALID_MODULE_CODE_CS2103T).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // lecture details
        userInput = targetIndex.getOneBased() + LECTURE_DETAILS_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withLectureDetails(VALID_LECTURE_DETAILS_CS2103T).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // lecture zoom link
        userInput = targetIndex.getOneBased() + LECTURE_ZOOM_LINK_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withLectureZoomLink(VALID_LECTURE_ZOOM_LINK_CS2103T).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tutorial details
        userInput = targetIndex.getOneBased() + TUTORIAL_DETAILS_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2103T).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tutorial zoom link
        userInput = targetIndex.getOneBased() + TUTORIAL_ZOOM_LINK_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withTutorialZoomLink(VALID_TUTORIAL_ZOOM_LINK_CS2103T).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // assignment details
        userInput = targetIndex.getOneBased() + ASSIGNMENT_DETAILS_NORMAL;
        descriptor = new EditModuleDescriptorBuilder().withAssignmentDetails(VALID_ASSIGNMENT_DETAILS_NORMAL).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + MODULE_CODE_CS2100 + MODULE_CODE_CS2103T
            + LECTURE_DETAILS_CS2103T + TUTORIAL_DETAILS_CS2103T + LECTURE_ZOOM_LINK_CS2100 + LECTURE_ZOOM_LINK_CS2103T
            + TUTORIAL_ZOOM_LINK_CS2100 + TUTORIAL_ZOOM_LINK_CS2103T + ASSIGNMENT_DETAILS_NORMAL;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_CS2103T)
            .withLectureDetails(VALID_LECTURE_DETAILS_CS2103T).withLectureZoomLink(VALID_LECTURE_ZOOM_LINK_CS2103T)
            .withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2103T).withTutorialZoomLink(VALID_TUTORIAL_ZOOM_LINK_CS2103T)
            .withAssignmentDetails(VALID_ASSIGNMENT_DETAILS_NORMAL).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_MODULE;
        String userInput = targetIndex.getOneBased() + INVALID_TUTORIAL_DETAILS + TUTORIAL_DETAILS_CS2103T;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
            .withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2103T).build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TUTORIAL_DETAILS_CS2103T + INVALID_LECTURE_DETAILS
            + LECTURE_ZOOM_LINK_CS2103T + LECTURE_DETAILS_CS2103T;
        descriptor = new EditModuleDescriptorBuilder().withTutorialDetails(VALID_TUTORIAL_DETAILS_CS2103T)
            .withLectureZoomLink(VALID_LECTURE_ZOOM_LINK_CS2103T)
            .withLectureDetails(VALID_LECTURE_DETAILS_CS2103T).build();
        expectedCommand = new EditModuleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_MODULE;
        String userInput = targetIndex.getOneBased() + ASSIGNMENT_EMPTY;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withAssignmentDetails().build();
        EditModuleCommand expectedCommand = new EditModuleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
