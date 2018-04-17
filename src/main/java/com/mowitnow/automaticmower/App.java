package com.mowitnow.automaticmower;

import com.mowitnow.automaticmower.File.CmdFileException;
import com.mowitnow.automaticmower.File.CmdFileHelper;
import com.mowitnow.automaticmower.Mower.Grid.IGrid;
import com.mowitnow.automaticmower.Mower.Grid.RectangularGrid;
import com.mowitnow.automaticmower.Mower.Mower;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import com.mowitnow.automaticmower.Mower.position.TwoDimensionPosition;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class main
 *
 * @author epelmoine
 */
class App {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(App.class);
    /**
     * The command file name.
     */
    private static final String COMMAND_FILE_NAME = "command.txt";

    public static void main(String[] args) {
        try {
            App app = new App();

            //Verify file
            app.verifyCommandFile(COMMAND_FILE_NAME);
            // Get command file lines
            List<String> lines = app.getCommandFileLines(COMMAND_FILE_NAME);
            //Create grid
            IGrid grid = createGrid(lines.get(0));
            // Create mowers
            List<Mower> mowers = app.createMowers(lines, grid);
            // Move mowers
            app.moveMower(mowers, lines);
            // Return mowers positions
            app.printMowerPosition(mowers);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Create grid with String line info
     * @param gridLine grid line information
     * @return IGrid created.
     */
    private static IGrid createGrid(String gridLine) {
        int[] maxPosition = CmdFileHelper.getGridSize(gridLine);
        return new RectangularGrid(new TwoDimensionPosition(maxPosition[0], maxPosition[1]));
    }

    /**
     * Print mowers final position
     *
     * @param mowers mowers to print position
     */
    private void printMowerPosition(List<Mower> mowers) {
        for (Mower mower : mowers) {
            System.out.println(mower.getFinalPositionAndOrientation());
        }
    }

    /**
     * Move mowers by using execute movement mower method.
     * @param mowers the mowers to move
     * @param mowerLines the mower lines that provide mower movement.
     */
    private void moveMower(List<Mower> mowers, List<String> mowerLines) {
        for (Mower mower : mowers) {
            String mowerLine = mowerLines.get(mowers.indexOf(mower) * 2 + 1);
            mower.executeMovement(mowerLine);
        }
    }

    /**
     * Get command file lines (grid size + initial position and orientation mower + command mowers)
     *
     * @return the command file lines.
     */
    private List<String> getCommandFileLines(String commandFileName) {
        Path path = CmdFileHelper.getCommandFilePath(commandFileName);
        return CmdFileHelper.readCommandFile(path);
    }

    /**
     * Create mowers form command file.
     *
     * @param lines command file lines
     * @param grid the mower grid
     * @return mowers created.
     */
    public List<Mower> createMowers(List<String> lines, IGrid grid) {
        List<String> mowerLines = CmdFileHelper.getMowerLine(lines);
        List<Mower> mowers = new ArrayList<>();
        for (String mowerLine : mowerLines) {
            if (mowerLines.indexOf(mowerLine) % 2 == 0) {
                mowers.add(createMower(mowerLine, grid));
            }
        }
        return mowers;
    }

    /**
     * Create and initialized a mower.
     *
     * @param line the line represented a mower
     * @param grid the mower grid
     * @return a mower initialized by command file line.
     */
    private Mower createMower(String line, IGrid grid) {
        int[] position = CmdFileHelper.getInitialPosition(line);
        Orientation initialOrientation = CmdFileHelper.getInitialOrientation(line);
        return new Mower(new TwoDimensionPosition(position[0], position[1]), initialOrientation, grid);
    }

    /**
     * Verify command file : verify the presence of file in system, the first line match with grid size
     * and the other line match with the mower declarations and instructions.
     * @param commandFileName the command file name
     */
    public void verifyCommandFile(String commandFileName) {
        CmdFileHelper.checkCmdFilePresent(commandFileName);
        List<String> fileLines = getCommandFileLines(commandFileName);
        if (!CmdFileHelper.verifyLineMatchGrid(fileLines.get(0)) || !CmdFileHelper.verifyLinesMatchMower(fileLines)) {
            throw new CmdFileException(String.format("Error : the file %s doesn't match with the expected format.", commandFileName));
        }
    }
}
