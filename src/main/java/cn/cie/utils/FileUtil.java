package cn.cie.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by RojerAlone on 2017/6/15.
 */
public class FileUtil {

    public static void uploadFile(byte[] data, String filePath, String fileName) throws Exception {
        File file = new File(filePath);

        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(data);
        out.flush();
        out.close();
    }

}
