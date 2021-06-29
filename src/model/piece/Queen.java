package model.piece;

import java.util.List;
import model.player.EChessPlayer;
import model.posn.BoardPosn;

public class Queen extends AChessPiece {

  public static final BoardPosn INITIAL_WHITE_POSN
      = new BoardPosn(BoardPosn.MAX_DIMENSION, 3);

  public Queen(EChessPlayer owner) {
    super(owner, 9, INITIAL_WHITE_POSN);
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
