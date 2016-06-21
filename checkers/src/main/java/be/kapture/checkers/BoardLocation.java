package be.kapture.checkers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by thiboya on 20/06/2016.
 */
class BoardLocation {

    static final int MINIMUM_COORDINATE = 0;
    static final int MAXIMUM_COORDINATE = 7;

    private int x, y;

    public BoardLocation(int x, int y) throws IllegalArgumentException {
        if (min(x,y) < MINIMUM_COORDINATE ) {
            throw new IllegalArgumentException();
        }
        if (max(x, y) > MAXIMUM_COORDINATE) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOnColorBlack(){
        return (x+y) %2 ==0;
    }

    public List<BoardLocation> getDiagonalNeighbouringLocations() {
        List<BoardLocation> neighbours = new ArrayList<>();
        try {
            neighbours.add(new BoardLocation(getX() - 1, getY() + - 1));
        } catch (IllegalArgumentException ex) {

        }
        try {
            neighbours.add(new BoardLocation(getX() - 1, getY() + 1));
        } catch (IllegalArgumentException ex) {

        }
        try {
            neighbours.add(new BoardLocation(getX() + 1, getY() - 1));
        } catch (IllegalArgumentException ex) {

        }
        try {
            neighbours.add(new BoardLocation(getX() + 1, getY() + 1));
        } catch (IllegalArgumentException ex) {

        }
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardLocation that = (BoardLocation) o;

        if (x != that.x) return false;
        return y == that.y;

    }
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }


}
