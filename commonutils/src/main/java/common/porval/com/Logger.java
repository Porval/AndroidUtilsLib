package common.porval.com;

import android.util.Log;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by jiangpeng on 15/7/22.
 * 日志函数 初始化{@link #setLogEnabled(boolean)}}
 * 默认情况下是不打印日志文件的
 */
public class Logger {
    private static boolean mIsLogEnabled = false;

    private static long sLastTimeStamp = 0L;

    private static final ThreadLocal<SimpleDateFormat> sDateFormatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss.SSS");
        }
    };

    private static String getTag(String tag) {
        return String.format(":Porval:%s:(%s):%s:",
                sDateFormatter.get().format(new Date(System.currentTimeMillis())),
                Thread.currentThread().getName(),
                tag);
    }

    private static String getMessage(String msg, Object... params) {
        if (params == null || params.length == 0) {
            return msg;
        }
        try {
            return String.format(msg, params);
        } catch (Throwable e) {
            StringBuilder builder = new StringBuilder(msg);
            for (Object param : params) {
                builder.append(", ").append(param);
            }
            builder.append("\n").append(Log.getStackTraceString(e));
            return builder.toString();
        }
    }

    public static boolean isLogEnabled() {
        return mIsLogEnabled;
    }

    /**
     * @param enabled 是否打印日志信息
     */
    public static void setLogEnabled(boolean enabled) {
        mIsLogEnabled = enabled;
    }

    public static int i(String tag, String msg, Object... params) {
        return isLogEnabled() ? Log.i(getTag(tag), getMessage(msg, params)) : 0;
    }

    public static int d(String tag, String msg, Object... params) {
        return isLogEnabled() ? Log.d(getTag(tag), getMessage(msg, params)) : 0;
    }

    public static int dc(String tag, String msg, Object... params) {
        String formattedTag = getTag(tag);
        String formattedMessage = getMessage(msg, params);
        return isLogEnabled() ? Log.d(formattedTag, formattedMessage) : 0;
    }

    public static int v(String tag, String msg, Object... params) {
        return isLogEnabled() ? Log.v(getTag(tag), getMessage(msg, params)) : 0;
    }

    public static int e(String tag, String msg, Object... params) {
        return isLogEnabled() ? Log.e(getTag(tag), getMessage(msg, params)) : 0;
    }

    public static int w(String tag, String msg, Object... params) {
        return isLogEnabled() ? Log.w(getTag(tag), getMessage(msg, params)) : 0;
    }

    public static int e(String tag, Throwable e) {
        return isLogEnabled() ? Log.e(getTag(tag), Log.getStackTraceString(e)) : 0;
    }

    public static int e(String tag, Throwable e, String msg, Object... params) {
        return isLogEnabled() ? Log.e(getTag(tag), getMessage(msg, params) + "\n" + Log.getStackTraceString(e)) : 0;
    }

    public static int t(String tag, String msg, Object... params) {
        if (isLogEnabled()) {
            long currentTimeStamp = System.currentTimeMillis();
            long diff = currentTimeStamp - sLastTimeStamp;
            int result = Log.d(getTag(tag), "" + currentTimeStamp
                    + ((sLastTimeStamp > 0) ? " (+" + diff + "ms) " : " ")
                    + getMessage(msg, params));
            sLastTimeStamp = currentTimeStamp;
            return result;
        }
        return 0;
    }

    public static void printStackTrace(String tag) {
        if (!isLogEnabled()) {
            return;
        }
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Logger.d(tag, "\n---------------------");
        // 不需要打印前三项，因为他们都是Logger.printStackTrace自身相关的信息
        for (int i = 3; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            Logger.d(tag, "    " + element.getClassName() + "." + element.getMethodName() +
                    "(" + element.getFileName() + ":" + element.getLineNumber() + ")");
        }
        Logger.d(tag, "---------------------\n");
    }
}
