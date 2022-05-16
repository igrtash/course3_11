/** Урок 9: Коллекции
 * Практическое задание 11 - Коллекции
 * Создать класс Student:
 * Поля (ФИО, Возраст, класс, любимый предмет(enum))
 * В методе main дать возможность внести информацию об учениках.
 * Если ученик уже был введён, то вывести сообщение (Ученик уже
 * есть в базе)
 * Создать компаратор для учеников по 2 полям (ФИО и возраст)
 * Вариант меню:
 * 1. Ввести ученика.
 * 2. Вывести всех учеников по возрастанию.
 * 3. Вывести всех учеников по убыванию.
 * 4. Удалить ученика по индексу.
 * 5. Выйти из программы.
 */
import java.util.*;

public class Main {

    public static void main(String[] args) {
        TreeMap<Student, Integer> treeMap = new TreeMap<>(new StudentNameComporator().thenComparing(new StudentAgeComporator()));
        treeMap.put(new Student("Петров Валерий Иванович",15, 9, FavoriteLesson.Mathematics),1);
        treeMap.put(new Student("Сидоров Иван Анатольевич",10, 4, FavoriteLesson.Biology),2);
        treeMap.put(new Student("Егоров Костантин Петрович",10, 4, FavoriteLesson.Chemistry),3);
        treeMap.put(new Student("Иванов Иван Иванович",11, 5, FavoriteLesson.PhysicalEducation),4);

        int menuSelect = 0;
        while (menuSelect != 5) {
            menuSelect = getMenu();
            switch (menuSelect) {
                case 1: {
                    Student sIns = inputStudent(">Введите данные ученика для добавления.");
                    if (treeMap.containsKey(sIns))
                        System.out.println("Ученик уже есть в базе.");
                    else
                        treeMap.put(sIns, 1);
                    break;
                }
                case 2: PrintStudent(treeMap, false); break;
                case 3: PrintStudent(treeMap, true); break;
                case 4: {
                    Student sDel = inputStudent(">Введите данные ученика для удаления.");
                    if (treeMap.containsKey(sDel))
                        treeMap.remove(sDel);
                    else
                        System.out.println("Ученик с такими данными не найден.");
                    break;
                }
                case 5: return;
                default: System.out.println("Неправильно выбран пункт меню. Повторите ввод");; break;
            }
        }
    }

    public static void printMenu() {
        String[] menu = {
                "1. Ввести ученика.",
                "2. Вывести всех учеников по возрастанию.",
                "3. Вывести всех учеников по убыванию.",
                "4. Удалить ученика.",
                "5. Выйти из программы."
        };
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
    }

    public static int getMenu() {
        printMenu();
        try {
            System.out.print("Выберите пункт меню цифрой [1-5]: ");
            return new Scanner(System.in).nextInt();
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getNum(int lower, int upper) {
        Scanner sc = new Scanner(System.in);
        boolean loop;
        int result = -1;
        loop = true;
        while (loop) {
            try {
                result = sc.nextInt();
                if (result < lower || result > upper)
                    throw new NumberFormatException();
                loop = false;
            } catch (Exception e) {
                System.out.println("Повторите ввод.");
            }
        }
        return result;
    }

    public static Student inputStudent(String caption) {
        String fio;
        int age, nclass, fl;
        System.out.println(caption);

        System.out.print("ФИО: ");
        fio = new Scanner(System.in).nextLine();

        System.out.print("Возраст [6-140]: ");
        age = getNum(6,140);

        System.out.print("Класс [1-11]: ");
        nclass = getNum(1,11);

        System.out.print("Любимый предмет [1-9] ");
        for (FavoriteLesson item : FavoriteLesson.values())
            System.out.print(item.getNum() + "-" + item.name() + " ");
        System.out.print(": ");
        fl = getNum(1,9);

        FavoriteLesson favoriteLesson = null;
        for (FavoriteLesson item : FavoriteLesson.values()) {
            if (fl == item.getNum()) {
                favoriteLesson = item;
            }
        }
        System.out.println(favoriteLesson);
        return new Student(fio, age, nclass, favoriteLesson);
    }

    public static void PrintStudent(TreeMap<Student, Integer> tm, boolean desc) {
        if (desc) {
            for (Map.Entry<Student, Integer> elem : tm.descendingMap().entrySet()) {
                System.out.println(elem.getKey() + " : " + elem.getValue());
            }
        } else {
            for (Map.Entry<Student, Integer> elem : tm.entrySet()) {
                System.out.println(elem.getKey() + " : " + elem.getValue());
            }
        }
    }
}

class StudentNameComporator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.name.compareTo(o2.name);
    }
}

class StudentAgeComporator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return (o1.age - o2.age);
    }
}
