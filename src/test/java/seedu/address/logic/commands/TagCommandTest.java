package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;


public class TagCommandTest {

    @Test
    public void execute_parse_exception() {
        assertThrows(ParseException.class, () -> new TagCommand().execute(new ModelManager()));
    }

}
