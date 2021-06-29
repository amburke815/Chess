package model;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Static utility methods to be used liberally in all parts of the IME project.
 * Supports general, frequently used functions, such as:
 * <ul>
 *   <li>
 *     {@link Utils#checkNotNull(Object o)} to check if an object is null and throw an error
 *     message.
 *   </li>
 *   <li>
 *     {@link Utils#println(String)} to print a String with a newline below it.
 *   </li>
 *   <li>
 *     {@link Utils#paddedPrint(String)} to print a String padded above and below with a newline.
 *   </li>
 * </ul>
 */
public class Utils {

  /**
   * TODO
   * @param toCheck
   * @param errorMsg
   * @return
   * @throws IllegalArgumentException
   */
  public static <X> X checkNotNull(X toCheck, String errorMsg)
      throws IllegalArgumentException {
    if (toCheck == null) {
      throw new IllegalArgumentException(errorMsg);
    }
    return toCheck;
  }

  public static <X> X nonNullConstructor(X toCheck, String className, String paramType)
    throws IllegalArgumentException {
    return checkNotNull(toCheck, "Cannot construct a "
        + className
        + " with a null "
        + paramType);
  }

  public static int checkNonNegativeInt(int toCheck) {
    return checkIntBetween(toCheck, 0, Integer.MAX_VALUE);
  }


  /**
   * TODO
   * @param toCheck
   * @param lowerBound
   * @param upperBound
   * @return
   * @throws IllegalArgumentException
   */
  public static int checkIntBetween(int toCheck, int lowerBound, int upperBound)
      throws IllegalArgumentException {
    if (toCheck < lowerBound || toCheck > upperBound) {
      throw new IllegalArgumentException("integer " + toCheck + " out of bounds for range ["
          + lowerBound + "," + upperBound + "].");
    }
    return toCheck;
  }

  /**
   * TODO
   * @param toPad
   * @return
   */
  public static String println(String toPad) {
    return toPad + "\n";
  }

  /**
   * TODO
   * @param toPad
   * @return
   */
  public static String paddedPrint(String toPad) {
    return println("") + println(toPad);
  }

}
