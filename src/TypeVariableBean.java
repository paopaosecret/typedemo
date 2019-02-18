import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaobing04 on 2019/2/14.
 */
public class TypeVariableBean<K extends Animal & Serializable, V> {

    //K的上边界是Animal
    K key;

    //V的上边界没指定  默认是Object
    V value;

    V[] values;

    String str;

    List<K> kList;
}
