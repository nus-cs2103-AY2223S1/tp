package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.checkcommands.CheckBuyerCommand;
import seedu.address.logic.commands.checkcommands.CheckCommand;
import seedu.address.logic.commands.checkcommands.CheckDelivererCommand;
import seedu.address.logic.commands.checkcommands.CheckOrderCommand;
import seedu.address.logic.commands.checkcommands.CheckPetCommand;
import seedu.address.logic.commands.checkcommands.CheckSupplierCommand;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class CheckCommandParser implements Parser<CheckCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<checkType>.\\S*)(?<index>.\\d*)");

    @Override
    public CheckCommand parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }

        final String checkType = matcher.group("checkType");
        final String indexStr = matcher.group("index");
        Index index = ParserUtil.parseIndex(indexStr);

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_BUYER_PARAMETER, checkType)) {
            return new CheckBuyerCommand(index);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_SUPPLIER_PARAMETER, checkType)) {
            return new CheckSupplierCommand(index);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_DELIVERER_PARAMETER, checkType)) {
            return new CheckDelivererCommand(index);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_ORDER_PARAMETER, checkType)) {
            return new CheckOrderCommand(index);
        }

        if (CommandUtil.isValidParameter(CommandUtil.ACCEPTABLE_PET_PARAMETER, checkType)) {
            return new CheckPetCommand(index);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));

    }
}
