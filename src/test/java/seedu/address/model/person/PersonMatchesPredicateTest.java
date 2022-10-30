package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonMatchesPredicateBuilder;
import seedu.address.testutil.ProfessorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TeachingAssistantBuilder;

public class PersonMatchesPredicateTest {

    @Test
    public void equals() {
        PersonMatchesPredicate firstPredicate = new PersonMatchesPredicateBuilder()
                .withNamesList(Collections.singletonList("name1")).withEmailsList(Collections.singletonList("email1"))
                .withGenderList(Collections.singletonList("gender1")).withLocationsList(Collections.singletonList("location1"))
                .withModulesSet(Collections.singleton("module1"), true).withPhonesList(Collections.singletonList("111"))
                .withOfficeHoursList(Collections.singletonList("officeHour1")).withRatingsList(Collections.singletonList("1"))
                .withSpecList(Collections.singletonList("spec1")).withTypesList(Collections.singletonList("type1"))
                .withTagsSet(Collections.singleton("tag1"), true).withYearsList(Collections.singletonList("year1"))
                .withUserNamesList(Collections.singletonList("username1")).build();

        PersonMatchesPredicate secondPredicate = new PersonMatchesPredicateBuilder()
                .withNamesList(Collections.singletonList("name2")).withEmailsList(Collections.singletonList("email2"))
                .withGenderList(Collections.singletonList("gender2")).withLocationsList(Collections.singletonList("location2"))
                .withModulesSet(Collections.singleton("module2"), true).withPhonesList(Collections.singletonList("222"))
                .withOfficeHoursList(Collections.singletonList("officeHour2")).withRatingsList(Collections.singletonList("2"))
                .withSpecList(Collections.singletonList("spec2")).withTypesList(Collections.singletonList("type2"))
                .withTagsSet(Collections.singleton("tag2"), true).withYearsList(Collections.singletonList("year2"))
                .withUserNamesList(Collections.singletonList("username1")).build();


        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(secondPredicate.equals(secondPredicate));

        // same values -> returns true
        PersonMatchesPredicate firstPredicateCopy = new PersonMatchesPredicateBuilder(firstPredicate).build();
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One names
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withName("Alice Bob").build()));

        // Multiple names
        predicate.setNamesList(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withName("Alice Bob").build()));

        // Only one matching names
        predicate.setNamesList(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Carol").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withName("Alice Carol").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withName("Alice Carol").build()));

        // Mixed-case names
        predicate.setNamesList(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withName("Alice Carol").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withName("Alice Carol").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setNamesList(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withName("Alice").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withName("Alice").build()));
        // Non-matching keyword
        predicate.setNamesList(Arrays.asList("Carol"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withName("Alice Bob").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withName("Alice Bob").build()));

        // Keywords match other fields but not name
        PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
        PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();

        assertFalse(studentPredicate.test(new StudentBuilder().withName("bob").build()));
        assertFalse(professorPredicate.test(new ProfessorBuilder().withName("bob").build()));
        assertFalse(taPredicate.test(new TeachingAssistantBuilder().withName("bob").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One email
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withEmailsList(Collections.singletonList("example@example.com")).build();


        assertTrue(predicate.test(new StudentBuilder().withEmail("example@example.com").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withEmail("example@example.com").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withEmail("example@example.com").build()));

        // Only one matching email
        predicate.setEmailsList(Arrays.asList("example1@ex.com", "example2@ex.com"));
        assertTrue(predicate.test(new StudentBuilder().withEmail("example1@ex.com").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withEmail("example1@ex.com").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withEmail("example1@ex.com").build()));

        // Mixed-case email
        predicate.setEmailsList(Arrays.asList("exaMplE1@ex.com"));
        assertTrue(predicate.test(new StudentBuilder().withEmail("example1@ex.com").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withEmail("example1@ex.com").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withEmail("example1@ex.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
            // Zero keywords
            PersonMatchesPredicate predicate = new PersonMatchesPredicate();
            predicate.setEmailsList(Collections.emptyList());
            assertFalse(predicate.test(new StudentBuilder().withEmail("example@example.com").build()));
            assertFalse(predicate.test(new ProfessorBuilder().withEmail("example@example.com").build()));
            assertFalse(predicate.test(new TeachingAssistantBuilder().withEmail("example@example.com").build()));
            // Non-matching keyword
            predicate.setEmailsList(Arrays.asList("a@example.com"));
            assertFalse(predicate.test(new StudentBuilder().withEmail("example@example.com").build()));
            assertFalse(predicate.test(new ProfessorBuilder().withEmail("example@example.com").build()));
            assertFalse(predicate.test(new TeachingAssistantBuilder().withEmail("example@example.com").build()));

            // Keywords match other fields but not email
            PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
            PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
            PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();

            assertFalse(studentPredicate.test(new StudentBuilder().withEmail("e@gmail.com").build()));
            assertFalse(professorPredicate.test(new ProfessorBuilder().withEmail("e@gmail.com").build()));
            assertFalse(taPredicate.test(new TeachingAssistantBuilder().withEmail("e@gmail.com").build()));
    }

    @Test
    public void test_genderContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withGenderList(Collections.singletonList("M")).build();


        assertTrue(predicate.test(new StudentBuilder().withGender("M").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withGender("M").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withGender("M").build()));

        // Only one matching keyword
        predicate.setGendersList(Arrays.asList("M", "F"));
        assertTrue(predicate.test(new StudentBuilder().withGender("M").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withGender("M").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withGender("M").build()));

        // Mixed-case keywords
        predicate.setGendersList(Arrays.asList("m", "F"));
        assertTrue(predicate.test(new StudentBuilder().withGender("M").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withGender("M").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withGender("M").build()));
    }

    @Test
    public void test_genderDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setGendersList(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withGender("M").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withGender("M").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withGender("M").build()));
        // Non-matching keyword
        predicate.setGendersList(List.of("t"));
        assertFalse(predicate.test(new StudentBuilder().withGender("M").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withGender("M").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withGender("M").build()));

        // Keywords match other fields but not gender
        PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
        PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();

        assertFalse(studentPredicate.test(new StudentBuilder().withGender("M").build()));
        assertFalse(professorPredicate.test(new ProfessorBuilder().withGender("M").build()));
        assertFalse(taPredicate.test(new TeachingAssistantBuilder().withGender("M").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withPhonesList(Collections.singletonList("12345")).build();


        assertTrue(predicate.test(new StudentBuilder().withPhone("12345").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withPhone("12345").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withPhone("12345").build()));

        // Only one matching keyword
        predicate.setPhonesList(Arrays.asList("12345", "67890"));
        assertTrue(predicate.test(new StudentBuilder().withPhone("12345").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withPhone("12345").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withPhone("12345").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setPhonesList(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withPhone("123").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withPhone("123").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withPhone("123").build()));
        // Non-matching keyword
        predicate.setPhonesList(List.of("1234"));
        assertFalse(predicate.test(new StudentBuilder().withPhone("123").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withPhone("123").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withPhone("123").build()));

        // Keywords match other fields but not phone
        PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
        PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();

        assertFalse(studentPredicate.test(new StudentBuilder().withPhone("123").build()));
        assertFalse(professorPredicate.test(new ProfessorBuilder().withPhone("123").build()));
        assertFalse(taPredicate.test(new TeachingAssistantBuilder().withPhone("123").build()));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withLocationsList(Collections.singletonList("NUS")).build();


        assertTrue(predicate.test(new StudentBuilder().withLocation("NUS").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withLocation("NUS").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withLocation("NUS").build()));

        // Only one matching keyword
        predicate.setLocationsList(Arrays.asList("NUS", "NTU"));
        assertTrue(predicate.test(new StudentBuilder().withLocation("NUS").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withLocation("NUS").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withLocation("NUS").build()));

        // Mixed-case keywords
        predicate.setLocationsList(Arrays.asList("nUs", "NtU"));
        assertTrue(predicate.test(new StudentBuilder().withLocation("NUS").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withLocation("NTU").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withLocation("NUS").build()));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setLocationsList(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withLocation("NUS").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withLocation("NUS").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withLocation("NUS").build()));
        // Non-matching keyword
        predicate.setLocationsList(List.of("NTU"));
        assertFalse(predicate.test(new StudentBuilder().withLocation("NUS").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withLocation("NUS").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withLocation("NUS").build()));

        // Keywords match other fields but not location
        PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
        PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();

        assertFalse(studentPredicate.test(new StudentBuilder().withLocation("NTU").build()));
        assertFalse(professorPredicate.test(new ProfessorBuilder().withLocation("NTU").build()));
        assertFalse(taPredicate.test(new TeachingAssistantBuilder().withLocation("NTU").build()));
    }

    @Test
    public void test_usernameContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withUserNamesList(Collections.singletonList("codR")).build();


        assertTrue(predicate.test(new StudentBuilder().withGithubUsername("codR").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withGithubUsername("codR").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withGithubUsername("codR").build()));

        // Only one matching keyword
        predicate.setUserNamesList(Arrays.asList("codR", "keyboard"));
        assertTrue(predicate.test(new StudentBuilder().withGithubUsername("codR").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withGithubUsername("codR").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withGithubUsername("codR").build()));

        // Mixed-case keywords
        predicate.setUserNamesList(Arrays.asList("CoDr", "KeybOarD"));
        assertTrue(predicate.test(new StudentBuilder().withGithubUsername("codR").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withGithubUsername("codR").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withGithubUsername("codR").build()));
    }

    @Test
    public void test_usernameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setUserNamesList(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withGithubUsername("codR").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withGithubUsername("codR").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withGithubUsername("codR").build()));
        // Non-matching keyword
        predicate.setUserNamesList(List.of("NTU"));
        assertFalse(predicate.test(new StudentBuilder().withGithubUsername("codR").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withGithubUsername("codR").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withGithubUsername("codR").build()));

        // Keywords match other fields but not username
        PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
        PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();

        assertFalse(studentPredicate.test(new StudentBuilder().withGithubUsername("codR").build()));
        assertFalse(professorPredicate.test(new ProfessorBuilder().withGithubUsername("codR").build()));
        assertFalse(taPredicate.test(new TeachingAssistantBuilder().withGithubUsername("codR").build()));;
    }

    @Test
    public void test_ratingContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withRatingsList(Collections.singletonList("1")).build();

        assertTrue(predicate.test(new ProfessorBuilder().withRating("1").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withRating("1").build()));

        // Only one matching keyword
        predicate.setRatingsList(Arrays.asList("1", "2"));

        assertTrue(predicate.test(new ProfessorBuilder().withRating("1").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withRating("2").build()));
    }

    @Test
    public void test_ratingDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setRatingsList(Collections.emptyList());
        //always returns false for student
        assertFalse(predicate.test(new StudentBuilder().build()));
        assertFalse(predicate.test(new ProfessorBuilder().withRating("1").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withRating("1").build()));
        // Non-matching keyword
        predicate.setRatingsList(List.of("2"));

        assertFalse(predicate.test(new ProfessorBuilder().withRating("1").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withRating("1").build()));

        // Keywords match other fields but not rating
        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
        PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();


        assertFalse(professorPredicate.test(new ProfessorBuilder().withRating("1").build()));
        assertFalse(taPredicate.test(new TeachingAssistantBuilder().withRating("1").build()));
    }

    @Test
    public void test_specialisationContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withSpecList(Collections.singletonList("discrete math")).build();

        assertTrue(predicate.test(new ProfessorBuilder().withSpecialisation("discrete math").build()));

        // Only one matching keyword
        predicate.setSpecList(Arrays.asList("discrete math", "networks"));

        assertTrue(predicate.test(new ProfessorBuilder().withSpecialisation("networks").build()));

        // Mixed-case keywords
        predicate.setSpecList(Arrays.asList("DiScReTe MaTh", "NeTwOrks"));
        assertTrue(predicate.test(new ProfessorBuilder().withSpecialisation("networks").build()));;
    }

    @Test
    public void test_specialisationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setSpecList(Collections.emptyList());
        //always returns false for student
        assertFalse(predicate.test(new StudentBuilder().build()));

        assertFalse(predicate.test(new ProfessorBuilder().withSpecialisation("Algorithms").build()));
        //always return false for ta
        assertFalse(predicate.test(new TeachingAssistantBuilder().build()));

        // Non-matching keyword
        predicate.setSpecList(List.of("Algorithms"));

        assertFalse(predicate.test(new ProfessorBuilder().withSpecialisation("graphics").build()));


        // Keywords match other fields but not specialisation
        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();

        assertFalse(professorPredicate.test(new ProfessorBuilder().withSpecialisation("Gaming").build()));
    }

    @Test
    public void test_typeContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withTypesList(Collections.singletonList("stu")).build();

        assertTrue(predicate.test(new StudentBuilder().build()));
        predicate.setTypesList(Collections.singletonList("prof"));
        assertTrue(predicate.test(new ProfessorBuilder().build()));
        predicate.setTypesList(Collections.singletonList("ta"));
        assertTrue(predicate.test(new TeachingAssistantBuilder().build()));

        // Only one matching keyword
        predicate.setTypesList(Arrays.asList("ta", "stu", "prof"));
        assertTrue(predicate.test(new StudentBuilder().build()));
        assertTrue(predicate.test(new ProfessorBuilder().build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().build()));

        // Mixed-case keywords
        predicate.setTypesList(Arrays.asList("Ta", "ProF", "sTU"));
        assertTrue(predicate.test(new StudentBuilder().build()));
        assertTrue(predicate.test(new ProfessorBuilder().build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().build()));
    }

    @Test
    public void test_typeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setTypesList(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().build()));
        assertFalse(predicate.test(new ProfessorBuilder().build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().build()));

        // Non-matching keyword
        predicate.setTypesList(List.of("mentor"));
        assertFalse(predicate.test(new StudentBuilder().build()));
        assertFalse(predicate.test(new ProfessorBuilder().build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().build()));;
    }

    @Test
    public void test_officeHourContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withOfficeHoursList(Collections.singletonList("1-12:00-2")).build();

        assertTrue(predicate.test(new ProfessorBuilder().withOfficeHour("MONDAY, 12:00 PM - 02:00 PM").build()));

        // Only one matching keyword
        predicate.setOfficeHoursList(Arrays.asList("1-12:00-2", "2-14:00-2"));

        assertTrue(predicate.test(new ProfessorBuilder().withOfficeHour("MONDAY, 12:00 PM - 02:00 PM").build()));

    }

    @Test
    public void test_officeHourDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setOfficeHoursList(Collections.emptyList());
        //always returns false for student
        assertFalse(predicate.test(new StudentBuilder().build()));

        assertFalse(predicate.test(new ProfessorBuilder().withOfficeHour("MONDAY, 12:00 PM - 02:00 PM").build()));
        //always return false for ta
        assertFalse(predicate.test(new TeachingAssistantBuilder().build()));

        // Non-matching keyword
        predicate.setOfficeHoursList(List.of("1-12:00-2"));

        assertFalse(predicate.test(new ProfessorBuilder().withOfficeHour("TUESDAY, 02:00 PM - 04:00 PM").build()));

        // Keywords match other fields but not office hour
        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();

        assertFalse(professorPredicate.test(new ProfessorBuilder().withOfficeHour("TUESDAY, 02:00 PM - 04:00 PM").build()));
    }

    @Test
    public void test_yearContainsKeywords_returnsTrue() {
        // One keyword
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withYearsList(Collections.singletonList("1")).build();

        assertTrue(predicate.test(new StudentBuilder().withYear("1").build()));

        // Only one matching keyword
        predicate.setYearsList(Arrays.asList("1", "3"));

        assertTrue(predicate.test(new StudentBuilder().withYear("3").build()));
    }

    @Test
    public void test_yearDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setYearsList(Collections.emptyList());

        assertFalse(predicate.test(new StudentBuilder().withYear("1").build()));
        //always return false for professor
        assertFalse(predicate.test(new ProfessorBuilder().build()));
        //always return false for ta
        assertFalse(predicate.test(new TeachingAssistantBuilder().build()));

        // Non-matching keyword
        predicate.setYearsList(List.of("3"));

        assertFalse(predicate.test(new StudentBuilder().withYear("4").build()));

        // Keywords match other fields but not year
        PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
        studentPredicate.setYearsList(List.of("3"));

        assertFalse(studentPredicate.test(new StudentBuilder().withYear("4").build()));
    }

    @Test
    public void test_moduleContainsKeywords_returnsTrue() {
        // One module
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withModulesSet(new HashSet<>(List.of("CS1101S")), false).build();

        assertTrue(predicate.test(new StudentBuilder().withModuleCodes("CS1101S", "CS2222", "CS3333").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withModuleCode("CS1101S").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withModuleCode("CS1101S").build()));

        // Only one matching module
        predicate.setModulesSet(new HashSet<>(Arrays.asList("CS1101S", "CS2222", "CS3333")), false);
        assertTrue(predicate.test(new StudentBuilder().withModuleCodes("CS1101S").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withModuleCode("CS1101S").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withModuleCode("CS1101S").build()));

        // ALL search
        predicate.setModulesSet(new HashSet<>(Arrays.asList("CS1101S", "CS2222", "CS3333")), true);
        assertTrue(predicate.test(new StudentBuilder().withModuleCodes("CS1101S", "CS2222", "CS3333").build()));
        //ALL search for professor and ta
        predicate.setModulesSet(new HashSet<>(List.of("CS1101S")), true);
        assertTrue(predicate.test(new ProfessorBuilder().withModuleCode("CS1101S").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withModuleCode("CS1101S").build()));

        // Mixed-case modules
        predicate.setModulesSet(new HashSet<>(Arrays.asList("cS1101S", "Cs2222", "cs3333")), false);
        assertTrue(predicate.test(new StudentBuilder().withModuleCodes("cs1101S").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withModuleCode("CS2222").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withModuleCode("CS3333").build()));

        // ALL search mixed case
        predicate.setModulesSet(new HashSet<>(Arrays.asList("cs1101S", "cS2222", "Cs3333")), true);
        assertTrue(predicate.test(new StudentBuilder().withModuleCodes("CS1101S", "CS2222", "CS3333").build()));
    }

    @Test
    public void test_moduleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setModulesSet(new HashSet<>(), false);

        assertFalse(predicate.test(new StudentBuilder().withModuleCodes("CS1101S").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withModuleCode("CS1231S").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withModuleCode("CS2100").build()));

        // Non-matching keyword
        predicate.setModulesSet(new HashSet<>(Arrays.asList("CS1101S", "CS2222", "CS3333")), false);
        assertFalse(predicate.test(new StudentBuilder().withModuleCodes("CS4444", "CS2121").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withModuleCode("CS7777").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withModuleCode("CS3443").build()));

        // ALL search - non-matching keyword
        predicate.setModulesSet(new HashSet<>(Arrays.asList("CS1101S", "CS2222", "CS3333")), true);
        assertFalse(predicate.test(new StudentBuilder().withModuleCodes("CS1101S", "CS2222").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withModuleCode("CS3333").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withModuleCode("CS2222").build()));

        // Keywords match other fields but not modules
        PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
                studentPredicate.setModulesSet(new HashSet<>(Arrays.asList("CS1101S", "CS2222", "CS3333")), false);

        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
        professorPredicate.setModulesSet(new HashSet<>(List.of("CS1101S")), false);
        PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();
        taPredicate.setModulesSet(new HashSet<>(List.of("CS1231")), false);

        assertFalse(studentPredicate.test(new StudentBuilder().withModuleCodes("CS1231S", "CS3333").build()));
        assertFalse(professorPredicate.test(new ProfessorBuilder().withModuleCode("CS1431").build()));
        assertFalse(taPredicate.test(new TeachingAssistantBuilder().withModuleCode("CS4323").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One tag
        PersonMatchesPredicate predicate = new PersonMatchesPredicateBuilder()
                .withTagsSet(new HashSet<>(List.of("friend")), false).build();

        assertTrue(predicate.test(new StudentBuilder().withTags("friend", "goodCoder").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withTags("friend").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withTags("friend", "buddy").build()));

        // Only one matching tag
        predicate.setTagsSet(new HashSet<>(Arrays.asList("friend", "nice", "cool")), false);
        assertTrue(predicate.test(new StudentBuilder().withTags("friend").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withTags("nice").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withTags("cool").build()));

        // ALL search
        predicate.setTagsSet(new HashSet<>(Arrays.asList("friend", "nice", "cool")), true);
        assertTrue(predicate.test(new StudentBuilder().withTags("friend", "nice", "cool").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withTags("friend", "nice", "cool").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withTags("friend", "nice", "cool").build()));
        //ALL search with one tag
        predicate.setTagsSet(new HashSet<>(List.of("friend")), true);
        assertTrue(predicate.test(new StudentBuilder().withTags("friend", "nice", "cool").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withTags("friend", "nice", "cool").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withTags("friend", "nice", "cool").build()));
        // Mixed-case tags
        predicate.setTagsSet(new HashSet<>(Arrays.asList("fRieNd", "nIce", "cOol")), false);
        assertTrue(predicate.test(new StudentBuilder().withTags("friend").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withTags("nice").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withTags("cool").build()));

        // ALL search
        predicate.setTagsSet(new HashSet<>(Arrays.asList("fRieNd", "nIce", "cOol")), true);
        assertTrue(predicate.test(new StudentBuilder().withTags("friend", "nice", "cool").build()));
        assertTrue(predicate.test(new ProfessorBuilder().withTags("friend", "nice", "cool").build()));
        assertTrue(predicate.test(new TeachingAssistantBuilder().withTags("friend", "nice", "cool").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setTagsSet(new HashSet<>(), false);

        assertFalse(predicate.test(new StudentBuilder().withTags("friends", "goodCoder").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withTags("friendly").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withTags("niceGuy").build()));

        // Non-matching keyword
        predicate.setTagsSet(new HashSet<>(Arrays.asList("friend", "nice", "cool")), false);
        assertFalse(predicate.test(new StudentBuilder().withTags("friends", "goodCoder").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withTags("friendly", "fast").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withTags("niceGuy", "strong").build()));

        // ALL search - non-matching keyword
        predicate.setTagsSet(new HashSet<>(Arrays.asList("friend", "nice", "cool")), true);
        assertFalse(predicate.test(new StudentBuilder().withTags("friend", "cool").build()));
        assertFalse(predicate.test(new ProfessorBuilder().withTags("friend", "nice").build()));
        assertFalse(predicate.test(new TeachingAssistantBuilder().withTags("nice", "cool").build()));

        // Keywords match other fields but not tags
        PersonMatchesPredicate studentPredicate = PersonMatchesPredicateBuilder.buildStudentPredicate();
        studentPredicate.setTagsSet(new HashSet<>(Arrays.asList("friend", "nice", "cool")), true);

        PersonMatchesPredicate professorPredicate = PersonMatchesPredicateBuilder.buildProfessorPredicate();
        professorPredicate.setTagsSet(new HashSet<>(Arrays.asList("friend", "nice", "cool")), true);
        PersonMatchesPredicate taPredicate = PersonMatchesPredicateBuilder.buildTeachingAssistantPredicate();
        taPredicate.setTagsSet(new HashSet<>(Arrays.asList("friend", "nice", "cool")), true);

        assertFalse(studentPredicate.test(new StudentBuilder().withTags("friends", "goodCoder").build()));
        assertFalse(professorPredicate.test(new ProfessorBuilder().withTags("friendly").build()));
        assertFalse(taPredicate.test(new TeachingAssistantBuilder().withTags("niceGuy").build()));
    }
}
