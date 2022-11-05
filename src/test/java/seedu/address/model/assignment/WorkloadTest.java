package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WorkloadTest {
    private Workload work1 = Workload.LOW;
    private Workload work2 = Workload.MEDIUM;
    private Workload work3 = Workload.HIGH;

    @Test
    public void toStringTest() {
        assertTrue(work1.toString().equals("LOW"));
        assertTrue(work2.toString().equals("MEDIUM"));
        assertTrue(work3.toString().equals("HIGH"));
    }

    @Test
    public void workLoadValueTest() {
        assertTrue(work1.workloadValue() == 10);
        assertTrue(work2.workloadValue() == 20);
        assertTrue(work3.workloadValue() == 25);
    }

}
