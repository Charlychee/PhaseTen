import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.Phase.ORIGINAL_PHASES;
import static org.junit.Assert.*;

public class PhaseTest {
    protected static final ArrayList<Integer> EMPTY = new ArrayList<>();
    protected static final ArrayList<Integer> ONEFOUR = new ArrayList<>(Arrays.asList(4));
    protected static final ArrayList<Integer> ONESEVEN = new ArrayList<>(Arrays.asList(7));

    @Test
    public void testOriginalPhases() {
        assertEquals(new ArrayList<>(Arrays.asList(3, 3)), ORIGINAL_PHASES[0].getNumSets());
        assertEquals(EMPTY, ORIGINAL_PHASES[0].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[0].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[0].getColorSets());
        assertEquals(1, ORIGINAL_PHASES[0].getPhaseNumber());
        assertEquals("Phase 1:\n2 number set(s) of size 3, 3", ORIGINAL_PHASES[0].toString());

        assertEquals(new ArrayList<>(Arrays.asList(3)), ORIGINAL_PHASES[1].getNumSets());
        assertEquals(ONEFOUR, ORIGINAL_PHASES[1].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[0].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[0].getColorSets());
        assertEquals(2, ORIGINAL_PHASES[1].getPhaseNumber());
        assertEquals("Phase 2:\n1 number set(s) of size 3\n1 run(s) of size 4", ORIGINAL_PHASES[1].toString());

        assertEquals(ONEFOUR, ORIGINAL_PHASES[2].getNumSets());
        assertEquals(ONEFOUR, ORIGINAL_PHASES[2].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[2].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[2].getColorSets());
        assertEquals(3, ORIGINAL_PHASES[2].getPhaseNumber());
        assertEquals("Phase 3:\n1 number set(s) of size 4\n1 run(s) of size 4", ORIGINAL_PHASES[2].toString());

        assertEquals(EMPTY, ORIGINAL_PHASES[3].getNumSets());
        assertEquals(ONESEVEN, ORIGINAL_PHASES[3].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[3].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[3].getColorSets());
        assertEquals(4, ORIGINAL_PHASES[3].getPhaseNumber());
        assertEquals("Phase 4:\n1 run(s) of size 7", ORIGINAL_PHASES[3].toString());

        assertEquals(EMPTY, ORIGINAL_PHASES[4].getNumSets());
        assertEquals(new ArrayList<>(Arrays.asList(8)), ORIGINAL_PHASES[4].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[4].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[4].getColorSets());
        assertEquals(5, ORIGINAL_PHASES[4].getPhaseNumber());
        assertEquals("Phase 5:\n1 run(s) of size 8", ORIGINAL_PHASES[4].toString());

        assertEquals(EMPTY, ORIGINAL_PHASES[5].getNumSets());
        assertEquals(new ArrayList<>(Arrays.asList(9)), ORIGINAL_PHASES[5].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[5].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[5].getColorSets());
        assertEquals(6, ORIGINAL_PHASES[5].getPhaseNumber());
        assertEquals("Phase 6:\n1 run(s) of size 9", ORIGINAL_PHASES[5].toString());

        assertEquals(new ArrayList<>(Arrays.asList(4, 4)), ORIGINAL_PHASES[6].getNumSets());
        assertEquals(EMPTY, ORIGINAL_PHASES[6].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[6].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[6].getColorSets());
        assertEquals(7, ORIGINAL_PHASES[6].getPhaseNumber());
        assertEquals("Phase 7:\n2 number set(s) of size 4, 4", ORIGINAL_PHASES[6].toString());

        assertEquals(EMPTY, ORIGINAL_PHASES[7].getNumSets());
        assertEquals(EMPTY, ORIGINAL_PHASES[7].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[7].getEvenOdds());
        assertEquals(ONESEVEN, ORIGINAL_PHASES[7].getColorSets());
        assertEquals(8, ORIGINAL_PHASES[7].getPhaseNumber());
        assertEquals("Phase 8:\n1 color set(s) of size 7", ORIGINAL_PHASES[7].toString());

        assertEquals(new ArrayList<>(Arrays.asList(2, 5)), ORIGINAL_PHASES[8].getNumSets());
        assertEquals(EMPTY, ORIGINAL_PHASES[8].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[8].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[8].getColorSets());
        assertEquals(9, ORIGINAL_PHASES[8].getPhaseNumber());
        assertEquals("Phase 9:\n2 number set(s) of size 2, 5", ORIGINAL_PHASES[8].toString());

        assertEquals(new ArrayList<>(Arrays.asList(3, 5)), ORIGINAL_PHASES[9].getNumSets());
        assertEquals(EMPTY, ORIGINAL_PHASES[9].getRuns());
        assertEquals(EMPTY, ORIGINAL_PHASES[9].getEvenOdds());
        assertEquals(EMPTY, ORIGINAL_PHASES[9].getColorSets());
        assertEquals(10, ORIGINAL_PHASES[9].getPhaseNumber());
        assertEquals("Phase 10:\n2 number set(s) of size 3, 5", ORIGINAL_PHASES[9].toString());

    }
}

