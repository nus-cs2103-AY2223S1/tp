package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code ModuleCode} matches any of the keywords given.
 */
public class ModuleDetailsContainsKeywordsPredicate implements Predicate<Module> {

    private final List<String> keywords;

    public ModuleDetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * This method converts the String returned from calling toString method on a Set to correct format.
     * @param assignmentDetails String returned from calling toString method on AssignmentDetails,
     *                          for example: "[[lab1], [assignment1]]".
     * @return String representation of assignment details seperated by space, for example: "lab1 assignment1".
     */
    private String assignmentDetailsToString(String assignmentDetails) {
        //case when no assignment details
        if (assignmentDetails.equals("[]")) {
            return "";
        }
        String[] assignmentDetailsSplit = assignmentDetails.split(", ");
        String result = "";
        int numAssignmentDetails = assignmentDetailsSplit.length;
        for (int i = 0; i < numAssignmentDetails; i++) {
            //in the case of only 1 word, need to trim 2 brackets from either side
            if (numAssignmentDetails == 1) {
                result = assignmentDetailsSplit[i].substring(2, assignmentDetailsSplit[i].length() - 2);
                //for the first word, need to trim 2 brackets from front, 1 from rear
            } else if (i == 0) {
                result = result + assignmentDetailsSplit[i].substring(2, assignmentDetailsSplit[i].length() - 1) + " ";
                //for last word, need trim 1 bracket from front, 2 from rear
            } else if (i == numAssignmentDetails - 1) {
                result = result + assignmentDetailsSplit[i].substring(1, assignmentDetailsSplit[i].length() - 2);
                //if middle, just trim 1 bracket from both front and rear
            } else {
                result = result + assignmentDetailsSplit[i].substring(1, assignmentDetailsSplit[i].length() - 1) + " ";
            }
        }
        return result;
    }

    @Override
    public boolean test(Module module) {
        String moduleDetails = module.getModuleCode().moduleCode
                + " " + module.getModuleCode().getModuleTitle()
                + " " + module.getLectureDetails().toString()
                + " " + module.getTutorialDetails().toString()
                + " " + assignmentDetailsToString(module.getAssignmentDetails().toString());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(moduleDetails, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleDetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleDetailsContainsKeywordsPredicate) other).keywords)); // state check
    }
}
