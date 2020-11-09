
import org.junit.jupiter.api.Assertions;
import com.lsedillo.Model.Number;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"111001 57", "1111111111111111 65535", "10011010010 1234"})
    void shouldConvertToDecimal(String input) {
        String inputBin = input.split(" ")[0];
        String correctDec = input.split(" ")[1];
        Number newBinary = new Number(inputBin, 2);
        Number newDec = newBinary.toBase(10);
        Assertions.assertEquals(correctDec, newDec.getValueString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"57 39", "65535 FFFF", "1234 4D2"})
    void shouldConvertToHex(String input) {
        String inputDec = input.split(" ")[0];
        String correctHex = input.split(" ")[1];
        Number newDec = new Number(inputDec);
        Number newHex = newDec.toBase(Number.HEXADECIMAL);
        Assertions.assertEquals(correctHex, newHex.getValueString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"57 111001", "65535 1111111111111111 ", "1234 10011010010"})
    void shouldConvertToBin(String input) {
        String inputDec = input.split(" ")[0];
        String correctBin= input.split(" ")[1];
        Number newDec = new Number(inputDec);
        Number newBin= newDec.toBase(Number.BINARY);
        Assertions.assertEquals(correctBin, newBin.getValueString());
    }
//    @Test void shouldMakeValueString() {
//        Number n = new Number(100);
//        Number n2 = new Number(101101, 2);
//        Number n3 = new Number(
//    }
}