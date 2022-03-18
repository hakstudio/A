package MainClasses;

import Langs.A;
import SubClasses.CreateJar;
import SubClasses.ClassObject;
import Langs.OtherLangs.CSharp;
import Langs.OtherLangs.Java;
import Langs.ALangs.Deutsch;
import Langs.ALangs.English;
import Langs.ALangs.Turkce;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static String aCode = "";
    private static File tempFolder = null;
    private static File haka = null;
    public static String lang = null;
    private static String projectName = null;
    public static String projectType = null;
    public static String _projectType = null;
    public static String classes = null;
    public static String mainClass = null;
    public static String output = null;

    private static final ArrayList<String> Lang = new ArrayList<>(Arrays.asList(Turkce.Lang, English.Lang, Deutsch.Lang));
    private static final ArrayList<String> ProjectName = new ArrayList<>(Arrays.asList(Turkce.ProjectName, English.ProjectName, Deutsch.ProjectName));
    private static final ArrayList<String> Type = new ArrayList<>(Arrays.asList(Turkce.Type, English.Type, Deutsch.Type));
    private static final ArrayList<String> Classes = new ArrayList<>(Arrays.asList(Turkce.Classes, English.Classes, Deutsch.Classes));
    private static final ArrayList<String> MainClass = new ArrayList<>(Arrays.asList(Turkce.MainClass, English.MainClass, Deutsch.MainClass));
    private static final ArrayList<String> Output = new ArrayList<>(Arrays.asList(Turkce.Output, English.Output, Deutsch.Output));

    public static void main(String[] args) {
        if (args.length > 0) {
            if (new File(args[0]).getName().equals("hak.a")) {
                parseHaka(new File(args[0]));
                if (args.length == 1) {
                    compile();
                } else if (args.length == 2) {
                    if (A.langs.contains(args[1])) {
                        _projectType = projectType;
                        projectType = args[1];
                        if (!lang.equals(projectType)) compile();
                        else Messages.langsSameError(lang);
                    } else Messages.langNotFoundError();
                } else {
                    Messages.incorrectUsage();
                }
            } else if (args.length == 3) {
                if (A.langs.contains(args[0])) {
                    if (A.types.contains(args[2])) createProject(args[0], args[1], args[2]);
                    else Messages.typeNotFoundError();
                } else Messages.langNotFoundError();
            } else Messages.incorrectUsage();
        } else Messages.usage();
    }

    private static void parseHaka(File _haka) {
        haka = _haka;
        Parse parse = new Parse(_haka, fileToString(_haka));
        ArrayList<String> list = new ArrayList<>(Arrays.asList(A.SEMICOLON, A.NEWLINE, A.EQUAL));
        for (ArrayList<String> item : parse.mainList) {
            item.removeAll(A.spaces);
            item.removeAll(list);
            for (int i = 0; i < item.size(); i++) {
                if (Lang.contains(item.get(i))) {
                    String text = starToString(item.get(i + 1), parse.quotesList);
                    if (A.langs.contains(text)) lang = text;
                }
                if (ProjectName.contains(item.get(i)))
                    projectName = starToString(item.get(i + 1), parse.quotesList);
                if (Type.contains(item.get(i))) {
                    String text = starToString(item.get(i + 1), parse.quotesList);
                    if (A.types.contains(text)) projectType = text;
                }
                if (Classes.contains(item.get(i)))
                    classes = starToString(item.get(i + 1), parse.quotesList);
                if (MainClass.contains(item.get(i)))
                    mainClass = starToString(item.get(i + 1), parse.quotesList);
                if (Output.contains(item.get(i)))
                    output = starToString(item.get(i + 1), parse.quotesList);
            }
        }
        if (lang == null) Messages.langNotFoundError();
        if (projectName == null) Messages.projectNameNotFoundError();
        if (projectType == null) Messages.typeNotFoundError();
        if (classes == null || Files.notExists(Paths.get(classes))) Messages.classesNotFoundError();
        if (mainClass == null || Files.notExists(Paths.get(classes, mainClass))) Messages.mainClassNotFoundError();
        if (output == null) Messages.mainClassNotFoundError();
    }

    private static void compile() {
        mainClass = mainClass.substring(0, mainClass.length() - 2);
        List<ClassObject> files = new ArrayList<>();
        for (File file : fileList(new File(classes))) {
            String pathInProject = file.getPath().substring(classes.length() + 1).replaceAll("\\\\", "/");
            aCode = fileToString(file);
            Parse parse = new Parse(file, aCode);
            System.out.println(parse.mainList);
            System.out.println(parse.bracketList);
            System.out.println(parse.curlyBracketList);
            System.out.println(parse.quotesList);
            Compile compile = new Compile(file, aCode, parse.mainList, parse.bracketList, parse.curlyBracketList, parse.quotesList);
            files.add(new ClassObject(pathInProject.substring(0, pathInProject.length() - 2), compile.code));
        }
        if (A.Console.contains(projectType)) {
            tempFolder = new File(creatableTempFolder());
            tempFolder.deleteOnExit();
            createTempFolder();
            compileJava(files);
            String outputFile = new File(output, projectName).toString();
            deleteIfExists(outputFile + ".jar");
            new CreateJar(tempFolder, mainClass, outputFile);
            deleteFiles(tempFolder);
        }
        if (projectType.equals(Java.lang)) otherLangsCreateOutput(Java.lang, Java.extension, files);
        if (projectType.equals(CSharp.lang)) otherLangsCreateOutput(CSharp.lang, CSharp.extension, files);
        if (projectType.equals(Turkce.lang)) changeLang(Turkce.lang, Turkce.Copy, files);
        if (projectType.equals(English.lang)) changeLang(English.lang, English.Copy, files);
        if (projectType.equals(Deutsch.lang)) changeLang(Deutsch.lang, Deutsch.Copy, files);
    }

    private static void changeLang(String targetLang, String Copy, List<ClassObject> files) {
        try {
            String messageLang = projectType;
            projectType = _projectType;
            String classesCopy = classes + "_" + Copy;
            deleteIfExists(classesCopy);
            Files.move(Paths.get(classes), Paths.get(classesCopy));
            String hakaCopy = new File(haka.getParent(), "hak_" + Copy + A.extension).toString();
            deleteIfExists(hakaCopy);
            Files.move(haka.toPath(), Paths.get(hakaCopy));
            if (A.Console.contains(projectType)) {
                if (targetLang.equals(Turkce.lang)) projectType = Turkce.Console;
                if (targetLang.equals(English.lang)) projectType = English.Console;
                if (targetLang.equals(Deutsch.lang)) projectType = Deutsch.Console;
            }
            if (A.Mobile.contains(projectType)) {
                if (targetLang.equals(Turkce.lang)) projectType = Turkce.Mobile;
                if (targetLang.equals(English.lang)) projectType = English.Mobile;
                if (targetLang.equals(Deutsch.lang)) projectType = Deutsch.Mobile;
            }
            Files.write(haka.toPath(), Arrays.asList(createHaka(targetLang, projectName, projectType, classes, mainClass, output)), StandardCharsets.UTF_8);
            Files.createDirectory(Paths.get(classes));
            for (ClassObject classObject : files) {
                File classesFile = new File(classes);
                File classConst = new File(classes, classObject.classs + A.extension);
                File classs = new File(classes, classObject.classs + A.extension);
                ArrayList<File> fileList = new ArrayList<>();
                while (!classs.equals(classesFile)) {
                    fileList.add(classs);
                    classs = classs.getParentFile();
                }
                for (int i = fileList.size() - 1; i >= 0; i--) {
                    if (fileList.get(i).equals(classConst))
                        Files.write(fileList.get(i).toPath(), Arrays.asList(classObject.code), StandardCharsets.UTF_8);
                    else if (Files.notExists(fileList.get(i).toPath()))
                        Files.createDirectory(fileList.get(i).toPath());
                }
            }
            deleteIfExists(hakaCopy);
            deleteIfExists(classesCopy);
            Messages.langChangedSuccessfully(messageLang);
        } catch (IOException e) {
            Messages.langChangeError(e);
        }
    }

    private static String createHaka(String lang, String projectName, String type, String classes, String mainClass, String output) {
        String Lang = "";
        String ProjectName = "";
        String Type = "";
        String Classes = "";
        String MainClass = "";
        String Output = "";

        if (lang.equals(Turkce.lang)) {
            Lang = Turkce.Lang;
            ProjectName = Turkce.ProjectName;
            Type = Turkce.Type;
            Classes = Turkce.Classes;
            MainClass = Turkce.MainClass;
            Output = Turkce.Output;
        }
        if (lang.equals(English.lang)) {
            Lang = English.Lang;
            ProjectName = English.ProjectName;
            Type = English.Type;
            Classes = English.Classes;
            MainClass = English.MainClass;
            Output = English.Output;
        }
        if (lang.equals(Deutsch.lang)) {
            Lang = Deutsch.Lang;
            ProjectName = Deutsch.ProjectName;
            Type = Deutsch.Type;
            Classes = Deutsch.Classes;
            MainClass = Deutsch.MainClass;
            Output = Deutsch.Output;
        }
        String haka = Lang + "=\"" + lang + "\";\n" +
                ProjectName + "=\"" + projectName + "\";\n" +
                Type + "=\"" + type + "\";\n" +
                Classes + "=\"" + classes + "\";\n" +
                MainClass + "=\"" + mainClass + ".a\";\n" +
                Output + "=\"" + output + "\";";
        return haka;
    }

    private static void otherLangsCreateOutput(String lang, String extension, List<ClassObject> files) {
        String outputFile = new File(output, projectName).toString();
        deleteIfExists(outputFile);
        try {
            if (!Files.exists(Paths.get(output))) Files.createDirectory(Paths.get(output));
            if (!Files.exists(Paths.get(outputFile))) Files.createDirectory(Paths.get(outputFile));
            for (ClassObject classObject : files) {
                File classesFile = new File(outputFile);
                File classConst = new File(outputFile, classObject.classs + extension);
                File classs = new File(outputFile, classObject.classs + extension);
                ArrayList<File> fileList = new ArrayList<>();
                while (!classs.equals(classesFile)) {
                    fileList.add(classs);
                    classs = classs.getParentFile();
                }
                for (int i = fileList.size() - 1; i >= 0; i--) {
                    if (fileList.get(i).equals(classConst))
                        Files.write(fileList.get(i).toPath(), Arrays.asList(classObject.code), StandardCharsets.UTF_8);
                    else if (Files.notExists(fileList.get(i).toPath()))
                        Files.createDirectory(fileList.get(i).toPath());
                }
            }
        } catch (IOException e) {
            Messages.fileCreationError(e);
        }

        Messages.createdSuccessfully(lang, Paths.get(outputFile).toString());
    }

    private static void createProject(String lang, String projectName, String type) {
        File currentFile = new File(System.getProperty("user.dir"));
        String Classes = "";
        String MainClass = "";
        String Output = "";
        String mainClass = "";

        if (lang.equals(Turkce.lang)) {
            Classes = Turkce.Classes;
            MainClass = Turkce.MainClass;
            Output = Turkce.Output;
            mainClass = "anafonk() yazdır(\"Merhaba\");";
        }
        if (lang.equals(English.lang)) {
            Classes = English.Classes;
            MainClass = English.MainClass;
            Output = English.Output;
            mainClass = "mainfunc() print(\"Hello\");";
        }
        if (lang.equals(Deutsch.lang)) {
            Classes = Deutsch.Classes;
            MainClass = Deutsch.MainClass;
            Output = Deutsch.Output;
            mainClass = "hauptfunk() drucken(\"Hallo\");";
        }

        Path projectFile = Paths.get(currentFile.getPath(), projectName);
        Path hakaFile = Paths.get(projectFile.toString(), "hak.a");
        Path classFile = Paths.get(projectFile.toString(), Classes);
        Path mainClassFile = Paths.get(projectFile.toString(), Classes, MainClass + ".a");
        Path outputFile = Paths.get(projectFile.toString(), Output);

        try {
            if (!Files.exists(projectFile)) {
                Files.createDirectory(projectFile);
                Files.createDirectory(classFile);
                Files.createDirectory(outputFile);
                Files.write(hakaFile, Arrays.asList(createHaka(lang, projectName, type, classFile.toString(), MainClass, outputFile.toString())), StandardCharsets.UTF_8);
                Files.write(mainClassFile, Arrays.asList(mainClass), StandardCharsets.UTF_8);
                Messages.projectCreated(lang);
            } else Messages.fileAlreadyExistsError(lang, projectFile.toString());
        } catch (IOException e) {
            Messages.projectFileNotCreatedError(lang, e.getMessage());
        }
    }

    private static String fileToString(File file) {
        String fileText = "";
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            while (scanner.hasNextLine()) {
                fileText += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            Messages.end(e.getMessage());
        }
        return fileText;
    }

    private static void deleteFiles(File file) {
        try {
            if (file.isFile()) {
                Files.delete(file.toPath());
            } else {
                for (File _file : file.listFiles()) {
                    if (_file.isFile()) {
                        Files.delete(_file.toPath());
                    } else {
                        if (_file.listFiles().length > 0) {
                            deleteFiles(_file);
                        } else {
                            Files.delete(_file.toPath());
                        }
                    }
                }
                Files.delete(file.toPath());
            }
        } catch (IOException e) {
            Messages.fileNotDeletedError(e);
        }
    }

    private static ArrayList<File> fileList(File projectFolder) {
        if (projectFolder.exists()) {
            ArrayList<File> fileList = new ArrayList<>();
            for (File file : projectFolder.listFiles()) {
                if (file.isDirectory()) {
                    if (file.list().length > 0) {
                        fileList.addAll(fileList(file));
                    }
                } else if (file.getName().endsWith(".a")) {
                    fileList.add(file);
                }
            }
            return fileList;
        }
        Messages.fileNotFoundError(projectFolder);
        return null;
    }

    private static String creatableTempFolder() {
        if (Files.notExists(Paths.get(output))) {
            try {
                Files.createDirectory(Paths.get(output));
            } catch (IOException e) {
                Messages.fileCreationError(e);
            }
        }
        File aRandomFolder;
        int i = 0;
        while (true) {
            aRandomFolder = new File(output, "A_" + i);
            if (!aRandomFolder.exists()) break;
            i++;
        }
        return aRandomFolder.toString();
    }

    private static void compileJava(List files) {
        try {
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> compilationResults = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, StandardCharsets.UTF_8);
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(tempFolder));
            CompilationTask compilationTask = javaCompiler.getTask(null, fileManager, compilationResults, Arrays.asList("-encoding", "utf8"), null, files);
            boolean compiled = compilationTask.call();
            int totalError = 0;
            for (Diagnostic compilationResult : compilationResults.getDiagnostics()) {
                if (compilationResult.getKind().toString().equals("ERROR")) {
                    System.out.println(((ClassObject) compilationResult.getSource()).classs + ".a: " + compilationResult.getLineNumber() + " => " + compilationResult.getMessage(null));
                    totalError++;
                }
            }
            Messages.compilationResult(totalError, compiled);
        } catch (IOException e) {
            Messages.javaCompilationError(e);
        }
    }

    private static void createTempFolder() {
        try {
            Files.createDirectory(tempFolder.toPath());
        } catch (IOException e) {
            Messages.tempFolderNotCreatedError(e);
        }
    }

    private static void deleteIfExists(String file) {
        if (Files.exists(Paths.get(file))) deleteFiles(new File(file));
    }

    private static String starToString(String text, ArrayList<String> quotesList) {
        String key = text.substring(1, text.length() - 1);
        if (text.startsWith("*") && text.endsWith("*") && key.startsWith("\"\"")) {
            String[] keyList = key.split(":");
            return quotesList.get(Integer.parseInt(keyList[1]));
        }
        return text;
    }
}