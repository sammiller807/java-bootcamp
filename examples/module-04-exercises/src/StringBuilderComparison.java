public class StringBuilderComparison {
    private static final int ITERATIONS = 50_000;

    static String withString() {
        String result = "";
        for (int i = 0; i < ITERATIONS; i++) {
            result += "x";
        }
        return result;
    }

    static String withBuilder() {
        // Initial capacity avoids repeated buffer growth.
        StringBuilder result = new StringBuilder(ITERATIONS);
        for (int i = 0; i < ITERATIONS; i++) {
            result.append('x');
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // time withString() with System.nanoTime()
        // time withBuilder() with System.nanoTime()
        // printf both lengths and ms (stringNanos / 1_000_000.0)
        System.out.println("| Run | String ms | StringBuilder ms |");
        System.out.println("| --- | --------- | ---------------- |");
        for(int i = 1; i < 4; i++) {
            long misTime = System.nanoTime();
            String str = withString();
            long strTime = System.nanoTime() - misTime;
            String bul = withBuilder();
            long bulTime = System.nanoTime() - strTime - misTime;
            System.out.printf("| %d | %.2f | %.2f |\n", i, strTime / 1_000_000.0, bulTime / 1_000_000.0);
        }

    }
}