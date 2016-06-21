package be.kapture.checkers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Yannick Thibos on 20/06/2016.
 */
class BoardLocation {

    static final int MINIMUM_COORDINATE = 0;
    static final int MAXIMUM_COORDINATE = 7;

    private int x, y;

    public BoardLocation(int x, int y) throws IllegalArgumentException {
        if (!isLegalCoordinate(x, y)) {
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

    private static boolean isLegalCoordinate(int x, int y) {
        if (min(x,y) < MINIMUM_COORDINATE ) {
            return false;
        }
        if (max(x, y) > MAXIMUM_COORDINATE) {
            return false;
        }
        return true;
    }

    public List<BoardLocation> getDiagonalNeighbouringLocations() {
        List<BoardLocation> neighbours = new ArrayList<>();

        int oneColumnLeft = getX() - 1;
        int oneColumnRight = getX() + 1;
        int oneRowLower = getY() - 1;
        int oneRowHigher = getY() + 1;

        if (isLegalCoordinate(oneColumnLeft, oneRowLower)) {
            neighbours.add(new BoardLocation(oneColumnLeft, oneRowLower));
        }
        if (isLegalCoordinate(oneColumnLeft, oneRowHigher)) {
            neighbours.add(new BoardLocation(oneColumnLeft, oneRowHigher));
        }
        if (isLegalCoordinate(oneColumnRight, oneRowHigher)) {
            neighbours.add(new BoardLocation(oneColumnRight, oneRowHigher));
        }
        if (isLegalCoordinate(oneColumnRight, oneRowLower)) {
            neighbours.add(new BoardLocation((oneColumnRight), oneRowLower));
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
