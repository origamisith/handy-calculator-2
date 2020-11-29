import com.lsedillo.Model.Data;
import com.lsedillo.Model.Number;
import com.lsedillo.Model.Rate;
import com.lsedillo.Model.Time;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    Data d1 = new Data(500.0, "megabytes");
    Data d2 = new Data(256.0, "kilobits");
    Data d3 = new Data(1000.0, "terabytes");

    Time t1 = new Time(500.0, "second");
    Time t2 = new Time( 256.0, "hour");
    Time t3 = new Time(1000.0, "minute");

    Rate r1 = new Rate(1024.0, "Mbit/second");
    Rate r2 = new Rate(256.0, "Tbit/second");
    Rate r3 = new Rate(1024.0, "Kbit/second");
    Rate r4 = new Rate(336614.4, "Gigabytes/month");
    Rate r5 = new Rate(256.0, "Terabyte/month");
    Rate r6 = new Rate(1000.0, "Kilobyte/month");
    @Test
    void shouldConvert() {
        Assertions.assertEquals(new Data(.500, "gigabytes"), d1.convertTo("gigabytes"));
        Assertions.assertEquals(new Data(.000000032, "terabytes"), d2.convertTo("terabytes"));
        Assertions.assertEquals(new Data(1000000000.0, "megabytes"), d3.convertTo( "megabytes"));
        assertEquals(new Data(4000.0, "mbits"), d1.convertTo("mbits"));
        assertEquals(r4, r1.convertTo("gigabytes/month"));
//        assertEquals(r1, r4.convertTo("Mbit/s"));
    }
}