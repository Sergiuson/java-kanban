import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        System.out.println("Добро пожаловать в приложение Трекер задач!");
        System.out.println("Введите название задачи:");
        String name  = console.nextLine();
        System.out.println("Опишите задачу:");
        String description  = console.nextLine();
        int id = 0;
        Task task = new Task(name, description, id);

        System.out.println(task);

    }
}
