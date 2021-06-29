package model;

import java.util.List;
import model.matrix.IMatrix;
import model.piece.IChessPiece;
import model.player.EChessPlayer;
import model.posn.BoardPosn;

public class ChessModelImpl implements IChessModel {

  @Override
  public void move(IChessPiece toMove, BoardPosn destination) {

  }

  @Override
  public EChessPlayer getWinner() {
    return null;
  }

  @Override
  public EChessPlayer getCurrentPlayer() {
    return null;
  }

  @Override
  public IMatrix<IChessPiece> getBoard() {
    return null;
  }

  @Override
  public IChessPiece getPieceAt(BoardPosn destination) {
    return null;
  }

  @Override
  public int getScoreOf(EChessPlayer aPlayer) {
    return 0;
  }

  @Override
  public List<IChessPiece> getGraveyardOf(EChessPlayer aPlayer) {
    return null;
  }
}
