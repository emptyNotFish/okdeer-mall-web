package com.okdeer.mall.utils;

import java.util.HashSet;
import java.util.Set;
/**
 * 字符串转换成数组集合工具类
 * @author Administrator
 *
 */
public class Strings {
    public static final String[] EMPTY_ARRAY = new String[0];
    public static final String FOLDER_SEPARATOR = "/";
    public static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    public static final String CURRENT_PATH = ".";
    public static final char EXTENSION_SEPARATOR = '.';
    public static Set<String> splitStringByCommaToSet(final String s) {
        return splitStringToSet(s, ',');
    }
    public static String[] splitStringByCommaToArray(final String s) {
        return splitStringToArray(s, ',');
    }
    public static Set<String> splitStringToSet(final String s, final char c) {
        final char[] chars = s.toCharArray();
        int count = 1;
        for (final char x : chars) {
            if (x == c) {
                count++;
            }
        }
        final HashSet<String> result = new HashSet<String>(count);
        final int len = chars.length;
        int start = 0; // starting index in chars of the current substring.
        int pos = 0; // current index in chars.
        for (; pos < len; pos++) {
            if (chars[pos] == c) {
                int size = pos - start;
                if (size > 0) { // only add non empty strings
                    result.add(new String(chars, start, size));
                }
                start = pos + 1;
            }
        }
        int size = pos - start;
        if (size > 0) {
            result.add(new String(chars, start, size));
        }
        return result;
    }
    public static String[] splitStringToArray(final String s, final char c) {
        if (s.length() == 0) {
            return Strings.EMPTY_ARRAY;
        }
        final char[] chars = s.toCharArray();
        int count = 1;
        for (final char x : chars) {
            if (x == c) {
                count++;
            }
        }
        final String[] result = new String[count];
        final int len = chars.length;
        int start = 0; // starting index in chars of the current substring.
        int pos = 0; // current index in chars.
        int i = 0; // number of the current substring.
        for (; pos < len; pos++) {
            if (chars[pos] == c) {
                int size = pos - start;
                if (size > 0) {
                    result[i++] = new String(chars, start, size);
                }
                start = pos + 1;
            }
        }
        int size = pos - start;
        if (size > 0) {
            result[i++] = new String(chars, start, size);
        }
        if (i != count) {
            // we have empty strings, copy over to a new array
            String[] result1 = new String[i];
            System.arraycopy(result, 0, result1, 0, i);
            return result1;
        }
        return result;
    }
    /**
     * Split a String at the first occurrence of the delimiter. Does not include
     * the delimiter in the result.
     *
     * @param toSplit the string to split
     * @param delimiter to split the string up with
     * @return a two element array with index 0 being before the delimiter, and
     * index 1 being after the delimiter (neither element includes the
     * delimiter); or <code>null</code> if the delimiter wasn't found in the
     * given input String
     */
    public static String[] split(String toSplit, String delimiter) {
        if (!hasLength(toSplit) || !hasLength(delimiter)) {
            return null;
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return null;
        }
        String beforeDelimiter = toSplit.substring(0, offset);
        String afterDelimiter = toSplit.substring(offset + delimiter.length());
        return new String[]{beforeDelimiter, afterDelimiter};
    }
    public static String toCamelCase(String value) {
        return toCamelCase(value, null);
    }
    public static String toCamelCase(String value, StringBuilder sb) {
        boolean changed = false;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '_') {
                if (!changed) {
                    if (sb != null) {
                        sb.setLength(0);
                    } else {
                        sb = new StringBuilder();
                    }
                    // copy it over here
                    for (int j = 0; j < i; j++) {
                        sb.append(value.charAt(j));
                    }
                    changed = true;
                }
                sb.append(Character.toUpperCase(value.charAt(++i)));
            } else {
                if (changed) {
                    sb.append(c);
                }
            }
        }
        if (!changed) {
            return value;
        }
        return sb.toString();
    }
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }
}

