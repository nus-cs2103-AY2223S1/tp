package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} to check its validity.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static void parseIndexValidity(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String name} to check if it is valid.
     * Leading and trailing whitespaces will be trimmed.
     * It is an overloaded method that doesn't return a Name object.
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static void parseNameValidity(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param clientId in string format from user input
     * @return Client of the client id
     * @throws ParseException if the fiven {@code client} is invalid.
     */
    public static ClientId parseClientId(String clientId) throws ParseException, NumberFormatException {
        requireNonNull(clientId);
        String trimmedClientId = clientId.trim();
        if (!ClientId.isValidClientId(trimmedClientId)) {
            throw new ParseException(ClientId.MESSAGE_CONSTRAINTS);
        }
        int clientIdInt = Integer.parseInt(trimmedClientId);
        return new ClientId(clientIdInt);
    }

    /**
     * Parses a {@code String repository} into a {@code Repository}.
     * Leading a trailing whitespaces will be trimmed.
     *
     * @param repository string argument input
     * @return parsed Repository object
     * @throws ParseException if the given {@code repository} is invalid.
     */
    public static Repository parseRepository(String repository) throws ParseException {
        requireNonNull(repository);
        String trimmedRepository = repository.trim();
        if (!Repository.isValidRepository(trimmedRepository)) {
            throw new ParseException(Repository.MESSAGE_CONSTRAINTS);
        }
        return new Repository(trimmedRepository);
    }

    /**
     * Parses a {@code String repository} and checks its validity.
     * Leading a trailing whitespaces will be trimmed.
     * It is an overloaded method that does not return a Repository object
     * @param repository string argument input
     * @throws ParseException if the given {@code repository} is invalid.
     */
    public static void parseRepositoryValidity(String repository) throws ParseException {
        requireNonNull(repository);
        String trimmedRepository = repository.trim();
        if (!Repository.isValidRepository(trimmedRepository)) {
            throw new ParseException(Repository.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading a trailing whitespaces will be trimmed.
     *
     * @param deadline string argument input
     * @return parsed Deadline object
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }


    /**
     * Parses a {@code String mobile} into a {@code ClientMobile}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mobile} is invalid.
     */
    public static ClientMobile parseMobile(String mobile) throws ParseException {
        requireNonNull(mobile);
        String trimmedMobile = mobile.trim();
        if (!ClientMobile.isValidClientMobile(trimmedMobile)) {
            throw new ParseException(ClientMobile.MESSAGE_CONSTRAINTS);
        }
        return new ClientMobile(trimmedMobile);
    }

    /**
     * Parses a {@code String mobile} to check if it is valid.
     * Leading and trailing whitespaces will be trimmed.
     * It is an overloaded method that doesn't return a ClientMobile object.
     * @throws ParseException if the given {@code mobile} is invalid.
     */
    public static void parseMobileValidity(String mobile) throws ParseException {
        requireNonNull(mobile);
        String trimmedMobile = mobile.trim();
        if (!ClientMobile.isValidClientMobile(trimmedMobile)) {
            throw new ParseException(ClientMobile.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String email} into an {@code ClientEmail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static ClientEmail parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!ClientEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(ClientEmail.MESSAGE_CONSTRAINTS);
        }
        return new ClientEmail(trimmedEmail);
    }

    /**
     * Parses a {@code String email} to check if it is valid.
     * Leading and trailing whitespaces will be trimmed.
     * It is an overloaded method that doesn't return a ClientEmail object.
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static void parseEmailValidity(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!ClientEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(ClientEmail.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     * It is an overloaded method that doesn't return a Title object.
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static void parseTitleValidity(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(seedu.address.model.issue.Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String urgency} into a {@code Urgency}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code urgency} is invalid.
     */
    public static Urgency parseUrgency(String urgency) throws ParseException {
        requireNonNull(urgency);
        String trimmedUrgency = urgency.trim();
        if (!Urgency.isValidUrgency(trimmedUrgency)) {
            throw new ParseException(Urgency.MESSAGE_CONSTRAINTS);
        }
        switch (trimmedUrgency) {
        case ("0"):
            return Urgency.NONE;
        case ("1"):
            return Urgency.LOW;
        case ("2"):
            return Urgency.MEDIUM;
        case ("3"):
            return Urgency.HIGH;
        default:
            throw new ParseException(Urgency.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String status} to check if it is valid.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static void parseStatusValidity(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        boolean isValidStringCounterpart =
                trimmedStatus.equalsIgnoreCase("completed")
                        || trimmedStatus.equalsIgnoreCase("incomplete");
        if (!isValidStringCounterpart) {
            throw new ParseException(Status.MESSAGE_STRING_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String urgency} into a {@code Urgency}.
     * Leading and trailing whitespaces will be trimmed.
     * It is an overloaded method that doesn't return a Urgency object.
     * @throws ParseException if the given {@code urgency} is invalid.
     */

    public static void parseUrgencyValidity(String urgency) throws ParseException {
        requireNonNull(urgency);
        String trimmedUrgency = urgency.trim();
        boolean isValidStringCounterpart =
                trimmedUrgency.equalsIgnoreCase("high")
                        || trimmedUrgency.equalsIgnoreCase("low")
                        || trimmedUrgency.equalsIgnoreCase("medium")
                        || trimmedUrgency.equalsIgnoreCase("none");
        if (!isValidStringCounterpart) {
            throw new ParseException(Urgency.MESSAGE_STRING_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String projectId} into a {@code Project}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return project id.
     * @throws ParseException if the given {@code projectId} is invalid.
     */
    public static ProjectId parseProjectId(String projectId) throws ParseException {
        requireNonNull(projectId);
        String trimmedId = projectId.trim();
        if (!ProjectId.isValidProjectId(trimmedId)) {
            throw new ParseException(ProjectId.MESSAGE_CONSTRAINTS);
        }
        int trimmedIdInt = Integer.parseInt(trimmedId);
        return new ProjectId(trimmedIdInt);
    }

    /**
     * Parses a {@code String projectId} into a stub default {@code Project}.
     *
     * @param projectId is the id of the project
     * @return A stub default project
     */
    public static Project parseProjectStub(String projectId) throws ParseException {
        requireNonNull(projectId);
        String trimmedId = projectId.trim();
        if (!ProjectId.isValidProjectId(trimmedId)) {
            throw new ParseException(ProjectId.MESSAGE_INVALID);
        }
        int trimmedIdInt = Integer.parseInt(trimmedId);
        return new Project(new Name("default"), new Repository("default/default"),
                new Deadline("2022-03-05"), new Client(new Name("default")),
                new ArrayList<>(), new ProjectId(trimmedIdInt), new Pin(false));
    }


    /**
     * Parses a {@code String issueId} into a {@code IssueId}.
     *
     * @param issueId is the id of the issue
     * @return An issueId object
     */
    public static IssueId parseIssueId(String issueId) throws ParseException {
        requireNonNull(issueId);
        String trimmedId = issueId.trim();
        if (!IssueId.isValidIssueId(trimmedId)) {
            throw new ParseException(IssueId.MESSAGE_INVALID);
        }
        int trimmedIdInt = Integer.parseInt(trimmedId);
        return new IssueId(trimmedIdInt);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by deadline.
     * @return Integer of 0 or 1 which specifies the chronology of sort
     */
    public static Integer parseDeadlineSortForProject(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Project.isValidDeadlineSortKey(trimmedKey)) {
            throw new ParseException(Project.MESSAGE_INVALID_DEADLINE_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by issue count.
     * @return Integer of 0 or 1 which specifies the numeric order of sorting.
     */
    public static Integer parseIssueCountSort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Project.isValidIssueCountSortKey(trimmedKey)) {
            throw new ParseException(Project.MESSAGE_INVALID_ISSUE_COUNT_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by name.
     * @return Integer of 0 or 1 which specifies the alphabetical order of sorting.
     */
    public static Integer parseProjectNameSort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Project.isValidNameSortKey(trimmedKey)) {
            throw new ParseException(Project.MESSAGE_INVALID_NAME_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user to sort by urgency.
     * @return Integer of 0 or 1 which specifies the urgency order of sorting.
     */
    public static Integer parseUrgencySort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Issue.isValidUrgencySortKey(trimmedKey)) {
            throw new ParseException(Issue.MESSAGE_INVALID_URGENCY_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by deadline.
     * @return Integer of 0 or 1 which specifies the chronology of sort
     */
    public static Integer parseDeadlineSortForIssue(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Issue.isValidDeadlineSortKey(trimmedKey)) {
            throw new ParseException(Issue.MESSAGE_INVALID_DEADLINE_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by name.
     * @return Integer of 0 or 1 which specifies the alphabetical order of sorting.
     */
    public static Integer parseClientNameSort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Client.isValidNameSortKey(trimmedKey)) {
            throw new ParseException(Client.MESSAGE_INVALID_NAME_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by project id.
     * @return Integer of 0 or 1 which specifies the order of sorting.
     */
    public static Integer parseProjectIdSort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Project.isValidProjectIdSortKey(trimmedKey)) {
            throw new ParseException(Project.MESSAGE_INVALID_PROJECT_ID_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by issue id.
     * @return Integer of 0 or 1 which specifies the order of sorting.
     */
    public static Integer parseIssueIdSort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Issue.isValidIssueIdSortKey(trimmedKey)) {
            throw new ParseException(Issue.MESSAGE_INVALID_ISSUE_ID_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parses a {@code String key} into an {@code Integer}.
     *
     * @param key is the value entered by the user for sort by client id.
     * @return Integer of 0 or 1 which specifies the order of sorting.
     */
    public static Integer parseClientIdSort(String key) throws ParseException {
        requireNonNull(key);
        String trimmedKey = key.trim();
        if (!Client.isValidClientIdSortKey(trimmedKey)) {
            throw new ParseException(Client.MESSAGE_INVALID_CLIENT_ID_SORT_KEY);
        }
        return Integer.parseInt(trimmedKey);
    }

    /**
     * Parse a string to get only the first word of said string.
     * @param str string to parse
     * @return the first word of the string
     */
    public static String getFirstWord(String str) {
        requireNonNull(str);

        return str.split(" ", 2)[0];
    }
}
