import java.lang.reflect.*;

/**
 * Created by zhaobing04 on 2019/2/14.
 */
public class TypeTest {

    public static void main(String args[]){
        testParameterizedType();

//        testTypeVariable();

//        testGeenericArrayType();

//        testWildcardType();
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
            getParameterizedTypeMes("aList");

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
        System.out.println("is ParameterizedType : " + b);

        boolean c = f.getGenericType() instanceof Class;
        System.out.println("is Class : " + c );
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

    public static void testGeenericArrayType(){
        GenericArrayTypeBean bean = new GenericArrayTypeBean();
        Field[] fieldList = bean.getClass().getDeclaredFields();
        if(fieldList != null && fieldList.length > 0){
            for(Field field : fieldList){
                Type type = field.getGenericType();
                System.out.println(field.getName() + " typename = " + type.getTypeName());
                if(type instanceof GenericArrayType){
                    System.out.println("----------- type instanceof GenericArrayType -----------");
                    System.out.println(((GenericArrayType)type).getGenericComponentType());
                }

            }
        }
    }

    public static void testWildcardType(){
        Field[] fields = WildcardTypeBean.class.getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field : fields){
                Type type = field.getGenericType();
                System.out.println(field.getName() + "---" + type.getTypeName());

                System.out.println("-------------分段显示---------------------");
                Type[] types = ((ParameterizedType)type).getActualTypeArguments();
                for(Type type1 : types){
                    if(type1 instanceof WildcardType){
                        System.out.println(((WildcardType)type1).getTypeName());
                        Type[] wildcardTypeList = ((WildcardType)type1).getUpperBounds();
                        if(wildcardTypeList != null && wildcardTypeList.length > 0){
                            for(Type type2 : wildcardTypeList){
                                if(type2 instanceof Class){
                                    System.out.println(type2.getTypeName() + "---- is Class类型");
                                }
                            }
                        }
                    }
                }
                System.out.println("");

            }
        }

    }

}
