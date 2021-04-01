package com.cmj.example.utils.common;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/11
 */
public class CommonUtils {

    /**
     * 随机生成UUID
     *
     * @param
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2020/10/11
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isNullOrEmpty(Collection<? extends Object> userSessionList) {
        return Objects.isNull(userSessionList) || userSessionList.size() == 0;
    }

    public static Long randomLongId() {
        long bits, val;
        do {
            bits = (new Random().nextLong() << 1) >>> 1;
            val = bits % 9999999999999999L;
        } while (bits - val + (9999999999999999L - 1) < 0L);

        return val;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(CommonUtils.randomLongId());
        }
    }

    public static <T> List<List<T>> subList(List<T> sourceList, int batchSize) {
        List<List<T>> list = new ArrayList<>(10);
        int sourceSize = sourceList.size();
        int batch = sourceSize / batchSize + (sourceSize % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < batch; i++) {
            List<T> subList = sourceList.subList(i * batchSize, (i == batch - 1) ? sourceSize : i * batchSize + batchSize);
            list.add(subList);
        }
        return list;
    }

    /**
     * 除数运算，保留2位小数四舍五入
     *
     * @param v1 除数
     * @param v2 被除数
     * @return java.math.BigDecimal
     * @author mengjie_chen
     * @date 2020/12/13
     */
    public static BigDecimal div(String v1, String v2) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除以100
     *
     * @param v1 除数
     * @return java.math.BigDecimal
     * @author mengjie_chen
     * @date 2020/12/13
     */
    public static BigDecimal div100(String v1) {
        return new BigDecimal(v1).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
    }
}
