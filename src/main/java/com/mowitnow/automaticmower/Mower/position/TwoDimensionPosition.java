package com.mowitnow.automaticmower.Mower.position;

import com.mowitnow.automaticmower.Mower.position.IPosition;

/**
 * Class object represented a two dimension position.
 */
public class TwoDimensionPosition implements IPosition {

    private int x;
    private int y;

    /**
     * Constructor.
     *
     * @param x x position.
     * @param y y position
     * */
    public TwoDimensionPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get X.
     * @return x position.
     */
    public int getX() {
        return x;
    }

    /**
     * Get Y.
     * @return y position.
     */
    public int getY() {
        return y;
    }

    /**
     * Set the new x position
     * @param x x position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the new y position.
     * @param y y position.
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
