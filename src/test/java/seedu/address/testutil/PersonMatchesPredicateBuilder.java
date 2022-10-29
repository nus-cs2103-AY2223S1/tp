package seedu.address.testutil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.PersonMatchesPredicate;

/**
 * A utility class to help with building PersonMatchesPredicate objects.
 */
public class PersonMatchesPredicateBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GENDER = "F";
    public static final String DEFAULT_LOCATION = "NUS";
    public static final String DEFAULT_USERNAME = "amyb";
    private PersonMatchesPredicate predicate = new PersonMatchesPredicate();
    public PersonMatchesPredicateBuilder() {

    }

    /**
     * Sets the predicate object by copying the provided predicate object.
     * @param predicateToCopy the other predicate to copy
     */
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

    /**
     * Sets the location list of the predicate
     * @param locationsList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withLocationsList(List<String> locationsList) {
        predicate.setLocationsList(locationsList);
        return this;
    }

    /**
     * Sets the phones list of the predicate
     * @param phonesList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withPhonesList(List<String> phonesList) {
        predicate.setPhonesList(phonesList);
        return this;
    }

    /**
     * Sets the emails list of the predicate
     * @param emailsList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withEmailsList(List<String> emailsList) {
        predicate.setEmailsList(emailsList);
        return this;
    }

    /**
     * Sets the types list of the predicate
     * @param typesList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withTypesList(List<String> typesList) {
        predicate.setTypesList(typesList);
        return this;
    }

    /**
     * Sets the gender list of the predicate
     * @param genderList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withGenderList(List<String> genderList) {
        predicate.setGendersList(genderList);
        return this;
    }

    /**
     * Sets the rating list of the predicate
     * @param ratingsList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withRatingsList(List<String> ratingsList) {
        predicate.setRatingsList(ratingsList);
        return this;
    }

    /**
     * Sets the rating list of the predicate
     * @param yearsList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withYearsList(List<String> yearsList) {
        predicate.setYearsList(yearsList);
        return this;
    }

    /**
     * Sets the specialisation list of the predicate
     * @param specList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withSpecList(List<String> specList) {
        predicate.setSpecList(specList);
        return this;
    }

    /**
     * Sets the office hour list of the predicate
     * @param officeHoursList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withOfficeHoursList(List<String> officeHoursList) {
        predicate.setOfficeHoursList(officeHoursList);
        return this;
    }

    /**
     * Sets the modules set of the predicate
     * @param modulesList the list to set to
     * @param needsAll if all modules need to be matched
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withModulesSet(Set<String> modulesList, boolean needsAll) {
        predicate.setModulesSet(modulesList, needsAll);
        return this;
    }

    /**
     * Sets the tags set of the predicate
     * @param tagsList the list to set to
     * @param needsAll if all tags need to be matched
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withTagsSet(Set<String> tagsList, boolean needsAll) {
        predicate.setTagsSet(tagsList, needsAll);
        return this;
    }

    /**
     * Sets the username list of the predicate
     * @param userNamesList the list to set to
     * @return the builder object
     */
    public PersonMatchesPredicateBuilder withUserNamesList(List<String> userNamesList) {
        predicate.setUserNamesList(userNamesList);
        return this;
    }

    public PersonMatchesPredicate build() {
        return predicate;
    }

    /**
     * Builds a predicate that match the {@code StudentBuilder class} default values
     * @return the predicate builder object
     */
    public static PersonMatchesPredicate buildStudentPredicate() {
        String moduleCode = "CS1101S";
        String year = "1";
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(List.of(DEFAULT_NAME.split("\\s+")));
        predicate.setEmailsList(List.of(DEFAULT_EMAIL));
        predicate.setPhonesList(List.of(DEFAULT_PHONE));
        predicate.setGendersList(List.of(DEFAULT_GENDER));
        predicate.setUserNamesList(List.of(DEFAULT_USERNAME));
        predicate.setLocationsList(List.of(DEFAULT_LOCATION));
        predicate.setYearsList(List.of(year));
        predicate.setModulesSet(new HashSet<>(List.of(moduleCode)), false);
        predicate.setTypesList(List.of("stu"));

        return predicate;
    }

    /**
     * Builds a predicate that match the {@code ProfessorBuilder class} default values
     * @return the predicate builder object
     */
    public static PersonMatchesPredicate buildProfessorPredicate() {
        String moduleCode = "CS1231S";
        String rating = "5";
        String specialisation = "Computer Graphics";
        String officeHour = "TUESDAY, 06:00 PM - 09:00 PM";
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(List.of(DEFAULT_NAME.split("\\s+")));
        predicate.setEmailsList(List.of(DEFAULT_EMAIL));
        predicate.setPhonesList(List.of(DEFAULT_PHONE));
        predicate.setGendersList(List.of(DEFAULT_GENDER));
        predicate.setUserNamesList(List.of(DEFAULT_USERNAME));
        predicate.setLocationsList(List.of(DEFAULT_LOCATION));
        predicate.setModulesSet(new HashSet<>(List.of(moduleCode)), false);
        predicate.setRatingsList(List.of(rating));
        predicate.setSpecList(List.of(specialisation));
        predicate.setOfficeHoursList(List.of(officeHour));
        predicate.setTypesList(List.of("prof"));

        return predicate;
    }

    /**
     * Builds a predicate that match the {@code TeachingAssistantBuilder class} default values
     * @return the predicate builder object
     */
    public static PersonMatchesPredicate buildTeachingAssistantPredicate() {
        String moduleCode = "CS1231S";
        String rating = "5";
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(List.of(DEFAULT_NAME.split("\\s+")));
        predicate.setEmailsList(List.of(DEFAULT_EMAIL));
        predicate.setPhonesList(List.of(DEFAULT_PHONE));
        predicate.setGendersList(List.of(DEFAULT_GENDER));
        predicate.setUserNamesList(List.of(DEFAULT_USERNAME));
        predicate.setLocationsList(List.of(DEFAULT_LOCATION));
        predicate.setModulesSet(new HashSet<>(List.of(moduleCode)), false);
        predicate.setRatingsList(List.of(rating));
        predicate.setTypesList(List.of("ta"));

        return predicate;
    }
}
