package hobbylist.commons.core;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the Alias settings.
 * Guarantees: immutable.
 */
public class AliasSettings implements Serializable {
    private static final String DEFAULT_ADD = "add";
    private static final String DEFAULT_CLEAR = "clear";
    private static final String DEFAULT_DELETE = "delete";
    private static final String DEFAULT_EDIT = "edit";
    private static final String DEFAULT_EXIT = "exit";
    private static final String DEFAULT_FIND_TAG = "findTag";
    private static final String DEFAULT_FIND_STATUS = "findStatus";
    private static final String DEFAULT_FIND = "find";
    private static final String DEFAULT_LIST = "list";
    private static final String DEFAULT_HELP = "help";
    private static final String DEFAULT_RATE = "rate";
    private static final String DEFAULT_RATE_ABOVE = "r/above";
    private static final String DEFAULT_SELECT = "select";

    private String add;
    private String clear;
    private String delete;
    private String edit;
    private String exit;
    private String findTag;
    private String findStatus;
    private String find;
    private String list;
    private String help;
    private String rate;
    private String rateAbove;
    private String select;

    /**
     * Constructs a {@code AliasSettings} with the default command words.
     */
    public AliasSettings() {
        add = DEFAULT_ADD;
        clear = DEFAULT_CLEAR;
        delete = DEFAULT_DELETE;
        edit = DEFAULT_EDIT;
        exit = DEFAULT_EXIT;
        findTag = DEFAULT_FIND_TAG;
        findStatus = DEFAULT_FIND_STATUS;
        find = DEFAULT_FIND;
        list = DEFAULT_LIST;
        help = DEFAULT_HELP;
        rate = DEFAULT_RATE;
        rateAbove = DEFAULT_RATE_ABOVE;
        select = DEFAULT_SELECT;
    }

    /**
     * Constructs a {@code AliasSettings} with the specified command words.
     */
    public AliasSettings(String add, String clear, String delete, String edit, String exit, String findTag,
                         String find, String list, String help, String rate, String findStatus, String rateAbove,
                         String select) {
        this.add = add;
        this.clear = clear;
        this.delete = delete;
        this.edit = edit;
        this.exit = exit;
        this.findTag = findTag;
        this.findStatus = findStatus;
        this.find = find;
        this.list = list;
        this.help = help;
        this.rate = rate;
        this.rateAbove = rateAbove;
        this.select = select;
    }

    public String getAdd() {
        return add;
    }

    public String getClear() {
        return clear;
    }

    public String getDelete() {
        return delete;
    }

    public String getEdit() {
        return edit;
    }

    public String getExit() {
        return exit;
    }

    public String getFindTag() {
        return findTag;
    }

    public String getFindStatus() {
        return findStatus;
    }

    public String getFind() {
        return find;
    }

    public String getList() {
        return list;
    }

    public String getHelp() {
        return help;
    }

    public String getRate() {
        return rate;
    }

    public String getRateAbove() {
        return rateAbove;
    }

    public String getSelect() {
        return select;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AliasSettings)) { //this handles null as well.
            return false;
        }

        AliasSettings o = (AliasSettings) other;

        return add.equals(o.add)
                && clear.equals(o.clear)
                && delete.equals(o.delete)
                && edit.equals(o.edit)
                && exit.equals(o.exit)
                && findTag.equals(o.findTag)
                && findStatus.equals(o.findStatus)
                && find.equals(o.find)
                && list.equals(o.list)
                && help.equals(o.help)
                && rate.equals(o.rate)
                && rateAbove.equals(o.rateAbove)
                && select.equals(o.select);
    }

    @Override
    public int hashCode() {
        return Objects.hash(add, clear, delete, edit, exit, findTag, find, list, help, rate, findStatus, rateAbove,
                select);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("add : " + add + "\n");
        sb.append("clear : " + clear + "\n");
        sb.append("delete : " + delete + "\n");
        sb.append("edit : " + edit + "\n");
        sb.append("exit : " + exit + "\n");
        sb.append("findStatus : " + findStatus + "\n");
        sb.append("findTag : " + findTag + "\n");
        sb.append("find : " + find + "\n");
        sb.append("list : " + list + "\n");
        sb.append("help : " + help + "\n");
        sb.append("rate : " + rate + "\n");
        sb.append("rateAbove : " + rateAbove + "\n");
        sb.append("select : " + select);
        return sb.toString();
    }
}
