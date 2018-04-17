package com.mowitnow.automaticmower.Mower.Grid;

import com.mowitnow.automaticmower.Mower.position.IPosition;
import com.mowitnow.automaticmower.Mower.orientation.Orientation;
import com.mowitnow.automaticmower.Mower.position.TwoDimensionPosition;

/**
 * Class object represented a grid and actions associated.
 */
public class RectangularGrid<T extends IPosition> implements IGrid  {

    private final TwoDimensionPosition maxPosition;

    /**
     * Grid constructor.
     * @param maxPosition two dimension position.
     */
    public RectangularGrid(TwoDimensionPosition maxPosition) {
        this.maxPosition = maxPosition;
    }



    @Override
    public void move(Orientation orientation, IPosition newPosition) {
        if(!(newPosition instanceof TwoDimensionPosition)) {
            throw new RuntimeException("Error, the rectangular grid can only move on two dimension position");
        }
        TwoDimensionPosition position = (TwoDimensionPosition)newPosition;
        switch (orientation) {
            case SOUTH:
                if (position.getY() - 1 >= 0) {
                    position.setY(position.getY() - 1);
                }
                break;
            case WEST:
                if (position.getX() - 1 >= 0) {
                    position.setX(position.getX() - 1);
                }
                break;
            case NORTH:
                if (position.getY() + 1 <= this.maxPosition.getY()) {
                    position.setY(position.getY() + 1);
                }
                break;
            case EAST:
                if (position.getX() + 1 <= this.maxPosition.getY()) {
                    position.setX(position.getX() + 1);
                }
                break;
        }
    }

    @Override
    public IPosition getMaxPosition() {
        return maxPosition;
    }


}
