package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getSampleUniqueRemarkList;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.remark.Remark;
import seedu.address.model.remark.Text;
import seedu.address.model.remark.UniqueRemarkList;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {
    @Test
    public void getSampleUniqueRemarkList_twoRemarkTexts_returnsSuccess() {
        UniqueRemarkList list = new UniqueRemarkList();
        String first = "Remark 1";
        String second = "Remark 2";

        list.add(new Remark(new Text(first)));
        list.add(new Remark(new Text(second)));

        assertEquals(list, getSampleUniqueRemarkList(first, second));
    }

    @Test
    public void getSampleUniqueRemarkList_noRemarkTexts_returnsSuccess() {
        UniqueRemarkList list = new UniqueRemarkList();

        assertEquals(list, getSampleUniqueRemarkList());
    }

    @Test
    public void getTagSet_twoTagStrings_returnsSuccess() {
        Set<Tag> set = new HashSet<>();
        String first = "TagOne";
        String second = "TagTwo";

        set.add(new Tag(first));
        set.add(new Tag(second));

        assertEquals(set, getTagSet(first, second));
    }

    @Test
    public void getTagSet_noTagStrings_returnsSuccess() {
        Set<Tag> set = new HashSet<>();

        assertEquals(set, getTagSet());
    }
}
