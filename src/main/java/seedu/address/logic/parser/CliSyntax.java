package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    public static final String FLAG_ASSIGNEE_STR = "-a";
    public static final String FLAG_ASSIGNEE_STR_LONG = "--assignee";
    public static final String FLAG_COMPLETE_TASKS_STR = "-c";
    public static final String FLAG_COMPLETE_TASKS_STR_LONG = "--complete";
    public static final String FLAG_DESCRIPTION_STR = "-d";
    public static final String FLAG_DESCRIPTION_LONG = "--description";
    public static final String FLAG_DEADLINE_STR = "-d";
    public static final String FLAG_DEADLINE_STR_LONG = "--deadline";
    public static final String FLAG_EMAIL_STR = "-e";
    public static final String FLAG_EMAIL_STR_LONG = "--email";
    public static final String FLAG_HELP_STR = "-h";
    public static final String FLAG_HELP_STR_LONG = "--help";
    public static final String FLAG_INCOMPLETE_TASKS_STR = "-i";
    public static final String FLAG_INCOMPLETE_TASKS_STR_LONG = "--incomplete";
    public static final String FLAG_URL_STR = "-l";
    public static final String FLAG_URL_STR_LONG = "--link";
    public static final String FLAG_NAME_STR = "-n";
    public static final String FLAG_NAME_STR_LONG = "--name";
    public static final String FLAG_PHONE_STR = "-p";
    public static final String FLAG_PHONE_STR_LONG = "--phone";
    public static final String FLAG_TAG_STR = "-t";
    public static final String FLAG_TAG_STR_LONG = "--tag";

    /* Descriptions for flags */
    public static final String FLAG_HELP_DESCRIPTION = "Shows this message";
    public static final String FLAG_PERSON_NAME_DESCRIPTION = "Name of person (e.g. \"John Doe\")";
    public static final String FLAG_PERSON_PHONE_DESCRIPTION = "Phone of person (e.g. 98765432)";
    public static final String FLAG_PERSON_EMAIL_DESCRIPTION = "Email of person (e.g. johnd@example.com)";
    public static final String FLAG_PERSON_INDEX_DESCRIPTION = "Index of person (must be a positive integer)";
    public static final String FLAG_PERSON_TAGS_DESCRIPTION = "Tags of person (e.g. \"Frontend\" \"Backend\"";
    public static final String FLAG_LINK_NAME_DESCRIPTION = "Name of link (e.g. \"Meeting #1\")";
    public static final String FLAG_LINK_URL_DESCRIPTION = "URL of link (e.g. https://google.com)";
    public static final String FLAG_LINK_INDEX_DESCRIPTION = "Index of link (must be a positive integer)";
    public static final String FLAG_TEAM_NAME_DESCRIPTION = "Name of team (e.g. \"CS2103T\")";
    public static final String FLAG_TEAM_DESCRIPTION_DESCRIPTION = "Description of team (e.g. \"A team to manage "
            + "CS2103T\")";
    public static final String FLAG_TASK_INDEX_DESCRIPTION = "Index of task (must be a positive integer)";
    public static final String FLAG_TASK_NAME_DESCRIPTION = "Name of task (e.g. \"merge PR#12\")";
    public static final String FLAG_TASK_DEADLINE_DESCRIPTION = "Deadline of task (e.g. 2023-02-25 23:59)";
    public static final String FLAG_TASK_ASSIGNEES_DESCRIPTION = "Index of member in Team list "
            + "(must be a positive integer)";
    public static final String FLAG_MEMBER_NAME_DESCRIPTION = "Name of member (e.g. \"John Doe\")";
    public static final String FLAG_MEMBER_INDEX_DESCRIPTION = "Index of member (must be a positive integer)";
    public static final String FLAG_NAME_SEARCH_KEYWORDS_DESCRIPTION = "Keywords to search for (e.g. Alice Bob)";
    public static final String FLAG_EMAIL_SEARCH_KEYWORDS_DESCRIPTION = "Keywords to search for (e.g. johnd@example"
            + ".com alexy@example.com)";
    public static final String FLAG_COMPLETE_TASK_DESCRIPTION = "Filter for completed tasks";
    public static final String FLAG_INCOMPLETE_TASK_DESCRIPTION = "Filter for incomplete tasks";
    public static final String FLAG_SORT_ORDER_DESCRIPTION = "Sort by order specified (e.g. asc, dsc, res)";
}
