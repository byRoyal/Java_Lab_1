# MyContainer 🗃️

![Java](https://img.shields.io/badge/Java-8%2B-blue)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-orange)
![JUnit](https://img.shields.io/badge/JUnit-5-brightgreen)

Универсальный типобезопасный контейнер для хранения элементов с автоматическим расширением и базовыми операциями коллекции.

## 📋 Оглавление

- [Особенности](#особенности)
- [Быстрый старт](#быстрый-старт)
- [Установка](#установка)
- [Использование](#использование)
- [API Документация](#api-документация)
- [Примеры](#примеры)
- [Тестирование](#тестирование)
- [Разработка](#разработка)

## ✨ Особенности

- 🚀 **Универсальность** - поддержка дженериков для любого типа данных
- 📦 **Авторасширение** - автоматическое увеличение емкости при необходимости
- 🔧 **Типобезопасность** - гарантии типов на уровне компиляции
- 🛡️ **Безопасность** - проверки границ и валидация входных данных
- 🎯 **Производительность** - оптимизированные операции с использованием `System.arraycopy`
- 📝 **Читаемость** - понятное строковое представление элементов

## 🚀 Быстрый старт

```java
MyContainer<String> container = new MyContainer<>();
container.add("Hello");
container.add("World");

System.out.println(container.get(0)); // "Hello"
System.out.println(container.size()); // 2
System.out.println(container); // [Hello, World]
```
## ⚡ Установка
**Требования**
- Java 8 или выше
- Maven 3.6+

## Сборка из исходников
```
git clone <https://github.com/byRoyal/Java_Lab_1.git>
cd mycontainer
mvn clean install
```
## 🎯 Использование
**Создание контейнера**
```java
// Контейнер с емкостью по умолчанию (10 элементов)
MyContainer<String> container1 = new MyContainer<>();

// Контейнер с указанной емкостью
MyContainer<Integer> container2 = new MyContainer<>(100);

// Контейнер с нулевой начальной емкостью
MyContainer<Double> container3 = new MyContainer<>(0);
```
**Базовые операции**
```java
MyContainer<String> container = new MyContainer<>();

// Добавление элементов
container.add("Apple");
container.add("Banana");
container.add("Cherry");

// Получение элементов
String fruit = container.get(1); // "Banana"

// Удаление элементов
String removed = container.remove(0); // "Apple"

// Размер контейнера
int size = container.size(); // 2

// Строковое представление
System.out.println(container); // [Banana, Cherry]
```

## 📚 API Документация

**Конструкторы:**
- MyContainer()	Создает контейнер с емкостью по умолчанию (10)
- MyContainer(int capacity)	Создает контейнер с указанной емкостью	IllegalArgumentException если capacity < 0

## Публичные методы
```java
void add(T value)
```
Добавляет элемент в контейнер. Емкость автоматически увеличивается при необходимости.

**Параметры:**

- value - элемент для добавления
```java
T get(int index)
```
Возвращает элемент по указанному индексу.

**Параметры:**

- index - индекс элемента

**Возвращает:**

- элемент по указанному индексу

**Исключения:**
IndexOutOfBoundsException если индекс вне границ [0, size-1]

```java
T remove(int index)
```
Удаляет элемент по указанному индексу и возвращает его. Сдвигает последующие элементы влево.

**Параметры:**

- index - индекс удаляемого элемента

**Возвращает:**

- удаленный элемент

**Исключения:**

IndexOutOfBoundsException если индекс вне границ [0, size-1]
```java
int size()
```
Возвращает количество элементов в контейнере.

**Возвращает:**

- количество элементов
```java
String toString()
```
Возвращает строковое представление контейнера в формате [element1, element2, ...].

**Возвращает:**

- строковое представление контейнера

## 💡 Примеры
**Работа с различными типами**
```java
// Строки
MyContainer<String> strings = new MyContainer<>();
strings.add("Hello");
strings.add("World");

// Числа
MyContainer<Integer> numbers = new MyContainer<>();
numbers.add(42);
numbers.add(123);

// Пользовательские объекты
MyContainer<Person> people = new MyContainer<>();
people.add(new Person("John"));
people.add(new Person("Jane"));
```
## Автоматическое расширение
```java
MyContainer<Integer> container = new MyContainer<>(3);

// Добавляем больше элементов, чем начальная емкость
for (int i = 0; i < 10; i++) {
    container.add(i);
}

System.out.println(container.size()); // 10
System.out.println(container); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```
## Комплексные операции
```java
MyContainer<String> container = new MyContainer<>();

// Добавление
container.add("A");
container.add("B");
container.add("C");
container.add("D");

// Удаление из середины
String removed = container.remove(1); // "B"
System.out.println(container); // [A, C, D]

// Удаление первого элемента
removed = container.remove(0); // "A"
System.out.println(container); // [C, D]

// Удаление последнего элемента
removed = container.remove(1); // "D"
System.out.println(container); // [C]
```
## 🧪 Тестирование
**Проект включает комплексные unit-тесты, покрывающие все основные сценарии использования:**

### Запуск тестов
```
mvn test
```
### Запуск тестов с отчетом о покрытии
```
mvn jacoco:report
```
### Покрытие тестами:
- ✅ Конструкторы (включая обработку ошибок)

- ✅ Добавление элементов

- ✅ Получение элементов

- ✅ Удаление элементов (с разных позиций)

- ✅ Автоматическое расширение емкости

- ✅ Проверки границ индексов

- ✅ Строковое представление

- ✅ Работа с различными типами данных

- ✅ Комплексные сценарии использования

## 🏗 Разработка
**Структура проекта**
```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               └── MyContainer.java
└── test/
    └── java/
        └── com/
            └── example/
                └── MyContainerTest.java
```
## Сборка и документация

### Компиляция
```
mvn clean compile
```
### Генерация Javadoc
```
mvn javadoc:javadoc
```
### Создание JAR
```
mvn package
```
## Принципы реализации

- **Авторасширение:** емкость удваивается при заполнении

- **Эффективность:** использование System.arraycopy для быстрого копирования

- **Безопасность:** проверки границ для всех операций с индексами

- **Память:** обнуление ссылок при удалении для помощи GC

## 📄 Лицензия

**Этот проект лицензирован under the MIT License - смотрите файл LICENSE для деталей.**

## 🤝 Вклад
- Форкните проект

- Создайте feature ветку (git checkout -b feature/AmazingFeature)

- Закоммитьте изменения (git commit -m 'Add some AmazingFeature')

- Запушьте ветку (git push origin feature/AmazingFeature)

- Откройте Pull Request

# MyContainer - простой, но мощный контейнер для ваших Java проектов! 🚀
