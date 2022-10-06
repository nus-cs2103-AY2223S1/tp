package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommissionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commission.*;
import seedu.address.model.customer.Address;
import seedu.address.model.tag.Tag;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddCommissionCommandParser implements Parser<AddCommissionCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommissionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_FEE, PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_FEE, PREFIX_DEADLINE, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommissionCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Fee fee = ParserUtil.parseFee(argMultimap.getValue(PREFIX_FEE).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        CompletionStatus status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Optional<String> rawDesc = argMultimap.getValue(PREFIX_DESCRIPTION);
        Optional<Description> desc = rawDesc.isEmpty()
                ? Optional.empty()
                : Optional.of(ParserUtil.parseDescription(rawDesc.get()));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));


        Commission.CommissionBuilder commissionBuilder = new Commission.CommissionBuilder(title, fee, deadline, status, tagList);
        desc.ifPresent(commissionBuilder::setDescription);
        Commission commission = commissionBuilder.build();

        return new AddCommissionCommand(commission);
    }
}
