package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assignment object that encapsulates the
 * Assignment's name, grade and weightage
 */
public class Assignment {

    public static final String GRADE_VALIDATION_REGEX = "\\d{1,3}" + "/" + "\\d{1,3}";

    public static final String WEIGHTAGE_VALIDATION_REGEX = "^(100|[1-9]?[0-9])$";

    public static final String GRADE_CONSTRAINTS =
            "Grade should be in the format [integer (0-100)]/[integer (0-100)], where the first number is smaller than "
                    + "or equal to the second number.";

    public static final String WEIGHTAGE_CONSTRAINTS =
            "Weightage should be in terms of percentages, i.e. 0 - 100";

    private final String name;
    private String grade;
    private final String weightage;
    private boolean isGradeUpdated;

    /**
     * Constructs an {@code Assignment}.
     *
     * @param name An assignment name.
     * @param weightage A valid weightage.
     */

    public Assignment(String name, String weightage) {
        requireNonNull(name);
        requireNonNull(weightage);
        checkArgument(isValidWeightage(weightage), WEIGHTAGE_CONSTRAINTS);
        this.name = name;
        this.grade = "0/0";
        this.weightage = weightage;
        this.isGradeUpdated = false;
    }

    /**
     * Constructs an {@code Assignment}.
     *
     * @param name An assignment name.
     * @param grade A valid grade.
     * @param weightage A valid weightage.
     */
    public Assignment(String name, String grade, String weightage) {
        requireNonNull(name);
        requireNonNull(grade);
        requireNonNull(weightage);
        checkArgument(isValidRetrievedGrade(grade), GRADE_CONSTRAINTS);
        checkArgument(isValidWeightage(weightage), WEIGHTAGE_CONSTRAINTS);
        this.name = name;
        this.grade = grade;
        this.weightage = weightage;
        this.isGradeUpdated = false;
    }
    /**
     * Returns if a given string is a valid grade.
     */
    public static boolean isValidInputGrade(String test) {
        if (!test.matches(GRADE_VALIDATION_REGEX)) {
            return false;
        } else {
            String[] split = test.split("/");
            Integer firstNumber = Integer.parseInt(split[0]);
            Integer secondNumber = Integer.parseInt(split[1]);
            return firstNumber <= secondNumber && secondNumber <= 100;
        }
    }

    /**
     * Returns if a given string is a valid grade.
     */
    public static boolean isValidRetrievedGrade(String test) {
        if (!test.matches(GRADE_VALIDATION_REGEX)) {
            return false;
        } else {
            String[] split = test.split("/");
            Integer firstNumber = Integer.parseInt(split[0]);
            Integer secondNumber = Integer.parseInt(split[1]);
            return firstNumber <= secondNumber && secondNumber <= 100;
        }
    }

    /**
     * Returns if a given string is a valid weightage.
     */
    public static boolean isValidWeightage(String test) {
        return test.matches(WEIGHTAGE_VALIDATION_REGEX);
    }

    public void setGrade(String grade) {
        this.grade = grade;
        this.isGradeUpdated = true;
    }

    public int getWeightage() {
        return Integer.parseInt(weightage);
    }

    public float getGradePercentage() {
        float gradeAchieved = (float) Integer.parseInt(grade.split("/")[0]);
        float fullMark = (float) Integer.parseInt(grade.split("/")[1]);
        if (fullMark == 0.0) {
            return 0;
        }
        float gradePercentage = gradeAchieved / fullMark;
        return gradePercentage;
    }

    public String getAssignmentName() {
        return name;
    }

    public Integer getScore() {
        return Integer.valueOf(grade.split("/")[0]);
    }

    public Integer getMaximumScore() {
        return Integer.valueOf(grade.split("/")[1]);
    }

    public boolean getIsGradeUpdated() {
        return isGradeUpdated;
    }

    @Override
    public String toString() {
        return "(" + name + " Score: " + grade + " Weightage: " + weightage + "%" + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && name.equals(((Assignment) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }



}
