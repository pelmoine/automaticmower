package com.mowitnow.automaticmower.Mower.Grid;

import com.mowitnow.automaticmower.Mower.position.IPosition;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;

/**
 * Interface representing a grid.
 */
public interface IGrid <T extends IPosition>{

    /**
     * Move to one position to the North, South, East, West.
     * No movement if it's out the grid.
     * @param orientation
     * @param position
     */
    void move(Orientation orientation, T position);

    /**
     * Get position in grid.
     * @return
     */
    IPosition getMaxPosition();
}
