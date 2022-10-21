package modtrekt.logic.parser;

import static modtrekt.commons.util.LambdaExceptionUtil.rethrowFunction;
import static modtrekt.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static modtrekt.logic.parser.CliSyntax.PREFIX_MOD_CODE;
import static modtrekt.logic.parser.CliSyntax.PREFIX_TASK;
import static modtrekt.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.time.LocalDate;
import java.util.Optional;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.EditTaskCommand;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.logic.parser.util.CommandParserBuilder;
import modtrekt.logic.parser.util.ParsedCommand;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Description;


/**
 * Parses input arguments for remove command and returns a Command object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of RemoveCommand.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public Command parse(String args) throws ParseException {
        ParsedCommand command = new CommandParserBuilder()
                .requiredPrefixes(PREFIX_TASK)
                .usageMessage("hi")
                .optionalPrefixes(PREFIX_DEADLINE, PREFIX_MOD_CODE, PREFIX_TASK_DESCRIPTION)
                .build()
                .parse(args);

        Index index = command.mapMandatoryArgument(PREFIX_TASK, rethrowFunction(ParserUtil::parseIndex));

        Optional<ModCode> optionalModCode =
                command.mapOptionalArgument(PREFIX_MOD_CODE, rethrowFunction(ParserUtil::parseCode));
        ModCode code = optionalModCode.isPresent() ? optionalModCode.get() : null;

        Optional<Description> optionalDescription =
                command.mapOptionalArgument(PREFIX_TASK_DESCRIPTION, rethrowFunction(ParserUtil::parseDescription));
        Description description = optionalDescription.isPresent() ? optionalDescription.get() : null;
        System.out.println(description);

        Optional<LocalDate> optionalDeadline =
                command.mapOptionalArgument(PREFIX_DEADLINE, rethrowFunction(ParserUtil::parseDueDate));
        LocalDate deadline = optionalDeadline.isPresent() ? optionalDeadline.get() : null;

        if (deadline == null && code == null && description == null) {
            throw new ParseException(
                    "Please enter a deadline(-d) and/or module code(-c) and/or description(-ds) to edit");
        }

        return new EditTaskCommand(index, code, deadline, description);
    }
}
