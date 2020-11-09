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
    Time t2 = new Time( 256.0, "month");
    Time t3 = new Time(1000.0, "week");

    Rate r1 = new Rate(500.0, "Mbit/s");
    Rate r2 = new Rate(256.0, "Tbit/s");
    Rate r3 = new Rate(1000.0, "Kbit/month");
    @Test
    void shouldConvert() {
        Assertions.assertEquals(d1,new Data(500.0, "megabytes"));
    }
}