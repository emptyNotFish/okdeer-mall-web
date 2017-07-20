package com.okdeer.mall.utils;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;

/**
 * 比较两个字符串的大小
 * @author Administrator
 *
 */
public class CollatorComparator implements Comparator<String> {
    Collator collator = Collator.getInstance();
    public int compare(String o1, String o2) {
        CollationKey key1 = collator.getCollationKey(o1);
        CollationKey key2 = collator.getCollationKey(o2);
        return key1.compareTo(key2);
    }
}

