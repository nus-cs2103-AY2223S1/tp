package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.InternshipHasApplicationStatusPredicate;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_validFilter_returnsFilterCommand() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new InternshipHasApplicationStatusPredicate(ApplicationStatus.Applied));

        // lower case
        assertParseSuccess(parser, "applied", expectedFilterCommand);

        // alternating caps
        assertParseSuccess(parser, "aPpLiEd", expectedFilterCommand);
    }

    @Test
    public void parse_emptyFilter_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFilter_throwsParseException() {
        assertParseFailure(parser, "invalidfilter", ApplicationStatus.MESSAGE_CONSTRAINTS);
    }
}
