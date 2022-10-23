package seedu.address.logic.parser;

import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;

public class IndexConverter implements CommandLine.ITypeConverter<Index> {
    @Override
    public Index convert(String value) throws Exception {
        String trimmedIndex = value.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new CommandLine.TypeConversionException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }
}
