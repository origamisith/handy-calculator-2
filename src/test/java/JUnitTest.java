import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lsedillo.Model.Data;

public class JUnitTest {
    class MyFirstJUnitJupiterTests {
        private final Calculator calculator = new Calculator();
        @Test
        void addition() {
            assertEquals(2, calculator.add(1, 1));
        }
    }
}
