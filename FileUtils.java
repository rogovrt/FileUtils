import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileUtils implements AutoCloseable{
    public void close() {

    }

    static List<String> readAll(String path) {
        try (BufferedReader buf = new BufferedReader(new FileReader(path))){
            List<String> lines = new ArrayList<String>();
            String s = buf.readLine();
            while (s != null) {
                lines.add(s);
                s = buf.readLine();
            }
            return lines;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    static void writeAll(String path, List lines) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(path))) {
            for (int i = 0; i < lines.size(); i++) {
                pw.println(lines.get(i));
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void copy(String sourceFile, String destinationFile) {
        File source = new File(sourceFile);
        File destination = new File(destinationFile);
        try (FileChannel sourseChannel = new FileInputStream(source).getChannel()) {
            try (FileChannel destinationChannel = new FileOutputStream(destination).getChannel()) {
                destinationChannel.transferFrom(sourseChannel, 0, sourseChannel.size());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            e.getMessage();
        }
    }

    static void delete(String path) {
        File f = new File(path);
        try {
            if (!f.delete())
                throw new FileNotFoundException(path + " (Нет такого файла или каталога)");
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
