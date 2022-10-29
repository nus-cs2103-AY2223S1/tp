package seedu.address.testutil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.PersonMatchesPredicate;

/**
 * A utility class to help with building PersonMatchesPredicate objects.
 */
public class PersonMatchesPredicateBuilder {
    private PersonMatchesPredicate predicate = new PersonMatchesPredicate();
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_LOCATION = "NUS";
    public static final String DEFAULT_USERNAME = "amyb";
    public static final String DEFAULT_TAG = "friends";
    public PersonMatchesPredicateBuilder() {

    }

    public PersonMatchesPredicateBuilder(PersonMatchesPredicate predicateToCopy) {
        predicate.setNamesList(predicateToCopy.getNamesList());
        predicate.setEmailsList(predicateToCopy.getEmailsList());
        predicate.setTypesList(predicateToCopy.getTypesList());
        predicate.setPhonesList(predicateToCopy.getPhonesList());
        predicate.setTagsSet(predicateToCopy.getTagsSet(), predicateToCopy.getNeedsAllTags());
        predicate.setGendersList(predicateToCopy.getGendersList());
        predicate.setModulesSet(predicateToCopy.getModulesSet(), predicateToCopy.getNeedsAllModules());
        predicate.setRatingsList(predicateToCopy.getRatingsList());
        predicate.setSpecList(predicateToCopy.getSpecList());
        predicate.setOfficeHoursList(predicateToCopy.getOfficeHoursList());
        predicate.setUserNamesList(predicateToCopy.getUserNamesList());
        predicate.setYearsList(predicateToCopy.getYearsList());
        predicate.setLocationsList(predicateToCopy.getLocationsList());
    }
    /**
     * Sets the List of Names for the PersonMatchesPredicate we are building.
     */
    public PersonMatchesPredicateBuilder withNamesList(List<String> namesList) {
        predicate.setNamesList(namesList);
        return this;
    }

    public PersonMatchesPredicateBuilder withLocationsList(List<String> locationsList) {
        predicate.setLocationsList(locationsList);
        return this;
    }

    public PersonMatchesPredicateBuilder withPhonesList(List<String> phonesList) {
        predicate.setPhonesList(phonesList);
        return this;
    }

    public PersonMatchesPredicateBuilder withEmailsList(List<String> emailsList) {
        predicate.setEmailsList(emailsList);
        return this;
    }

    public PersonMatchesPredicateBuilder withTypesList(List<String> typesList) {
        predicate.setTypesList(typesList);
        return this;
    }

    public PersonMatchesPredicateBuilder withGenderList(List<String> genderList) {
        predicate.setGendersList(genderList);
        return this;
    }

    public PersonMatchesPredicateBuilder withRatingsList(List<String> ratingsList) {
        predicate.setRatingsList(ratingsList);
        return this;
    }

    public PersonMatchesPredicateBuilder withYearsList(List<String> yearsList) {
        predicate.setYearsList(yearsList);
        return this;
    }

    public PersonMatchesPredicateBuilder withSpecList(List<String> specList) {
        predicate.setSpecList(specList);
        return this;
    }

    public PersonMatchesPredicateBuilder withOfficeHoursList(List<String> officeHoursList) {
        predicate.setOfficeHoursList(officeHoursList);
        return this;
    }

    public PersonMatchesPredicateBuilder withModulesSet(Set<String> modulesList, boolean needsAll) {
        predicate.setModulesSet(modulesList, needsAll);
        return this;
    }

    public PersonMatchesPredicateBuilder withTagsSet(Set<String> tagsList, boolean needsAll) {
        predicate.setTagsSet(tagsList, needsAll);
        return this;
    }

    public PersonMatchesPredicateBuilder withUserNamesList(List<String> userNamesList) {
        predicate.setUserNamesList(userNamesList);
        return this;
    }

    public PersonMatchesPredicate build() {
        return predicate;
    }

    public static PersonMatchesPredicate buildStudentPredicate() {
        String DEFAULT_MODULE_CODE = "CS1101S";
        String DEFAULT_YEAR = "";
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(List.of(DEFAULT_NAME.split("\\s+")));
        predicate.setEmailsList(List.of(DEFAULT_EMAIL));
        predicate.setPhonesList(List.of(DEFAULT_PHONE));
        predicate.setGendersList(List.of(DEFAULT_GENDER));
        predicate.setUserNamesList(List.of(DEFAULT_USERNAME));
        predicate.setLocationsList(List.of(DEFAULT_LOCATION));
        predicate.setYearsList(List.of(DEFAULT_YEAR));
        predicate.setModulesSet(new HashSet<>(List.of(DEFAULT_MODULE_CODE)), false);
        predicate.setTypesList(List.of("stu"));

        return predicate;
    }

    public static PersonMatchesPredicate buildProfessorPredicate() {
        String DEFAULT_MODULE_CODE = "CS1231S";
        String DEFAULT_RATING = "5";
        String DEFAULT_SPECIALISATION = "Computer Graphics";
        String DEFAULT_OFFICE_HOUR = "TUESDAY, 06:00 PM - 09:00 PM";
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(List.of(DEFAULT_NAME.split("\\s+")));
        predicate.setEmailsList(List.of(DEFAULT_EMAIL));
        predicate.setPhonesList(List.of(DEFAULT_PHONE));
        predicate.setGendersList(List.of(DEFAULT_GENDER));
        predicate.setUserNamesList(List.of(DEFAULT_USERNAME));
        predicate.setLocationsList(List.of(DEFAULT_LOCATION));
        predicate.setModulesSet(new HashSet<>(List.of(DEFAULT_MODULE_CODE)), false);
        predicate.setRatingsList(List.of(DEFAULT_RATING));
        predicate.setSpecList(List.of(DEFAULT_SPECIALISATION));
        predicate.setOfficeHoursList(List.of(DEFAULT_OFFICE_HOUR));
        predicate.setTypesList(List.of("prof"));

        return predicate;
    }

    public static PersonMatchesPredicate buildTeachingAssistantPredicate() {
        String DEFAULT_MODULE_CODE = "CS1231S";
        String DEFAULT_RATING = "5";
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(List.of(DEFAULT_NAME.split("\\s+")));
        predicate.setEmailsList(List.of(DEFAULT_EMAIL));
        predicate.setPhonesList(List.of(DEFAULT_PHONE));
        predicate.setGendersList(List.of(DEFAULT_GENDER));
        predicate.setUserNamesList(List.of(DEFAULT_USERNAME));
        predicate.setLocationsList(List.of(DEFAULT_LOCATION));
        predicate.setModulesSet(new HashSet<>(List.of(DEFAULT_MODULE_CODE)), false);
        predicate.setRatingsList(List.of(DEFAULT_RATING));
        predicate.setTypesList(List.of("ta"));

        return predicate;
    }
}
