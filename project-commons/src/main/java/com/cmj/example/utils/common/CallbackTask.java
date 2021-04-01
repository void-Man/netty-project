package com.cmj.example.utils.common;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/10/8
 */
public interface CallbackTask<R> {

    /**
     * 业务逻辑方法
     *
     * @param
     * @return R
     * @author mengjie_chen
     * @date 2020/10/8
     */
    R execute() throws Exception;

    /**
     * 回调方法
     *
     * @param r
     * @return void
     * @author mengjie_chen
     * @date 2020/10/8
     */
    void onBack(R r);

    /**
     * 异常方法
     *
     * @param t
     * @return void
     * @author mengjie_chen
     * @date 2020/10/8
     */
    void onException(Throwable t);
}
