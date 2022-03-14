import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJavaFifteen {

    @Test
    void hiddenClass() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException {
        InputStream stream = TestJavaFifteen.class.getClassLoader().getResourceAsStream("HiddenClass.class");
        MethodHandles.Lookup lookup = MethodHandles.lookup()
                .defineHiddenClass(stream.readAllBytes(), true, MethodHandles.Lookup.ClassOption.NESTMATE);
        Class<?> hidden = lookup.lookupClass();
        Object instance = hidden.getDeclaredConstructor().newInstance();
        Object result = hidden.getMethod("hello").invoke(instance);
        assertEquals("Hello World!", result);
    }

    @Test
    void textBlocks() {
        var atLast = """
            {
                "text": "blocks!"
            }
        """;
    }

}
