package SubClasses;

import MainClasses.Messages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Match {
    boolean dontRemoveBwChars = false;
    private final File file;
    private final HashMap<Integer, Integer> curlyBrackets = new HashMap<>();

    public Match(File file) {
        this.file = file;
    }

    public HashMap<Integer, Integer> match(String chr1, String chr2, String code) {
        if (chr1.length() > 0 && chr2.length() > 0 && code.length() > 0) {
            if (!chr1.equals(chr2) && !chr1.equals("\"")) {
                if (!dontRemoveBwChars) code = removeBwChars("\"", "\"", code);
                code = removeBackslash(chr1, code);
                code = removeBackslash(chr2, code);
                int openNum = howMany(chr1, code);
                int closeNum = howMany(chr2, code);
                if (openNum == closeNum) {
                    HashMap<Integer, Integer> indexMap = new HashMap<>();
                    int lastOpen = code.length();
                    int lastClose;
                    for (int i = 0; i < openNum; i++) {
                        lastOpen = code.lastIndexOf(chr1, lastOpen);
                        while (indexMap.containsKey(lastOpen + 1)) {
                            lastOpen--;
                            lastOpen = code.lastIndexOf(chr1, lastOpen);
                        }
                        lastClose = code.indexOf(chr2, lastOpen);
                        while (indexMap.containsValue(lastClose)) {
                            lastClose++;
                            lastClose = code.indexOf(chr2, lastClose);
                        }
                        indexMap.put(lastOpen + 1, lastClose);
                    }
                    if (chr1.equals("{") && chr2.equals("}")) curlyBrackets.putAll(indexMap);
                    return indexMap;
                }
                Messages.matchingError(file, chr1, chr2);
                return null;
            } else {
                if (howMany("\"", code) % 2 == 0) {
                    dontRemoveBwChars = true;
                    code = removeBwChars("{", "}", code);
                    dontRemoveBwChars = false;
                    ArrayList<Integer> quotesList = new ArrayList<>();
                    HashMap<Integer, Integer> quotesMap = new HashMap<>();
                    for (int i = 0; i < code.length(); i++) {
                        if (code.charAt(i) == '"') {
                            if (i > 0) {
                                if (code.charAt(i - 1) != '\\') {
                                    quotesList.add(i);
                                }
                            } else {
                                quotesList.add(i);
                            }
                        }
                    }
                    for (int i = 0; i < quotesList.size(); i += 2) {
                        quotesMap.put(quotesList.get(i) + 1, quotesList.get(i + 1));
                    }
                    return quotesMap;
                }
                Messages.matchingError(file, chr1, chr2);
            }
        }
        return new HashMap<>();
    }

    private String removeBwChars(String chr1, String chr2, String code) {
        HashMap<Integer, Integer> matchChars = match(chr1, chr2, code);
        removeExists(matchChars, curlyBrackets);
        Integer[] openChr = matchChars.keySet().toArray(new Integer[matchChars.size()]);
        Integer[] closeChr = matchChars.values().toArray(new Integer[matchChars.size()]);
        while (matchChars.size() > 0) {
            int _openChr = openChr[0] - 1;
            int _closeChr = closeChr[0] + 1;
            code = code.substring(0, _openChr) + createSpace(_closeChr - _openChr) + code.substring(_closeChr);
            matchChars = match(chr1, chr2, code);
            openChr = matchChars.keySet().toArray(new Integer[matchChars.size()]);
            closeChr = matchChars.values().toArray(new Integer[matchChars.size()]);
        }
        return code;
    }

    private String removeBackslash(String chr, String code) {
        for (int i = 0; i < code.length() - 1; i++) {
            String chrs = code.substring(i, i + 2);
            String target = "\\" + chr;
            if (chrs.equals(target))
                code = code.substring(0, i) + createSpace(target.length()) + code.substring(i + target.length());

        }
        return code;
    }

    private Integer howMany(String chr, String code) {
        int count = 0;
        if (chr.equals("\"")) {
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == '"') {
                    if (i > 0) {
                        if (code.charAt(i - 1) != '\\') {
                            count++;
                        }
                    } else {
                        count++;
                    }
                }
            }
        } else {
            if (!dontRemoveBwChars) code = removeBwChars("\"", "\"", code);
            for (int i = 0; i < code.length(); i++) {
                if (code.substring(i, i + 1).equals(chr)) {
                    count++;
                }
            }
        }

        return count;
    }

    private String createSpace(Integer count) {
        String space = "";
        for (int i = 0; i < count; i++) {
            space += " ";
        }
        return space;
    }

    private void removeExists(HashMap<Integer, Integer> hm1, HashMap<Integer, Integer> hm2) {
        Integer[] h1keys = hm1.keySet().toArray(new Integer[hm1.size()]);
        Integer[] h2keys = hm2.keySet().toArray(new Integer[hm2.size()]);
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 0; i < hm1.size(); i++)
            for (int j = 0; j < hm2.size(); j++)
                if (h1keys[i].equals(h2keys[j])) keys.add(h1keys[i]);
        for (int key : keys) hm1.remove(key);
    }
} 
