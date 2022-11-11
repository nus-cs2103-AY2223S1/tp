package coydir.model.person;

import static coydir.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DepartmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Department(null));
    }

    @Test
    public void constructor_invalidDepartment_throwsIllegalArgumentException() {
        String invalidDepartment = "Invalid";
        assertThrows(IllegalArgumentException.class, () -> new Department(invalidDepartment));
    }

    @Test
    public void isValidDepartment() {
        // null department
        assertThrows(NullPointerException.class, () -> Department.isValidDepartment(null));

        // invalid departments
        assertFalse(Department.isValidDepartment("")); // empty string
        assertFalse(Department.isValidDepartment(" ")); // spaces only
        assertFalse(Department.isValidDepartment("Not a Department")); // not in list
        assertFalse(Department.isValidDepartment("Logistics")); // not in list

        // valid departments
        assertTrue(Department.isValidDepartment("Administration"));
        assertTrue(Department.isValidDepartment("Board of Directors"));
        assertTrue(Department.isValidDepartment("Customer Service"));
        assertTrue(Department.isValidDepartment("Finance"));
        assertTrue(Department.isValidDepartment("General Management"));
        assertTrue(Department.isValidDepartment("Human Resources"));
        assertTrue(Department.isValidDepartment("Information Technology"));
        assertTrue(Department.isValidDepartment("Legal"));
        assertTrue(Department.isValidDepartment("Marketing"));
        assertTrue(Department.isValidDepartment("Operations"));
        assertTrue(Department.isValidDepartment("Product Management"));
        assertTrue(Department.isValidDepartment("Production"));
        assertTrue(Department.isValidDepartment("Quality Assurance"));
        assertTrue(Department.isValidDepartment("Research and Development"));
        assertTrue(Department.isValidDepartment("Sales"));
        assertTrue(Department.isValidDepartment("Technology"));
    }
}
