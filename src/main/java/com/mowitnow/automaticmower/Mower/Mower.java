package com.mowitnow.automaticmower.Mower;

import com.mowitnow.automaticmower.Mower.Grid.IGrid;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import com.mowitnow.automaticmower.Mower.position.IPosition;

/**
 * Class object represented a mower and actions associated.
 */
public class Mower<T extends IPosition> {

    private T position;
    private Orientation orientation;
    private IGrid grid;
    /**
     * Constructor.
     *
     * @param initialPosition    the initial mower position.
     * @param initialOrientation the initial mower orientation.
     * @param grid the mower grid move on.
     * */
    public Mower(T initialPosition, Orientation initialOrientation, IGrid grid) {
        position = initialPosition;
        orientation = initialOrientation;
        this.grid = grid;
    }

    /**
     * Get the mower position.
     *
     * @return the mower position.
     */
    public T getPosition() {
        return position;
    }

    /**
     * Get the mower orientation.
     *
     * @return the mower orientation.
     */
    public Orientation getOrientation() {
        return this.orientation;
    }

    /**
     * Rotate the position to the left
     */
    public void leftRotate() {
        orientation = orientation.leftRotate();
    }

    /**
     * Rotate the position to the right.
     */
    public void rightRotate() {
        orientation = orientation.rightRotate();
    }

    /**
     * Move to one position to the North, South, East, West.
     * No movement if it's out the grid.
     */
    public void move() {
        grid.move(getOrientation(), getPosition());
    }

    /**
     * Get mower position to String : [x-position] [y-position] [direction]. Ex : "3 3 S".
     *
     * @return mower position to string.
     */
    public String getFinalPositionAndOrientation() {
        return position.toString() +
                " " +
                orientation.value;
    }

    /**
     * Execute movement list with information from command file line.
     *
     * @param mowerLine the movement mower line
     */
    public void executeMovement(String mowerLine) {
        for (char movement : mowerLine.toCharArray()) {
            switch (movement) {
                case 'G':
                    leftRotate();
                    break;
                case 'D':
                    rightRotate();
                    break;
                case 'A':
                    move();
                    break;
                default:
                    return;
            }
        }
    }
}
