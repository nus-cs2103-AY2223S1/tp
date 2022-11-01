package seedu.address.testutil;

import seedu.address.model.tag.DeadlineTag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTagBuilder {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final LocalDate DEFAULT_DATE = LocalDate.parse("31-12-2022",dtf);
    private final LocalDate deadline;


    public DeadlineTagBuilder() {
        deadline = DEFAULT_DATE;
    }

    public DeadlineTagBuilder(LocalDate deadlineToAdd) {
        deadline = deadlineToAdd;
    }

    public DeadlineTag build() {
        return new DeadlineTag(deadline);
    }

}
