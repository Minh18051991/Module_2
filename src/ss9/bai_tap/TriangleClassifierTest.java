package ss9.bai_tap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleClassifierTest {

    @Test
    public void testEquilateralTriangle() {
        assertEquals("tam giác đều", TriangleClassifier.classify(2, 2, 2));
    }

    @Test
    public void testIsoscelesTriangle() {
        assertEquals("tam giác cân", TriangleClassifier.classify(2, 2, 3));
    }//

    @Test
    public void testScaleneTriangle() {
        assertEquals("tam giác thường", TriangleClassifier.classify(3, 4, 5));
    }

    @Test
    public void testNotATriangle() {
        assertEquals("không phải là tam giác", TriangleClassifier.classify(8, 2, 3));
    }

    @Test
    public void testNegativeSide() {
        assertEquals("không phải là tam giác", TriangleClassifier.classify(-1, 2, 1));
    }

    @Test
    public void testZeroSide() {
        assertEquals("không phải là tam giác", TriangleClassifier.classify(0, 1, 1));
    }
}
