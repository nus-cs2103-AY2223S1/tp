package seedu.address.model.team;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TeamBuilder;

import static seedu.address.testutil.Assert.assertThrows;

public class TeamTest {

    @Test
    public void asObservableList_modifyList_throwsPersonNotFoundException() {
        Team team = new TeamBuilder().build();
        assertThrows(PersonNotFoundException.class, () -> team.getMembers().remove(new PersonBuilder().build()));
    }

    @Test
    public void asObservableList_modifyList_throwsTaskNotFoundException() {
        Team team = new TeamBuilder().build();
        assertThrows(TaskNotFoundException.class, () -> team.getTasks().remove(new PersonBuilder().build()));
    }

}
