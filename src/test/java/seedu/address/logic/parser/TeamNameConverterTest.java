package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FIRST_TEAM;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.team.TeamName;

class TeamNameConverterTest {

    private TeamNameConverter converter = new TeamNameConverter();

    @Test
    public void convert_validTeamName_success() throws Exception {
        String validTeamName = VALID_NAME_FIRST_TEAM;
        TeamName teamName = converter.convert(validTeamName);
        assertEquals(new TeamName(validTeamName), teamName);
    }

    @Test
    public void convert_invalidTeamName_throwsTypeConversionException() {
        String inValidTeamName = "question?mark";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(inValidTeamName));
    }
}
