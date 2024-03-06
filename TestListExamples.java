import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);

    List<String> strLeft = Arrays.asList("3", "4", "5");
    List<String> strRight = Arrays.asList("1", "2", "4", "8");
    List<String> strMerged = ListExamples.merge(strLeft, strRight);
    List<String> strExpected = Arrays.asList("1", "2", "3", "4", "4", "5", "8");
    assertEquals(strExpected, strMerged);
  }

  @Test(timeout = 500)
  public void testMergeLeftEnd() {
    List<String> right = Arrays.asList("a", "b", "c");
    List<String> left = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);

    List<String> strLeft = Arrays.asList("3", "4", "5");
    List<String> strRight = Arrays.asList("1", "2", "4", "8");
    List<String> strMerged = ListExamples.merge(strLeft, strRight);
    List<String> strExpected = Arrays.asList("1", "2", "3", "4", "4", "5", "8");
    assertEquals(strExpected, strMerged);
  }

  @Test(timeout = 500)
  public void testMergeEmpty() {
    List<String> empty1 = Arrays.asList();
    List<String> empty2 = Arrays.asList();
    List<String> strList = Arrays.asList("a", "b", "c");
    List<String> expectedEmpty = Arrays.asList();
    List<String> expectedMerge = Arrays.asList("a", "b", "c");
    assertEquals(expectedEmpty, ListExamples.merge(empty1, empty2));
    assertEquals(expectedMerge, ListExamples.merge(empty1, strList));
    assertEquals(expectedMerge, ListExamples.merge(strList, empty1));
  }

  @Test(timeout = 500)
  public void testFilterEmpty() {
    List<String> empty = Arrays.asList();
    StringChecker sc = new IsMoon();
    List<String> filtered = ListExamples.filter(empty, sc);
    List<String> expectedEmpty = Arrays.asList();
    assertEquals(expectedEmpty, filtered);
  }

  @Test(timeout = 500)
  public void testFilterPartial() {
    List<String> testList = Arrays.asList("Moon", "Hi", "moon", "dsfjkhrih");
    StringChecker sc = new IsMoon();
    List<String> filtered = ListExamples.filter(testList, sc);
    List<String> expected = Arrays.asList("Moon", "moon");
    assertEquals(expected, filtered);
  }

  @Test(timeout = 500)
  public void testFilterFull() {
    List<String> testList = Arrays.asList("Moon", "moon", "mOOn", "MoOn", "mooN");
    StringChecker sc = new IsMoon();
    List<String> filtered = ListExamples.filter(testList, sc);
    List<String> expected = Arrays.asList("Moon", "moon", "mOOn", "MoOn", "mooN");
    assertEquals(expected, filtered);
  }
}
