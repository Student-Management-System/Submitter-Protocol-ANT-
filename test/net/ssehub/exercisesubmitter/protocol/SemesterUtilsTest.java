package net.ssehub.exercisesubmitter.protocol;


import java.util.Calendar;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * Unittests for the semester calculation.
 * 
 * @author Kunold
 *
 */
@RunWith(Parameterized.class)
public class SemesterUtilsTest {
    
    /**
     * A month of a year.
     */
    private int month;
    
    /**
     * The expected Semster.
     */
    private String expectedSemester;
    
    public SemesterUtilsTest(int month, String expectedSemester) {
        this.month = month;
        this.expectedSemester = expectedSemester;
    }
    
    /**
     * Declares all Parameters for the Testmethod.
     * @return List of all Parameters to test them.
     */
    @Parameters(name = "Month {0} -> {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {Calendar.JANUARY, "WiSe1920"},
            {Calendar.FEBRUARY, "WiSe1920"},
            {Calendar.MARCH, "WiSe1920"},
            {Calendar.APRIL, "SoSe20"},
            {Calendar.MAY, "SoSe20"},
            {Calendar.JUNE, "SoSe20"},
            {Calendar.JULY, "SoSe20"},
            {Calendar.AUGUST, "SoSe20"},
            {Calendar.SEPTEMBER, "SoSe20"},
            {Calendar.OCTOBER, "WiSe2021"},
            {Calendar.NOVEMBER, "WiSe2021"},
            {Calendar.DECEMBER, "WiSe2021"}
            });        
    }
    
    /**
     * Method to run all tests.
     */
    @Test
    public void test() {
        Calendar cd = SemesterUtils.getCalendar();
        cd.set(Calendar.YEAR, 2020);
        cd.set(Calendar.MONTH, month);
        String actualValue = SemesterUtils.getSemester();
        assertEquals(expectedSemester, actualValue);
    }

}
