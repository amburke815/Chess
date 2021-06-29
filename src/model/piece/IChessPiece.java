package model.piece;

import java.util.List;
import model.posn.BoardPosn;
import model.player.EChessPlayer;

public interface IChessPiece {

  boolean canMoveTo(BoardPosn newPosition);

  boolean isAlive();

  List<BoardPosn> getPossibleMoves();

  EChessPlayer getOwner();

  int getValue();

  String toString();

  boolean equals(Object o);

  int hashCode();


}
