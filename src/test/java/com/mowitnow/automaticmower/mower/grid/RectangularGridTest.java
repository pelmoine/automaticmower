package com.mowitnow.automaticmower.mower.grid;


import com.mowitnow.automaticmower.File.CmdFileHelper;
import com.mowitnow.automaticmower.Mower.Grid.IGrid;
import com.mowitnow.automaticmower.Mower.Grid.RectangularGrid;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import com.mowitnow.automaticmower.Mower.position.IPosition;
import com.mowitnow.automaticmower.Mower.position.TwoDimensionPosition;
import com.mowitnow.automaticmower.mower.position.TestDimensionPosition;
import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Unit test for RectangularGrid class implement of IGrid interface
 */
public class RectangularGridTest {

    private final int x = 5;
    private final int y = 4;

    private RectangularGrid createRectangularGrid() {
        TwoDimensionPosition position = new TwoDimensionPosition(x, y);
        return new RectangularGrid(position);
    }
    /**
     * Test right orientation method of Enum Orientation
     */
    @Test
    public void createRectangulareGridTest() {
        RectangularGrid grid = createRectangularGrid();
        Assert.assertNotNull(grid);
        Assert.assertTrue(grid instanceof IGrid);
    }

    /**
     * Test getMaxPosition method.
     */
    @Test
    public void getMaxPositionTest() {
        RectangularGrid grid = createRectangularGrid();
        Assert.assertTrue(grid.getMaxPosition() instanceof IPosition);
        Assert.assertEquals(((TwoDimensionPosition) grid.getMaxPosition()).getX(), 5);
        Assert.assertEquals(((TwoDimensionPosition) grid.getMaxPosition()).getY(), 4);
    }

    @Test
    public void testCheckPositionTypeRuntimeException() {
        RectangularGrid grid = createRectangularGrid();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> grid.move(Orientation.EAST, new TestDimensionPosition()))
                .withMessageContaining("Error, the rectangular grid can only move on two dimension position");
    }

    @Test
    public void moveRectangularGridTest() {
        RectangularGrid grid = createRectangularGrid();
        TwoDimensionPosition position = new TwoDimensionPosition(1, 1);
        grid.move(Orientation.EAST, position);
        Assert.assertEquals(2,position.getX());
        Assert.assertEquals(1,position.getY());
        grid.move(Orientation.SOUTH, position);
        Assert.assertEquals(2,position.getX());
        Assert.assertEquals(0,position.getY());
        grid.move(Orientation.WEST, position);
        Assert.assertEquals(1,position.getX());
        Assert.assertEquals(0,position.getY());
        grid.move(Orientation.NORTH, position);
        Assert.assertEquals(1,position.getX());
        Assert.assertEquals(1,position.getY());
    }
    @Test
    public void moveRectangularGridYLimitMaxTest() {
        RectangularGrid grid = createRectangularGrid();
        TwoDimensionPosition position = new TwoDimensionPosition(1, 1);
        grid.move(Orientation.NORTH, position);
        grid.move(Orientation.NORTH, position);
        grid.move(Orientation.NORTH, position);
        grid.move(Orientation.NORTH, position);
        grid.move(Orientation.NORTH, position);
        grid.move(Orientation.NORTH, position);
        Assert.assertEquals(1,position.getX());
        Assert.assertEquals(4,position.getY());
    }
    @Test
    public void moveRectangularGridYLimitMinTest() {
        RectangularGrid grid = createRectangularGrid();
        TwoDimensionPosition position = new TwoDimensionPosition(1, 1);
        grid.move(Orientation.SOUTH, position);
        grid.move(Orientation.SOUTH, position);
        grid.move(Orientation.SOUTH, position);
        grid.move(Orientation.SOUTH, position);
        Assert.assertEquals(1,position.getX());
        Assert.assertEquals(0,position.getY());
    }

    @Test
    public void moveRectangularGridXLimitMinTest() {
        RectangularGrid grid = createRectangularGrid();
        TwoDimensionPosition position = new TwoDimensionPosition(1, 1);
        grid.move(Orientation.WEST, position);
        grid.move(Orientation.WEST, position);
        grid.move(Orientation.WEST, position);
        grid.move(Orientation.WEST, position);
        grid.move(Orientation.WEST, position);
        grid.move(Orientation.WEST, position);
        Assert.assertEquals(0,position.getX());
        Assert.assertEquals(1,position.getY());
    }
    @Test
    public void moveRectangularGridXLimitMaxTest() {

        RectangularGrid grid = createRectangularGrid();
        TwoDimensionPosition position = new TwoDimensionPosition(1, 1);
        grid.move(Orientation.EAST, position);
        grid.move(Orientation.EAST, position);
        grid.move(Orientation.EAST, position);
        grid.move(Orientation.EAST, position);
        grid.move(Orientation.EAST, position);
        Assert.assertEquals(4,position.getX());
        Assert.assertEquals(1,position.getY());
    }

}
