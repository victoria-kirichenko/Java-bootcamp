package ex02;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Vika", 1500);
        User user2 = new User("Vasya", 200);
        User user3 = new User("Julia", 1000);
        UsersArrayList list = new UsersArrayList();
        list.userAdd(user1);
        list.userAdd(user2);
        list.userAdd(user3);
        System.out.println(list.getCountUser());
        for (int i = 0; i < list.getCountUser(); i++) {
            list.getUser(i).print();
        }
        list.getUserByIdx(1).print();
        System.out.println(list.getCountUser());
        for (int i = 0; i < list.getCountUser(); i++) {
            list.getUser(i).print();
        }

    }
}