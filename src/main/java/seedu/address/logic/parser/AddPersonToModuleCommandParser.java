package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.AddPersonToModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new {@code AddPersonToModuleCommand} object.
 */
public class AddPersonToModuleCommandParser implements Parser<AddPersonToModuleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddPersonToModuleCommand}
     * and returns an {@code AddPersonToModuleCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddPersonToModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_NAME);
        boolean isPrefixModuleCodeOrNameAbsent = !arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE, PREFIX_NAME);
        boolean isPreamblePresent = !argMultimap.getPreamble().isEmpty();
        if (isPrefixModuleCodeOrNameAbsent || isPreamblePresent) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPersonToModuleCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        assert moduleCode != null;
        assert name != null;
        return new AddPersonToModuleCommand(moduleCode, name);
    }
}
