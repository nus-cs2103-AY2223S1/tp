package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CONTENT_DESC_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.CONTENT_DESC_TUTORIAL2;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_TUTORIAL2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_TUTORIAL2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_TUTORIAL1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTutorials.TUTORIAL1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.model.tutorial.Content;
import seedu.address.model.tutorial.Group;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.TutorialBuilder;

public class AddTutorialCommandParserTest {
    private AddTutorialCommandParser parser = new AddTutorialCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tutorial expectedTutorial = new TutorialBuilder(TUTORIAL1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + GROUP_DESC_TUTORIAL1 + CONTENT_DESC_TUTORIAL1
                        + TIME_DESC_TUTORIAL1,
                new AddTutorialCommand(expectedTutorial));

        // multiple group - last group accepted
        assertParseSuccess(parser, GROUP_DESC_TUTORIAL2 + GROUP_DESC_TUTORIAL1 + CONTENT_DESC_TUTORIAL1
                        + TIME_DESC_TUTORIAL1,
                new AddTutorialCommand(expectedTutorial));

        // multiple content- last content accepted
        assertParseSuccess(parser, GROUP_DESC_TUTORIAL1 + CONTENT_DESC_TUTORIAL2 + CONTENT_DESC_TUTORIAL1
                        + TIME_DESC_TUTORIAL1,
                new AddTutorialCommand(expectedTutorial));

        // multiple time - last time accepted
        assertParseSuccess(parser, GROUP_DESC_TUTORIAL1 + CONTENT_DESC_TUTORIAL1 + TIME_DESC_TUTORIAL2
                        + TIME_DESC_TUTORIAL1,
                new AddTutorialCommand(expectedTutorial));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTutorialCommand.MESSAGE_USAGE);

        // missing group prefix
        assertParseFailure(parser, VALID_GROUP_TUTORIAL1 + CONTENT_DESC_TUTORIAL1 + TIME_DESC_TUTORIAL1,
                expectedMessage);

        // missing content prefix
        assertParseFailure(parser, GROUP_DESC_TUTORIAL1 + VALID_CONTENT_TUTORIAL1 + TIME_DESC_TUTORIAL1,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, GROUP_DESC_TUTORIAL1 + CONTENT_DESC_TUTORIAL1 + VALID_TIME_TUTORIAL1,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_GROUP_TUTORIAL1 + VALID_CONTENT_TUTORIAL1 + VALID_TIME_TUTORIAL1,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid group
        assertParseFailure(parser, INVALID_GROUP_DESC + CONTENT_DESC_TUTORIAL1 + TIME_DESC_TUTORIAL1,
                Group.MESSAGE_CONSTRAINTS);

        // invalid content
        assertParseFailure(parser, GROUP_DESC_TUTORIAL1 + INVALID_CONTENT_DESC + TIME_DESC_TUTORIAL1,
                Content.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, GROUP_DESC_TUTORIAL1 + CONTENT_DESC_TUTORIAL1 + INVALID_TIME_DESC,
                Time.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_GROUP_DESC + INVALID_CONTENT_DESC + TIME_DESC_TUTORIAL1,
                Group.MESSAGE_CONSTRAINTS);
    }
}
