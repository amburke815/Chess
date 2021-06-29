package model.piece;

import java.util.List;
import model.piece.AChessPiece;
import model.player.EChessPlayer;
import model.posn.BoardPosn;

public class Knight extends AChessPiece {
  public static final BoardPosn INITIAL_WHITE_POSN_WHITE_SQUARE
      = new BoardPosn(BoardPosn.MAX_DIMENSION, 1);
  public static final BoardPosn INITIAL_WHITE_POSN_BLACK_SQUARE
      = new BoardPosn(BoardPosn.MAX_DIMENSION, BoardPosn.MAX_DIMENSION - 1);

  public Knight(EChessPlayer owner, int value,
      BoardPosn posn) {
    super(owner, 3, posn);
  }

  @Override
  public boolean canMoveTo(BoardPosn newPosition) {
    return false;
  }

  @Override
  public List<BoardPosn> getPossibleMoves() {
    return null;
  }
}
