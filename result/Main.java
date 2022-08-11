package result;

public class Main {

    private static String FILE_URL = "https://github.com/PeacockTeam/new-job/releases/download/v1.0/lng-4.txt.gz";
    public static void main(String[] args) {
        FileTransporter fileTransporter = new FileTransporterImpl();
        Parser parser = new ParserImpl(fileTransporter.getFileContent(FILE_URL));
        parser.parseList();


    }
}
