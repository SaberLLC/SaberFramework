package pw.saber.framework.utils;

import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MoreExecutors {

    private static final Executor directExecutor = Runnable::run;

    private static final ListeningExecutorService asyncExecutor =
            com.google.common.util.concurrent.MoreExecutors.listeningDecorator( Executors.newCachedThreadPool() );


    public static ListeningExecutorService getAsyncExecutor() {
        return asyncExecutor;
    }

    public static Executor getDirectExecutor() {
        return directExecutor;
    }
}
