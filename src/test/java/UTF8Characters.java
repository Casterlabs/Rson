import co.casterlabs.rakurai.json.Rson;
import co.casterlabs.rakurai.json.element.JsonString;
import co.casterlabs.rakurai.json.serialization.JsonParseException;
import co.casterlabs.rakurai.json.validation.JsonValidationException;

public class UTF8Characters {

    public static void main(String[] args) throws JsonValidationException, JsonParseException {
        System.out.println(
            Rson.DEFAULT
                .fromJson("\"身正不怕影子斜\"", JsonString.class)
                .getAsString()
        );
    }

}
