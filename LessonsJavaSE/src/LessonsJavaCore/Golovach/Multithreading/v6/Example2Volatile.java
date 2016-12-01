package LessonsJavaCore.Golovach.Multithreading.v6;

/**
 * Ключевое слово volatile
 * - Можно записывать только в поля класса и нельзя применять в локальных переменных
 * - Замедляет работу программы приблизительно в 200 раз
 *
 * Когда работают два и больше потока одновременно, мы не можем узнать что на данный момент делает этот поток,
 * особенно когда мы пытаемся обратится к данным, какие обрабатываются несколькими потоками. Один из этих потоков
 * всегда будет получать информацию из прошлого. Потому что у нас есть несколько видов памяти: ОЗУ и дополнительно
 * с каждым потоком работает одно ядро, а соответсвенно у каждого потока есть три вида кеша, какие не всегда передают
 * свежие данные в ОЗУ. Поэтому остальные потоки с большой вероятностью будут получать старую информацию
 * (будет выполнятся отношение "happens-before").
 * Ключевое слово volatile помогает перескочить и словить свежие обработанные данные другими потоками, но это
 * существенно замедляет работу программы.
 * Важно понимать, что с помощью volatile обновляются все глобальные данные между этими потоками. Поток какой хочет
 * получить свежие данные, он обращается к кешу нужного потока и обновляет свой, таким образом получая свежие данные.
 * Из-за этого и весомо замедляется работа программы. Так как обращение к кешу другого ядра занимает значительно
 * больше времени, чем к своему кешу.
 *
 * Обновляются все данные, какие были изменены до вызова переменной volatile.
 * Именно это ключевое слово говорит, что нужно обратится к кешу другого потока
 */

public class Example2Volatile {
    static boolean ready = false;
    static int data = 0;
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1000);
                data = 1;
                ready = true;
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!ready);
                System.out.println(data);
            }
        }).start();
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}