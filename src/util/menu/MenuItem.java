package util.menu;

class MenuItem {
    private int id;
    private String title;

    MenuItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getID() {
        return id;
    }

    public String toString() {
        return title;
    }
}
