import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TestJavaEleven {

    @Test
    void newStringMethods() {

        assertTrue("".isBlank());
        assertTrue("  ".isBlank());
        assertFalse("not blank".isBlank());

        assertEquals(
            List.of("hello", "world!"),
            "hello\nworld!".lines().collect(Collectors.toList())
        );

        assertEquals(
            "hello",
            " hello ".strip()
        );

        assertEquals(
            "echo echo echo ",
            "echo ".repeat(3)
        );

    }

    @Test
    void fileReadWriteString() throws IOException {

        Path tempFile = Files.createTempFile(Path.of("target"), "test", ".txt");
        Files.writeString(tempFile, "Hello World!");

        String read = Files.readString(tempFile);
        assertEquals("Hello World!", read);

    }

    @Test
    void collectionToArray() {

        var list = List.of("x", "y", "z");

        assertArrayEquals(
            new String[]{"x", "y", "z"},
            list.toArray(String[]::new)
        );

    }

    @Test
    void notPredicate() {
        Predicate<Boolean> isTrue = (Boolean b) -> b;
        assertTrue(
            Predicate.not(isTrue).test(false)
        );
    }

    @Test
    void lambdaLocalVars() {
        Predicate<Boolean> isTrue = (var b) -> b;
        assertTrue(
            isTrue.test(true)
        );
    }

    @Test
    void newHttpClient() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://worldtimeapi.org/api/timezone/Europe/London")).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }

}
