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