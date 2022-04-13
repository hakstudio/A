package MainClasses;

import Langs.A;
import SubClasses.Match;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Parse {
    private HashMap<Integer, Integer> bracket = new HashMap<>();
    private HashMap<Integer, Integer> curlyBracket = new HashMap<>();
    private HashMap<Integer, Integer> quotes = new HashMap<>();
    private HashMap<Integer, Integer> sComments = new HashMap<>();
    private HashMap<Integer, Integer> mComments = new HashMap<>();
    public ArrayList<ArrayList<String>> mainList = new ArrayList<>();
    public ArrayList<Object> bracketList = new ArrayList<>();
    public ArrayList<Object> curlyBracketList = new ArrayList<>();
    public ArrayList<String> quotesList = new ArrayList<>();
    private boolean worked = false;

    private final File file;
    private final Match match;

    public Parse(File file, String classs) {
        this.file = file;
        match = new Match(file);
        while (true) {
            int cb = contains(bracketList, A.chars);
            int ccb = contains(curlyBracketList, A.chars);
            if (!worked) {
                bracket = match.match("(", ")", classs);
                curlyBracket = match.match("{", "}", classs);
                quotes = match.match("\"", "\"", classs);
                sComments = match.match("//", "\n", classs);
                mComments = match.match("/*", "*/", classs);
                mainList = parse(classs);
                worked = true;
            } else if (cb > -1 && bracketList.get(cb).getClass().getName() == "java.lang.String") {
                String code = (String) bracketList.get(cb);
                bracket = match.match("(", ")", code);
                curlyBracket = match.match("{", "}", code);
                quotes = match.match("\"", "\"", code);
                sComments = match.match("//", "\n", code);
                mComments = match.match("/*", "*/", code);
                bracketList.set(cb, parse(code));
            } else if (ccb > -1 && curlyBracketList.get(ccb).getClass().getName() == "java.lang.String") {
                String code = (String) curlyBracketList.get(ccb);
                bracket = match.match("(", ")", code);
                curlyBracket = match.match("{", "}", code);
                quotes = match.match("\"", "\"", code);
                sComments = match.match("//", "\n", code);
                mComments = match.match("/*", "*/", code);
                curlyBracketList.set(ccb, parse(code));
            } else break;
        }
    }

    private int contains(ArrayList<Object> codeList, ArrayList<String> chars) {
        for (String charr : chars) {
            for (int i = 0; i < codeList.size(); i++) {
                if (codeList.get(i).getClass().getName() == "java.lang.String") {
                    String kod = (String) codeList.get(i);
                    if (kod.contains(charr)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private ArrayList<ArrayList<String>> parse(String code) {
        if (code.length() == 0) Messages.fileEmptyError(file);
        ArrayList<String> codeArrayList = new ArrayList<>();
        ArrayList<ArrayList<String>> codesArrayList = new ArrayList<>();
        String lastChar = code.substring(code.length() - 1);
        String item = "";
        for (int i = 1; i <= code.length(); i++) {
            String charr = code.substring(i - 1, i);
            switch (charr) {
                case " ":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.SPACE);
                    item = "";
                    break;
                case "(":
                    if (item.length() > 0) {
                        codeArrayList.add(item);
                        item = "";
                    }
                    item += code.substring(i, bracket.get(i)) + ";";
                    i = bracket.get(i) + 1;
                    String bkey = "*():" + bracketList.size() + "*";
                    bracketList.add(item);
                    codeArrayList.add(bkey);
                    item = "";
                    break;
                case "{":
                    if (item.length() > 0) {
                        codeArrayList.add(item);
                        item = "";
                    }
                    item += code.substring(i, curlyBracket.get(i)) + ";";
                    i = curlyBracket.get(i) + 1;
                    String cbkey = "*{}:" + curlyBracketList.size() + "*";
                    curlyBracketList.add(item);
                    codeArrayList.add(cbkey);
                    item = "";
                    codesArrayList.add(codeArrayList);
                    codeArrayList = new ArrayList<>();
                    break;
                case "\"":
                    item += code.substring(i, quotes.get(i));
                    i = quotes.get(i) + 1;
                    String qkey = "*\"\":" + quotesList.size() + "*";
                    quotesList.add(item);
                    codeArrayList.add(qkey);
                    item = "";
                    break;
                case ".":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.DOT);
                    item = "";
                    break;
                case ":":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.COLON);
                    item = "";
                    break;
                case "=":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.EQUAL);
                    item = "";
                    break;
                case ">":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.GREATER);
                    item = "";
                    break;
                case "<":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.LESS);
                    item = "";
                    break;

                case "+":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.PLUS);
                    item = "";
                    break;
                case "-":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.MINUS);
                    item = "";
                    break;
                case "*":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.TIMES);
                    item = "";
                    break;
                case "/":
                    if (code.charAt(i) == '/') i = sComments.get(i - 1);
                    else if (code.charAt(i) == '*') i = mComments.get(i - 1) + 2;
                    else {
                        if (item.length() > 0) codeArrayList.add(item);
                        codeArrayList.add(A.DIVISION);
                        item = "";
                    }
                    break;
                case "&":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.AND);
                    item = "";
                    break;
                case "|":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.OR);
                    item = "";
                    break;
                case "[":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.OPENSQUARE);
                    item = "";
                    break;
                case "]":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.CLOSESQUARE);
                    item = "";
                    break;
                case "\n":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.NEWLINE);
                    item = "";
                    break;
                case "\t":
                    if (item.length() > 0) codeArrayList.add(item);
                    codeArrayList.add(A.TAB);
                    item = "";
                    break;
                case ",":
                    if (item.length() > 0) {
                        codeArrayList.add(item);
                        item = "";
                    }
                    codeArrayList.add(A.COMMA);
                    codesArrayList.add(codeArrayList);
                    codeArrayList = new ArrayList<>();
                    break;
                case ";":
                    if (item.length() > 0) {
                        codeArrayList.add(item);
                        item = "";
                    }
                    codeArrayList.add(A.SEMICOLON);
                    codesArrayList.add(codeArrayList);
                    codeArrayList = new ArrayList<>();
                    break;

                default:
                    if (i == code.length()) {
                        codeArrayList.add(item + lastChar);
                        item = "";
                    } else {
                        item += charr;
                    }
                    break;
            }
        }
        return codesArrayList;
    }
}