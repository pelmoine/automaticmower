package com.mowitnow.automaticmower.file;

import com.mowitnow.automaticmower.File.CmdFileHelper;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Unit test for File Helper Class.
 */
public class CmdFileHelperTest {

    private static final String NAME_COMMAND_FILE = "command.txt";
    private static final String NAME_COMMAND_FILE_NOT_EXIST = "command_not_exist.txt";
    private static final String NAME_COMMAND_FILE_EMPTY = "command_empty.txt";
    private static final String NAME_COMMAND_FILE_NO_MOWER_ONLY_GRID = "command_correct_no_mower_only_grid.txt";
    private static final String COMMAND_CORRECT_MOWER = "command_correct_mower.txt";
    private static final String NAME_BAD_COMMAND_MOWER_FILE = "bad_command_mower.txt";

    @Test
    public void testCheckCmdFilePresent() {
        CmdFileHelper.checkCmdFilePresent(NAME_COMMAND_FILE);
    }

    @Test
    public void testCheckCmdFilePresentRuntimeExceptionUrl() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> CmdFileHelper.checkCmdFilePresent(NAME_COMMAND_FILE_NOT_EXIST))
                .withMessageContaining("impossible to load URL from command file");
    }


    @Test
    public void testGetCommandFilePath() {
        Assert.assertNotNull(CmdFileHelper.getCommandFilePath(NAME_COMMAND_FILE_EMPTY));
    }

    @Test
    public void testReadFile() {
        Path commandFilePath = CmdFileHelper.getCommandFilePath(NAME_COMMAND_FILE_NO_MOWER_ONLY_GRID);
        List<String> lines = CmdFileHelper.readCommandFile(commandFilePath);
        Assert.assertNotNull(lines);
        Assert.assertFalse(lines.isEmpty());
        Assert.assertTrue(lines.size() == 1);
    }

    @Test
    public void testReadFileExceptionEmptyFile() {
        Path commandFilePath = CmdFileHelper.getCommandFilePath(NAME_COMMAND_FILE_EMPTY);
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> CmdFileHelper.readCommandFile(commandFilePath))
                .withMessageContaining(" is empty.");
    }

    @Test
    public void testVerifyLineMatchGrid() {
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid(null));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid(""));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid("s"));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid("55"));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid("55 "));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid(" 55 "));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid(" 55 s"));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid(" 55 s"));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid(" 55 55"));
        Assert.assertFalse(CmdFileHelper.verifyLineMatchGrid("55 55 "));
        Assert.assertTrue(CmdFileHelper.verifyLineMatchGrid("0 0"));
        Path commandFilePath = CmdFileHelper.getCommandFilePath(NAME_COMMAND_FILE_NO_MOWER_ONLY_GRID);
        List<String> line = CmdFileHelper.readCommandFile(commandFilePath);
        Assert.assertNotNull(line);
        Assert.assertTrue(CmdFileHelper.verifyLineMatchGrid(line.get(0)));
    }

    @Test
    public void testVerifyLinesMatchMower() {
        Path commandFilePath = CmdFileHelper.getCommandFilePath(COMMAND_CORRECT_MOWER);
        List<String> line = CmdFileHelper.readCommandFile(commandFilePath);
        Assert.assertTrue(CmdFileHelper.verifyLinesMatchMower(line));


        commandFilePath = CmdFileHelper.getCommandFilePath(NAME_COMMAND_FILE_NO_MOWER_ONLY_GRID);
        line = CmdFileHelper.readCommandFile(commandFilePath);
        Assert.assertFalse(CmdFileHelper.verifyLinesMatchMower(line));

        commandFilePath = CmdFileHelper.getCommandFilePath(NAME_BAD_COMMAND_MOWER_FILE);
        line = CmdFileHelper.readCommandFile(commandFilePath);
        Assert.assertFalse(CmdFileHelper.verifyLinesMatchMower(line));
    }

    @Test
    public void testGetGridSize() {
        String gridSize = "5 4";
        int[] gridSizeArray = CmdFileHelper.getGridSize(gridSize);
        Assert.assertEquals(5, gridSizeArray[0]);
        Assert.assertEquals(4, gridSizeArray[1]);

        gridSize = "55 45";
        gridSizeArray = CmdFileHelper.getGridSize(gridSize);
        Assert.assertEquals(55, gridSizeArray[0]);
        Assert.assertEquals(45, gridSizeArray[1]);

    }

    @Test
    public void testGetMowerLine() {
        Path commandFilePath = CmdFileHelper.getCommandFilePath(COMMAND_CORRECT_MOWER);
        List<String> line = CmdFileHelper.readCommandFile(commandFilePath);
        Assert.assertNotNull(line);
        Assert.assertTrue(line.size() == 5);
        line = CmdFileHelper.getMowerLine(line);
        Assert.assertNotNull(line);
        Assert.assertTrue(line.size() == 4);
    }

    @Test
    public void testGetInitialPosition() {
        String line = "54 35 E";
        int[] initialPosition = CmdFileHelper.getInitialPosition(line);
        Assert.assertNotNull(initialPosition);
        Assert.assertEquals(54, initialPosition[0]);
        Assert.assertEquals(35, initialPosition[1]);

    }

    @Test
    public void testGetInitialOrientationChar() {
        String line = "54 35 E";
        char initialOrientation = CmdFileHelper.getInitialOrientationChar(line);
        Assert.assertEquals('E', initialOrientation);
    }

    @Test
    public void testGetInitialOrientation() {
        String line = "54 35 E";
        Orientation initialOrientation = CmdFileHelper.getInitialOrientation(line);
        Assert.assertEquals(Orientation.EAST, initialOrientation);
    }
}
