package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.parser.exceptions.DuplicatePrefixException;

public class CliSyntaxTest {
    @Test
    void addTagPrefix_existingPrefixInPrefixes_throwsDuplicatePrefixException() {
        Prefix toAdd = new Prefix(CliSyntax.getPrefixTags().get(0).getPrefix());
        assertThrows(DuplicatePrefixException.class, () -> CliSyntax.addTagPrefix(toAdd));
    }
}
