package com.neutron.config;

public class NeutronConfig {
    public static boolean enableDynamicResolution = true;
    public static boolean enableMultiThreadedChunking = true;
    public static boolean enableEntityCulling = true;
    public static boolean enableLightingCache = true;
    public static boolean enableSmartTicking = true;
    public static boolean useComputeShaders = false; // Requires OpenGL 4.3+

    public static void load() {
        // Basic hardcoded config loader. JSON handling can be integrated later.
        System.out.println("[Neutron] Configuration profiles loaded.");
    }
}
