package logic;

import java.io.File;
import java.io.IOException;

public interface IEncode {
    void compress (String text, File fileLocation) throws IOException;
    String decompress(File filename) throws IOException;

}
