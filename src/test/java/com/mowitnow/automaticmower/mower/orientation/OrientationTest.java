package com.mowitnow.automaticmower.mower.orientation;


import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for Orientation class object.
 */
public class OrientationTest {

    /**
     * Test right orientation method of Enum Orientation
     */
    @Test
    public void rightOrientationTest() {
        Assert.assertEquals(Orientation.EAST, Orientation.NORTH.rightRotate());
        Assert.assertEquals(Orientation.SOUTH, Orientation.EAST.rightRotate());
        Assert.assertEquals(Orientation.NORTH, Orientation.WEST.rightRotate());
        Assert.assertEquals(Orientation.WEST, Orientation.SOUTH.rightRotate());
    }

    /**
     * Test left orientation method of Enum Orientation
     */
    @Test
    public void leftOrientationTest() {
        Assert.assertEquals(Orientation.WEST, Orientation.NORTH.leftRotate());
        Assert.assertEquals(Orientation.NORTH, Orientation.EAST.leftRotate());
        Assert.assertEquals(Orientation.SOUTH, Orientation.WEST.leftRotate());
        Assert.assertEquals(Orientation.EAST, Orientation.SOUTH.leftRotate());
    }

    @Test
    public void valueOrientationTest() {
        Assert.assertEquals('S', Orientation.SOUTH.value);
        Assert.assertEquals('N', Orientation.NORTH.value);
        Assert.assertEquals('E', Orientation.EAST.value);
        Assert.assertEquals('O', Orientation.WEST.value);
    }

    @Test
    public void getOrientationfromValueTest() {
        Assert.assertEquals(Orientation.NORTH, Orientation.getOrientationFromValue('N').get());
        Assert.assertEquals(Orientation.EAST, Orientation.getOrientationFromValue('E').get());
        Assert.assertEquals(Orientation.SOUTH, Orientation.getOrientationFromValue('S').get());
        Assert.assertEquals(Orientation.WEST, Orientation.getOrientationFromValue('O').get());
    }

    @Test
    public void toStringTest() {
        Assert.assertEquals("NORTH: " + Orientation.NORTH.value, Orientation.NORTH.toString());
        Assert.assertEquals("EAST: " + Orientation.EAST.value, Orientation.EAST.toString());
        Assert.assertEquals("SOUTH: " + Orientation.SOUTH.value, Orientation.SOUTH.toString());
        Assert.assertEquals("WEST: " + Orientation.WEST.value, Orientation.WEST.toString());
    }
}
