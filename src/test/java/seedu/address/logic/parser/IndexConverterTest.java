package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_ONE;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;

class IndexConverterTest {
    private IndexConverter converter = new IndexConverter();

    @Test
    public void convert_validIndex_success() throws Exception {
        String validIndex = "1";
        Index index = converter.convert(validIndex);
        assertEquals(INDEX_ONE, index);
    }

    @Test
    public void convert_invalidIndex_throwsTypeConversionException() {
        String inValidIndex = "0";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(inValidIndex));
    }
}
