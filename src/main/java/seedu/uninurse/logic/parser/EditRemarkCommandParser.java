package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditRemarkCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.remark.Remark;

/**
 * Parses input arguments and creates a new EditRemarkCommand object
 */
public class EditRemarkCommandParser implements Parser<EditRemarkCommand> {
    /**
     * Parses the given arguments in the context of the EditRemarkCommand
     * and returns an EditRemarkCommand object for execution.
     *
     * @param args the string of arguments given
     * @return EditRemarkCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditRemarkCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        try {
            List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());
            Remark updatedRemark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElseThrow());

            return new EditRemarkCommand(indices.get(0), indices.get(1), updatedRemark);
        } catch (NoSuchElementException nse) {
            throw new ParseException(EditRemarkCommand.MESSAGE_NOT_EDITED);
        }
    }
}
