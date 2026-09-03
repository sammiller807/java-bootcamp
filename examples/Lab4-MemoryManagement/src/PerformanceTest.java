import java.lang.reflect.Array;
import java.util.ArrayList;

public class PerformanceTest {

    private static class SampleObject {
        private final int value;
        private final byte[] data = new byte[64];

        SampleObject(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Performance Measurement =====");
        MemoryMonitor.printMemoryReport("Start");

        int[] objectCounts = {10, 100, 1_000, 100_000, 1_000_000};

        System.out.println();
        System.out.printf("%-12s %-14s %-18s%n", "Objects", "Used Memory", "Execution Time");
        System.out.println("--------------------------------------------------");

        for (int count : objectCounts) {
            runAllocationTest(count);
        }

        System.out.println();
        System.out.println("Additional measurements:");
        measureLoopExecution();
        measureArrayAllocation();
        measureLargeByteArray();
    }

    private static void runAllocationTest(int count) {
        MemoryMonitor.triggerGarbageCollection();
        long memoryBefore = MemoryMonitor.getUsedMemoryBytes();
        long start = System.nanoTime();

        // allocate SampleObject[count], fill each slot
        // measure elapsed ms + memoryUsed; printf row; null array + GC
        ArrayList<SampleObject> objects = new ArrayList<SampleObject>();
        for(int i = 0; i < count; i++) {
            objects.add(new SampleObject(i));
        }
        long end = System.nanoTime();
        long memoryAfter = MemoryMonitor.getUsedMemoryBytes();
        System.out.printf("%-12s %-14.2f MB %-18s ms\n", count, MemoryMonitor.toMegabytesDouble(memoryAfter - memoryBefore), (end - start) / 1_000_000);

        objects.clear();
        objects = null;
        MemoryMonitor.triggerGarbageCollection();
    }

    private static void measureLoopExecution() {
        // loop 10_000_000 iterations summing i into sum; print elapsed ms
        long start = System.nanoTime();
        long sum = 0;

        for(int i = 0; i < 10_000_000; i++) {
            sum += i;
        }
        long end = System.nanoTime();
        System.out.printf("Loop execution (10M iterations) : %s ms | sum = %s\n", (end - start) / 1_000_000, sum);
    }

    private static void measureArrayAllocation() {
        // allocate int[1_000_000], fill with i, print elapsed ms
        long start = System.nanoTime();
        int[] array = new int[1_000_000];
        for(int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        long end = System.nanoTime();

        System.out.printf("int[1,000,000] allocation    : %s ms", (end - start) / 1_000_000);
    }

    private static void measureLargeByteArray() {
        MemoryMonitor.printMemoryReport("Before Large byte[]");
        // TODO: allocate 10 MB byte[]; print After report; null + GC; print After Releasing
        byte[] array = new byte[10 * 1024 * 1024];

        MemoryMonitor.printMemoryReport("After Large byte[]");

        array = null;
        MemoryMonitor.triggerGarbageCollection();

        MemoryMonitor.printMemoryReport("After GC");
    }
}
