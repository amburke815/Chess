package model.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import model.Utils;

/**
 * <p>An abstract implementation of an {@link IMatrix} containing elements of some type {@code X}.
 * Supports the operations listed in the {@link IMatrix} interface from the perspective of an
 * abstract, computable algebra on the set {@code M(X)}, where M is the set of all matrices of any
 * dimensions and {@code X} is the set membership of an entry</p><p> </p>
 * <header><u>INVARIANT</u></header>
 * <sub><div>Assumes an <b><i>invariant</i></b> that the nested {@link List} structures that store
 * the entries in this {@link AMatrix} have identical dimensions to the 'physical' matrix they
 * represent. For this reason there are no 'empty'--{@link java.util.Optional}, {@code null}, ...
 * etc-- entries</p>
 * </div></sub>
 *
 * @param <X> the type of entry in this Matrix
 */
public abstract class AMatrix<X> implements IMatrix<X> {

  protected final List<List<X>> entries;

  /**
   * Creates a new {@link AMatrix} with no contents, nullary constructor.
   */
  public AMatrix() {
    this.entries = new ArrayList<>();
  }

  /**
   * <p>Creates a new {@link AMatrix} given a variable number of {@link List}s of values to add
   * to the matrix, representing its rows.</p>
   * <p>Due to the <b><i>invariant</i></b> outlined in the header JavaDoc of {@link AMatrix},
   * each supplied row must be of the same size to preserve the dimensions of the matrix, and each
   * entry must be a non-empty value (not null, Optional, etc).
   * </p>
   *
   * @param listEntries the row(s) to make up the new matrix, which all have the same length.
   * @throws IllegalArgumentException if the given list(s) are {@null}, if any of the given lists
   *                                  contain {@code null}, or if any of the lists are not of the
   *                                  same size
   */
  public AMatrix(List<List<X>> listEntries)
      throws IllegalArgumentException {
    // check that each row and entry are not null
    Utils.checkNotNull(listEntries, "cannot construct matrix with null list entries");

    if (!this.checkAllRowsSameSize(listEntries)) {
      throw new IllegalArgumentException("cannot create a Matrix without all "
          + "rows having equal size to ensure dimensions exist");
    }

    // initialize a list for entries to be placed in
    this.entries = new ArrayList<>();

    for (List<X> row : listEntries) {
      List<X> copy = new ArrayList<>(row);
      this.entries.add(copy);
    }
  }

  /**
   * Creates a new {@link AMatrix} by copying a given row of entries {@code oneRow} {@code
   * numCopies} times. For example, {@code AMatrix({1, 2, 3,}, 3} would generate the matrix
   * <p>{{1, 2, 3}, </p>
   * <p> {1, 2, 3},</p>
   * <p>{1, 2, 3}} </p>
   *
   * @param oneRow    the row of numbers to be copied some number of times
   * @param numCopies the number of times to copy the desired row (the number of rows)
   * @throws IllegalArgumentException if the given row is null or if the number of copies to make is
   *                                  negative.
   */
  public AMatrix(List<X> oneRow, int numCopies)
      throws IllegalArgumentException {
    Utils.checkNotNull(oneRow, "cannot copy a null row");
    Utils.checkIntBetween(numCopies, 0, Integer.MAX_VALUE);
    List<List<X>> rows = new ArrayList<>();
    for (int i = 0; i < numCopies; i++) {
      rows.add(oneRow);
    }

    this.entries = rows;
  }

  /**
   * Creates a new {@link AMatrix} with {@code numRows} rows and {@code numCols} columns, all filled
   * with {@code uniformEntry}.
   *
   * @param uniformEntry the entry to be placed at every index in the resultant matrix.
   * @param numRows      the number of rows in the resultant matrix.
   * @param numCols      the number of columns in the resultant matrix.
   * @throws IllegalArgumentException if {@code numRows} or {@code numCols} is negative, or if
   *                                  {@code uniformEntry} is {@code null}.
   */
  public AMatrix(X uniformEntry, int numRows, int numCols)
      throws IllegalArgumentException {
    Utils.checkNotNull(uniformEntry, "cannot fill a matrix with a null entry");
    Utils.checkIntBetween(numRows, 0, Integer.MAX_VALUE);
    Utils.checkIntBetween(numCols, 0, Integer.MAX_VALUE);

    List<List<X>> entries = new ArrayList<>();
    for (int rowNum = 0; rowNum < numRows; rowNum++) {
      List<X> thisRow = new ArrayList<>();
      for (int colNum = 0; colNum < numCols; colNum++) {
        thisRow.add(uniformEntry);
      }
      entries.add(thisRow);
    }

    this.entries = entries;
  }


  /**
   * Are all of the rows in a Matrix proposed by {@code listEntries} of the same size?. In other
   * words, are all of the subsets of a set of the same cardinality?
   * <p>Note that this is trivially true for empty matrices--they contain no rows</p>
   *
   * @param listEntries the proposed rows in a Matrix to answer the above question for.
   * @return the answer to the question posed at the beginning of this JavaDoc
   * @throws IllegalArgumentException if the supplied {@link List} is null;
   */
  protected boolean checkAllRowsSameSize(List<List<X>> listEntries)
      throws IllegalArgumentException {

    Utils.checkNotNull(listEntries, "cannot verify all rows same size for null"
        + " list entries");

    // fast check
    if (listEntries.size() == 0) {
      return true; // trivially all rows are same size when there are none
    }

    boolean allRowsSameSize = true;

    for (int row = 0; row < listEntries.size() - 1; row++) {
      allRowsSameSize &= listEntries.get(row).size() == listEntries.get(row + 1).size();
    }

    return allRowsSameSize;
  }

  @Override
  public X getElement(int row, int col)
      throws IllegalArgumentException {

    return entries.get(Utils.checkIntBetween(row, 0, this.getHeight())).
        get(Utils.checkIntBetween(col, 0, this.getWidth()));

  }

  @Override
  public int getWidth() {
    if (entries.size() == 0) {
      return 0;
    }

    // guaranteed to exist
    return entries.get(0).size();
  }

  @Override
  public int getHeight() {
    if (entries.size() == 0) {
      return 0;
    }

    // guaranteed to exist
    return entries.size();
  }

  @Override
  public void fillWith(X entry)
      throws IllegalArgumentException {
    Utils.checkNotNull(entry, "cannot fill a matrix with a null entry");

    if (entries.isEmpty()) {
      throw new IllegalArgumentException("cannot fill an empty matrix with an entry");
    }

    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {
        this.updateEntry(entry, row, col);
      }
    }
  }

  @Override
  public void updateEntry(X newEntry, int row, int col)
      throws IllegalArgumentException {
    Utils.checkIntBetween(row, 0, this.getHeight());
    Utils.checkIntBetween(col, 0, this.getWidth());

    this.entries.get(row).set(col, newEntry);
  }

  @Override
  public IMatrix<X> elementWiseOperation(BiFunction<X, X, X> binaryOperation,
      IMatrix<X> toCombine)
      throws IllegalArgumentException {
    if (this.getWidth() != toCombine.getWidth()
        || this.getHeight() != toCombine.getHeight()) {
      throw new IllegalArgumentException("cannot complete an elementwise operation on two matrices "
          + "of different dimensions. Indices must line up");
    }

    IMatrix<X> copy = this.copy();

    for (int rowNum = 0; rowNum < copy.getHeight(); rowNum++) {
      for (int colNum = 0; colNum < copy.getWidth(); colNum++) {
        copy.updateEntry(
            binaryOperation.apply(
                copy.getElement(rowNum, colNum), toCombine.getElement(rowNum, colNum)),
            rowNum,
            colNum);
      }
    }

    return copy;

  }

  @Override
  public <Y> IMatrix<Y> map(Function<X, Y> unaryOperation) {
    List<List<Y>> newRows = new ArrayList<>();

    for (List<X> row : this.entries) {
      List<Y> newRow = new ArrayList<>();
      for (X entry : row) {
        newRow.add(unaryOperation.apply(entry));
      }
      newRows.add(newRow);
    }

    return this.factoryMatrix(newRows);
  }

  /**
   * Factory method to return a new {@link IMatrix} object based on the supplied rows.
   *
   * @param rows the rows to create this new matrix with
   * @param <Y>  the type of entry in the new Matrix
   * @return the new Matrix as described above
   * @throws IllegalArgumentException if the given list is null or contains null in its sublists or
   *                                  their elements
   */
  protected abstract <Y> IMatrix<Y> factoryMatrix(List<List<Y>> rows)
      throws IllegalArgumentException;

  @Override
  public IMatrix<X> copy() {
    List<List<X>> rows = new ArrayList<>();

    for (List<X> row : this.entries) {
      rows.add(new ArrayList<>(row));
    }

    return this.factoryMatrix(rows);
  }

  @Override
  public X reduceToVal(BiFunction<X, X, X> operation, X base) {
    X reduced = base;

    for (int rowNum = 0; rowNum < this.getHeight(); rowNum++) {
      for (int colNum = 0; colNum < this.getWidth(); colNum++) {
        reduced = operation.apply(reduced, this.getElement(rowNum, colNum));
      }
    }

    return reduced;
  }


  @Override
  public boolean equals(Object o) {
    // fast check
    if (this == o) {
      return true;
    }

    // check for instance
    if (!(o instanceof IMatrix)) {
      return false;
    }

    // safe cast
    IMatrix otherMatrix = (IMatrix) o;

    boolean allSameEntries = true;

    for (int rowNum = 0; rowNum < this.getHeight(); rowNum++) {
      for (int colNum = 0; colNum < this.getWidth(); colNum++) {
        allSameEntries &=
            this.getElement(rowNum, colNum).equals(otherMatrix.getElement(rowNum, colNum));
      }
    }

    return allSameEntries;

  }

  @Override
  public int hashCode() {
    int hashValue = 0;

    for (int rowNum = 0; rowNum < this.getHeight(); rowNum++) {
      for (int colNum = 0; colNum < this.getWidth(); colNum++) {
        hashValue += Objects.hashCode(this.getElement(rowNum, colNum));
      }
    }

    return hashValue;
  }

  @Override
  public String toString() {

    String renderedMatrix = "";

    for (int rowNum = 0; rowNum < this.getHeight(); rowNum++) {
      renderedMatrix += "R" + rowNum + ":";
      for (int colNum = 0; colNum < this.getWidth(); colNum++) {
        renderedMatrix += " " + this.getElement(rowNum, colNum).toString();
      }
      renderedMatrix += "\n";
    }

    return renderedMatrix;
  }


}