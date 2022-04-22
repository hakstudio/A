package Langs.OtherLangs;

import SubClasses.LangList;
import Langs.A;

public class CSharp {
    public static final String lang = "CSharp";
    public static final String extension = ".cs";

    public static final LangList list = new LangList();

    static {
        list.addBDTO("bool", A.TRUTH, "bool");
        list.addBDTO("byte", A.BYTE, "byte");
        list.addBDTO("short", A.SHORT, "short");
        list.addBDTO("int", A.INT, "int");
        list.addBDTO("long", A.LONG, "long");
        list.addBDTO("float", A.DECIMAL, "float");
        list.addBDTO("double", A.NUMBER, "double");
        list.addBDTO("char", A.CHARACTER, "char");
        list.addBDTO("string", A.TEXT, "string");
        list.add("object", A.OBJECT);

        list.add("class", A.CLASS);
        list.add("true", A.TRUE);
        list.add("false", A.FALSE);
        list.add("return", A.RETURN);
        list.add("if", A.IF);
        list.add("else", A.ELSE);
        list.add("public", A.PUBLIC);
        list.add("private", A.PRIVATE);
        list.add("protected", A.PROTECTED);
        list.add("static", A.STATIC);
        list.add("void", A.FUNC);
        list.add("new", A.NEW);
        list.add("public static void Main", A.MAINFUNC);
        list.add("namespace", A.PACKAGE);
        list.add("using", A.USE);
        list.add("Console.ReadLine", A.READ_CONSOLE);
        list.add("Environment.Exit", A.EXIT_CONSOLE);

        list.add("for", A.REPEAT_LOOP);
        list.add("foreach", A.TRAVEL_LOOP);
        list.add("while", A.CONDITIONAL_LOOP);

        list.add("Console.WriteLine", A.PRINT_WITH_LINE, "System");
        list.add("Console.Write", A.PRINT_WITHOUT_LINE, "System");

        list.add("Length", A.LENGTH);
        list.add("Length", A.INDEX_SIZE);
        list.add("Count", A.SIZE);
        list.add("Add", A.ADD_LIST);
        list.add("Add", A.ADD_DICTIONARY);
        list.add("Remove", A.REMOVE);
        list.add("Equals", A.EQUALS);
        list.add("Substring", A.SEPARATE);

        list.add("'", A.CHARACTER_QUOTES);
        list.add("\"", A.TEXT_QUOTES);

        list.add("List", A.LIST, "System.Collections.Generic");
        list.add("Dictionary", A.DICTIONARY, "System.Collections.Generic");
        list.add("new Random().Next", A.RANDOM, "System");

        list.add("Parse", A.TRANSLATE);
        list.add("const", A.CONST);
        list.add("break", A.BREAK);
        list.add("null", A.NULL);

        list.add("try", A.TRY);
        list.add("catch", A.CATCH);
        list.add("Exception", A.ERROR);

        list.add("Join", A.JOIN);

        list.add(":", A.INHERIT);
        list.add("this", A.THIS);
        list.add("base", A.MAININIT);
        list.add("public override", A.OVER);

        list.add("Keys", A.KEYS);
        list.add("Values", A.VALUES);

        list.add("StartsWith", A.STARTS_WITH);
        list.add("EndsWith", A.ENDS_WITH);
        list.add("Contains", A.CONTAINS);

        list.add("throw new Exception", A.RETURN_ERROR);
        list.add("finally",A.FINALLY);
    }
}