package model.posn;

import model.Utils;

public class BoardPosn {
  public static final int MAX_DIMENSION = 7;
  private final int row;
  private final int col;

  /**
   * INVARIANT: 0 < row, col < boardHeight, BoardWidth
   * @param row
   * @param col
   */
  public BoardPosn(int row, int col) {
    this.row = Utils.checkNonNegativeInt(row);
    this.col = Utils.checkNonNegativeInt(col);
  }

  public int col() {
    return this.col;
  }

  public int row() {
    return this.row;
  }

}
