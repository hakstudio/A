package MainClasses;

import Langs.ALangs.Deutsch;
import Langs.ALangs.English;
import Langs.ALangs.Turkce;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Messages {
    private static final String lang = Main.lang;

    public static void end(String message) {
        System.out.println(message);
        System.exit(0);
    }

    public static void forbiddenWordError(File file, Integer line, String word) {
        if (lang.equals(Turkce.lang))
            end(file.toString() + ": " + line + ": " + "Yasak Kelime Hatası: " + word);
        if (lang.equals(English.lang))
            end(file.toString() + ": " + line + ": " + "Forbidden Word Error: " + word);
        if (lang.equals(Deutsch.lang))
            end(file.toString() + ": " + line + ": " + "Fehler Beim Verbotenen Wort: " + word);

    }

    public static void matchingError(File file, String k1, String k2) {
        if (Objects.equals(lang, null))
            end(file.toString() + ": Eşleştirme Hatası|Matching Error|Passenden Fehler: " + k1 + " " + k2);
        if (lang.equals(Turkce.lang)) end(file.toString() + ": Eşleştirme Hatası: " + k1 + " " + k2);
        if (lang.equals(English.lang)) end(file.toString() + ": Matching Error: " + k1 + " " + k2);
        if (lang.equals(Deutsch.lang)) end(file.toString() + ": Passenden Fehler: " + k1 + " " + k2);
    }

    public static void fileNotFoundError(File file) {
        if (lang.equals(Turkce.lang)) end("Dosya Bulunamadı Hatası: " + file);
        if (lang.equals(English.lang)) end("File Not Found Error: " + file);
        if (lang.equals(Deutsch.lang)) end("Datei Nicht Gefunden Fehler: " + file);
    }

    public static void langNotFoundError() {
        System.out.println("Dil Bulunamadı Hatası");
        System.out.println("Language Not Found Error");
        System.out.println("Sprache Nicht Gefunden Fehler");
        System.exit(0);
    }

    public static void classesNotFoundError() {
        if (lang.equals(Turkce.lang)) end("Sınıflar Bulunamadı Hatası");
        if (lang.equals(English.lang)) end("Classes Not Found Error");
        if (lang.equals(Deutsch.lang)) end("Klassen Nicht Gefunden Fehler");
    }

    public static void projectNameNotFoundError() {
        if (lang.equals(Turkce.lang)) end("ProjeAdı Bulunamadı Hatası");
        if (lang.equals(English.lang)) end("ProjectName Not Found Error");
        if (lang.equals(Deutsch.lang)) end("ProjektName Nicht Gefunden Fehler");
    }

    public static void mainClassNotFoundError() {
        if (lang.equals(Turkce.lang)) end("AnaSınıf Bulunamadı Hatası");
        if (lang.equals(English.lang)) end("MainClass Not Found Error");
        if (lang.equals(Deutsch.lang)) end("HauptKlasse Nicht Gefunden Fehler");
    }

    public static void outputNotFoundError() {
        if (lang.equals(Turkce.lang)) end("Çıktı Bulunamadı Hatası");
        if (lang.equals(English.lang)) end("Output Not Found Error");
        if (lang.equals(Deutsch.lang)) end("Ausgabe Nicht Gefunden Fehler");
    }

    public static void typeNotFoundError() {
        if (lang.equals(Turkce.lang)) end("Tür Bulunamadı Hatası");
        if (lang.equals(English.lang)) end("Type Not Found Error");
        if (lang.equals(Deutsch.lang)) end("Typ Nicht Gefunden Fehler");
    }

    public static void compilationResult(Integer totalError, Boolean compiled) {
        if (lang.equals(Turkce.lang)) {
            if (totalError > 0) System.out.println("Toplam Hata: " + totalError);
            if (compiled) System.out.println("Derleme Başarılı");
            else end("Derleme Başarısız");
        }
        if (lang.equals(English.lang)) {
            if (totalError > 0) System.out.println("Total Error: " + totalError);
            if (compiled) System.out.println("Compilation Successful");
            else end("Compilation Failed");
        }
        if (lang.equals(Deutsch.lang)) {
            if (totalError > 0) System.out.println("Totaler Fehler: " + totalError);
            if (compiled) System.out.println("Kompilieren Erfolgreich");
            else end("Kompilieren Fehlgeschlagen");
        }
    }

    public static void jarCreationError(IOException e) {
        if (lang.equals(Turkce.lang)) end("Jar Oluşturma Hatası: " + e.getMessage());
        if (lang.equals(English.lang)) end("Jar Creation Error: " + e.getMessage());
        if (lang.equals(Deutsch.lang)) end("Jar Erstellen Fehler: " + e.getMessage());
    }

    public static void fileCreationError(IOException e) {
        if (lang.equals(Turkce.lang)) end("Dosya Oluşturma Hatası: " + e.getMessage());
        if (lang.equals(English.lang)) end("File Creation Error: " + e.getMessage());
        if (lang.equals(Deutsch.lang)) end("Datei Erstellen Fehler: " + e.getMessage());
    }

    public static void langChangeError(IOException e) {
        if (lang.equals(Turkce.lang)) end("Dil Değiştirme Hatası: " + e.getMessage());
        if (lang.equals(English.lang)) end("Language Change Error: " + e.getMessage());
        if (lang.equals(Deutsch.lang)) end("Sprache Ändern Fehler: " + e.getMessage());
    }

    public static void createdSuccessfully(String type, String filePath) {
        if (lang.equals(Turkce.lang)) System.out.println("Başarıyla Oluşturuldu: " + type + ": " + filePath);
        if (lang.equals(English.lang)) System.out.println("Created Successfully: " + type + ": " + filePath);
        if (lang.equals(Deutsch.lang)) System.out.println("Erfolgreich Erstellt: " + type + ": " + filePath);
    }

    public static void langChangedSuccessfully(String lang) {
        if (lang.equals(Turkce.lang)) end("Dil Başarıyla Değiştirildi");
        if (lang.equals(English.lang)) end("Language Changed Successfully");
        if (lang.equals(Deutsch.lang)) end("Sprache Erfolgreich Geändert");
    }

    public static void fileNotDeletedError(IOException e) {
        if (lang.equals(Turkce.lang)) end("Dosya Silinemedi Hatası: " + e.getMessage());
        if (lang.equals(English.lang)) end("File Not Deleted Error: " + e.getMessage());
        if (lang.equals(Deutsch.lang)) end("Datei Nicht Gelöscht Fehler: " + e.getMessage());
    }

    public static void javaCompilationError(IOException e) {
        if (lang.equals(Turkce.lang)) end("Java Derleme Hatası: " + e.getMessage());
        if (lang.equals(English.lang)) end("Java Compilation Error: " + e.getMessage());
        if (lang.equals(Deutsch.lang)) end("Java Kompilieren Fehler: " + e.getMessage());
    }

    public static void tempFolderNotCreatedError(IOException e) {
        if (lang.equals(Turkce.lang)) end("Geçici Klasör Oluşturulamadı Hatası: " + e.getMessage());
        if (lang.equals(English.lang)) end("Temporary Folder Not Created Error: " + e.getMessage());
        if (lang.equals(Deutsch.lang)) end("Temporärer Ordner Nicht Erstellt Fehler: " + e.getMessage());
    }

    public static void loopTypeNotFoundError(File file) {
        if (lang.equals(Turkce.lang)) end(file.toString() + ": Döngü Türü Bulunamadı Hatası: ");
        if (lang.equals(English.lang)) end(file.toString() + ": Loop Type Not Found Error: ");
        if (lang.equals(Deutsch.lang)) end(file.toString() + ": Schleife Typ Nicht Gefunden Fehler: ");
    }

    public static void printTypeNotFoundError(File file) {
        if (lang.equals(Turkce.lang)) end(file.toString() + ": Yazdır Türü Bulunamadı Hatası: ");
        if (lang.equals(English.lang)) end(file.toString() + ": Print Type Not Found Error: ");
        if (lang.equals(Deutsch.lang)) end(file.toString() + ": Drucken Typ Nicht Gefunden Fehler: ");
    }

    public static void addTypeNotFoundError(File file) {
        if (lang.equals(Turkce.lang)) end(file.toString() + ": Ekle Türü Bulunamadı Hatası: ");
        if (lang.equals(English.lang)) end(file.toString() + ": Add Type Not Found Error: ");
        if (lang.equals(Deutsch.lang)) end(file.toString() + ": Hinzufügen Typ Nicht Gefunden Fehler: ");
    }

    public static void itemsNotMatchingError(File file) {
        if (lang.equals(Turkce.lang)) end(file.toString() + ": Öğeler Eşleşmiyor Hatası");
        if (lang.equals(English.lang)) end(file.toString() + ": Items Not Matching Error");
        if (lang.equals(Deutsch.lang)) end(file.toString() + ": Artikel Nicht Passenden Fehler");
    }

    public static String exceptionNotFoundError(File file, String exception) {
        if (lang.equals(Turkce.lang)) end(file.toString() + ": İstisna Bulunamadı Hatası: " + exception);
        if (lang.equals(English.lang)) end(file.toString() + ": Exception Not Found Error: " + exception);
        if (lang.equals(Deutsch.lang)) end(file.toString() + ": Ausnahme Nicht Gefunden Fehler: " + exception);
        return null;
    }

    public static String fileEmptyError(File file) {
        if (lang.equals(Turkce.lang)) end(file.toString() + ": Dosya Boş Hatası");
        if (lang.equals(English.lang)) end(file.toString() + ": File Empty Error");
        if (lang.equals(Deutsch.lang)) end(file.toString() + ": Datei Leer Fehler");
        return null;
    }

    public static String projectCreated(String lang) {
        if (lang.equals(Turkce.lang)) end("Proje Oluşturuldu");
        if (lang.equals(English.lang)) end("Project Created");
        if (lang.equals(Deutsch.lang)) end("Projekt Erstellt");
        return null;
    }

    public static String projectFileNotCreatedError(String lang, String message) {
        if (lang.equals(Turkce.lang)) end("Proje Dosyası Oluşturulamadı Hatası: " + message);
        if (lang.equals(English.lang)) end("Project File Not Created Error: " + message);
        if (lang.equals(Deutsch.lang)) end("Projekt Datei Nicht Erstellt Fehler: " + message);
        return null;
    }

    public static String fileAlreadyExistsError(String lang, String file) {
        if (lang.equals(Turkce.lang)) end("Dosya Zaten Var Hatası: " + file);
        if (lang.equals(English.lang)) end("File Already Exists Error: " + file);
        if (lang.equals(Deutsch.lang)) end("Datei Existiert Bereits Fehler: " + file);
        return null;
    }

    public static String langsSameError(String lang) {
        if (lang.equals(Turkce.lang)) end("Diller Aynı Hatası: Türkçe => Türkçe");
        if (lang.equals(English.lang)) end("Languages Same Error: English => English");
        if (lang.equals(Deutsch.lang)) end("Sprachen Gleich Fehler: Deutsch => Deutsch");
        return null;
    }

    public static void usage() {
        System.out.println("Kullanımı|Usage|Nutzung");
        System.out.println();
        System.out.println("Türkçe:");
        System.out.println("------");
        System.out.println("Proje Oluştur: <Dil> <ProjeAdı> <Tür>");
        System.out.println("Çalıştır: <hak.a>");
        System.out.println("Dil Değiştir: <hak.a> <YeniDil>");
        System.out.println("------");
        System.out.println();
        System.out.println("English:");
        System.out.println("------");
        System.out.println("Create Project: <Language> <ProjectName> <Type>");
        System.out.println("Run: <hak.a>");
        System.out.println("Change Language: <hak.a> <NewLanguage>");
        System.out.println("------");
        System.out.println();
        System.out.println("Deutsch:");
        System.out.println("------");
        System.out.println("Projekt Erstellen: <Sprache> <ProjektName> <Typ>");
        System.out.println("Ausführen: <hak.a>");
        System.out.println("Sprache Ändern: <hak.a> <NeueSprache>");
        System.out.println("------");
    }

    public static void incorrectUsage() {
        System.out.println("Hatalı Kullanım");
        System.out.println("Incorrect Usage");
        System.out.println("Falsch Nutzung");
        System.exit(0);
    }
}