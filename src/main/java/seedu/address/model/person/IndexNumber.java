package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class IndexNumber {

    public static final String MESSAGE_CONSTRAINTS =
        "Index number should only contain numbers from 1 to 99";

    public final String indexNumber;

    public IndexNumber(String indexNumber) {
        requireNonNull(indexNumber);
        checkArgument(isValidIndexNumber(indexNumber), MESSAGE_CONSTRAINTS);
        this.indexNumber = indexNumber;
    }

    public static boolean isValidIndexNumber(String test) {
        Integer ind = Integer.valueOf(test);
        return ind >= 1 && ind <= 99;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof IndexNumber // instanceof handles nulls
                   && indexNumber.equals(((IndexNumber) other).indexNumber)); // state check
    }
}
