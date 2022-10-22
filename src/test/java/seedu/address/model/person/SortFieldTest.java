package seedu.address.model.person;

import org.junit.jupiter.api.Test;

public class SortFieldTest {

    @Test
    public void viewObject() {
        PersonSortField s = PersonSortField.createSortField("n");
        System.out.println(s);
    }

}
