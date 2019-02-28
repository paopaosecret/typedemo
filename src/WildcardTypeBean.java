import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaobing04 on 2019/2/27.
 */
public class WildcardTypeBean<K extends Animal> {
    List<? extends Animal> dataList;
    Map<? extends Object,? super ArrayList> dataMap;
}
