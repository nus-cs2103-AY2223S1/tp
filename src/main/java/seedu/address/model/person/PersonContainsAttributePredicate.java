package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s attributes matches any of the keywords given for each attribute.
 */
public class PersonContainsAttributePredicate implements Predicate<Person> {

    private final List<String> nameList;
    private final List<String> phoneList;
    private final List<String> emailList;
    private final List<String> addressList;
    private final List<String> genderList;
    private final List<String> birthdateList;
    private final List<String> raceList;
    private final List<String> religionList;
    private final List<String> surveyList;
    private final Set<Tag> tagsList;

    /**
     * Every field must be non-null.
     */
    public PersonContainsAttributePredicate(List<String> nameList, List<String> phoneList, List<String> emailList,
                                            List<String> addressList, List<String> genderList, List<String> birthdateList,
                                            List<String> raceList, List<String> religionList, List<String> surveyList,
                                            Set<Tag> tagsList) {

        this.nameList = nameList;
        this.phoneList = phoneList;
        this.emailList = emailList;
        this.addressList = addressList;
        this.genderList = genderList;
        this.birthdateList = birthdateList;
        this.raceList = raceList;
        this.religionList = religionList;
        this.surveyList = surveyList;
        this.tagsList = tagsList;
    }

    @Override
    public boolean test(Person person) {

        boolean containsName = nameList.isEmpty() || nameList.stream()
                .anyMatch(doesKeywordMatchWith(person.getName().fullName));
        boolean containsPhone = phoneList.isEmpty() || phoneList.stream()
                .anyMatch(doesKeywordMatchWith(person.getPhone().value));
        boolean containsEmail = emailList.isEmpty() || emailList.stream()
                .anyMatch(doesKeywordMatchWith(person.getEmail().value));
        boolean containsAddress = addressList.isEmpty() || addressList.stream()
                .anyMatch(doesKeywordMatchWith(person.getAddress().value));
        boolean containsGender = genderList.isEmpty() || genderList.stream()
                .anyMatch(doesKeywordMatchWith(person.getGender().gender));
        boolean containsBirthdate = birthdateList.isEmpty() || birthdateList.stream()
                .anyMatch(keyword -> person.getBirthdate().toString().contains(keyword));
        boolean containsRace = raceList.isEmpty() || raceList.stream()
                .anyMatch(doesKeywordMatchWith(person.getRace().race));
        boolean containsReligion = religionList.isEmpty() || religionList.stream()
                .anyMatch(doesKeywordMatchWith(person.getReligion().religion));
        boolean containsSurvey = surveyList.isEmpty() || surveyList.stream()
                .anyMatch(doesKeywordMatchWith(person.getSurvey().survey));
        boolean containsTags = tagsList.isEmpty() || person.getTags().containsAll(tagsList);

        return (containsName && containsPhone && containsEmail && containsAddress && containsGender
                && containsBirthdate && containsRace && containsReligion && containsSurvey
                && containsTags);
    }

    public Predicate<String> doesKeywordMatchWith(String targetString) {
        return keyword -> StringUtil.containsWordIgnoreCase(targetString, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsAttributePredicate // instanceof handles nulls
                && nameList.equals(((PersonContainsAttributePredicate) other).nameList)
                && phoneList.equals(((PersonContainsAttributePredicate) other).phoneList)
                && emailList.equals(((PersonContainsAttributePredicate) other).emailList)
                && addressList.equals(((PersonContainsAttributePredicate) other).addressList)
                && genderList.equals(((PersonContainsAttributePredicate) other).genderList)
                && birthdateList.equals(((PersonContainsAttributePredicate) other).birthdateList)
                && raceList.equals(((PersonContainsAttributePredicate) other).raceList)
                && religionList.equals(((PersonContainsAttributePredicate) other).religionList)
                && surveyList.equals(((PersonContainsAttributePredicate) other).surveyList)
                && tagsList.equals(((PersonContainsAttributePredicate) other).tagsList)); //state check
    }

}
