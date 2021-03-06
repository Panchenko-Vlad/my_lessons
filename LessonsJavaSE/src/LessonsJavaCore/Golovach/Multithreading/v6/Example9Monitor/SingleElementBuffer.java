package LessonsJavaCore.Golovach.Multithreading.v6.Example9Monitor;

/**
 * Был использован тип Integer, он абсолютно такой же как тип int, только в добавок поддерживает значение null.
 *
 *          - Шаблон проектирования для многопоточности - Производитель/Потребитель (Producer/Consumer) -
 * У нас есть двухсвязный список, в одну сторону мы ложим, что изготовил производитель, с другой
 * стороны списка забираем элементы для потребителя. В нашей реализации размер у списка всего один элемент, таким
 * образом реализовано, что производитель не вносит данные в список, если в списке есть данные, а потребитель
 * заморожен пока в списке не появятся данные.
 * Таким образом пример построен, что метод put проверяет есть ли данные в списке, если они есть поток замараживается,
 * разморозит его метод get. Если в списке нет данных он их записывает и будит все потоки, чтобы метод get мог забрать
 * только что внесенные данные.
 * Метод get спит до тех пор пока в списке нет данных, если они есть он их считывает и будит потоки, чтобы метод put
 * мог заново записать данные.
 *
 * Суть в том, чтобы разделить работу в программе. Чтобы каждая часть работала над своим заданием, грубо говоря
 * чтобы производитель производил, а потребитель употреблял и всё это происходило отдельно.
 */

public class SingleElementBuffer {

    private Integer elem = null;

    public synchronized void put(int newElem) throws InterruptedException {
        while (this.elem != null) {
            this.wait();
        }

        this.elem = newElem;
        this.notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (elem == null) {
            this.wait();
        }

        Integer result = this.elem;
        this.elem = null;
        this.notifyAll();
        return result;
    }
}
