package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import picocli.CommandLine;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class NameContainsKeywordsPredicateConverter implements CommandLine.ITypeConverter<NameContainsKeywordsPredicate> {
    @Override
    public NameContainsKeywordsPredicate convert(String value) throws Exception {
        String trimmedArgs = value.trim();
        if (trimmedArgs.isEmpty()) {
            throw new CommandLine.TypeConversionException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        List<String> keywords = List.of(trimmedArgs.split("\\s+"));
        return new NameContainsKeywordsPredicate(keywords);
    }
}
