
import org.junit.jupiter.api.Assertions;
import com.lsedillo.Model.Number;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"111001 57", "1111111111111111 65535", "10011010010 1234"})
    void shouldConvertBinToDecimal(String input) {
        String inputBin = input.split(" ")[0];
        String correctDec = input.split(" ")[1];
        Number newBinary = new Number(inputBin, 2);
        Number newDec = newBinary.toBase(10);
        Assertions.assertEquals(correctDec, newDec.getValueString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"4D2 1234", "3404C2E 54545454", "12B9B0A1 314159265"})
    void shouldConvertHexToDecimal(String input) {
        String inputBin = input.split(" ")[0];
        String correctDec = input.split(" ")[1];
        Number newBinary = new Number(inputBin, 16);
        Number newDec = newBinary.toBase(10);
        Assertions.assertEquals(correctDec, newDec.getValueString());
    }
    @ParameterizedTest
    @ValueSource(strings = {"57 39", "65535 FFFF", "1234 4D2"})
    void shouldConvertDecToHex(String input) {
        String inputDec = input.split(" ")[0];
        String correctHex = input.split(" ")[1];
        Number newDec = new Number(inputDec);
        Number newHex = newDec.toBase(Number.HEXADECIMAL);
        Assertions.assertEquals(correctHex, newHex.getValueString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"57 111001", "65535 1111111111111111 ", "1234 10011010010"})
    void shouldConvertDecToBin(String input) {
        String inputDec = input.split(" ")[0];
        String correctBin= input.split(" ")[1];
        Number newDec = new Number(inputDec);
        Number newBin= newDec.toBase(Number.BINARY);
        Assertions.assertEquals(correctBin, newBin.getValueString());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "10101010 11001100 101110110 binary",
            "1111000011110000 111000111000 1111111100101000 binary",
            "101101110 111011010 1101001000 binary",
            "1000 500 1500 decimal",
            "42 97 139 decimal",
            "123456789 987654321 1111111110 decimal",
            "8AB B78 1423 hexadecimal",
            "FFF ABCD BBCC hexadecimal",
            "A1B2C FFC1 B1AED hexadecimal"
    })
    void shouldAdd(String input) {
        String base = input.split(" ")[3];
        Number first = new Number(input.split(" ")[0], base);
        Number second = new Number (input.split(" ")[1], base);
        String expectedSum = input.split(" ")[2];
        Number actualSum = first.operation('+', second);
        Assertions.assertEquals(expectedSum, actualSum.getValueString());
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "10101010 11001100 -100010 binary",
            "1111000011110000 111000111000 1110001010111000 binary",
            "101101110 111011010 -1101100 binary",
            "1000 500 500 decimal",
            "42 97 -55 decimal",
            "123456789 987654321 -864197532 decimal",
            "8AB B78 -2CD hexadecimal",
            "FFF ABCD -9BCE hexadecimal",
            "A1B2C FFC1 91B6B hexadecimal"
    })
    void shouldSubtract(String input) {
        String base = input.split(" ")[3];
        Number first = new Number(input.split(" ")[0], base);
        Number second = new Number (input.split(" ")[1], base);
        String expected= input.split(" ")[2];
        Number actual= first.operation('-', second);
        Assertions.assertEquals(expected, actual.getValueString());
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "10101010 11001100 1000011101111000 binary",
            "1111000011110000 111000111000 1101011000011101010010000000 binary",
            "101101110 111011010 101010010110101100 binary",
            "1000 500 500000 decimal",
            "42 97 4074 decimal",
            "123456789 987654321 121932631112635269 decimal",
            "8AB B78 636928 hexadecimal",
            "FFF ABCD ABC2433 hexadecimal",
            "A1B2C FFC1 A18AF502C hexadecimal"
    })
    void shouldMultiply(String input) {
        String base = input.split(" ")[3];
        Number first = new Number(input.split(" ")[0], base);
        Number second = new Number (input.split(" ")[1], base);
        String expected = input.split(" ")[2];
        Number actual = first.operation('*', second);
        Assertions.assertEquals(expected, actual.getValueString());
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "11001100 10101010 1 100010 binary",
            "1111000011110000 111000111000 10000 110101110000 binary",
            "111011010 101101110 1 1101100 binary",
            "1000 500 2 0 decimal",
            "970 42 23 4 decimal",
            "987654321 123456789 8 9 decimal",
            "B78 8AB 1 2CD hexadecimal",
            "ABCD FFF A BD7 hexadecimal",
            "A1B2C FFC1 A 1DA2 hexadecimal"
    })
    void shouldDivide(String input) {
        String base = input.split(" ")[4];
        Number first = new Number(input.split(" ")[0], base);
        Number second = new Number (input.split(" ")[1], base);
        String expectedDividend= input.split(" ")[2];
        String expectedRemainder = input.split(" ")[3];
        Number actualDividend= first.operation('/', second);
        Number actualRemainder= first.operation('%', second);
        Assertions.assertEquals(expectedDividend, actualDividend.getValueString());
        Assertions.assertEquals(expectedRemainder, actualRemainder.getValueString());
    }
}