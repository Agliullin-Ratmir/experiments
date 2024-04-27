package org.example.sorting;

import java.security.SecureRandom;
import java.util.random.RandomGenerator;

public class BubbleSortExample {

    public static void main(String[] args) {
        ArrayBubble bubble = new ArrayBubble(10);
        RandomGenerator randomGenerator = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            bubble.into(randomGenerator.nextLong(100L));
        }
        System.out.println("Before sorting:");
        bubble.printer();
        System.out.println("After sorting:");
        bubble.bubbleSorter();
        bubble.printer();
    }

    static class ArrayBubble{
        private long[] a;   //ссылка на массив
        private int elems;  //количество элементов в массиве

        public ArrayBubble(int max){    //конструктор класса
            a = new long[max];          //создание массива размером max
            elems = 0;                  //при создании массив содержит 0 элементов
        }

        public void into(long value){   //метод вставки элемента в массив
            a[elems] = value;           //вставка value в массив a
            elems++;                    //размер массива увеличивается
        }

        public void printer(){          //метод вывода массива в консоль
            for (int i = 0; i < elems; i++){    //для каждого элемента в массиве
                System.out.print(a[i] + " ");   //вывести в консоль
                System.out.println("");         //с новой строки
            }
            System.out.println("----Окончание вывода массива----");
        }

        private void toSwap(int first, int second){ //метод меняет местами пару чисел массива
            long dummy = a[first];      //во временную переменную помещаем первый элемент
            a[first] = a[second];       //на место первого ставим второй элемент
            a[second] = dummy;          //вместо второго элемента пишем первый из временной памяти
        }

        public void bubbleSorter() {   //МЕТОД ПУЗЫРЬКОВОЙ СОРТИРОВКИ
            for (int out = elems - 1; out >= 1; out--) { //Внешний цикл
                System.out.println("Cycle from end of array, index: " + out);
                for (int in = 0; in < out; in++) {       //Внутренний цикл
                    System.out.println("Cycle inside, step: " + in);
                    if (a[in] > a[in + 1])               //Если порядок элементов нарушен
                        toSwap(in, in + 1);             //вызвать метод, меняющий местами
                }
                printer();
            }
        }
    }
}
