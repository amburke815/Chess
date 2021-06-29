package model.piece;

import java.util.List;
import model.player.EChessPlayer;
import model.posn.BoardPosn;

public class Rook extends AChessPiece {
  public static final BoardPosn INITIAL_WHITE_POSN_BLACK_SQUARE
      = new BoardPosn(BoardPosn.MAX_DIMENSION, 0);
  public static final BoardPosn INITIAL_WHITE_POSN_WHITE_SQUARE
      = new BoardPosn(BoardPosn.MAX_DIMENSION, BoardPosn.MAX_DIMENSION - 0);


  public Rook(EChessPlayer owner, int value,
      BoardPosn posn) {
    super(owner, 5, posn);
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
