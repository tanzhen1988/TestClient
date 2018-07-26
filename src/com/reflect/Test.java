/*
Java反射机制主要提供了以下功能： 在运行时判断任意一个对象所属的类；在运行时构造任意一个类的对象；在运行时判断任意一个类所具有的成员变量和方法；在运行时调用任意一个对象的方法；生成动态代理。
1. 得到某个对象的属性

1 public Object getProperty(Object owner, String fieldName) throws Exception {
2     Class ownerClass = owner.getClass();
3 
4     Field field = ownerClass.getField(fieldName);
5 
6     Object property = field.get(owner);
7 
8     return property;
9 }
Class ownerClass = owner.getClass()：得到该对象的Class。

Field field = ownerClass.getField(fieldName)：通过Class得到类声明的属性。

Object property = field.get(owner)：通过对象得到该属性的实例，如果这个属性是非公有的，这里会报IllegalAccessException。

2. 得到某个类的静态属性

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

Class ownerClass = Class.forName(className) ：首先得到这个类的Class。

Field field = ownerClass.getField(fieldName)：和上面一样，通过Class得到类声明的属性。

Object property = field.get(ownerClass) ：这里和上面有些不同，因为该属性是静态的，所以直接从类的Class里取。

3. 执行某对象的方法

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
Class owner_class = owner.getClass() ：首先还是必须得到这个对象的Class。

5～9行：配置参数的Class数组，作为寻找Method的条件。

Method method = ownerClass.getMethod(methodName, argsClass)：通过Method名和参数的Class数组得到要执行的Method。

method.invoke(owner, args)：执行该Method，invoke方法的参数是执行这个方法的对象，和参数数组。返回值是Object，也既是该方法的返回值。

4. 执行某个类的静态方法

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

基本的原理和实例3相同，不同点是最后一行，invoke的一个参数是null，因为这是静态方法，不需要借助实例运行。

5. 新建实例
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

这里说的方法是执行带参数的构造函数来新建实例的方法。如果不需要参数，可以直接使用newoneClass.newInstance()来实现。

Class newoneClass = Class.forName(className)：第一步，得到要构造的实例的Class。

第5～第9行：得到参数的Class数组。

Constructor cons = newoneClass.getConstructor(argsClass)：得到构造子。

cons.newInstance(args)：新建实例。

6. 判断是否为某个类的实例

1 public boolean isInstance(Object obj, Class cls) {
2     return cls.isInstance(obj);
3 }

7. 得到数组中的某个元素
1 public Object getByArray(Object array, int index) {
2     return Array.get(array,index);
3 }
*/
package com.reflect;

import java.lang.reflect.Method;

public class Test {
	/*
	如果想要完成动态代理，首先需要定义一个InvocationHandler接口的子类，已完成代理的具体操作。

	package Reflect;
	import java.lang.reflect.*;
	 
	//定义项目接口
	interface Subject {
	    public String say(String name, int age);
	}
	 
	// 定义真实项目
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
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: tanzhen  2014-2-24 下午1:54:21
	 * @param args    
	 * @return void 
	 */
	public static void main(String[] args) {
		try {
			Class<?> cls=Class.forName("com.reflect.User");
			Class<?> intes[]=cls.getInterfaces();
		        for (int i = 0; i < intes.length; i++) {
		            System.out.println("实现的接口   "+intes[i].getName());
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
	 * 获得obj对象相应属性fieldName的值
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
	 * 设置对象obj的属性fieldName的值为value
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
