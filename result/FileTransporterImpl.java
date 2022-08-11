package result;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class FileTransporterImpl implements FileTransporter {

    @Override
    public List<String> getFileContent(String url) {
        List<String> listOfString = new ArrayList<>();
        File file = getFile(url);
        try (BufferedReader in = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = in.readLine()) != null) {
                listOfString.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfString;
    }

    private File getFile(String url) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream())){
            String filename = Paths.get(new URI(url).getPath()).getFileName().toString();

            File file = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            int n = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return decompressGzip(file);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private File decompressGzip(File file) throws IOException {
        File outputFile = new File(file.getName() + ".txt");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(outputFile);
        GZIPInputStream gzis = new GZIPInputStream(fis);
        byte[] buffer = new byte[1024];
        int len = 0;
        int n = 0;
        while ((len = gzis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fis.close();
        gzis.close();

        return outputFile;

    }

}
