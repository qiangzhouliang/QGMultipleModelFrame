package qzl.com.tools.thread

/**
 * @author 强周亮
 * @desc 线程池管理
 * @email 2538096489@qq.com
 * @time 2018-09-18 10:15
 * @class hzz
 */
object ThreadPoolProxyFactory {
    var mNormalThreadPoolProxy: ThreadPoolProxy? = null
    var mDownLoadThreadPoolProxy: ThreadPoolProxy? = null

    /**
     * 得到普通线程池代理对象mNormalThreadPoolProxy
     */
    @JvmStatic
    val normalThreadPoolProxy: ThreadPoolProxy?
        get() {
            if (mNormalThreadPoolProxy == null) {
                synchronized(ThreadPoolProxyFactory::class.java) {
                    if (mNormalThreadPoolProxy == null) {
                        mNormalThreadPoolProxy = ThreadPoolProxy(5, 5)
                    }
                }
            }
            return mNormalThreadPoolProxy
        }

    /**
     * 得到下载线程池代理对象mDownLoadThreadPoolProxy
     */
    @JvmStatic
    val downLoadThreadPoolProxy: ThreadPoolProxy?
        get() {
            if (mDownLoadThreadPoolProxy == null) {
                synchronized(ThreadPoolProxyFactory::class.java) {
                    if (mDownLoadThreadPoolProxy == null) {
                        mDownLoadThreadPoolProxy = ThreadPoolProxy(3, 3)
                    }
                }
            }
            return mDownLoadThreadPoolProxy
        }
}
