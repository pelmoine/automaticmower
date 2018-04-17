package com.mowitnow.automaticmower.mower.position;

import com.mowitnow.automaticmower.Mower.Grid.RectangularGrid;
import com.mowitnow.automaticmower.Mower.Mower;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import com.mowitnow.automaticmower.Mower.position.IPosition;
import com.mowitnow.automaticmower.Mower.position.TwoDimensionPosition;
import org.junit.Assert;
import org.junit.Test;
import org.omg.CORBA.INV_POLICY;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for Mower class object.
 */
public class PositionTest {

    private final int x = 5;
    private final int y = 4;

    /**
     * Create a TwoDimensionPosition Object for test.
     * @return TwoDimensionPosition.
     */
    private TwoDimensionPosition createTwoDimensionPosition() {
        return new TwoDimensionPosition(x,y);
    }

    /**
     * Test of heritage.
     */
    @Test
    public void testCreateTwoDimPosition() {
        TwoDimensionPosition position  = createTwoDimensionPosition();
        Assert.assertTrue(position instanceof IPosition);
    }

    /**
     * Test get two dimensions positions.
     */
    @Test
    public void testTwoDimPositionGetXY() {
        TwoDimensionPosition position  = createTwoDimensionPosition();
        Assert.assertEquals(x,position.getX());
        Assert.assertEquals(y, position.getY());
    }

    /**
     * Test set two dimension positions.
     */
    @Test
    public void testTwoDimPositionSetXY() {
        TwoDimensionPosition position  = createTwoDimensionPosition();
        position.setX(y);
        position.setY(x);
        Assert.assertEquals(x,position.getY());
        Assert.assertEquals(y, position.getX());
    }

    /**
     * Test to string method
     */
    @Test
    public void testTwoDimPositionToString() {
        TwoDimensionPosition position  = createTwoDimensionPosition();
        Assert.assertEquals(x + " " + y, position.toString());
    }



}
