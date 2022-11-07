package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.UnappendCommand.MESSAGE_NOT_UNAPPENDED;
import static seedu.address.logic.parser.AppendCommandParser.arePrefixesPresent;
import static seedu.address.logic.parser.AppendCommandParser.parseSurveysForEdit;
import static seedu.address.logic.parser.AppendCommandParser.parseTagsForEdit;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURVEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnappendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Survey;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new UnappendCommand object
 */
public class UnappendCommandParser implements Parser<UnappendCommand> {

    @Override
    public UnappendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SURVEY, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnappendCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_SURVEY) && !arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NOT_UNAPPENDED));
        }

        Set<Tag> newTags = parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG));
        Set<Survey> newSurveys = parseSurveysForEdit(argMultimap.getAllValues(PREFIX_SURVEY));

        return new UnappendCommand(index, newSurveys, newTags);
    }
}
