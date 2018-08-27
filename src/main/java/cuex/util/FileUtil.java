package cuex.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static final String TMP_DIR = "C:\\tmp";

    public static void saveToTmpDir(byte[] bytes, String name) throws IOException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(
                        new File(TMP_DIR, name)))) {
            outputStream.write(bytes);
        }
    }
}
