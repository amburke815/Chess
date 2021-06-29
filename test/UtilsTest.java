import model.Utils;
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

  @Test
  public void testNonNullConstructor() {
    try {
      Utils.nonNullConstructor(null, "Foo", "Bar");
    }catch (IllegalArgumentException e) {
      Assert.assertEquals("Cannot construct a Foo with a null Bar", e.getMessage());
    }
  }

}
