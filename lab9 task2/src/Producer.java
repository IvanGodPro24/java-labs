public class Producer implements Runnable {
    private final RingBuffer<String> buffer;
    private final int id;

    public Producer(RingBuffer<String> buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        int counter = 0;
        try {
            while (true) {
                String msg = "Потік №" + id + " згенерував повідомлення " + counter++;
                buffer.put(msg);
                Thread.sleep(50);
            }
        } catch (InterruptedException ignored) {}
    }
}
