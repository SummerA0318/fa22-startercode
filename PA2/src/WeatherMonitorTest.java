import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherMonitorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void numDays() {
        WeatherMonitor sm = new WeatherMonitor();
        assertEquals(sm.numDays(59), 0);
        assertEquals(sm.numDays(60), 1);
        assertEquals(sm.numDays(70), 2);
        assertEquals(sm.numDays(69), 0);
        assertEquals(sm.numDays(71), 4);
    }
}