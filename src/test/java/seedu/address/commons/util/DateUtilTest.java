package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.DateUtil.dateIsEqualAndAfterToday;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateUtilTest {
    @Test
    public void targetDate_equalToday_correctResult() {
        assertEquals(dateIsEqualAndAfterToday(LocalDate.now()), true);
    }

    @Test
    public void targetDate_afterToday_correctResult() {
        assertEquals(dateIsEqualAndAfterToday(LocalDate.now().plusDays(2)), true);
    }

    @Test
    public void targetDate_beforeToday_correctResult() {
        assertEquals(dateIsEqualAndAfterToday(LocalDate.now().minusDays(2)), false);
    }
}
