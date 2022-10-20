package modtrekt.logic.parser;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.LambdaExceptionUtil.rethrowFunction;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CODE;

import modtrekt.logic.commands.UndoneModuleCommand;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.logic.parser.util.CommandParserBuilder;
import modtrekt.logic.parser.util.ParsedCommand;
import modtrekt.model.module.ModCode;

/**
 * Parser for a command string input for a UndoneModuleCommand object.
 */
public class UndoneModuleCommandParser {
    /**
     * Returns a new UndoneModuleCommand object parsed from the given command string.
     */
    public UndoneModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ParsedCommand command = new CommandParserBuilder()
                .requiredPrefixes(PREFIX_MOD_CODE)
                .usageMessage(UndoneModuleCommand.MESSAGE_USAGE)
                .build()
                .parse(args);

        // Java doesn't support re-throwing checked exceptions in method references, so we have to wrap
        // it in `rethrowFunction` from `LambdaExceptionUtil`.
        ModCode code = command.mapMandatoryArgument(PREFIX_MOD_CODE, rethrowFunction(ParserUtil::parseCode));

        return new UndoneModuleCommand(code);
    }
}
