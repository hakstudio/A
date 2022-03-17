package Langs.OtherLangs;

import SubClasses.LangList;
import Langs.A;

public class Java {
    public static final String lang = "Java";
    public static final String extension = ".java";

    public static final LangList list = new LangList();

    static {
        list.addBDTO("boolean", A.TRUTH,"Boolean");
        list.addBDTO("byte", A.BYTE,"Byte");
        list.addBDTO("short", A.SHORT,"Short");
        list.addBDTO("int", A.INT,"Integer");
        list.addBDTO("long", A.LONG,"Long");
        list.addBDTO("float", A.DECIMAL,"Float");
        list.addBDTO("double", A.NUMBER,"Double");
        list.addBDTO("char", A.CHARACTER,"Character");
        list.addBDTO("String", A.TEXT,"String");
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
        list.add("static", A.STATIC);
        list.add("void", A.FUNC);
        list.add("new", A.NEW);
        list.add("public static void main", A.MAINFUNC);
        list.add("package", A.PACKAGE);
        list.add("import", A.USE);
        list.add("System.console().readLine", A.READ_CONSOLE);
        list.add("System.exit", A.EXIT_CONSOLE);

        list.add("for", A.REPEAT_LOOP);
        list.add("for",A.TRAVEL_LOOP);
        list.add("while", A.CONDITIONAL_LOOP);

        list.add("System.out.println", A.PRINT_WITH_LINE);
        list.add("System.out.print", A.PRINT_WITHOUT_LINE);

        list.add("length()", A.LENGTH);
        list.add("length", A.INDEX_SIZE);
        list.add("size()", A.SIZE);
        list.add("add", A.ADD_LIST);
        list.add("put", A.ADD_DICTIONARY);
        list.add("get", A.GET);
        list.add("remove",A.REMOVE);
        list.add("equals", A.EQUALS);
        list.add("substring", A.SEPARATE);

        list.add("ArrayList", A.LIST, "java.util.ArrayList");
        list.add("HashMap", A.DICTIONARY, "java.util.HashMap");
        list.add("new Random().nextInt", A.RANDOM, "java.util.Random");

        list.add("parseInt",A.TRANSLATE);
        list.add("final",A.CONST);
        list.add("break",A.BREAK);

        list.add("'",A.CHARACTER_QUOTES);
        list.add("\"",A.TEXT_QUOTES);

        list.add("Arrays.asList",A.ARRAY,"java.util.Arrays");
        list.add("null",A.NULL);
    }
}
