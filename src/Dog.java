import java.io.Serializable;

/**
 * Created by zhaobing04 on 2019/2/14.
 */
public class Dog implements Animal,Serializable {
    @Override
    public String speak() {
        return "汪汪汪";
    }
}
