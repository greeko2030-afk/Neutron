package com.neutron.core;

import com.neutron.config.NeutronConfig;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OptimizationEngine {
    // Thread pool dedicated to handling intensive chunk compilation sub-tasks
    private static final ExecutorService CHUNK_WORKER_POOL = Executors.newFixedThreadPool(
        Math.max(1, Runtime.getRuntime().availableProcessors() - 1)
    );

    private static float currentResolutionScale = 1.0f;
    private static long lastFrameTime = System.nanoTime();

    public static ExecutorService getChunkWorkerPool() {
        return CHUNK_WORKER_POOL;
    }

    public static void updateDynamicResolution(long frameTimeNs) {
        if (!NeutronConfig.enableDynamicResolution) return;

        double fps = 1_000_000_000.0 / frameTimeNs;
        if (fps < 55.0) {
            currentResolutionScale = Math.max(0.7f, currentResolutionScale - 0.05f); // Scale down
        } else if (fps > 61.0) {
            currentResolutionScale = Math.min(1.0f, currentResolutionScale + 0.02f); // Scale up
        }
    }

    public static float getCurrentResolutionScale() {
        return currentResolutionScale;
    }
}
