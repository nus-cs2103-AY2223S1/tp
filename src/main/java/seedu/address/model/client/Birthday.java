package seedu.address.model.client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Birthday {
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date of birth cannot be in the future or invalid";

    private static final String STANDARD_DATE = "yyyy-MM-dd";

    private LocalDateTime birthday;
    private boolean celebrated;

    public Birthday(LocalDateTime t) {
        this.birthday = t;
        this.celebrated = false;
    }

    /**
     * Overloaded birthday parameter to dictate whether a birthday is celebrated or not
     */
    public Birthday(LocalDateTime t, boolean celebrated) {
        this.birthday = t;
        this.celebrated = celebrated;
    }

    /**
     * Default constructor for birthday where it is unspecified!
     */

    public Birthday unspecified() {
        return new Birthday(null);
    }

    public boolean checkCelebrated() {
        return this.celebrated;
    }

    /**Add a reminder to celebrate if not true?
     *
     */
    public void celebrateBirthday() {
        this.celebrated = true;
    }

    public LocalDateTime getBirthday() {
        return this.birthday;
    }

    /**
     * Utility function to check if a birthday is valid
     */

    public static boolean isValidBirthday(LocalDateTime date) {
        return LocalDateTime.now().isEqual(date) || LocalDate.now().isAfter(ChronoLocalDate.from(date));
    }

    /**TODO: Think about how to addbirthday
     *
     */

    public static LocalDateTime parseBirthday(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STANDARD_DATE);
        LocalDateTime dateTime = LocalDateTime.parse(s, formatter);
        checkArgument(isValidBirthday(dateTime), MESSAGE_DATE_CONSTRAINTS);
        return dateTime;
    }



    public int calculateAge() {
        int currentYear = LocalDateTime.now().getYear();
        return currentYear - birthday.getYear();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Birthday)) {
            return false;
        }
        Birthday otherBirthday = (Birthday) other;
        boolean sameCelebrate = this.checkCelebrated() == otherBirthday.checkCelebrated();
        if (this.getBirthday() == null || otherBirthday.getBirthday() == null) {
            return sameCelebrate && this.getBirthday() == otherBirthday.getBirthday();
        } else {
            return sameCelebrate && this.getBirthday().equals(otherBirthday.getBirthday());
        }
    }
    
}
