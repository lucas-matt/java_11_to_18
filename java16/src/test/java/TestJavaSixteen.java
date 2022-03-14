import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJavaSixteen {

    @Test
    void dayPeriod() {
        LocalTime date = LocalTime.parse("07:13:09");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h B");
        assertEquals(
            "7 in the morning",
            date.format(formatter)
        );
    }

    @Test
    void streamToList() {
        var list = Stream.of(1, 2, 3).toList();
        assertEquals(
            List.of(1, 2, 3),
            list
        );
    }

    public record Movie(String title, Integer rating) {}

    @Test
    void records() {
        var starWars = new Movie("Star Wars", 5);
        assertEquals(
            "Star Wars", starWars.title()
        );
        assertEquals(
            5, starWars.rating()
        );
        assertEquals(
            starWars,
            new Movie("Star Wars", 5)
        );
    }

    @Test
    void patterns() {
        Object b = true;
        if (b instanceof Boolean c) {
            // c is Boolean type
        }
    }

    @Test
    void localEnums() {
        enum TriBoolean {
            True,
            False,
            Maybe
        }
        assertEquals(
            TriBoolean.True,
            TriBoolean.True
        );
    }

    @Test
    void interfaces() {
        interface Hello {
            String hello();
            String goodbye();
        }

        Hello hello = new Hello() {
            @Override
            public String hello() {
                return "hello world!";
            }

            @Override
            public String goodbye() {
                return "goodbye cruel world!";
            }
        };

        assertEquals(
            "hello world!",
            hello.hello()
        );

        assertEquals(
            "goodbye cruel world!",
            hello.goodbye()
        );

    }

}
