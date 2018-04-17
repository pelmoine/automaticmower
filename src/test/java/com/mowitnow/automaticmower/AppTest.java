package com.mowitnow.automaticmower;

import com.mowitnow.automaticmower.Mower.Grid.RectangularGrid;
import com.mowitnow.automaticmower.Mower.Mower;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import com.mowitnow.automaticmower.Mower.position.TwoDimensionPosition;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Unit test for Mower class object.
 */
public class AppTest {

    /**
     * The command file name.
     */
    private static final String COMMAND_FILE_NAME = "command.txt";
    private static final String COMMAND_EMPTY = "command_empty.txt";
    private static final String COMMAND_BAD_GRID = "command_bad_grid.txt";
    private static final String COMMAND_BAD_MOVEMENT = "bad_command_mower.txt";


    @Test
    public void testVerifyCommandFile() {
        App app = new App();
        app.verifyCommandFile(COMMAND_FILE_NAME);
    }

    @Test
    public void testVerifyCommandFileExceptionEmptyFile() {
        App app = new App();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> app.verifyCommandFile(COMMAND_EMPTY))
                .withMessageContaining(" is empty.");
    }

    @Test
    public void testVerifyCommandFileExceptionBadGridFile() {
        App app = new App();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> app.verifyCommandFile(COMMAND_BAD_GRID))
                .withMessageContaining("doesn't match with the expected format");
    }

    @Test
    public void testVerifyCommandFileExceptionBadMovementFile() {
        App app = new App();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> app.verifyCommandFile(COMMAND_BAD_MOVEMENT))
                .withMessageContaining("doesn't match with the expected format");
    }

    /**
     * Test of mower creation.
     */
    @Test
    public void testCreateInitMowers() {
        List<String> lines = new ArrayList<>();
        lines.add("5 5");
        lines.add("1 2 N");
        lines.add("GAGGAGAA");
        lines.add("3 3 E");
        lines.add("AADAADADDA");
        App app = new App();
        List<Mower> mowers = app.createMowers(lines, new RectangularGrid(new TwoDimensionPosition(5,5)));
        Assert.assertNotNull(mowers);
        Assert.assertEquals(1,((TwoDimensionPosition) mowers.get(0).getPosition()).getX());
        Assert.assertEquals(2, ((TwoDimensionPosition) mowers.get(0).getPosition()).getY());
        Assert.assertEquals(Orientation.NORTH, mowers.get(0).getOrientation());
        Assert.assertEquals(3, ((TwoDimensionPosition) mowers.get(1).getPosition()).getX());
        Assert.assertEquals(3, ((TwoDimensionPosition) mowers.get(1).getPosition()).getY());
        Assert.assertEquals(Orientation.EAST, mowers.get(1).getOrientation());
    }
}
