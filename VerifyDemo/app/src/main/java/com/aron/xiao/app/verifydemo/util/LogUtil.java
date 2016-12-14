package com.aron.xiao.app.verifydemo.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 日志记录工具类
 * 在Release签名状态下命令行开启日志开关
 * @Description  Wrapper API for sending log output.
 * @author kuangbiao
 * @date 2014-4-24 下午3:59:58 
 * @version V1.3.1
 */
public class LogUtil
{
    private static final String TAG_TW = "TW";
    
    //日志文件最大尺寸: 10M
    public static int MAX_LOG_FILE_SIZE = 1024 * 1024 * 10;
    
    //默认日志级别 为debug，发布时改成ERR0R
    private static int logLevel = LogLevel.DEBUG;
    
    //是否将日志记录文件(默认否),日志记录文件是一个比较耗时的操作
    private static boolean writeFile = false;
    
    //日志文件绝对路径
    private static String logFilePath = "";
    
    private static FileWriter fw;
    
    public static interface LogLevel
    {
        public final int VERBOSE = Log.VERBOSE;
        
        public final int DEBUG = Log.DEBUG;
        
        public final int INFO = Log.INFO;
        
        public final int WARN = Log.WARN;
        
        public final int ERROR = Log.ERROR;
        
        public final int CLOSE = Log.ASSERT;
    }
    
    public LogUtil()
    {
    }
    
    public static int getLogLevel()
    {
        return logLevel;
    }
    
    public static void setLogLevel(int logLevel)
    {
        LogUtil.logLevel = logLevel;
    }
    
    public static void setWriteFile(boolean writeFile, String logFilePath)
    {
        LogUtil.writeFile = writeFile;
        LogUtil.logFilePath = logFilePath;
        
        if (LogUtil.writeFile && !TextUtils.isEmpty(LogUtil.logFilePath))
        {
            if (fw == null)
            {
                try
                {
                    File logFile = new File(LogUtil.logFilePath);
                    if (logFile.exists()
                            && logFile.length() > MAX_LOG_FILE_SIZE)
                    {
                        //                        logFile.delete();
                        File newFile = new File(logFile.getParent() + "/"
                                + logFile.getName() + "-"
                                + System.currentTimeMillis());
                        logFile.renameTo(newFile);
                        //                        boolean create = logFile.createNewFile();
                        //                        android.util.Log.i(TAG,
                        //                                "logFile too big and createNewFile == "
                        //                                        + create);
                    }
                    
                    if (!logFile.exists())
                    {
                        File parent = new File(logFile.getParent());
                        if (!parent.isDirectory())
                        {
                            parent.mkdirs();
                        }
                        logFile.createNewFile();
                    }
                    
                    fw = new FileWriter(new File(LogUtil.logFilePath), true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    LogUtil.writeFile = false;
                    fw = null;
                    LogUtil.logFilePath = null;
                }
            }
        }
        else
        {
            if (fw != null)
            {
                try
                {
                    fw.close();
                    fw = null;
                    LogUtil.logFilePath = null;
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static String getLogFilePath()
    {
        return logFilePath;
    }
    
    public static void v(boolean on, String TAG, String msg)
    {
        if (!on)
            return;
        v(TAG, msg);
    }
    
    /**
     * Send a VERBOSE log message.
     * @param msg The message you would like logged.
     */
    public static void v(String msg)
    {
        if (logLevel > LogLevel.VERBOSE)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.v(TAG_TW, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void v(String TAG, String msg)
    {
        if (logLevel > LogLevel.VERBOSE)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.v(TAG, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void destroy()
    {
        if (null != fw)
        {
            try
            {
                fw.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void writeLog2file(String msg)
    {
        //如果文件或者目录不存在，先创建
        File logFile = new File(logFilePath);
        if (!logFile.exists())
        {
            File parent = new File(logFile.getParent());
            if (!parent.isDirectory())
            {
                parent.mkdirs();
            }

            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        if (fw == null)
        {
            try
            {
                fw = new FileWriter(new File(logFilePath), true);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return;
            }
        }

        try
        {
            fw.append(msg).append("\n");
            fw.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Send a VERBOSE log message and log the exception.
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void v(String msg, Throwable thr)
    {
        if (logLevel > LogLevel.VERBOSE)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.v(TAG_TW, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void v(String TAG, String msg, Throwable thr)
    {
        if (logLevel > LogLevel.VERBOSE)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.v(TAG, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    /**
     * Send a DEBUG log message.
     * @param msg
     */
    public static void d(String msg)
    {
        if (logLevel > LogLevel.DEBUG)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.d(TAG_TW, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);
        }
    }

    public static void d(boolean on, String TAG, String msg)
    {
        if (!on)
            return;
        d(TAG, msg);
    }

    public static void d(String TAG, String msg)
    {
        //指定日志级别未开启
        if (logLevel > LogLevel.DEBUG)
        {
            //如果系统开启对应级别日志则打印
            if(Log.isLoggable(TAG_TW, Log.DEBUG))
            {
                android.util.Log.d(TAG_TW, msg);
            }
            return;
        }
        String logMsg = buildMessage(msg);
        android.util.Log.d(TAG, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    /**
     * Send a DEBUG log message and log the exception.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    public static void d(String msg, Throwable thr)
    {
        if (logLevel > LogLevel.DEBUG)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.d(TAG_TW, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void d(String TAG, String msg, Throwable thr)
    {
        if (logLevel > LogLevel.DEBUG)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.d(TAG, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    /**
     * Send an INFO log message.
     * @param msg The message you would like logged.
     */
    public static void i(String msg)
    {
        if (logLevel > LogLevel.INFO)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.i(TAG_TW, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void i(boolean on, String TAG, String msg)
    {
        if (!on)
            return;
        i(TAG, msg);
    }

    public static void i(String TAG, String msg)
    {
        if (logLevel > LogLevel.INFO)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.i(TAG, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    /**
     * Send a INFO log message and log the exception.
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void i(String msg, Throwable thr)
    {
        if (logLevel > LogLevel.INFO)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.i(TAG_TW, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void i(String TAG, String msg, Throwable thr)
    {
        if (logLevel > LogLevel.INFO)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.i(TAG, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    /**
     * Send a WARN log message
     * @param msg The message you would like logged.
     */
    public static void w(String msg)
    {
        if (logLevel > LogLevel.WARN)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.w(TAG_TW, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void w(boolean on, String TAG, String msg)
    {
        if (!on)
            return;
        w(TAG, msg);
    }

    public static void w(String TAG, String msg)
    {
        if (logLevel > LogLevel.WARN)
        {
            //如果系统开启对应级别日志则打印
            if(Log.isLoggable(TAG_TW, Log.WARN))
            {
                android.util.Log.w(TAG_TW, msg);
            }
            return;
        }
        String logMsg = buildMessage(msg);
        android.util.Log.w(TAG, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    /**
     * Send a WARN log message and log the exception.
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void w(String msg, Throwable thr)
    {
        if (logLevel > LogLevel.WARN)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.w(TAG_TW, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void w(String TAG, String msg, Throwable thr)
    {
        if (logLevel > LogLevel.WARN)
        {
            //如果系统开启对应级别日志则打印
            if(Log.isLoggable(TAG_TW, Log.WARN))
            {
                android.util.Log.w(TAG_TW, msg);
            }
            return;
        }
        String logMsg = buildMessage(msg);
        android.util.Log.w(TAG, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    /**
     * Send an ERROR log message.
     * @param msg The message you would like logged.
     */
    public static void e(String msg)
    {
        if (logLevel > LogLevel.ERROR)
            return;
        String logMsg = buildMessage(msg);
        android.util.Log.e(TAG_TW, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    public static void e(boolean on, String TAG, String msg)
    {
        if (logLevel > LogLevel.ERROR)
            return;
        if (!on)
            return;
        e(TAG, msg);
    }

    public static void e(String TAG, String msg)
    {
        if (logLevel > LogLevel.ERROR)
        {
            //如果系统开启对应级别日志则打印
            if(Log.isLoggable(TAG_TW, Log.ERROR))
            {
                android.util.Log.e(TAG_TW, msg);
            }
            return;
        }
        String logMsg = buildMessage(msg);
        android.util.Log.e(TAG, logMsg);
        if (writeFile)
        {
            writeLog2file(logMsg);

        }
    }

    /**
     * Send an ERROR log message and log the exception.
     * @param msg The message you would like logged.
     * @param thr An exception to log
     */
    public static void e(String msg, Throwable thr)
    {
        if (logLevel > LogLevel.ERROR)
        {
            return;
        }
        String logMsg = buildMessage(msg);
        android.util.Log.e(TAG_TW, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);
            printThrowable(TAG_TW, thr);
        }
    }

    public static void e(String TAG, String msg, Throwable thr)
    {
        if (logLevel > LogLevel.ERROR)
        {
            //如果系统开启对应级别日志则打印
            if(Log.isLoggable(TAG_TW, Log.ERROR))
            {
                android.util.Log.e(TAG_TW, msg,thr);
            }
            return;
        }
        String logMsg = buildMessage(msg);
        android.util.Log.e(TAG, logMsg, thr);
        if (writeFile)
        {
            writeLog2file(logMsg);
            printThrowable(TAG, thr);
        }
    }

    /**
     * 打印异常的堆栈信息
     * @Description
     * @author kuangbiao
     * @param thr
     */
    public static void printThrowable(String TAG, Throwable thr)
    {
        StackTraceElement[] stacks = thr.getStackTrace();
        if (stacks != null)
        {
            String start = TAG + ":printThrowable start ==============";
            writeLog2file(start);
            android.util.Log.e(TAG, start);
            for (StackTraceElement stack : stacks)
            {
                String error = stack.toString();
                android.util.Log.e(TAG, error);
                writeLog2file(error);
            }
            String end = TAG + ":printThrowable end ==============";
            writeLog2file(end);
            android.util.Log.e(TAG, end);
        }
    }
    
    /**
     * 或取当前打印日志的类名 、方法名
     * @param msg The message you would like logged.
     * @return Message String
     */
    protected static String buildMessage(String msg)
    {
        StackTraceElement caller = new Throwable().fillInStackTrace()
                .getStackTrace()[2];
        
        return new StringBuilder().append(caller.getClassName())
                .append(".")
                .append(caller.getMethodName())
                .append("(): ")
                .append(msg)
                .toString();
    }
    
    /**
     * 是否允许日志写入文件
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isWriteEnable()
    {
        return writeFile;
    }
}
