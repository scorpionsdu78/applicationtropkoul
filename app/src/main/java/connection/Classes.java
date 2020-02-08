package connection;

import org.json.JSONException;

import java.io.IOException;

public class Classes extends APIconnection {

    public Classes() throws IOException, JSONException{
        super("http://www.dnd5eapi.co/api/classes/");
    }

    public Classes(String classe) throws IOException, JSONException {
        super( "http://www.dnd5eapi.co/api/classes/" + classe);
    }
}
