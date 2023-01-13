package h11;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonUtils {

    private static final JsonFactory JSON_FACTORY = new JsonFactory();

    private static final ObjectCodec OBJECT_MAPPER = new ObjectMapper();

    private static final PrettyPrinter PRETTY_PRINTER = new DefaultPrettyPrinter();

    public static void writePojo(Path path, Object pojo) throws IOException {
        writePojo(Files.newOutputStream(path), pojo);
    }

    public static void writePojo(OutputStream out, Object pojo) throws IOException {
        JSON_FACTORY
            .createGenerator(out)
            .setCodec(OBJECT_MAPPER)
            .setPrettyPrinter(PRETTY_PRINTER)
            .writePOJO(pojo);
    }
}
