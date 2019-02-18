import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Animal animal = new Dog();
        System.out.println(animal.speak());

        List<Animal> list = new ArrayList<>();
        System.out.println();
    }
}
