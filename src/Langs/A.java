package Langs;

import Langs.OtherLangs.CSharp;
import Langs.OtherLangs.Java;
import Langs.ALangs.Deutsch;
import Langs.ALangs.English;
import Langs.ALangs.Turkce;

import java.util.ArrayList;
import java.util.Arrays;

public class A {
    public static final String extension = ".a";
    public static final ArrayList<String> Console = new ArrayList<>(Arrays.asList(Turkce.Console, English.Console, Deutsch.Console));
    public static final ArrayList<String> Mobile = new ArrayList<>(Arrays.asList(Turkce.Mobile, English.Mobile, Deutsch.Mobile));

    public static final ArrayList<String> langs = new ArrayList<>(Arrays.asList(Turkce.lang, English.lang, Deutsch.lang));
    public static final ArrayList<String> types = new ArrayList<>(Arrays.asList(Java.lang, CSharp.lang));
    public static final ArrayList<String> spaces = new ArrayList<>(Arrays.asList(A.SPACE, A.TAB));

    public static final ArrayList<String> chars=new ArrayList<>(Arrays.asList(" ", "(", "{", "\"", ".", ":", "=", ">", "<", "+", "-", "*", "/", "&", "|", "[", "]", "\n", ",", ";", "\t"));

    static {
        types.addAll(Console);
        types.addAll(Mobile);
    }

    public static final int TRUTH = 0;
    public static final int BYTE = 1;
    public static final int SHORT = 2;
    public static final int INT = 3;
    public static final int LONG = 4;
    public static final int DECIMAL = 5;
    public static final int NUMBER = 6;
    public static final int CHARACTER = 7;
    public static final int TEXT = 8;
    public static final int OBJECT = 9;
    public static final int CLASS = 10;
    public static final int TRUE = 11;
    public static final int FALSE = 12;
    public static final int RETURN = 13;
    public static final int IF = 14;
    public static final int ELSE = 15;
    public static final int PUBLIC = 16;
    public static final int PRIVATE = 17;
    public static final int PROTECTED = 18;
    public static final int STATIC = 19;
    public static final int FUNC = 20;
    public static final int NEW = 21;
    public static final int MAINFUNC = 22;
    public static final int PACKAGE = 23;
    public static final int USE = 24;
    public static final int INIT = 25;
    public static final int CONST = 26;
    public static final int BREAK = 27;
    public static final int NULL = 28;
    public static final int LOOP = 29;
    public static final int REPEAT_LOOP = 30;
    public static final int CONDITIONAL_LOOP = 31;
    public static final int TRAVEL_LOOP = 32;
    public static final int PRINT = 33;
    public static final int PRINT_WITH_LINE = 34;
    public static final int PRINT_WITHOUT_LINE = 35;
    public static final int READ_CONSOLE = 36;
    public static final int EXIT_CONSOLE = 37;
    public static final int TRY = 38;
    public static final int CATCH = 39;
    public static final int ERROR = 40;
    public static final int GET_MESSAGE = 41;
    public static final int LENGTH = 42;
    public static final int INDEX_SIZE = 43;
    public static final int SIZE = 44;
    public static final int ADD = 45;
    public static final int GET = 46;
    public static final int REMOVE = 47;
    public static final int EQUALS = 48;
    public static final int SEPARATE = 49;
    public static final int TRANSLATE = 50;
    public static final int JOIN = 51;
    public static final int ADD_LIST = 52;
    public static final int ADD_DICTIONARY = 53;
    public static final int LIST = 54;
    public static final int DICTIONARY = 55;
    public static final int RANDOM = 56;
    public static final int ARRAY = 57;
    public static final int CHARACTER_QUOTES = 58;
    public static final int TEXT_QUOTES = 59;

    public static final String SPACE ="* *";
    public static final String DOT ="*.*";
    public static final String COLON ="*:*";
    public static final String EQUAL ="*=*";
    public static final String GREATER ="*>*";
    public static final String LESS ="*<*";
    public static final String PLUS ="*+*";
    public static final String MINUS ="*-*";
    public static final String TIMES ="***";
    public static final String DIVISION ="*/*";
    public static final String AND ="*&*";
    public static final String OR ="*|*";
    public static final String COMMA ="*,*";
    public static final String SEMICOLON ="*;*";
    public static final String OPENSQUARE ="*[*";
    public static final String CLOSESQUARE ="*]*";
    public static final String NEWLINE ="*\n*";
    public static final String TAB="*\t*";
}