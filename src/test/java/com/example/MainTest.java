package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


class MyContainerTest{
    private MyContainer<String> stringContainer;
    private MyContainer<Integer> integerContainer;

    @BeforeEach
    void setUp(){
        stringContainer = new MyContainer<>();
        integerContainer = new MyContainer<>();
    }

    @Test
    void test_Default_Constructor(){
        MyContainer<Integer> container = new MyContainer<>();
        assertEquals(0, container.size());
    }

    @Test
    void test_Constructor_With_Capacity(){
        MyContainer<Integer> container = new MyContainer<>(5);
        assertEquals(0, container.size());
    } 

    @Test
    void test_Constructor_With_Zero_Capacity(){
        MyContainer<Integer> container = new MyContainer<>(0);
        assertEquals(0, container.size());
    }

    @Test
    void test_Constructor_With_Negative_Capacity(){
        assertThrows(IllegalArgumentException.class, () -> {new MyContainer<>(-1);});
    }

    @Test
    void test_Add_and_Size(){
        stringContainer.add("Hello");
        stringContainer.add("World");

        assertEquals(2, stringContainer.size());
    }

    @Test
    void test_Get(){
        stringContainer.add("Hello");
        stringContainer.add("my");
        stringContainer.add("friend");

        assertEquals("Hello", stringContainer.get(0));
        assertEquals("my", stringContainer.get(1));
        assertEquals("friend", stringContainer.get(2));
    }

    @Test
    void test_Get_With_Invalid_Index(){
        stringContainer.add("Testing");

        assertThrows(IndexOutOfBoundsException.class, () -> {stringContainer.get(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {stringContainer.get(1);});
    }

    @Test
    void test_Remove(){
        stringContainer.add("First");
        stringContainer.add("Delete");
        stringContainer.add("Second");

        String removed_value = stringContainer.remove(1);

        assertEquals("Delete", removed_value);
        assertEquals(2, stringContainer.size());
        assertEquals("First", stringContainer.get(0));
        assertEquals("Second", stringContainer.get(1));
    }

    @Test
    void test_Remove_First_Index(){
        stringContainer.add("Delete");
        stringContainer.add("First");

        String removed_value = stringContainer.remove(0);

        assertEquals("Delete", removed_value);
        assertEquals(1, stringContainer.size());
        assertEquals("First", stringContainer.get(0));
    }

    @Test
    void test_Remove_Last_Index(){
        stringContainer.add("First");
        stringContainer.add("Last");
        stringContainer.add("Delete");

        String removed_value = stringContainer.remove(2);

        assertEquals("Delete", removed_value);
        assertEquals(2, stringContainer.size());
        assertEquals("First", stringContainer.get(0));
        assertEquals("Last", stringContainer.get(1));
    }

    @Test
    void test_Remove_With_Invalid_Index(){
        stringContainer.add("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> {stringContainer.remove(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {stringContainer.remove(1);});
    }

    @Test
    void test_Remove_From_Empty_Container(){
        assertThrows(IndexOutOfBoundsException.class, () -> {stringContainer.remove(0);});
    }

    @Test
    void test_Auto_Resize(){
        MyContainer<Integer> container = new MyContainer<>(2);
        container.add(1);
        container.add(2);
        container.add(3);

        assertEquals(3, container.size());
        assertEquals(1, container.get(0));
        assertEquals(2, container.get(1));
        assertEquals(3, container.get(2));
    }

    @Test
    void test_Auto_Resize_With_Default_Capacity(){
        MyContainer<Integer> container = new MyContainer<>();

        for(int i = 0; i < 11; i++){
            container.add(i);
        }

        assertEquals(11, container.size());
        assertEquals(10, container.get(10));
    }

    @Test
    void test_Ineger_Container(){
        integerContainer.add(0);
        integerContainer.add(1);

        integerContainer.add(2);

        assertEquals(3, integerContainer.size());
        assertEquals(Integer.valueOf(0), integerContainer.get(0));
        assertEquals(Integer.valueOf(1), integerContainer.get(1));
        assertEquals(Integer.valueOf(2), integerContainer.get(2));

        int removed = integerContainer.remove(1);

        assertEquals(2, integerContainer.size());
        assertEquals(Integer.valueOf(1), removed);
    }

    @Test
    void test_ToString_Empty(){
        assertEquals("[]", stringContainer.toString());
    }

    @Test
    void test_ToString_Single_Element(){
        stringContainer.add("Testing");
        assertEquals("[Testing]", stringContainer.toString());
    }

    @Test
    void test_ToString_Multiply_Elements(){
        stringContainer.add("One");
        stringContainer.add("Two");
        stringContainer.add("Three");

        assertEquals("[One, Two, Three]", stringContainer.toString());
    }

    @Test
    void test_ToString_With_null_Values(){
        stringContainer.add("One");
        stringContainer.add(null);
        stringContainer.add("Two");

        assertEquals("[One, null, Two]", stringContainer.toString());
    }

    @Test
    void test_Mixed_Operation(){
        MyContainer<String> container = new MyContainer<>(3);
        
        container.add("One");
        container.add("Two");
        container.add("Three");

        String removed = container.remove(1);
        assertEquals("Two", removed);

        container.add("Four");
        container.add("Five");
        assertEquals(4, container.size());
        assertEquals("[One, Three, Four, Five]", container.toString());

        removed = container.remove(0);
        assertEquals(3, container.size());
        assertEquals("One", removed);

        removed = container.remove(2);
        assertEquals(2, container.size());
        assertEquals("Five", removed);

        assertEquals("[Three, Four]", container.toString());
    }
    
    @Test
    void test_Generic_Type_Safety(){
        MyContainer<Double> container = new MyContainer<>();

        container.add(3.14);
        container.add(2.71);
        assertEquals(Double.valueOf(3.14), container.get(0));
        assertEquals(Double.valueOf(2.71), container.get(1));
    }

    @Test
    void test_Zero_Capacity_Container_Operations(){
        MyContainer<String> container = new MyContainer<>(0);

        container.add("Testing");

        assertEquals(1, container.size());
        assertEquals("Testing", container.get(0));
    }
}

