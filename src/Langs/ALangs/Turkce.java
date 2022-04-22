package Langs.ALangs;

import SubClasses.LangList;
import Langs.A;

public class Turkce {
    public static final String lang = "Türkçe";
    public static final String Console = "Konsol";
    public static final String Mobile = "Mobil";
    public static final String Lang ="Dil";
    public static final String ProjectName ="ProjeAdı";
    public static final String Type ="Tür";
    public static final String Classes ="Sınıflar";
    public static final String MainClass ="AnaSınıf";
    public static final String Output ="Çıktı";
    public static final String Copy ="Kopya";

    public static final LangList list = new LangList();

    static {
        list.addBDTA("Doğruluk", A.TRUTH);
        list.addBDTA("Bayt", A.BYTE);
        list.addBDTA("Kısa", A.SHORT);
        list.addBDTA("Tam", A.INT);
        list.addBDTA("Uzun", A.LONG);
        list.addBDTA("Ondalık", A.DECIMAL);
        list.addBDTA("Sayı", A.NUMBER);
        list.addBDTA("Karakter", A.CHARACTER);
        list.addBDTA("Metin", A.TEXT);
        list.add("Obje", A.OBJECT);

        list.add("sınıf", A.CLASS);
        list.add("doğru", A.TRUE);
        list.add("yanlış", A.FALSE);
        list.add("döndür", A.RETURN);
        list.add("eğer", A.IF);
        list.add("olmazsa", A.ELSE);
        list.add("genel", A.PUBLIC);
        list.add("özel", A.PRIVATE);
        list.add("korumalı", A.PROTECTED);
        list.addException("statik", A.STATIC);
        list.add("fonk", A.FUNC);
        list.add("yeni", A.NEW);
        list.addException("anafonk", A.MAINFUNC);
        list.addException("yazdır", A.PRINT);
        list.add("paket", A.PACKAGE);
        list.addException("kullan", A.USE);
        list.addException("başlatıcı",A.INIT);
        list.add("konsolOku", A.READ_CONSOLE);
        list.addException("konsolÇık", A.EXIT_CONSOLE);

        list.add("uzunluğu", A.LENGTH);
        list.add("elemanSayısı", A.INDEX_SIZE);
        list.add("boyutu", A.SIZE);
        list.addException("ekle", A.ADD);
        list.addException("getir", A.GET);
        list.add("kaldır",A.REMOVE);
        list.add("eşitse", A.EQUALS);
        list.add("ayır", A.SEPARATE);

        list.add("Liste", A.LIST);
        list.add("Sözlük", A.DICTIONARY);
        list.addException("rastgele", A.RANDOM);

        list.addException("döngü", A.LOOP);
        list.add("çevir",A.TRANSLATE);
        list.add("sabit",A.CONST);
        list.add("kır",A.BREAK);

        list.add("birleştir",A.JOIN);

        list.add("dene",A.TRY);
        list.add("yakala",A.CATCH);
        list.add("Hata",A.ERROR);
        list.add("boş",A.NULL);

        list.add("miras",A.INHERIT);
        list.add("bu",A.THIS);
        list.add("anabaşlatıcı",A.MAININIT);
        list.add("üzerine",A.OVER);

        list.add("anahtarlar",A.KEYS);
        list.add("değerler",A.VALUES);

        list.add("başlıyorsa",A.STARTS_WITH);
        list.add("bitiyorsa",A.ENDS_WITH);
        list.add("içeriyorsa",A.CONTAINS);

        list.add("hataDöndür",A.RETURN_ERROR);
        list.add("sonunda",A.FINALLY);
    }
}