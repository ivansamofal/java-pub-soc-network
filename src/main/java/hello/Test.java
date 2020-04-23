package hello;

public class Test implements Runnable {
    @Override
    public void run() {
    }
    public static void main(String[] args) {
        System.out.println(20000);
        Thread guruthread1 = new Thread();
        guruthread1.start();
        try {
            guruthread1.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Автоматически сгенерированный блок catch
            e.printStackTrace();
        }
        guruthread1.setPriority(1);
        int gurupriority = guruthread1.getPriority();
        System.out.println(gurupriority);
        System.out.println("Thread Running");
    }
}