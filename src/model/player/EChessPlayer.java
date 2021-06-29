package model.player;
import model.Utils;

public enum EChessPlayer {
  WHITE("White"), BLACK("Black");

  private final String name;

  private EChessPlayer(String name) {
    this.name = Utils.nonNullConstructor(name, "EChessPlayer", "String");
  }

  public String toString() {
    return this.name;
  }
}
