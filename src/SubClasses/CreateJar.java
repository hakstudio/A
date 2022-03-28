
package SubClasses;

import MainClasses.Main;
import MainClasses.Messages;
import Langs.ALangs.Deutsch;
import Langs.ALangs.English;
import Langs.ALangs.Turkce;

import java.io.*;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class CreateJar {
    private final File projectFile;

    public CreateJar(File projectFile, String mainClass, String outputFile) {
        this.projectFile = projectFile;
        outputFile += ".jar";
        try {
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
            manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, mainClass);
            JarOutputStream jarOutput = new JarOutputStream(new FileOutputStream(outputFile), manifest);
            for (File file : projectFile.listFiles()) add(file, jarOutput);
            jarOutput.close();
            if (Main.lang.equals(Turkce.lang)) Messages.createdSuccessfully(Turkce.Console, outputFile);
            if (Main.lang.equals(English.lang)) Messages.createdSuccessfully(English.Console, outputFile);
            if (Main.lang.equals(Deutsch.lang)) Messages.createdSuccessfully(Deutsch.Console, outputFile);
        } catch (IOException e) {
            Messages.jarCreationError(e);
        }
    }

    private void add(File file, JarOutputStream jarOutput) {
        try {
            String filePath = file.getPath().substring(projectFile.getPath().length() + 1).replaceAll("\\\\", "/");
            if (!file.isDirectory()) {
                JarEntry jarEntry = new JarEntry(filePath);
                jarEntry.setTime(file.lastModified());
                jarOutput.putNextEntry(jarEntry);
                BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[1024];
                int count;
                while ((count = bufferedInput.read(buffer)) != -1) jarOutput.write(buffer, 0, count);
                jarOutput.closeEntry();
                bufferedInput.close();
            } else for (File _file : file.listFiles()) add(_file, jarOutput);
        } catch (IOException e) {
            Messages.jarCreationError(e);
        }
    }
} 
