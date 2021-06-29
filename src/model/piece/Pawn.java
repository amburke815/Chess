package model.piece;

import java.util.List;
import model.player.EChessPlayer;
import model.posn.BoardPosn;

public class Pawn extends AChessPiece {

  public Pawn(EChessPlayer owner, int value,
      BoardPosn posn) {
    super(true, owner, 1, posn);
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
