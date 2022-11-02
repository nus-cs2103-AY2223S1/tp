package seedu.address.logic.parser;

import picocli.CommandLine;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.LinkName;

public class LinkNameConverter implements CommandLine.ITypeConverter<LinkName> {
    @Override
    public LinkName convert(String value) throws Exception {
        try {
            return ParserUtil.parseLinkName(value);
        } catch (ParseException e) {
            throw new CommandLine.TypeConversionException(e.getMessage());
        }
    }
}

