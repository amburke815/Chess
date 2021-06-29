package model.piece;

import model.Utils;
import model.player.EChessPlayer;
import model.posn.BoardPosn;

public abstract class AChessPiece implements IChessPiece {
  public final static int PIECE_MAX_VALUE = 10;

  protected boolean isAlive;
  protected final EChessPlayer owner;
  protected final int value;
  protected BoardPosn posn;


  public AChessPiece(boolean isAlive, EChessPlayer owner, int value, BoardPosn posn) {
    this.isAlive = isAlive;
    this.owner = Utils.nonNullConstructor(owner, "AChessPiece",
        "EChessPlayer");
    this.value = Utils.checkIntBetween(value, 0, PIECE_MAX_VALUE);
    this.posn = Utils.nonNullConstructor(posn, "AChessPiece",
        "BoardPosn");
  }

  public AChessPiece(EChessPlayer owner, int value, BoardPosn initialPosn) {
    this(true, owner, value, initialPosn);
    if (owner == EChessPlayer.BLACK) {
      this.posn =
          new BoardPosn(initialPosn.row(), BoardPosn.MAX_DIMENSION - initialPosn.col());
    }
  }


  @Override
  public boolean isAlive() {
    return this.isAlive;
  }


  @Override
  public EChessPlayer getOwner() {
    return this.owner;
  }

  @Override
  public int getValue() {
    return this.value;
  }
}
