import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJavaTwelve {

    @Test
    void newStringMethods() {

        assertEquals(
                "    hello\n    world!\n",
                "hello\nworld!".indent(4)
        );

        assertEquals(
                "Capital",
                "capital".transform((str) -> str.substring(0, 1).toUpperCase() + str.substring(1))
        );

    }

    @Test
    void fileMismatch() throws IOException {

        Path target = Path.of("target");
        Path x = Files.createTempFile(target, "x", ".txt");
        Path y = Files.createTempFile(target, "y", ".txt");

        Files.writeString(x,"The quick brown fox jumps over the lazy dog");
        Files.writeString(y,"The fast brown fox jumps over the lazy dog");
        //                           ^
        //                           |

        assertEquals(
            4,
            Files.mismatch(x, y)
        );

    }

    @Test
    void teeingCollector() {

        var numbers = Stream.of(10, 20, 30);
        var average = numbers.collect(
                Collectors.teeing(
                        Collectors.summingInt(i -> i),
                        Collectors.counting(),
                        (sum, count) -> sum / count
                )
        );

        assertEquals(20, average);

    }

    @Test
    void numberFormatting() {

        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(new Locale("en", "GB"), NumberFormat.Style.SHORT);
        shortFormat.setMaximumFractionDigits(2);

        assertEquals(
                "12.35K",
                shortFormat.format(12345)
        );

    }

}
