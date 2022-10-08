package seedu.address.model.person;

import org.junit.jupiter.api.Test;

public class SortFieldTest {

    @Test
    public void viewObject() {
        SortField s = SortField.createSortField("n");
        System.out.println(s);
    }

}
