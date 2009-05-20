package com.g3d.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * Helper class for extracting the natives (dll, so) from the jars.
 * This class should only be used internally.
 */
public class Natives {

    private static File workingDir;
    private static final Logger logger = Logger.getLogger(Natives.class.getName());

    static {
        workingDir = new File(System.getProperty("user.dir"));
        if (!workingDir.exists())
            throw new RuntimeException("Working directory "+workingDir+" does not exist!");
    }

    public static void extractNativeLib(String sysName, String name) throws IOException{
        String fullname = System.mapLibraryName(name);
        File targetFile = new File(workingDir, fullname);
        InputStream in = Natives.class.getResourceAsStream("/native/"+sysName+"/" + fullname);
        if (in == null) {
            logger.warning("Cannot locate native library " + name);
        }
        if (targetFile.exists())
            return;

        OutputStream out = new FileOutputStream(targetFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();

        logger.fine("Copied "+fullname+" to "+targetFile);
    }

    public static void extractNativeLibs(String sysName) throws IOException{
        extractNativeLib(sysName, "lwjgl");
        extractNativeLib(sysName, "lwjgl64");
        if (sysName.equals("windows")){
            extractNativeLib(sysName, "jinput-dx8");
            extractNativeLib(sysName, "jinput-dx8_64");
            extractNativeLib(sysName, "jinput-raw");
            extractNativeLib(sysName, "jinput-raw_64");
        }else if (sysName.equals("linux")){
            extractNativeLib(sysName, "jinput-linux");
            extractNativeLib(sysName, "jinput-linux64");
        }else{
            extractNativeLib(sysName, "jinput");
            extractNativeLib(sysName, "jinput64");
        }
        System.setProperty("org.lwjgl.librarypath", workingDir.toString());
    }

}
