import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    static List<String> readAll(String path) throws UncheckedIOException {
        try (BufferedReader buf = new BufferedReader(new FileReader(path))) {
            List<String> lines = new ArrayList<String>();
            String s = buf.readLine();
            while (s != null) {
                lines.add(s);
                s = buf.readLine();
            }
            return lines;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static void writeAll(String path, List lines) throws UncheckedIOException {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(path))) {
            for (int i = 0; i < lines.size(); i++) {
                pw.println(lines.get(i));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static void copy(String sourceFile, String destinationFile) throws UncheckedIOException {
        File source = new File(sourceFile);
        File destination = new File(destinationFile);
        try (FileChannel sourseChannel = new FileInputStream(source).getChannel();
             FileChannel destinationChannel = new FileOutputStream(destination).getChannel()) {
            destinationChannel.transferFrom(sourseChannel, 0, sourseChannel.size());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static void delete(String path) {
        File f = new File(path);
        f.delete();
    }
}
