import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

/**
 *  JUnit test template for the PhaseTen project.
 */

public interface PhaseTenTestTemplate {
    void setUp();
    void runTest();
    void testConstructor();
}
