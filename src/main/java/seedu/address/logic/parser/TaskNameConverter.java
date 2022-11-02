package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.TaskName;

/**
 * Converter from {@code String} to {@code TaskName}.
 */
public class TaskNameConverter implements CommandLine.ITypeConverter<TaskName> {
    @Override
    public TaskName convert(String value) throws Exception {
        try {
            return ParserUtil.parseTaskName(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
