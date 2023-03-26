class Calculate implements Runnable{
    private final int[] arr;
    private final int start;
    private final int end;
    private final long[]partialSums;
    private final int position;
    Calculate(int[] arr, int start, int end, long[]partialSums, int position) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.partialSums = partialSums;
        this.position = position;
    }

    @Override
    public void run() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
        System.out.printf("Thread %d: from %d to %d sum is %d\n", position, start, end - 1, sum);
        partialSums[position] = sum;
    }

    public void start() {
        run();
    }
}
