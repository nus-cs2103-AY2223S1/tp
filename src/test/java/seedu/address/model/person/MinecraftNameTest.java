package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MinecraftNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MinecraftName(null));
    }

    @Test
    public void isValidMinecraftName() {
        // null MinecraftName
        assertThrows(NullPointerException.class, () -> MinecraftName.isValidMinecraftName(null));

        //Invalid MinecraftNames
        assertFalse(MinecraftName.isValidMinecraftName(" ")); //MinecraftName must contain at least 1 character
        assertFalse(MinecraftName.isValidMinecraftName("John Doe")); //MinecraftName cannot have any spaces
        assertFalse(MinecraftName.isValidMinecraftName("Amy Bee"));

        //Valid MinecraftNames
        assertTrue(MinecraftName.isValidMinecraftName("zbzlvlv"));
        // non-alpha-numeric symbols allowed
        assertTrue(MinecraftName.isValidMinecraftName("valid_minecraft_name!"));
        assertTrue(MinecraftName.isValidMinecraftName("1234567"));
    }

    @Test
    public void isEqualsMinecraftName() {
        MinecraftName testName = new MinecraftName("Chustinjeng");

        // null MinecraftName
        assertThrows(NullPointerException.class, () -> testName.equals(new MinecraftName(null)));

        // MinecraftNames that are not equal
        assertNotEquals(testName, new MinecraftName("zbzlvlv"));
        assertNotEquals(testName, new MinecraftName("johnDoe"));
        assertNotEquals(testName, new MinecraftName("random_Minecraft_Name"));

        //MinecraftNames that are equal
        assertEquals(testName, testName);
        assertEquals(testName, new MinecraftName("Chustinjeng"));
    }
}
