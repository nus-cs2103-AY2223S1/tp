package seedu.address.model.person.github;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void execute_validUsername_success() {
        User githubUser = new User("Vshnv2001", new ArrayList<>());
        assertTrue(User.isValidUsername(githubUser.getUsername()));
    }

    @Test
    public void execute_invalidUsername_failure() {
        assertFalse(User.isValidUsername("veggien@v"));
    }

    @Test
    public void execute_getUsernameWhenNotExists_failure() {
        User githubUser = new User("Vshnv2001", new ArrayList<>());
        assertFalse(githubUser.getEmail().isPresent());
    }

}
