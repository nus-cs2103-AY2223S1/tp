package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_CREDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleCredit;
import seedu.address.model.module.ModuleName;

/**
 * Parses arguments to create a new AddModuleCommand Object.
 */
public class AddModuleCommandParser implements Parser<Command> {
    @Override
    public Command parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD_CODE, PREFIX_MOD_NAME, PREFIX_MOD_CREDIT);

        if (!arePrefixesPresent(argMultimap, PREFIX_MOD_CODE, PREFIX_MOD_NAME, PREFIX_MOD_CREDIT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddModuleCommand.MESSAGE_USAGE));
        }

        ModuleCode modCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MOD_CODE)
                .orElseThrow(NullPointerException::new));

        ModuleName modName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MOD_NAME)
                .orElseThrow(NullPointerException::new));

        ModuleCredit modCredit = ParserUtil.parseModuleCredit(argMultimap.getValue(PREFIX_MOD_CREDIT)
                .orElseThrow(NullPointerException::new));

        Module module = new Module(modCode, modName, modCredit);
        return new AddModuleCommand(module);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
