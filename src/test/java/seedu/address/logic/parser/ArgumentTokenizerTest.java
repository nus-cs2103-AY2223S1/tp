package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ArgumentTokenizerTest {
    @Test
    public void tokenize_spaceSeparatedStr_returnsTokens() throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenize("space separated string");
        assertArrayEquals(tokens, new String[]{"space", "separated", "string"});
    }

    @Test
    public void tokenize_stringWithDoubleQuotedTokens_returnsTokens() throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenize("space separated string with \"double quoted\" tokens");
        assertArrayEquals(tokens, new String[]{"space", "separated", "string", "with", "double quoted", "tokens"});
    }

    @Test
    public void tokenize_stringWithSingleQuotedTokens_returnsTokens() throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenize("space separated string with 'single quoted' tokens");
        assertArrayEquals(tokens, new String[]{"space", "separated", "string", "with", "single quoted", "tokens"});
    }

    @Test
    public void tokenize_stringWithNestedQuotes_returnsTokens() throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenize("space separated string with '\"nested\" quoted' tokens");
        assertArrayEquals(tokens,
                new String[]{"space", "separated", "string", "with", "\"nested\" quoted", "tokens"});
        String[] tokens2 = ArgumentTokenizer.tokenize("space separated string with \"'nested' quoted\" tokens");
        assertArrayEquals(tokens2,
                new String[]{"space", "separated", "string", "with", "'nested' quoted", "tokens"});
    }
}
