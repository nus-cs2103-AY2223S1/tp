package modtrekt.logic.parser.tasks;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.LambdaExceptionUtil.rethrowFunction;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static modtrekt.logic.parser.CliSyntax.PREFIX_TASK;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.tasks.ArchiveTaskCommand;
import modtrekt.logic.parser.ParserUtil;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.logic.parser.util.CommandParserBuilder;
import modtrekt.logic.parser.util.ParsedCommand;
import modtrekt.model.module.ModCode;

/**
 * Parser for a command string input for a ArchiveTaskCommand object.
 */
public class ArchiveTaskCommandParser {
    /**
     * Returns a new ArchiveTaskCommand object parsed from the given command string.
     */
    public ArchiveTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ParsedCommand command = new CommandParserBuilder()
                .requiredPrefixes(PREFIX_MOD_CODE, PREFIX_TASK)
                .usageMessage(ArchiveTaskCommand.MESSAGE_COMMAND_USAGE)
                .build()
                .parse(args);

        // Java doesn't support re-throwing checked exceptions in method references, so we have to wrap
        // it in `rethrowFunction` from `LambdaExceptionUtil`.
        ModCode code = command.mapMandatoryArgument(PREFIX_MOD_CODE, rethrowFunction(ParserUtil::parseCode));
        Index index = command.mapMandatoryArgument(PREFIX_TASK, rethrowFunction(ParserUtil::parseIndex));

        return new ArchiveTaskCommand(code, index);
    }
}
