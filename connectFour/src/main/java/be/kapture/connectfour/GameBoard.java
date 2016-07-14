package be.kapture.connectfour;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * Created by UnnameDSoS on 14/07/2016.
 */
public class GameBoard {
	public static final Predicate<Colour> NOT_NULL = i -> i != null;
	static final int NR_OF_ROWS = 6;
	static final int NR_OF_COLUMNS = 7;
	private final Colour[][] board = new Colour[NR_OF_COLUMNS][NR_OF_ROWS];

	public void addPiece(int column, Colour colour) {
		if (column < 0 || column >= NR_OF_COLUMNS) {
			throw new IllegalArgumentException();
		}

		int nextFreeSpace = getNextFreeSpace(board[column]);
		if (nextFreeSpace == NR_OF_ROWS) {
			throw new IllegalArgumentException();
		}
		board[column][nextFreeSpace] = colour;

	}

	private int getNextFreeSpace(Colour[] colours) {
		int teller = 0;
		while (teller < NR_OF_ROWS && colours[teller] != null) {
			teller++;
		}
		return teller;
	}

	public List<Colour> getColumn(int column) {
		if (column < 0 || column >= NR_OF_COLUMNS) {
			throw new IllegalArgumentException();
		}
		return Arrays.stream(board[column]).filter(NOT_NULL).collect(toList());
	}

	public boolean hasWon(Colour colour) {

		for (Colour[] column : board) {
			if (hasFourSuccessive(column, colour)) {
				return true;
			}
		}
		for (int row = 0; row < NR_OF_ROWS; row++) {
			int amountOfSameColoursFeatured = 0;
			for (int column = 0; column < NR_OF_COLUMNS; column++) {
				Colour colour1 = board[column][row];
				if (colour1 == colour) {
					amountOfSameColoursFeatured++;
				} else {
					amountOfSameColoursFeatured = 0;
				}
				if (amountOfSameColoursFeatured == 4) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean hasFourSuccessive(Colour[] column, Colour colour) {
		int amountOfSameColoursFeatured = 0;
		for (Colour colour1 : column) {
			if (colour1 == null) {
				return false;
			}
			if (colour1 == colour) {
				amountOfSameColoursFeatured++;
			} else {
				amountOfSameColoursFeatured = 0;
			}
			if (amountOfSameColoursFeatured == 4) {
				return true;
			}
		}
		return false;
	}
}