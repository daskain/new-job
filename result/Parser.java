package result;

import java.io.BufferedReader;
import java.io.File;

public interface Parser {

    BufferedReader getFile(String url);
    void readFile(BufferedReader reader);

}
