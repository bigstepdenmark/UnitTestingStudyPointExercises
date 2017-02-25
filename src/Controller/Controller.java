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
