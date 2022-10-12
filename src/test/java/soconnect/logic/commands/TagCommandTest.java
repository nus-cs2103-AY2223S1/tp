package soconnect.logic.commands;

import static soconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.ModelManager;


public class TagCommandTest {

    @Test
    public void execute_parse_exception() {
        assertThrows(ParseException.class, () -> new TagCommand().execute(new ModelManager()));
    }

}
