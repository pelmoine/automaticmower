package com.mowitnow.automaticmower.File;


import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class used to manipulate command file.
 *
 * @author epelmoine
 */
public class CmdFileHelper {

    private static final Logger LOGGER = Logger.getLogger(CmdFileHelper.class);

    private static final String REGEX_SIZE_GRID = "^[0-9]{1,6}[ ][0-9]{1,6}$";

    private static final String REGEX_POSITION_INITIAL = "^[0-9]{1,6}[ ][0-9]{1,6}[ ][NSEO]$";

    private static final String REGEX_COMMAND = "^[GDA]*$";

    /**
     * Private constructor.
     * Java adds an implicit public constructor to every class which does not define at least one explicitly.
     * Hence, at least one non-public constructor should be defined.
     */
    private CmdFileHelper() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Is a the file command is present in resources.
     */
    public static void checkCmdFilePresent(String commandFileName) {
        URL urlCmdFileName = CmdFileHelper.class.getResource('/' + commandFileName);
        if (urlCmdFileName == null || urlCmdFileName.getFile() == null) {
            throw new CmdFileException(String.format("Error : impossible to load URL from command file : %s", commandFileName));
        }
    }

    /**
     * Get the command file Path Object.
     *
     * @return the command file Path Object
     */
    public static Path getCommandFilePath(String commandFileName) {
        return Paths.get(CmdFileHelper.class.getResource("/" + commandFileName).getPath());
    }

    /**
     * Read the command mower file.
     *
     * @return the Mower list created by the reading of command mower file.
     */
    public static List<String> readCommandFile(Path commandFilePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(commandFilePath);
            if(lines.isEmpty()) {
                throw new CmdFileException(String.format("Error : the file with the path (%s) is empty.",commandFilePath));
            }
        } catch (IOException e) {
            LOGGER.error(String.format("Error during reading command file from path %s.", commandFilePath), e);
        }
        return lines;
    }

    /**
     * Verify line match with size grid.
     *
     * @param sizeGrid the size grid
     * @return true if the size grid is not nul, empty and corresponding to the REGEX_SIZE_GRID, false otherwise.
     */
    public static boolean verifyLineMatchGrid(String sizeGrid) {
        return sizeGrid != null && !sizeGrid.isEmpty() && sizeGrid.matches(REGEX_SIZE_GRID);
    }

    /**
     * Get the grid size.
     *
     * @param sizeGridLine the line corresponding to the grid size
     * @return the grid size.
     */
    public static int[] getGridSize(String sizeGridLine) {
        String[] sizeGridTab = sizeGridLine.split(" ");
        int xMaxPosition = Integer.parseInt(sizeGridTab[0]);
        int yMaxPosition = Integer.parseInt(sizeGridTab[1]);
        return new int[]{xMaxPosition, yMaxPosition};

    }


    /**
     * Verify if lines match with mower initial position and command movement.
     *
     * @param lines corresponding to the command file lines.
     * @return true if the initial position and the
     */
    public static boolean verifyLinesMatchMower(List<String> lines) {
        if (lines == null || lines.size() <= 2) {
            return false;
        }
        List<String> mowerLines = getMowerLine(lines);
        for (String mowerLine : mowerLines) {
            if (mowerLines.indexOf(mowerLine) % 2 == 0) {
                if (!mowerLine.matches(REGEX_POSITION_INITIAL)) {
                    return false;
                }
            } else {
                if (!mowerLine.matches(REGEX_COMMAND)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get mower lines.
     *
     * @param lines the total lines read from command file.
     * @return line minus the first one.
     */
    public static List<String> getMowerLine(List<String> lines) {
        List<String> mowerLines = new ArrayList<>(lines);
        mowerLines.remove(0);
        return mowerLines;

    }

    /**
     * Get initial position from line.
     *
     * @param line line from command file.
     * @return the initial mower position.
     */
    public static int[] getInitialPosition(String line) {
        String[] sizeGridTab = line.split(" ");
        int xPosition = Integer.parseInt(sizeGridTab[0]);
        int yPosition = Integer.parseInt(sizeGridTab[1]);
        return new int[]{xPosition, yPosition};

    }

    /**
     * Get initial orientation from string line.
     *
     * @param line the first line of mower creation with format "[x-position] [y-position] [orientation]"
     * @return the orientation
     */
    public static char getInitialOrientationChar(String line) {
        String[] sizeGridTab = line.split(" ");
        return sizeGridTab[2].charAt(0);
    }

    public static Orientation getInitialOrientation(String line) {
        char charOrientation = getInitialOrientationChar(line);
        Optional<Orientation> optionalOrientation = Orientation.getOrientationFromValue(charOrientation);
        if(optionalOrientation.isPresent()){
           return optionalOrientation.get();
        }else {
            throw new CmdFileException(String.format("Error, the character in the file %c, not corresponding to an orientation.", charOrientation));
        }
    }
}
