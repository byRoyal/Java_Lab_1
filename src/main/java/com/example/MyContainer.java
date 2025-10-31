package com.example;

import java.util.Arrays;
import java.util.Objects;

/**
 * Универсальный контейнер для хранения элементов типа T.
 * Предоставляет базовые операции для работы с коллекцией элементов.
 * 
 * @param <T> тип элементов, хранящихся в контейнере
 */
public class MyContainer <T>{
    private Object[] container;
    private int size;

    private static final int Default_capacity = 10;

    /**
     * Создает контейнер с начальной емкостью по умолчанию (10 элементов).
     */
    public MyContainer(){
        container = new Object[Default_capacity];
        size = 0;
    }

    /**
     * Создает контейнер с указанной начальной емкостью.
     *
     * @param capacity начальная емкость контейнера
     * @throws IllegalArgumentException если указанная емкость отрицательна
     */
    public MyContainer(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        container = new Object[capacity];
        size = 0;
    }

    /**
     * Возвращает количество элементов в контейнере.
     *
     * @return количество элементов в контейнере
     */
    public int size(){
        return size;
    }

    /**
     * Добавляет элемент в контейнер.
     * Емкость контейнера автоматически увеличивается при необходимости.
     *
     * @param value элемент для добавления
     */
    public void add(T value){
        increase_capacity();
        container[size++] = value;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param i индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    @SuppressWarnings("unchecked")
    public T get(int i){
        indexOK(i);
        return (T) container[i];
    }

    /**
     * Удаляет элемент по указанному индексу и возвращает его.
     * Сдвигает все последующие элементы на одну позицию влево.
     *
     * @param index индекс удаляемого элемента
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    @SuppressWarnings("unchecked")
    public T remove(int index){
        indexOK(index);
        T old_value = (T) container[index]; 

        int num_to_move = size - index - 1;
        if(num_to_move > 0){
            System.arraycopy(container, index + 1, container, index, num_to_move);
        }
        container[--size] = null;

        return old_value;
    }

    /**
     * Увеличивает емкость контейнера при необходимости.
     * Если текущий размер равен емкости, емкость удваивается.
     * Если емкость равна 0, устанавливается емкость по умолчанию.
     */
    private void increase_capacity(){
        if(size == container.length){
            int new_length = container.length == 0 ? Default_capacity: (container.length * 2);
            container = Arrays.copyOf(container, new_length); 
        }
    }
    
    /**
     * Проверяет корректность индекса.
     *
     * @param ind проверяемый индекс
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    private void indexOK(int ind){
        if(ind < 0 || ind >= size){
            throw new IndexOutOfBoundsException("Index: " + ind + ", Size: " + size);
        }
    }

    /**
     * Возвращает строковое представление контейнера.
     * Элементы выводятся в квадратных скобках через запятую.
     *
     * @return строковое представление контейнера
     */
    @Override
    public String toString(){
        if(size == 0){
            return "[]";
        }
        StringBuilder stroka = new StringBuilder();

        stroka.append("[");
        for(int i = 0; i<size; i++){
            stroka.append(Objects.toString(container[i]));
            if(i < size - 1){
                stroka.append(", ");
            }
        }
        stroka.append("]");

        return stroka.toString();
    }
}