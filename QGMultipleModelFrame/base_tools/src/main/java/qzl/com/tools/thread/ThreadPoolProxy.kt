package qzl.com.tools.thread

import java.util.concurrent.*

/**
 * @author 强周亮
 * @desc 线程池代理
 * @email 2538096489@qq.com
 * @time 2018-09-18 10:16
 * @class hzz
 */
class ThreadPoolProxy
/**
 * @param corePoolSize    核心池的大小
 * @param maximumPoolSize 最大线程数
 */
    (private val mCorePoolSize: Int, private val mMaximumPoolSize: Int) {

    internal var mExecutor: ThreadPoolExecutor? = null

    /**
     * 初始化ThreadPoolExecutor
     * 双重检查加锁,只有在第一次实例化的时候才启用同步机制,提高了性能
     */
    fun initThreadPoolExecutor() {
        if (mExecutor == null || mExecutor?.isShutdown == true || mExecutor?.isTerminated == true) {
            synchronized(ThreadPoolProxy::class.java) {
                if (mExecutor == null || mExecutor?.isShutdown == true || mExecutor?.isTerminated == true) {
                    val keepAliveTime: Long = 3000
                    val unit = TimeUnit.MILLISECONDS
                    val workQueue = LinkedBlockingDeque<Runnable>()
                    val threadFactory = Executors.defaultThreadFactory()
                    val handler = ThreadPoolExecutor.DiscardPolicy()

                    mExecutor = ThreadPoolExecutor(
                        mCorePoolSize, mMaximumPoolSize, keepAliveTime, unit, workQueue,
                        threadFactory, handler
                    )
                }
            }
        }
    }
    /**
     * 执行任务和提交任务的区别?
     * 1.有无返回值
     * execute->没有返回值
     * submit-->有返回值
     * 2.Future的具体作用?
     * 1.有方法可以接收一个任务执行完成之后的结果,其实就是get方法,get方法是一个阻塞方法
     * 2.get方法的签名抛出了异常===>可以处理任务执行过程中可能遇到的异常
     */
    /**
     * 执行任务
     */
    fun execute(task: Runnable) {
        initThreadPoolExecutor()
        mExecutor?.execute(task)
    }

/**
     * 提交任务
     */
    fun submit(task: Runnable): Future<*>? {

        if (mExecutor != null){
            initThreadPoolExecutor()
            return mExecutor!!.submit(task)
        }else {
            return null
        }
    }

    /**
     * 移除任务
     */
    fun remove(task: Runnable) {
        initThreadPoolExecutor()
        mExecutor?.remove(task)
    }
}
