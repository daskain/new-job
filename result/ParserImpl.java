package result;

import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.stream.Collectors;

public class ParserImpl implements Parser {

    private List<String> listOfLine;
    ParserImpl(List<String> listOfLine){
        this.listOfLine = listOfLine;
    }
    @Override
    public void parseList() {
        clearListOfDuplicate();
        clearListOfIncorrectLine();
        List<String[]> listOfStringElement = listOfLine.stream()
                .map(s -> s.split(";"))
                .collect(Collectors.toList());



    }

    private void clearListOfDuplicate(){
        listOfLine = listOfLine.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private void clearListOfIncorrectLine(){
        listOfLine.removeIf(s -> s.matches("\"\\d*\"\\d\""));
    }
}
