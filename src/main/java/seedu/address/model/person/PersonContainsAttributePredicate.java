package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final List<String> surveysList;
    private final List<String> tagsList;

    /**
     * Every field must be non-null.
     */
    public PersonContainsAttributePredicate(List<String> nameList, List<String> phoneList, List<String> emailList,
                                        List<String> addressList, List<String> genderList, List<String> birthdateList,
                                        List<String> raceList, List<String> religionList, List<String> surveysList,
                                        List<String> tagsList) {

        this.nameList = nameList;
        this.phoneList = phoneList;
        this.emailList = emailList;
        this.addressList = addressList;
        this.genderList = genderList;
        this.birthdateList = birthdateList;
        this.raceList = raceList;
        this.religionList = religionList;
        this.surveysList = surveysList;
        this.tagsList = tagsList;
    }

    @Override
    public boolean test(Person person) {

        boolean containsName = nameList.isEmpty() || nameList.stream()
                .anyMatch(containsIgnoreCase(person.getName().toString()));
        boolean containsPhone = phoneList.isEmpty() || phoneList.stream()
                .anyMatch(containsIgnoreCase(person.getPhone().toString()));
        boolean containsEmail = emailList.isEmpty() || emailList.stream()
                .anyMatch(email -> person.getEmail().toString().contains(email));
        boolean containsAddress = addressList.isEmpty() || addressList.stream()
                .anyMatch(containsIgnoreCase(person.getAddress().toString()));
        boolean containsGender = genderList.isEmpty() || genderList.stream()
                .anyMatch(containsIgnoreCase(person.getGender().toString()));
        boolean containsBirthdate = birthdateList.isEmpty() || birthdateList.stream()
                .anyMatch(birthdate -> person.getBirthdate().toString().contains(birthdate));
        boolean containsRace = raceList.isEmpty() || raceList.stream()
                .anyMatch(containsIgnoreCase(person.getRace().toString()));
        boolean containsReligion = religionList.isEmpty() || religionList.stream()
                .anyMatch(containsIgnoreCase(person.getReligion().toString()));
        boolean containsSurvey = surveysList.isEmpty();
        for (Survey survey : person.getSurveys()) {
            if (surveysList.stream().anyMatch(containsIgnoreCase(survey.survey))) {
                containsSurvey = true;
                break;
            }
        }
        boolean containsTags = tagsList.isEmpty();
        for (Tag tag : person.getTags()) {
            if (tagsList.stream().anyMatch(containsIgnoreCase(tag.tagName))) {
                containsTags = true;
                break;
            }
        }

        return (containsName && containsPhone && containsEmail && containsAddress && containsGender
                && containsBirthdate && containsRace && containsReligion && containsSurvey
                && containsTags);
    }

    /**
     * Checks whether a given {@code String} contains a word or phrase (case-insensitive).
     * @return A predicate for whether a string contains a word or phrase.
     */
    public static Predicate<String> containsIgnoreCase(String targetString) {
        return keyword -> Arrays.stream(targetString.split("\\s+"))
                .anyMatch(targetWord -> targetWord.equalsIgnoreCase(keyword))
                || (keyword.contains(" ")
                && containsSubstringIgnoreCase(targetString, keyword)
                && containWordsIgnoreCase(targetString, keyword));
    }

    /**
     * Checks whether a given {@code String} contains a {@code String} as a substring (case-insensitive).
     * @return A boolean for whether a string contains the substring.
     */
    public static boolean containsSubstringIgnoreCase(String supersetString, String subsetString) {
        return supersetString.toLowerCase().contains(subsetString.toLowerCase());
    }

    /**
     * Checks whether a given {@code String} contains a {@code String} as a phrase (case-insensitive).
     * @return A boolean for whether a string contains a phrase.
     */
    public static boolean containWordsIgnoreCase(String supersetString, String subsetString) {
        List<String> supersetStringArray = Stream.of(supersetString.split("\\s+"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        List<String> subsetStringArray = Stream.of(subsetString.split("\\s+"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        return supersetStringArray.containsAll(subsetStringArray);
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
                && surveysList.equals(((PersonContainsAttributePredicate) other).surveysList)
                && tagsList.equals(((PersonContainsAttributePredicate) other).tagsList)); //state check
    }

}
