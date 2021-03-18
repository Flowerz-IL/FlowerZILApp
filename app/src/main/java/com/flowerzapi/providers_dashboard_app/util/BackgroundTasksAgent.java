package com.flowerzapi.providers_dashboard_app.util;

import android.os.Handler;
import android.os.Looper;

import com.flowerzapi.providers_dashboard_app.model.MainRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BackgroundTasksAgent{
    private final Executor executor;
    private final Handler handler;

    public BackgroundTasksAgent() {
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public <T> void executeAsync(Runnable runnable, MainRepository.CustomListener<Boolean> listener) {
        executor.execute(() -> {
            boolean res = true;

            try {
                runnable.run();
            } catch (Exception exception) { exception.printStackTrace(); res = false;}

            boolean finalRes = res;
            handler.post(() -> { if(listener != null) listener.onComplete(finalRes); });
        });
    }
}
