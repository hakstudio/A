package MainClasses;

import SubClasses.LangList;
import SubClasses.Match;
import Langs.A;
import Langs.OtherLangs.CSharp;
import Langs.OtherLangs.Java;
import Langs.ALangs.Deutsch;
import Langs.ALangs.English;
import Langs.ALangs.Turkce;
//import AltSiniflar.Java;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Compile {
    private final String aCode;
    private final ArrayList<ArrayList<String>> mainList;
    private final ArrayList<Object> bracketList;
    private final ArrayList<Object> curlyBracketList;
    private final ArrayList<String> quotesList;
    public String code = "";
    public ArrayList<String> libs = new ArrayList<>();
    private final File file;
    private final Match match;
    private LangList aLangsList;
    private LangList otherLangsList;
    private static final String lang = Main.lang;
    private final String projectType = Main.projectType;
    public String libsStr = "";
    private final ArrayList<String> funcs;
    private String className = "";
    public String pcClass = "";

    public Compile(File file, String aCode, ArrayList<ArrayList<String>> mainList, ArrayList<Object> bracketList, ArrayList<Object> curlyBracketList, ArrayList<String> quotesList) {
        this.file = file;
        match = new Match(file);
        this.aCode = aCode;
        this.mainList = mainList;
        this.bracketList = bracketList;
        this.curlyBracketList = curlyBracketList;
        this.quotesList = quotesList;
        setLists();
        funcs = new ArrayList<>(Arrays.asList(aLangsList.get(A.MAINFUNC), aLangsList.get(A.LOOP), aLangsList.get(A.IF), aLangsList.get(A.CATCH), aLangsList.get(A.INIT)));
        className = file.getName();
        className = className.substring(0, className.length() - 2);
        final String _className = className;
        compileCBs();
        compile(mainList);
        if (!A.langs.contains(projectType)) {
            if (!containsClass(mainList)) {
                pcClass = otherLangsList.get(A.PUBLIC) + " " + otherLangsList.get(A.CLASS) + " " + _className;
                code = pcClass + "{\n" + code + "\n}";
            }
            libs = deleteRepeat(libs);
            for (String lib : libs) libsStr += otherLangsList.get(A.USE) + " " + lib + ";";
            if (libsStr.length() > 0) code = libsStr + "\n" + code;
            File classes = new File(Main.classes);
            if (!file.getParentFile().equals(classes)) {
                String _package = file.toString().substring(classes.toString().length() + 1);
                _package = _package.substring(0, _package.length() - 2);
                _package = _package.replaceAll("\\\\", ".");
                _package = _package.replaceAll("/", ".");
                ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(_package.split("\\.")));
                if (arrayList.size() > 1) {
                    _package = "";
                    for (int i = 0; i < arrayList.size() - 1; i++) {
                        _package += arrayList.get(i);
                        if (i < arrayList.size() - 2) _package += ".";
                    }
                }
                code = otherLangsList.get(A.PACKAGE) + " " + _package + ";\n" + code;
            }
        }
    }

    private void compile(Object arrayList) {
        if (arrayList.getClass().getName() == "java.util.ArrayList") {
            ArrayList<ArrayList<String>> mainArray = (ArrayList<ArrayList<String>>) arrayList;
            for (int i = 0; i < mainArray.size(); i++) {
                ArrayList<String> wordArray = mainArray.get(i);
                for (Integer[] j = new Integer[]{0}; j[0] < wordArray.size(); j[0]++) {
                    String word = wordArray.get(j[0]);
                    if (word.startsWith("*") && word.endsWith("*")) {
                        String key = word.substring(1, word.length() - 1);
                        if (A.chars.contains(key)) {
                            code = code.concat(key);
                        } else if (key.equals("IN")) {
                            code = code.concat(" in ");
                        } else {
                            if (key.startsWith("()")) {
                                String[] keyList = key.split(":");
                                code = code.concat("(");
                                compile(bracketList.get(Integer.parseInt(keyList[1])));
                                code = code.substring(0, code.length() - 1);
                                code = code.concat(")");
                                Integer lastIndex = wordArray.size() - 1;
                                int previousFromLast = lastIndex - 1;
                                while (A.spaces.contains(wordArray.get(previousFromLast))) previousFromLast--;
                                if (!(j[0] == 0 || j[0] == previousFromLast || j[0] == lastIndex || A.langs.contains(projectType))) {
                                    int previous = j[0] - 1;
                                    while (A.spaces.contains(wordArray.get(previous))) previous--;
                                    if (funcs.contains(wordArray.get(previous))) {
                                        int next = j[0] + 1;
                                        while (A.spaces.contains(wordArray.get(next))) next++;
                                        ArrayList<String> subList = new ArrayList<>(wordArray.subList(next, lastIndex + 1));
                                        subList.add(";");
                                        j[0] = lastIndex;
                                        String cbkey = "*{}:" + curlyBracketList.size() + "*";
                                        curlyBracketList.add(new ArrayList<>(Arrays.asList(subList)));
                                        mainArray.set(i, new ArrayList<>(wordArray.subList(0, next)));
                                        wordArray.add(cbkey);
                                    } else if (j[0] > 1) {
                                        int previous2 = previous - 1;
                                        while (A.spaces.contains(wordArray.get(previous2))) previous2--;
                                        if (aLangsList.containsBDT(wordArray.get(previous2)) || wordArray.get(previous2).equals(aLangsList.get(A.FUNC))) {
                                            int next = j[0] + 1;
                                            while (A.spaces.contains(wordArray.get(next))) next++;
                                            ArrayList<String> subList = new ArrayList<>(wordArray.subList(next, lastIndex + 1));
                                            subList.add(";");
                                            j[0] = lastIndex;
                                            String cbkey = "*{}:" + curlyBracketList.size() + "*";
                                            curlyBracketList.add(new ArrayList<>(Arrays.asList(subList)));
                                            mainArray.set(i, new ArrayList<>(wordArray.subList(0, next)));
                                            wordArray.add(cbkey);
                                        }
                                    }
                                }
                            }
                            if (key.startsWith("{}")) {
                                String[] keyList = key.split(":");
                                code = code.concat("{");
                                compile(curlyBracketList.get(Integer.parseInt(keyList[1])));
                                if (code.endsWith(";")) code = code.substring(0, code.length() - 1);
                                while (code.endsWith(";;")) code = code.substring(0, code.length() - 1);
                                code = code.concat("}");
                            }
                            if (key.startsWith("\"\"")) {
                                String[] keyList = key.split(":");
                                String value = quotesList.get(Integer.parseInt(keyList[1]));
                                String quotes;
                                String charQuotes = A.langs.contains(projectType) ? "\"" : otherLangsList.get(A.CHARACTER_QUOTES);
                                String textQuotes = A.langs.contains(projectType) ? "\"" : otherLangsList.get(A.TEXT_QUOTES);
                                String textFirst = aLangsList.get(A.TEXT).substring(0, 1).toLowerCase();
                                if (wordArray.get(j[0] + 1).toLowerCase().equals(textFirst)) {
                                    quotes = textQuotes;
                                    wordArray.remove(j[0] + 1);
                                } else if (value.toCharArray().length == 1) {
                                    quotes = charQuotes;
                                } else if (value.toCharArray().length == 2 && value.charAt(0) == '\\') {
                                    quotes = charQuotes;
                                } else {
                                    quotes = textQuotes;
                                }
                                code = code.concat(quotes + value + quotes);
                            }
                        }
                    } else if (aLangsList.containsException(word)) {
                        String exception = getException(wordArray, j, word);
                        code = code.concat(exception);
                        if (!exception.equals("")) {
                            int codeNum = -1;
                            if (aLangsList.contains(exception)) codeNum = aLangsList.get(exception);
                            if (aLangsList.contains(word)) codeNum = aLangsList.get(word);
                            if (otherLangsList.contains(exception)) codeNum = otherLangsList.get(exception);
                            if (otherLangsList.contains(word)) codeNum = otherLangsList.get(word);
                            if (codeNum == -1) codeNum = otherLangsList.get(exception); //Error
                            if (otherLangsList.containsLib(codeNum)) libs.add(otherLangsList.getLib(codeNum));
                        }
                    } else if (aLangsList.contains(word)) {
                        int codeNum = aLangsList.get(word);
                        code = code.concat(otherLangsList.get(codeNum));
                        if (otherLangsList.containsLib(codeNum)) libs.add(otherLangsList.getLib(codeNum));
                        if (word.equals(aLangsList.get(A.CLASS))) {
                            int next = j[0] + 1;
                            while (A.spaces.contains(wordArray.get(next))) next++;
                            className = wordArray.get(next);
                        }
                    } else if (!otherLangsList.contains(word)) code = code.concat(lastCheck(word));
                    else
                        Messages.forbiddenWordError(file, indexToLine(aCode, aCode.indexOf(word)), word);
                }
            }
        }
    }

    private ArrayList<String> deleteRepeat(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            String mainIndex = arrayList.get(i);
            for (int j = i + 1; j < arrayList.size(); j++) {
                String index = arrayList.get(j);
                if (mainIndex.equals(index)) {
                    arrayList.remove(j);
                    j--;
                }
            }
        }
        return arrayList;
    }

    private String removeBackslash(String chr, String code) {
        for (int i = 0; i < code.length() - 1; i++) {
            String chrs = code.substring(i, i + 2);
            String target = "\\" + chr;
            if (chrs.equals(target)) {
                code = code.substring(0, i) + chr + code.substring(i + target.length());
            }
        }
        return code;
    }

    private String compileCB(String code) {
        HashMap<Integer, Integer> curlyBracked = match.match("{", "}", code);
        Integer[] openCB = curlyBracked.keySet().toArray(new Integer[curlyBracked.size()]);
        Integer[] closeCB = curlyBracked.values().toArray(new Integer[curlyBracked.size()]);
        while (curlyBracked.size() > 0) {
            code = code.substring(0, openCB[0] - 1) + "\"+(" + compileCode(code.substring(openCB[0], closeCB[0])) + ")+\"" + code.substring(closeCB[0] + 1);
            curlyBracked = match.match("{", "}", code);
            openCB = curlyBracked.keySet().toArray(new Integer[curlyBracked.size()]);
            closeCB = curlyBracked.values().toArray(new Integer[curlyBracked.size()]);
        }
        code = removeBackslash("{", code);
        code = removeBackslash("}", code);
        return code;
    }

    private void compileCBs() {
        for (int i = 0; i < quotesList.size(); i++) {
            quotesList.set(i, compileCB(quotesList.get(i)));
        }
    }

    private String getException(ArrayList<String> wordArray, Integer[] j, String exception) {
        if (exception.equals(aLangsList.get(A.LOOP))) return otherLangsList.get(setLoop(wordArray, j[0]));
        if (exception.equals(aLangsList.get(A.PRINT))) return otherLangsList.get(setPrint(wordArray, j[0]));
        if (exception.equals(aLangsList.get(A.ADD))) return otherLangsList.get(setAdd(wordArray, j[0]));
        if (exception.equals(aLangsList.get(A.MAINFUNC))) return setMainfunc(wordArray, j[0]);
        if (exception.equals(aLangsList.get(A.EXIT_CONSOLE))) return setExitConsole(wordArray, j[0]);
        if (exception.equals(aLangsList.get(A.RANDOM))) return setRandom(wordArray, j[0]);
        if (exception.equals(aLangsList.get(A.GET))) return setGet(wordArray, j);
        if (exception.equals(aLangsList.get(A.INIT))) return setInit();
        if (exception.equals(aLangsList.get(A.STATIC))) return setStatic(wordArray, j[0]);
        if (exception.equals(aLangsList.get(A.USE))) return setUse(wordArray, j[0]);
        if (aLangsList.containsBDT(exception)) return setBDT(wordArray, j[0], exception);
        return Messages.exceptionNotFoundError(file, exception);
    }

    private Integer setLoop(ArrayList<String> wordArray, Integer j) {
        if (A.langs.contains(projectType)) return A.LOOP;
        ArrayList<ArrayList> bracketArray = (ArrayList<ArrayList>) bracketList.get(getBrackedIndex(wordArray, j, A.LOOP));
        ArrayList bracket = new ArrayList(bracketArray.get(0));
        bracket.removeAll(A.spaces);
        if (bracket.size() == 1)
            bracketArray.set(0, new ArrayList(Arrays.asList(aLangsList.get(A.TRUE), A.SEMICOLON)));
        int semicolon = 0;
        int colon = 0;
        for (ArrayList<String> array : bracketArray)
            for (String str : array) {
                if (str.equals(A.SEMICOLON)) semicolon++;
                if (str.equals(A.COLON)) colon++;
            }
        if (semicolon == 1 && colon == 0) return A.CONDITIONAL_LOOP;
        if (semicolon == 3 && colon == 0) return A.REPEAT_LOOP;
        if (semicolon == 1 && colon == 1) {
            int index = (int) getIndex(bracketArray.get(0), A.COLON).get(0);
            if (A.Console.contains(projectType) || projectType.equals(Java.lang)) {
                return A.REPEAT_LOOP;
            }
            if (projectType.equals(CSharp.lang)) {
                bracketArray.get(0).set(index, "*IN*");
                return A.TRAVEL_LOOP;
            }
        }
        Messages.loopTypeNotFoundError(file);
        return null;
    }

    private Integer setPrint(ArrayList<String> wordArray, Integer j) {
        if (A.langs.contains(projectType)) return A.PRINT;
        ArrayList<ArrayList> bracketArray = (ArrayList<ArrayList>) bracketList.get(getBrackedIndex(wordArray, j, A.PRINT));
        ArrayList bracket = new ArrayList(bracketArray.get(0));
        bracket.removeAll(A.spaces);
        if (bracket.size() == 1)
            bracketArray.set(0, new ArrayList(Arrays.asList("\"\"", A.SEMICOLON)));
        int comma = 0;
        for (ArrayList<String> _wordArray : bracketArray)
            for (String str : _wordArray) if (str.equals(A.COMMA)) comma++;
        if (comma == 0) {
            bracketArray.get(0).removeAll(A.spaces);
            return A.PRINT_WITH_LINE;
        }
        if (comma == 1) {
            ArrayList<String> index0 = (ArrayList<String>) bracketArray.get(0);
            ArrayList<String> index1 = (ArrayList<String>) bracketArray.get(1);
            index0.removeAll(A.spaces);
            index1.removeAll(A.spaces);
            while (bracketArray.size() > 0) bracketArray.remove(0);
            ArrayList<String> newArray = new ArrayList<>();
            newArray.addAll(index0.subList(0, index0.size() - 1));
            newArray.add(A.PLUS);
            newArray.addAll(index1.subList(0, index1.size() - 1));
            newArray.add(A.SEMICOLON);
            bracketArray.add(newArray);
            return A.PRINT_WITHOUT_LINE;
        }
        Messages.printTypeNotFoundError(file);
        return null;
    }

    private Integer setAdd(ArrayList<String> wordArray, Integer j) {
        if (A.langs.contains(projectType)) return A.ADD;
        ArrayList<ArrayList> bracketArray = (ArrayList<ArrayList>) bracketList.get(getBrackedIndex(wordArray, j, A.ADD));
        int comma = 0;
        for (ArrayList<String> _wordArray : bracketArray)
            for (String str : _wordArray) if (str.equals(A.COMMA)) comma++;
        if (comma == 0) return A.ADD_LIST;
        if (comma == 1) return A.ADD_DICTIONARY;
        Messages.addTypeNotFoundError(file);
        return null;
    }

    private String setMainfunc(ArrayList<String> wordArray, Integer j) {
        if (A.langs.contains(projectType)) return otherLangsList.get(A.MAINFUNC);
        ArrayList<ArrayList> bracketArray = (ArrayList<ArrayList>) bracketList.get(getBrackedIndex(wordArray, j, A.MAINFUNC));
        String text = aLangsList.get(A.TEXT);
        ArrayList bracket = new ArrayList(bracketArray.get(0));
        bracket.removeAll(A.spaces);
        if (!bracket.containsAll(Arrays.asList(text, A.OPENSQUARE, A.CLOSESQUARE, A.SEMICOLON)) || bracket.size() <= 4)
            bracketArray.set(0, new ArrayList(Arrays.asList(text, A.OPENSQUARE, A.CLOSESQUARE, "args", A.SEMICOLON)));
        return otherLangsList.get(A.MAINFUNC);
    }

    private String setExitConsole(ArrayList<String> wordArray, Integer j) {
        if (A.langs.contains(projectType)) return otherLangsList.get(A.EXIT_CONSOLE);
        ArrayList<ArrayList> bracketArray = (ArrayList<ArrayList>) bracketList.get(getBrackedIndex(wordArray, j, A.EXIT_CONSOLE));
        ArrayList bracket = new ArrayList(bracketArray.get(0));
        bracket.removeAll(A.spaces);
        if (bracket.size() == 1)
            bracketArray.set(0, new ArrayList(Arrays.asList("0", A.SEMICOLON)));
        return otherLangsList.get(A.EXIT_CONSOLE);
    }

    private String setRandom(ArrayList<String> wordArray, Integer j) {
        if (A.langs.contains(projectType)) return otherLangsList.get(A.RANDOM);
        int bracketIndex = getBrackedIndex(wordArray, j, A.RANDOM);
        ArrayList<ArrayList> bracketArray = (ArrayList<ArrayList>) bracketList.get(bracketIndex);
        int comma = 0;
        for (ArrayList<String> _wordArray : bracketArray)
            for (String str : _wordArray) if (str.equals(A.COMMA)) comma++;
        if (comma == 0) bracketArray.get(0).removeAll(A.spaces);
        int min = 0;
        if (comma == 1) {
            ArrayList<String> index0 = (ArrayList<String>) bracketArray.get(0);
            ArrayList<String> index1 = (ArrayList<String>) bracketArray.get(1);
            index0.removeAll(A.spaces);
            index1.removeAll(A.spaces);
            min = Integer.parseInt(index0.get(0));
            int max = Integer.parseInt(index1.get(0));
            while (bracketArray.size() > 0) bracketArray.remove(0);
            bracketArray.add(new ArrayList<>(Arrays.asList(String.valueOf(max - min + 1), A.SEMICOLON)));
        }
        String key = "*():" + bracketIndex + "*";
        if (min > 0) {
            int itemIndex = wordArray.indexOf(key) + 1;
            wordArray.add(itemIndex, String.valueOf(min));
            wordArray.add(itemIndex, A.PLUS);
        }
        return otherLangsList.get(A.RANDOM);
    }

    private String setGet(ArrayList<String> wordArray, Integer[] j) {
        if (A.langs.contains(projectType)) return otherLangsList.get(A.GET);
        if (A.Console.contains(projectType) || projectType.equals(Java.lang)) return otherLangsList.get(A.GET);
        ArrayList<String> quotes = ((ArrayList<ArrayList>) bracketList.get(getBrackedIndex(wordArray, j[0], A.GET))).get(0);
        if (projectType.equals(CSharp.lang)) {
            code = code.substring(0, code.length() - 1);
            wordArray.remove(j[0] - 1);
            wordArray.remove(j[0] - 1);
            wordArray.remove(j[0] - 1);
            wordArray.add(j[0] - 1, A.OPENSQUARE);
            wordArray.addAll(j[0], quotes.subList(0, quotes.size() - 1));
            wordArray.add(j[0] + quotes.size() - 1, A.CLOSESQUARE);
            j[0] -= 2;
        }
        return "";
    }

    private String setInit() {
        if (A.langs.contains(projectType)) return otherLangsList.get(A.INIT);
        return otherLangsList.get(A.PUBLIC) + " " + className;
    }

    private String setStatic(ArrayList<String> wordArray, Integer j) {
        if (A.langs.contains(projectType)) return otherLangsList.get(A.STATIC);
        int next = j + 1;
        while (A.spaces.contains(wordArray.get(next))) next++;
        if (A.Console.contains(projectType) || projectType.equals(Java.lang) || (projectType.equals(CSharp.lang) && !wordArray.get(next).equals(aLangsList.get(A.CONST))))
            return otherLangsList.get(A.STATIC);
        return "";
    }

    private String setUse(ArrayList<String> wordArray, Integer j) {
        if (A.langs.contains(projectType)) return otherLangsList.get(A.USE);
        if (projectType.equals(CSharp.lang)) {
            int next1 = j + 3;
            while (A.spaces.contains(wordArray.get(next1))) next1++;
            int next2 = j + 4;
            while (A.spaces.contains(wordArray.get(next2))) next2++;
            if (wordArray.get(next1).equals(A.DOT) && wordArray.get(next2).equals(A.TIMES)) {
                wordArray.remove(next1);
                wordArray.remove(next1);
            }
        }
        return otherLangsList.get(A.USE);
    }

    private String setBDT(ArrayList<String> wordArray, Integer j, String dataType) {
        if (A.langs.contains(projectType)) return otherLangsList.get(aLangsList.get(dataType));
        String lastBDT = otherLangsList.get(aLangsList.get(dataType));
        for (Integer i : getIndex(wordArray, dataType))
            if (i == j && (getFromArray(wordArray, i + 1).equals(A.DOT) || getFromArray(wordArray, i + 1).equals(A.GREATER)))
                return otherLangsList.getClass(lastBDT);
        return lastBDT;
    }

    private String compileCode(String code) {
        code += ";";
        Parse parse = new Parse(file, code);
        Compile compile = new Compile(file, code, parse.mainList, parse.bracketList, parse.curlyBracketList, parse.quotesList);
        code = compile.code;
        int pc = -1;
        if (!compile.pcClass.equals("")) pc = code.indexOf(compile.pcClass);
        if (pc != -1) code = code.substring(pc);
        if (code.startsWith(compile.pcClass)) code = code.substring(compile.pcClass.length() + 2, code.length() - 3);
        else code = code.substring(0, code.length() - 1);
        libs.addAll(compile.libs);
        return code;
    }

    private Integer indexToLine(String aCode, Integer index) {
        ArrayList<Integer> lines = new ArrayList<>();
        for (int i = 0; i < aCode.length(); i++) if (aCode.charAt(i) == '\n') lines.add(i);
        for (int i = 0; i < lines.size(); i++) if (index < lines.get(i)) return i + 1;
        return lines.size() + 1;
    }

    private void setLists() {
        if (lang.equals(Turkce.lang)) aLangsList = Turkce.list;
        if (lang.equals(English.lang)) aLangsList = English.list;
        if (lang.equals(Deutsch.lang)) aLangsList = Deutsch.list;

        if (A.Console.contains(projectType)) otherLangsList = Java.list;
        if (projectType.equals(Java.lang)) otherLangsList = Java.list;
        if (projectType.equals(CSharp.lang)) otherLangsList = CSharp.list;
        if (projectType.equals(Turkce.lang)) otherLangsList = Turkce.list;
        if (projectType.equals(English.lang)) otherLangsList = English.list;
        if (projectType.equals(Deutsch.lang)) otherLangsList = Deutsch.list;
    }

    private Integer keyToIndex(String keyWithStar) {
        String key = keyWithStar.substring(1, keyWithStar.length() - 1);
        String[] keyArray = key.split(":");
        return Integer.parseInt(keyArray[1]);
    }

    private boolean containsClass(ArrayList<ArrayList<String>> mainList) {
        String classs = "";
        if (lang.equals(Turkce.lang)) classs = Turkce.list.get(A.CLASS);
        if (lang.equals(English.lang)) classs = English.list.get(A.CLASS);
        if (lang.equals(Deutsch.lang)) classs = Deutsch.list.get(A.CLASS);
        for (ArrayList<String> arrayList : mainList) if (arrayList.contains(classs)) return true;
        return false;
    }

    private ArrayList<Integer> getIndex(ArrayList<String> array, String str) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) if (array.get(i).equals(str)) index.add(i);
        return index;
    }

    private String getFromArray(ArrayList<String> array, Integer index) {
        while (A.spaces.contains(array.get(index))) index++;
        return array.get(index);
    }

    private Integer getBrackedIndex(ArrayList<String> wordArray, int j, int value) {
        ArrayList<Integer> indexArray = getIndex(wordArray, aLangsList.get(value));
        int bracketIndex = -1;
        for (Integer i : indexArray) if (i == j) bracketIndex = keyToIndex(getFromArray(wordArray, i + 1));
        if (bracketIndex > -1) return bracketIndex;
        else Messages.itemsNotMatchingError(file);
        return null;
    }

    private String lastCheck(String word) {
        String chr = "";
        if (word.length() > 0) chr = word.substring(0, word.length() - 1);
        if (chr.length() > 0) {
            try {
                Double.parseDouble(chr);
                String decimalFirst = aLangsList.get(A.DECIMAL).substring(0, 1).toLowerCase();
                if (word.substring(word.length() - 1).toLowerCase().equals(decimalFirst)) {
                    return chr + otherLangsList.get(A.DECIMAL).substring(0, 1).toLowerCase();
                }
            } catch (Exception e) {
            }
        }
        return word;
    }
}