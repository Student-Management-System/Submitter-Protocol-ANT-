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
 * Thie class declares unit tests for the {@link SemesterUtils} class.
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
     * The expected semester.
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
            {Calendar.JANUARY, "wise1920"},
            {Calendar.FEBRUARY, "wise1920"},
            {Calendar.MARCH, "wise1920"},
            {Calendar.APRIL, "sose20"},
            {Calendar.MAY, "sose20"},
            {Calendar.JUNE, "sose20"},
            {Calendar.JULY, "sose20"},
            {Calendar.AUGUST, "sose20"},
            {Calendar.SEPTEMBER, "sose20"},
            {Calendar.OCTOBER, "wise2021"},
            {Calendar.NOVEMBER, "wise2021"},
            {Calendar.DECEMBER, "wise2021"}
            });        
    }
    
    /**
     * Method to run all tests and set the Year for the test to 2020.
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
