package longtimenosee.logic.parser;

import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMPANY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COVERAGES;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;
import java.util.stream.Stream;

import longtimenosee.logic.commands.PolicyAddCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.policy.Commission;
import longtimenosee.model.policy.Company;
import longtimenosee.model.policy.Coverage;
import longtimenosee.model.policy.Policy;
import longtimenosee.model.policy.Title;

/**
 * Parses input arguments and creates a new PolicyAddCommand object.
 */
public class PolicyAddCommandParser implements Parser<PolicyAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PolicyAddCommand
     * and returns an PolicyAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PolicyAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_COMPANY, PREFIX_COMMISSION, PREFIX_COVERAGES);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_COMPANY, PREFIX_COMMISSION, PREFIX_COVERAGES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PolicyAddCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Commission commission = ParserUtil.parseCommission(argMultimap.getValue(PREFIX_COMMISSION).get());
        Set<Coverage> coverages = ParserUtil.parseCoverages(argMultimap.getAllValues(PREFIX_COVERAGES));

        Policy policy = new Policy(title, company, commission, coverages);

        return new PolicyAddCommand(policy);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
