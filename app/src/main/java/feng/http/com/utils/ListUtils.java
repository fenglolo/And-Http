package feng.http.com.utils;

import java.util.List;

/**
 * List 工具类
 * @author feng
 */
public class ListUtils {
    public static <D> boolean isEmpty(List<D> list) {
        return list == null || list.isEmpty();
    }
}
