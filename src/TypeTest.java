import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by zhaobing04 on 2019/2/14.
 */
public class TypeTest {

    public static void main(String args[]){
//        testParameterizedType();

        testTypeVariable();
    }



    public static void testParameterizedType(){
        Field f = null;
        try {
            Field[] fields = ParameterizedTypeBean.class.getDeclaredFields();
            // 打印出所有的 Field 的 TYpe 是否属于 ParameterizedType
            for (int i = 0; i < fields.length; i++) {
                f = fields[i];
                System.out.println(f.getName() + "-----" + f.getGenericType().getTypeName() + "---->" + (f.getGenericType() instanceof ParameterizedType));
            }
            getParameterizedTypeMes("map" );
            getParameterizedTypeMes("entry" );

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    private static void getParameterizedTypeMes(String fieldName) throws NoSuchFieldException {

        Field f;
        f = ParameterizedTypeBean.class.getDeclaredField(fieldName);
        f.setAccessible(true);
        System.out.println("---------------------------------------------------------");
        System.out.println(f.getGenericType());
        boolean b = f.getGenericType() instanceof ParameterizedType;
        System.out.println(b);
        if(b){
            ParameterizedType pType = (ParameterizedType) f.getGenericType();
            System.out.println(pType.getRawType());
            for (Type type : pType.getActualTypeArguments()) {
                System.out.println(type);
            }
            System.out.println(pType.getOwnerType()); // null
        }
    }

    public static void testTypeVariable(){
        TypeVariableBean bean = new TypeVariableBean<Dog,String>();
        try {
            Field key = TypeVariableBean.class.getDeclaredField("key");
            TypeVariable keyType = (TypeVariable)key.getGenericType();
            System.out.println(keyType.getName());
            System.out.println(keyType.getGenericDeclaration());
            Type[] types = keyType.getBounds();
            for(Type type : types){
                System.out.println(type.getTypeName());
            }

            System.out.println("-----------------------------------------------------------------");

            Field value = TypeVariableBean.class.getDeclaredField("value");
            TypeVariable valueType = (TypeVariable)value.getGenericType();
            System.out.println(valueType.getName());
            System.out.println(valueType.getGenericDeclaration());
            Type[] typeArray = valueType.getBounds();
            for(Type type : typeArray){
                System.out.println(type.getTypeName());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }



}
