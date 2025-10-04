public class Main {
    public static void main(String[] args) throws InterruptedException {
        RingBuffer<String> buffer1 = new RingBuffer<>(20);
        RingBuffer<String> buffer2 = new RingBuffer<>(20);

        for (int i = 1; i <= 5; i++) {
            Thread producer = new Thread(new Producer(buffer1, i));
            producer.setDaemon(true);
            producer.start();
        }

        for (int i = 1; i <= 2; i++) {
            Thread translator = new Thread(new Translator(buffer1, buffer2, i));
            translator.setDaemon(true);
            translator.start();
        }

        for (int i = 0; i < 100; i++) {
            String msg = buffer2.take();
            System.out.println(msg);
        }

        System.out.println("Програма завершена, 100 повідомлень надруковано.");
    }
}
