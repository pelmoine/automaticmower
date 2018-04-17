package com.mowitnow.automaticmower.Mower.orientation;

import java.util.Optional;

public enum Orientation {
    NORTH('N') {
        @Override
        public Orientation leftRotate() {
            return Orientation.WEST;
        }
        @Override
        public Orientation rightRotate() {
            return Orientation.EAST;
        }
    },
    EAST('E'){
        @Override
        public Orientation leftRotate() {
            return Orientation.NORTH;
        }
        @Override
        public Orientation rightRotate() {
            return Orientation.SOUTH;
        }
    },
    SOUTH('S'){
        @Override
        public Orientation leftRotate() {
            return Orientation.EAST;
        }
        @Override
        public Orientation rightRotate() {
            return Orientation.WEST;
        }
    },
    WEST('O'){
        @Override
        public Orientation leftRotate() {
            return Orientation.SOUTH;
        }
        @Override
        public Orientation rightRotate() {
            return Orientation.NORTH;
        }

    };

    public char value;

    /**
     * Private constructor.
     * @param value the orientation value.
     */
    private Orientation(char value) {
        this.value = value;
    }

    /**
     * Left orientation rotate.
     * @return return the new orientation.
     */
    public abstract Orientation leftRotate();

    /**
     * Right orientation rotate.
     * @return return the new orientation.
     */
    public abstract Orientation rightRotate();


    @Override
    public String toString() {
        String toString = this.name() + " toString() not implemented";
        switch (this) {
            case NORTH:
                toString = "NORTH: " + value;
                break;
            case EAST:
                toString = "EAST: " + value;
                break;
            case SOUTH:
                toString = "SOUTH: " + value;
                break;
            case WEST:
                toString = "WEST: " + value;
        }
        return toString;
    }

    /**
     * Get Orientation from value.
     * @param value value
     * @return an optional of orientation, empty if the value don't exist, not empty otherwise.
     */
    public static Optional<Orientation> getOrientationFromValue(char value) {
        Optional<Orientation> optOrientation =  Optional.empty();
        for(Orientation orientation : Orientation.values()) {
            if(orientation.value == value) {
                optOrientation = Optional.of(orientation);
            }
        }
        return optOrientation;
    }
}
