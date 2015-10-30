package app.games.commons.utils;

import java.util.Collection;

/**
 * @author abogaichuk
 */
public class GamesUtils {

    public static boolean isEmpty(Collection list) {
        return !isNotEmpty(list);
    }

    public static boolean isNotEmpty(Collection list) {
        return list != null && list.size() > 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
