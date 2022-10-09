package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_TITLE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;
import static seedu.address.model.module.Module.EMPTY_MODULE_TITLE;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;

/**
 * Parses input arguments and creates a new {@code AddModuleCommand} object.
 */
public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of {@code AddModuleCommand}
     * and returns an {@code AddModuleCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_MODULE_TITLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddModuleCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode =
                ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());
        ModuleTitle moduleTitle =
                ParserUtil.parseModuleTitle(argMultimap.getValue(PREFIX_MODULE_TITLE).orElse(EMPTY_MODULE_TITLE));

        Module module = new Module(moduleCode, moduleTitle);

        return new AddModuleCommand(module);
    }

}
