package Langs.ALangs;

import SubClasses.LangList;
import Langs.A;

public class English {
    public static final String lang = "English";
    public static final String Console = "Console";
    public static final String Mobile = "Mobile";
    public static final String Lang ="Language";
    public static final String ProjectName ="ProjectName";
    public static final String Type ="Type";
    public static final String Classes ="Classes";
    public static final String MainClass ="MainClass";
    public static final String Output ="Output";
    public static final String Copy ="Copy";

    public static final LangList list = new LangList();

    static {
        list.addBDTA("Truth", A.TRUTH);
        list.addBDTA("Byte", A.BYTE);
        list.addBDTA("Short", A.SHORT);
        list.addBDTA("Int", A.INT);
        list.addBDTA("Long", A.LONG);
        list.addBDTA("Decimal", A.DECIMAL);
        list.addBDTA("Number", A.NUMBER);
        list.addBDTA("Character", A.CHARACTER);
        list.addBDTA("Text", A.TEXT);
        list.add("Object", A.OBJECT);

        list.add("class", A.CLASS);
        list.add("true", A.TRUE);
        list.add("false", A.FALSE);
        list.add("return", A.RETURN);
        list.add("if", A.IF);
        list.add("else", A.ELSE);
        list.add("public", A.PUBLIC);
        list.add("private", A.PRIVATE);
        list.add("protected", A.PROTECTED);
        list.addException("static", A.STATIC);
        list.add("func", A.FUNC);
        list.add("new", A.NEW);
        list.addException("mainfunc", A.MAINFUNC);
        list.addException("print", A.PRINT);
        list.add("package", A.PACKAGE);
        list.addException("use", A.USE);
        list.addException("init",A.INIT);
        list.add("readConsole", A.READ_CONSOLE);
        list.addException("exitConsole", A.EXIT_CONSOLE);

        list.add("length", A.LENGTH);
        list.add("indexSize", A.INDEX_SIZE);
        list.add("size", A.SIZE);
        list.addException("add", A.ADD);
        list.addException("get", A.GET);
        list.add("remove",A.REMOVE);
        list.add("equals", A.EQUALS);
        list.add("separate", A.SEPARATE);

        list.add("List", A.LIST);
        list.add("Dictionary", A.DICTIONARY);
        list.addException("random", A.RANDOM);

        list.addException("loop", A.LOOP);
        list.add("translate",A.TRANSLATE);
        list.add("const",A.CONST);
        list.add("break",A.BREAK);

        list.add("join",A.JOIN);

        list.add("try",A.TRY);
        list.add("catch",A.CATCH);
        list.add("Error",A.ERROR);
        list.add("null",A.NULL);

        list.add("inherit",A.INHERIT);
        list.add("this",A.THIS);
        list.add("maininit",A.MAININIT);
        list.add("over",A.OVER);

        list.add("keys",A.KEYS);
        list.add("values",A.VALUES);

        list.add("startsWith",A.STARTS_WITH);
        list.add("endsWith",A.ENDS_WITH);
        list.add("contains",A.CONTAINS);

        list.add("returnError",A.RETURN_ERROR);
        list.add("finally",A.FINALLY);
    }
}