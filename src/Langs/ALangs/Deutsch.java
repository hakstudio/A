package Langs.ALangs;

import SubClasses.LangList;
import Langs.A;

public class Deutsch {
    public static final String lang = "Deutsch";
    public static final String Console = "Konsole";
    public static final String Mobile = "Mobile";
    public static final String Lang ="Sprache";
    public static final String ProjectName ="ProjektName";
    public static final String Type ="Typ";
    public static final String Classes ="Klassen";
    public static final String MainClass ="HauptKlasse";
    public static final String Output ="Ausgabe";
    public static final String Copy ="Kopieren";

    public static final LangList list = new LangList();

    static {
        list.addBDTA("Wahrheit", A.TRUTH);
        list.addBDTA("Byte", A.BYTE);
        list.addBDTA("Kurz", A.SHORT);
        list.addBDTA("Int", A.INT);
        list.addBDTA("Lang", A.LONG);
        list.addBDTA("Dezimal", A.DECIMAL);
        list.addBDTA("Nummer", A.NUMBER);
        list.addBDTA("Charakter", A.CHARACTER);
        list.addBDTA("Text", A.TEXT);
        list.add("Objekt", A.OBJECT);

        list.add("klasse", A.CLASS);
        list.add("wahr", A.TRUE);
        list.add("falsch", A.FALSE);
        list.add("rückkehr", A.RETURN);
        list.add("wenn", A.IF);
        list.add("sonst", A.ELSE);
        list.add("öffentlich", A.PUBLIC);
        list.add("privat", A.PRIVATE);
        list.add("geschützt", A.PROTECTED);
        list.addException("statisch", A.STATIC);
        list.add("funk", A.FUNC);
        list.add("neu", A.NEW);
        list.addException("hauptfunk", A.MAINFUNC);
        list.addException("drucken", A.PRINT);
        list.add("paket", A.PACKAGE);
        list.addException("nutzen", A.USE);
        list.addException("init",A.INIT);
        list.add("lesenKonsole", A.READ_CONSOLE);
        list.addException("verlassKonsole", A.EXIT_CONSOLE);

        list.add("länge", A.LENGTH);
        list.add("indexGröße", A.INDEX_SIZE);
        list.add("größe", A.SIZE);
        list.addException("hinzufügen", A.ADD);
        list.addException("holen", A.GET);
        list.add("entfernen",A.REMOVE);
        list.add("gleich", A.EQUALS);
        list.add("trennen", A.SEPARATE);

        list.add("Liste", A.LIST);
        list.add("Wörterbuch", A.DICTIONARY);
        list.addException("zufalls", A.RANDOM);

        list.addException("schleife", A.LOOP);
        list.add("übersetzen",A.TRANSLATE);
        list.add("konst",A.CONST);
        list.add("brechen",A.BREAK);

        list.add("beitreten",A.JOIN);

        list.add("try",A.TRY);
        list.add("fangen",A.CATCH);
        list.add("Fehler",A.ERROR);
        list.add("null",A.NULL);
    }
}