/*
Java���������Ҫ�ṩ�����¹��ܣ� ������ʱ�ж�����һ�������������ࣻ������ʱ��������һ����Ķ���������ʱ�ж�����һ���������еĳ�Ա�����ͷ�����������ʱ��������һ������ķ��������ɶ�̬����
1. �õ�ĳ�����������

1 public Object getProperty(Object owner, String fieldName) throws Exception {
2     Class ownerClass = owner.getClass();
3 
4     Field field = ownerClass.getField(fieldName);
5 
6     Object property = field.get(owner);
7 
8     return property;
9 }
Class ownerClass = owner.getClass()���õ��ö����Class��

Field field = ownerClass.getField(fieldName)��ͨ��Class�õ������������ԡ�

Object property = field.get(owner)��ͨ������õ������Ե�ʵ���������������Ƿǹ��еģ�����ᱨIllegalAccessException��

2. �õ�ĳ����ľ�̬����

 1 public Object getStaticProperty(String className, String fieldName)
 2             throws Exception {
 3     Class ownerClass = Class.forName(className);
 4 
 5     Field field = ownerClass.getField(fieldName);
 6 
 7     Object property = field.get(ownerClass);
 8 
 9     return property;
10 }

Class ownerClass = Class.forName(className) �����ȵõ�������Class��

Field field = ownerClass.getField(fieldName)��������һ����ͨ��Class�õ������������ԡ�

Object property = field.get(ownerClass) �������������Щ��ͬ����Ϊ�������Ǿ�̬�ģ�����ֱ�Ӵ����Class��ȡ��

3. ִ��ĳ����ķ���

 1 public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
 2 
 3     Class ownerClass = owner.getClass();
 4 
 5     Class[] argsClass = new Class[args.length];
 6 
 7     for (int i = 0, j = args.length; i < j; i++) {
 8         argsClass[i] = args[i].getClass();
 9     }
10 
11     Method method = ownerClass.getMethod(methodName, argsClass);
12 
13     return method.invoke(owner, args);
14 }
Class owner_class = owner.getClass() �����Ȼ��Ǳ���õ���������Class��

5��9�У����ò�����Class���飬��ΪѰ��Method��������

Method method = ownerClass.getMethod(methodName, argsClass)��ͨ��Method���Ͳ�����Class����õ�Ҫִ�е�Method��

method.invoke(owner, args)��ִ�и�Method��invoke�����Ĳ�����ִ����������Ķ��󣬺Ͳ������顣����ֵ��Object��Ҳ���Ǹ÷����ķ���ֵ��

4. ִ��ĳ����ľ�̬����

 1 public Object invokeStaticMethod(String className, String methodName,
 2             Object[] args) throws Exception {
 3     Class ownerClass = Class.forName(className);
 4 
 5     Class[] argsClass = new Class[args.length];
 6 
 7     for (int i = 0, j = args.length; i < j; i++) {
 8         argsClass[i] = args[i].getClass();
 9     }
10 
11     Method method = ownerClass.getMethod(methodName, argsClass);
12 
13     return method.invoke(null, args);
14 }

������ԭ���ʵ��3��ͬ����ͬ�������һ�У�invoke��һ��������null����Ϊ���Ǿ�̬����������Ҫ����ʵ�����С�

5. �½�ʵ��
 1 
 2 public Object newInstance(String className, Object[] args) throws Exception {
 3     Class newoneClass = Class.forName(className);
 4 
 5     Class[] argsClass = new Class[args.length];
 6 
 7     for (int i = 0, j = args.length; i < j; i++) {
 8         argsClass[i] = args[i].getClass();
 9     }
10 
11     Constructor cons = newoneClass.getConstructor(argsClass);
12 
13     return cons.newInstance(args);
14 
15 }

����˵�ķ�����ִ�д������Ĺ��캯�����½�ʵ���ķ������������Ҫ����������ֱ��ʹ��newoneClass.newInstance()��ʵ�֡�

Class newoneClass = Class.forName(className)����һ�����õ�Ҫ�����ʵ����Class��

��5����9�У��õ�������Class���顣

Constructor cons = newoneClass.getConstructor(argsClass)���õ������ӡ�

cons.newInstance(args)���½�ʵ����

6. �ж��Ƿ�Ϊĳ�����ʵ��

1 public boolean isInstance(Object obj, Class cls) {
2     return cls.isInstance(obj);
3 }

7. �õ������е�ĳ��Ԫ��
1 public Object getByArray(Object array, int index) {
2     return Array.get(array,index);
3 }
*/
package com.reflect;

import java.lang.reflect.Method;

public class Test {
	/*
	�����Ҫ��ɶ�̬����������Ҫ����һ��InvocationHandler�ӿڵ����࣬����ɴ���ľ��������

	package Reflect;
	import java.lang.reflect.*;
	 
	//������Ŀ�ӿ�
	interface Subject {
	    public String say(String name, int age);
	}
	 
	// ������ʵ��Ŀ
	class RealSubject implements Subject {
	    @Override
	    public String say(String name, int age) {
	        return name + "  " + age;
	    }
	}
	 
	class MyInvocationHandler implements InvocationHandler {
	    private Object obj = null;
	 
	    public Object bind(Object obj) {
	        this.obj = obj;
	        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
	                .getClass().getInterfaces(), this);
	    }
	 
	    @Override
	    public Object invoke(Object proxy, Method method, Object[] args)
	            throws Throwable {
	        Object temp = method.invoke(this.obj, args);
	        return temp;
	    }
	}
	 
	class hello {
	    public static void main(String[] args) {
	        MyInvocationHandler demo = new MyInvocationHandler();
	        Subject sub = (Subject) demo.bind(new RealSubject());
	        String info = sub.say("Rollen", 20);
	        System.out.println(info);
	    }
	}
    */

	/** 
	 * �����÷����Ĺ��ܼ��㷨����
	 *
	 * @autor: tanzhen  2014-2-24 ����1:54:21
	 * @param args    
	 * @return void 
	 */
	public static void main(String[] args) {
		try {
			Class<?> cls=Class.forName("com.reflect.User");
			Class<?> intes[]=cls.getInterfaces();
		        for (int i = 0; i < intes.length; i++) {
		            System.out.println("ʵ�ֵĽӿ�   "+intes[i].getName());
		        }
			User user=(User)cls.newInstance();
			setFieldValue(user,"name","java.lang.String","tanzhen");
			user.setAge(10);
			String name=(String)getFieldValue(user,"name");
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * ���obj������Ӧ����fieldName��ֵ
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getFieldValue(Object obj, String fieldName) {
		Class dealClass = obj.getClass();
		String methodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
		try {
			Method dealMethod = dealClass.getMethod(methodName, null);
			Object returnValue = dealMethod.invoke(obj, null);
			return returnValue;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ���ö���obj������fieldName��ֵΪvalue
	 * @param obj
	 * @param fieldName
	 * @param fieldType
	 * @param value
	 * @return
	 */
	public static boolean setFieldValue(Object obj, String fieldName, String fieldType, Object value) {
		if(obj!=null) {
			Class dealClass = obj.getClass();
			try {
				String methodName = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				Method dealMethod = dealClass.getMethod(methodName, Class.forName(fieldType));	//new Class[]{Class.forName(fieldType)}
				dealMethod.invoke(obj, value);	//new Object[]{value}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}
