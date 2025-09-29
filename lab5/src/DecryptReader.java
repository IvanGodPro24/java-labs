import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptReader extends FilterReader {
    private final int key;

    protected DecryptReader(Reader in, char keyChar) {
        super(in);
        this.key = keyChar;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1) ? -1 : c - key;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int count = super.read(cbuf, off, len);
        if (count == -1) return -1;

        for (int i = off; i < off + count; i++) {
            cbuf[i] -= key;
        }
        return count;
    }
}