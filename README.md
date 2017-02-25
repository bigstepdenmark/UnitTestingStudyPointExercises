# Unit Testing Study Point Exercise

Controller class
```java
package Controller;

import java.time.DateTimeException;
import java.time.YearMonth;

/**
 * Created by ismailcam on 25/02/2017.
 */
public class Controller
{
    public int getNumDaysinMonth(int month, int year)
    {
        try
        {
            YearMonth ym = YearMonth.of( year, month );
            return ym.lengthOfMonth();
        }
        catch( DateTimeException ex )
        {
            return 0;
        }
    }

    /**
     * Used Gregorian calendar (start 1582)
     */
    public boolean isLeapYear(int year)
    {
        return year >= 1582 && ( ( year % 4 == 0 && year % 100 != 0 ) || ( year % 400 == 0 ) );
    }

    public int getReimbursePercentage(boolean isDeductiblePaid, boolean doctorVisit, boolean hospitalVisit)
    {
        if( isDeductiblePaid )
            if( doctorVisit && !hospitalVisit ) return 50;
            else if( !doctorVisit && hospitalVisit ) return 80;
            else return 0;

        return 0;
    }
}

```

---

### UNIT TESTS BASED ON EQUIVALENCE PARTITIONING AND BOUNDARY VALUE ANALYSIS

GetNumDaysinMonthTest class
```java
package Test;

import org.junit.*;

import static org.junit.Assert.*;

import Controller.Controller;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by ismailcam on 25/02/2017.
 */

@RunWith( Parameterized.class )
public class GetNumDaysinMonthTest
{
    private Controller ctrl;
    private int month, year, expected;

    public GetNumDaysinMonthTest(int month, int year, int excepted)
    {
        this.month = month;
        this.year = year;
        this.expected = excepted;
    }

    @Parameterized.Parameters
    public static Collection< Object[] > data()
    {
        return Arrays.asList( new Object[][]{

                // Equivalences classes (month)
                // ***********************************************
                // Valid equivalence classes
                { 3, 2017, 31 }, // Months with 31 days
                { 4, 2017, 30 }, // Months with 30 days
                { 2, 2017, 28 }, // February with 28 days
                { 2, 2000, 29 }, // February with 29 days
                // Invalid equivalence classes
                { 0, 2017, 0 }, // Month < 1
                { 13, 2017, 0 }, // Month> 12

                // Equivalences classes (year)
                // ***********************************************
                // Valid equivalence classes
                { 1, 2000, 31 }, // Leap years
                { 1, 2017, 31 }, // Non‐leap years
                // Invalid equivalence classes
                { -1, 2017, 0 }, // Year < 0
                { Integer.MAX_VALUE * 2, 2017, 0 }, // Year > 231‐1

                // Equivalences classes (month + year)
                /// ***********************************************
                { 1, 2017, 31 }, // Months with 31 days, non‐leap year
                { 1, 2000, 31 }, // Months with 31 days, leap year
                { 4, 2017, 30 }, // Months with 30 days, non‐leap year
                { 4, 2000, 30 }, // Months with 30 days, leap year
                { 2, 2017, 28 }, // February, non‐leap year
                { 2, 2000, 29 }, // February, leap year

                // Boundary values(month + year)
                // ***********************************************
                // Leap years divisible by 400
                { 1, 1600, 31 },
                { 1, 2000, 31 },
                // Non‐leap years divisible by 100, but not with 400
                { 1, 1700, 31 },
                { 1, 3000, 31 },
                // Non‐positive invalid months (0)
                { 0, 2017, 0 },
                // Positive invalid months (13)
                { 13, 2017, 0 },
        } );
    }

    @org.junit.Before
    public void setUp() throws Exception
    {
        ctrl = new Controller();
    }

    @org.junit.After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getNumDaysinMonth()
    {
        assertEquals( expected, ctrl.getNumDaysinMonth( month, year ) );
    }
}
```
### Test result
![alt tag](https://raw.githubusercontent.com/bigstepdenmark/UnitTestingStudyPointExercises/master/images/GetNumDaysinMonthTest.png?token=AVDe6g15FxOUvZ4ZzpBCAQmHFJjDi1bEks5YuzdDwA%3D%3D)

---

### UNIT TESTS BASED ON DECISION TABLES

GetReimbursePercentageTest class
```java
package Test;

import Controller.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by ismailcam on 25/02/2017.
 */
@RunWith( Parameterized.class )
public class GetReimbursePercentageTest
{
    private Controller ctrl;
    private boolean isDeductiblePaid, doctorVisit, hospitalVisit;
    private int expected;

    public GetReimbursePercentageTest(boolean isDeductiblePaid, boolean doctorVisit, boolean hospitalVisit, int expected)
    {
        this.isDeductiblePaid = isDeductiblePaid;
        this.doctorVisit = doctorVisit;
        this.hospitalVisit = hospitalVisit;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection< Object[] > data()
    {
        return Arrays.asList( new Object[][]{

                { true, true, false, 50 },
                { true, false, true, 80 },
                { false, true, false, 0 },
                { false, false, true, 0 },
                { true, true, true, 0 },
        } );
    }

    @org.junit.Before
    public void setUp() throws Exception
    {
        ctrl = new Controller();
    }

    @org.junit.After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void getReimbursePercentage()
    {
        assertEquals( expected, ctrl.getReimbursePercentage( isDeductiblePaid, doctorVisit, hospitalVisit ) );
    }

}
```

### Test result
![alt tag](https://raw.githubusercontent.com/bigstepdenmark/UnitTestingStudyPointExercises/master/images/getReimbursePercentageTest.png?token=AVDe6iWcPb9Gt6FJIyNAmdFZrrK8kDZsks5YuzkBwA%3D%3D)
