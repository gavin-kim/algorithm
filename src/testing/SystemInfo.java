package testing;

import java.text.NumberFormat;

public class SystemInfo {

    private static final String OS_NAME = "os.name";
    private static final String OS_VERSION = "os.version";
    private static final String OS_ARCH = "os.arch";

    private Runtime runtime = Runtime.getRuntime();

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        return null;
    }

    public String osName() {
        return System.getProperty(OS_NAME);
    }

    public String osVersion() {
        return System.getProperty(OS_VERSION);
    }

    public String osArch() {
        return System.getProperty(OS_ARCH);
    }

    public String MemInfo() {
        StringBuilder sb = new StringBuilder();

        long maxMemory = runtime.maxMemory() / 1024;
        long totalAvailableMemory = runtime.totalMemory() / 1024;
        long freeMemory = runtime.freeMemory() / 1024;

        sb.append("Max memory: ")
            .append(maxMemory).append("kb\n")
            .append("Total available memory: ")
            .append(totalAvailableMemory).append("kb\n")
            .append("Free memory: ")
            .append(freeMemory).append("kb\n")
            .append("Used memory: ")
            .append(totalAvailableMemory - freeMemory).append("kb\n");
        return sb.toString();
    }

}
