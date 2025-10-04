import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class EncryptWriter extends FilterWriter {
    private final int key;

    protected EncryptWriter(Writer out, char keyChar) {
        super(out);
        this.key = keyChar;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c + key); // зсув на ключ
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            cbuf[i] += key;
        }
        super.write(cbuf, off, len);
    }
}