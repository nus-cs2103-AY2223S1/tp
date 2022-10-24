package nus.climods.model.module;

import java.math.BigDecimal;

/**
 * Enum to record the four possible semester numbers
 */
public enum Semester {
    SEMESTER_ONE(BigDecimal.valueOf(1)),
    SEMESTER_TWO(BigDecimal.valueOf(2)),
    SPECIAL_TERM_ONE(BigDecimal.valueOf(3)),
    SPECIAL_TERM_TWO(BigDecimal.valueOf(4));

    private final BigDecimal semesterNumber;

    Semester(BigDecimal semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public boolean equals(BigDecimal semesterNumber) {
        return this.semesterNumber.equals(semesterNumber);
    }
}
