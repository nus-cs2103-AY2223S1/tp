package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;

import java.util.Arrays;
import java.util.Locale;

import seedu.condonery.logic.commands.property.TypePropertyCommand;
import seedu.condonery.logic.parser.Parser;
import seedu.condonery.logic.parser.ParserUtil;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.property.PropertyTypeContainsKeywordsPredicate;
import seedu.condonery.model.tag.PropertyTypeEnum;

/**
 * Parses input arguments and creates a new TypePropertyCommand object
 */
public class TypePropertyCommandParser implements Parser<TypePropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TypeCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TypePropertyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toUpperCase();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TypePropertyCommand.MESSAGE_USAGE));
        }
        System.out.println(trimmedArgs);



        String[] nameKeywords = trimmedArgs.split("\\s+");

        for (int i = 0; i < nameKeywords.length; i++) {
            try {
                ParserUtil.parsePropertyType(
                        nameKeywords[i]);
            } catch (IllegalArgumentException ex) {
                throw new ParseException(
                        "Invalid Property Type specified! Property Type must be one of HDB, CONDO, or LANDED");
            }
        }

        return new TypePropertyCommand(new PropertyTypeContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
