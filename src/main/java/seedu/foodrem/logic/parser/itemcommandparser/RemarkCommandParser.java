package seedu.foodrem.logic.parser.itemcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.foodrem.commons.core.index.Index;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.commands.itemcommands.RemarkCommand;
import seedu.foodrem.logic.parser.ArgumentMultimap;
import seedu.foodrem.logic.parser.ArgumentTokenizer;
import seedu.foodrem.logic.parser.CliSyntax;
import seedu.foodrem.logic.parser.Parser;
import seedu.foodrem.logic.parser.ParserUtil;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.item.ItemRemark;

/**
 * Parses input arguments and creates a new RemarkCommand object.
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemarkCommand
     * and returns an RemarkCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args.trim(), CliSyntax.PREFIX_ITEM_REMARKS);
        Index index = StringUtil.validateAndGetIndexFromString(argMultimap.getPreamble(), RemarkCommand.getUsage());

        ItemRemark remark = ParserUtil.parseRemarks("");
        if (arePrefixesPresent(argMultimap, CliSyntax.PREFIX_ITEM_REMARKS)) {
            remark = ParserUtil.parseRemarks(argMultimap.getPresentValue(CliSyntax.PREFIX_ITEM_REMARKS));
        }

        return new RemarkCommand(index, remark);
    }
}
