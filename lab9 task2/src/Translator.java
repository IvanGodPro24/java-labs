public class Translator implements Runnable {
    private final RingBuffer<String> input;
    private final RingBuffer<String> output;
    private final int id;

    public Translator(RingBuffer<String> input, RingBuffer<String> output, int id) {
        this.input = input;
        this.output = output;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = input.take();
                String translated = "Потік №" + id + " переклав повідомлення [" + msg + "]";
                output.put(translated);
            }
        } catch (InterruptedException ignored) {}
    }
}
