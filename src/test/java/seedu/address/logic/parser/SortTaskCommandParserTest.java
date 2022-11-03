package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.SortTaskCommand;
import seedu.address.model.commons.Criteria;
import seedu.address.testutil.CriteriaBuilder;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class SortTaskCommandParserTest {
    private SortTaskCommandParser parser = new SortTaskCommandParser();

    @Test
    public void parse_criteriaFieldPresent_success() {
        Criteria criteria = new CriteriaBuilder().withCriteria("priority").build();
        String criteriaFieldPresent = " c/priority";
        String criteriaFieldPresentAllCaps = " c/PRIORITY";

        //valid input with priority as criteria
        assertParseSuccess(parser, criteriaFieldPresent, new SortTaskCommand(criteria));

        //valid input with priority in different casing as criteria
        assertParseSuccess(parser, criteriaFieldPresentAllCaps, new SortTaskCommand(criteria));

        //valid input with deadline as criteria
        criteria = new CriteriaBuilder().withCriteria("description").build();
        criteriaFieldPresent = " c/description";
        assertParseSuccess(parser, criteriaFieldPresent, new SortTaskCommand(criteria));
    }

    @Test
    public void parse_missingCriteriaField_failure() {
        String missingField = " priority";
        String missingWhitespaceAtStart = "c/priority";
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                SortTaskCommand.MESSAGE_USAGE);

        //Missing prefix
        assertParseFailure(parser, missingField, expectedMessage);

        //Missing whitespace before prefix
        assertParseFailure(parser, missingWhitespaceAtStart, expectedMessage);
    }

    @Test
    public void parse_invalidInputs_failure() {
        String invalidCriteria = " c/module_name";

        //Invalid criteria name
        assertParseFailure(parser, invalidCriteria, Criteria.CRITERIA_CONSTRAINTS);
    }

}
