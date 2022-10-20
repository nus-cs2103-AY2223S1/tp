package modtrekt.logic.parser;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.LambdaExceptionUtil.rethrowFunction;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CODE;

import modtrekt.logic.commands.DoneModuleCommand;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.logic.parser.util.CommandParserBuilder;
import modtrekt.logic.parser.util.ParsedCommand;
import modtrekt.model.module.ModCode;

/**
 * Parser for a command string input for a DoneModuleCommand object.
 */
public class DoneModuleCommandParser {
    /**
     * Returns a new DoneModuleCommand object parsed from the given command string.
     */
    public DoneModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ParsedCommand command = new CommandParserBuilder()
                .requiredPrefixes(PREFIX_MOD_CODE)
                .usageMessage(DoneModuleCommand.MESSAGE_USAGE)
                .build()
                .parse(args);

        // Java doesn't support re-throwing checked exceptions in method references, so we have to wrap
        // it in `rethrowFunction` from `LambdaExceptionUtil`.
        ModCode code = command.mapMandatoryArgument(PREFIX_MOD_CODE, rethrowFunction(ParserUtil::parseCode));

        return new DoneModuleCommand(code);
    }
}
