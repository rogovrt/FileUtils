import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FileIterator implements Iterator {
    private List<String> stringsFromFile;
    private int currentString;
    private String path;

    public FileIterator(String path) throws UncheckedIOException {
        stringsFromFile = FileUtils.readAll(path);
        currentString = -1;
        this.path = path;
    }

    public boolean hasNext() {
        return (currentString + 1 < stringsFromFile.size());
    }

    public String next() {
        if (this.hasNext()) {
            currentString++;
            return stringsFromFile.get(currentString);
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (currentString == -1)
            throw new IllegalStateException();
        stringsFromFile.remove(currentString);
        FileUtils.writeAll(path, stringsFromFile);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (o instanceof FileIterator) {
            return ((stringsFromFile.equals((((FileIterator)o).getStringsFromFile()))) &&
                    (currentString == ((FileIterator)o).getCurrentString()) &&
                    (path.equals((((FileIterator)o).getPath()))));
        }
        return false;
    }

    public int hashCode() {
        int res = 31;
        res = res * 17 + stringsFromFile.hashCode();
        res = res * 17 + currentString;
        res = res * 17 + path.hashCode();
        return res;
    }

    public List<String> getStringsFromFile() {
        return stringsFromFile;
    }

    public int getCurrentString() {
        return currentString;
    }

    public String getPath() {
        return path;
    }
}
