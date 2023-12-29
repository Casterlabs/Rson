import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import co.casterlabs.rakurai.json.Rson;
import co.casterlabs.rakurai.json.element.JsonElement;

public class TestSuite {

    public static void main(String[] args) throws IOException {
        for (File file : new File("./src/test/java/suite").listFiles()) {
            if (!file.getName().endsWith(".json")) continue; // Skip non-Json files.

            char type = file.getName().charAt(0); // y = should succeed,n = should fail, i = up to us if it should p|f.
            String contents = new String(Files.readAllBytes(file.toPath()));

            System.out.println();

            try {
                JsonElement e = Rson.DEFAULT.fromJson(contents, JsonElement.class);
                switch (type) {
                    case 'i':
                    case 'y': {
                        System.out.printf("Test passed! %s\n", file.getName());
                        continue;
                    }

                    case 'n': {
                        System.out.printf("Test failed! %s %s\n", file.getName(), e);
                        continue;
                    }
                }

                try {
                    Rson.DEFAULT.fromJson(e.toString(), JsonElement.class);
                    Rson.DEFAULT.fromJson(e.toString(true), JsonElement.class);
                } catch (Throwable t) {
                    System.out.printf("Could not validate own output: %s %s\n", e.toString(), file.getName());
                    t.printStackTrace();
                    return;
                }
            } catch (Throwable t) {
                switch (type) {
                    case 'y': {
                        System.out.printf("Deserialization failed! %s\n", file.getName());
                        t.printStackTrace();
                        return;
                    }

                    case 'n': {
                        System.out.printf("Test passed! %s\n", file.getName());
                        continue;
                    }

                    case 'i': {
                        System.out.printf("Deserialization failed, ignoring! %s\n", file.getName());
//                        t.printStackTrace();
                        continue;
                    }
                }
            }
        }
    }

}
