import org.junit.jupiter.api.Test;

import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJavaFourteen {

    @Test
    void switchExpressions() {

        var month = AUGUST;

        boolean isSummer = switch(month) {
            case JUNE, JULY, AUGUST -> true;
            default -> false;
        };

        assertTrue(isSummer);

    }

    @Test
    void helpfulNullPointers() {

        String missing = null;
        try {
            missing.toLowerCase();
        } catch (NullPointerException npe) {
            assertEquals("Cannot invoke \"String.toLowerCase()\" because \"missing\" is null", npe.getMessage());
        }

    }

}
