package seedu.address.logic.parser;

import picocli.CommandLine;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.TeamName;

/**
 * Converter from {@code String} to {@code TeamName}
 */
public class TeamNameConverter implements CommandLine.ITypeConverter<TeamName> {
    public TeamName convert(String value) throws Exception {
        try {
            return ParserUtil.parseTeamName(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}
