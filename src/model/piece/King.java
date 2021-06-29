package model.piece;

import java.util.List;
import model.player.EChessPlayer;
import model.posn.BoardPosn;

public class King extends AChessPiece {

  public static final BoardPosn INITIAL_WHITE_POSN
      = new BoardPosn(BoardPosn.MAX_DIMENSION, 5);

  public King(EChessPlayer owner) {
    super(owner, AChessPiece.PIECE_MAX_VALUE, INITIAL_WHITE_POSN);
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
