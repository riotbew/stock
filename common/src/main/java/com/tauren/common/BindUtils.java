package com.tauren.common;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * @author AwesomeJim  E-MAIL:AwesomeJim@foxmail.com
 */
public class BindUtils {
    public static void initBindView(Object curClass, View sourceView){
        // 通过反射获取到全部属性，反射的字段可能是一个类（静态）字段或实例字段
        Field[] fields = curClass.getClass().getDeclaredFields();
        if(fields != null && fields.length > 0){
            BindView bindView = null;
            for(Field field : fields){
                //获取自定义的注解类标志
                bindView = field.getAnnotation(BindView.class);
                if(bindView != null){
                    int viewId = bindView.id();
                    boolean clickBoolean = bindView.click();
                    try {
                        //反射访问私有成员，必须加上这句
                        field.setAccessible(true);
                        if (clickBoolean) {
                            sourceView.findViewById(viewId).setOnClickListener(
                                    (View.OnClickListener) curClass);
                        }
                        //将currentClass的field赋值为sourceView.findViewById(viewId)
                        //给我们要找的字段设置值
                        field.set(curClass, sourceView.findViewById(viewId));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 必须在setContentView之后调用
     *
     * @param aty
     */
    public static void initBindView(Activity aty) {
        initBindView(aty, aty.getWindow().getDecorView());
    }
}
