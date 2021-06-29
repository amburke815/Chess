package model;

import java.util.List;
import model.matrix.IMatrix;
import model.piece.IChessPiece;
import model.player.EChessPlayer;
import model.posn.BoardPosn;

/**
 * The model for a game of chess.
 */
public interface IChessModel {

  void move(IChessPiece toMove, BoardPosn destination);

  /**
   * TODO: should this return null or an enum type "Neither" if there is no winner
   * @return
   */
  EChessPlayer getWinner();

  EChessPlayer getCurrentPlayer();

  IMatrix<IChessPiece> getBoard();

  IChessPiece getPieceAt(BoardPosn destination);

  int getScoreOf(EChessPlayer aPlayer);

  List<IChessPiece> getGraveyardOf(EChessPlayer aPlayer);


}
