package result;

import java.io.*;
import java.net.URL;

public class ParserImpl implements Parser {

    @Override
    public BufferedReader getFile(String url) {
        try {
            return new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readFile(BufferedReader reader) {

    }

}
