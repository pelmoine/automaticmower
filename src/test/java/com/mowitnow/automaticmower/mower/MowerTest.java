package com.mowitnow.automaticmower.mower;

import com.mowitnow.automaticmower.Mower.Grid.RectangularGrid;
import com.mowitnow.automaticmower.Mower.Mower;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import com.mowitnow.automaticmower.Mower.position.TwoDimensionPosition;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for Mower class object.
 */
public class MowerTest {

    private Mower createMower() {
        Mower mower = new Mower(new TwoDimensionPosition(3, 3), Orientation.SOUTH, new RectangularGrid(new TwoDimensionPosition(5,5)));
        Assert.assertNotNull(mower);
        return mower;
    }

    @Test
    public void testCreateMower() {
        Assert.assertNotNull(createMower());
    }

    @Test
    public void testPositionMower() {
        Mower mower = createMower();
        Assert.assertNotNull(mower.getPosition());
        Assert.assertTrue(mower.getPosition() instanceof TwoDimensionPosition);
        Assert.assertEquals(3, ((TwoDimensionPosition) mower.getPosition()).getY());
        Assert.assertEquals(3, ((TwoDimensionPosition) mower.getPosition()).getY());
    }

    @Test
    public void testOrientationMower() {
        Mower mower = createMower();
        Assert.assertEquals(Orientation.SOUTH, mower.getOrientation());
    }

    @Test
    public void testLeftRotateMower() {
        Mower mower = createMower();
        mower.leftRotate();
        Assert.assertEquals(Orientation.EAST, mower.getOrientation());
        mower.leftRotate();
        Assert.assertEquals(Orientation.NORTH, mower.getOrientation());
        mower.leftRotate();
        Assert.assertEquals(Orientation.WEST, mower.getOrientation());
        mower.leftRotate();
        Assert.assertEquals(Orientation.SOUTH, mower.getOrientation());
    }

    @Test
    public void testRightRotateMower() {
        Mower mower = createMower();
        mower.rightRotate();
        Assert.assertEquals(Orientation.WEST, mower.getOrientation());
        mower.rightRotate();
        Assert.assertEquals(Orientation.NORTH, mower.getOrientation());
        mower.rightRotate();
        Assert.assertEquals(Orientation.EAST, mower.getOrientation());
        mower.rightRotate();
        Assert.assertEquals(Orientation.SOUTH, mower.getOrientation());
    }

    @Test
    public void testMoveMowerCheckRotate() {
        Mower mower = createMower();
        // move to South
        mower.move();

        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(2, ((TwoDimensionPosition)mower.getPosition()).getY());
        mower.rightRotate();
        mower.rightRotate();
        // Move to North
        mower.move();
        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getY());
        mower.leftRotate();
        mower.leftRotate();
        // Move to South
        mower.move();
        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(2, ((TwoDimensionPosition)mower.getPosition()).getY());
        mower.rightRotate();
        // Move to West
        mower.move();
        Assert.assertEquals(2, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(2, ((TwoDimensionPosition)mower.getPosition()).getY());
        mower.rightRotate();
        mower.rightRotate();
        // Move to East
        mower.move();
        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(2, ((TwoDimensionPosition)mower.getPosition()).getY());
    }

    @Test
    public void testMoveMowerCheckGridLimit() {
        Mower mower = createMower();
        mower.move();
        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(2, ((TwoDimensionPosition)mower.getPosition()).getY());
        mower.move();
        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(1, ((TwoDimensionPosition)mower.getPosition()).getY());
        mower.move();
        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(0, ((TwoDimensionPosition)mower.getPosition()).getY());
        mower.move();
        Assert.assertEquals(3, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(0, ((TwoDimensionPosition)mower.getPosition()).getY());
    }

    @Test
    public void testGetFinalPosition() {
        Mower mower = createMower();
        mower.move();
        Assert.assertEquals("3 2 S", mower.getFinalPositionAndOrientation());
    }

    @Test
    public void testExecuteMovement() {
        Mower mower = createMower();
        String movement = "AGAAGGA";
        mower.executeMovement(movement);
        Assert.assertEquals(4, ((TwoDimensionPosition)mower.getPosition()).getX());
        Assert.assertEquals(2, ((TwoDimensionPosition)mower.getPosition()).getY());
        Assert.assertEquals(Orientation.WEST, mower.getOrientation());

    }

}
