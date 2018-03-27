package util.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Menu<E> {
    private List<MenuItem> items = new ArrayList<>();

    protected Menu() {
        addMenu();
    }

    protected abstract void addMenu();

    protected abstract E callMethod(int no);

    protected void add(int id, String title) {
        items.add(new MenuItem(id, title));
    }

    public Menu<E> show() {
        for (int i = 0; i < items.size(); i++ ) {
            System.out.println((i + 1) + ": " + items.get(i));
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public E select() {
        int no = input();
        MenuItem item = items.get(no - 1);
        return callMethod(item.getID());
    }

    private int input() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("> ");
                int i = Integer.parseInt(sc.nextLine());

                if (i <= 0 || i > items.size()) {
                    System.err.println("その番号の要素は存在しません。");
                }
                else {
                    return i;
                }
            }
            catch (NumberFormatException e) {
                System.err.println("整数ではありません。");
            }
        }
    }
}
