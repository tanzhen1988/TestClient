
public class B extends A {

	
	@Override
	public void test1() {
		System.out.println("B test1");
	}
	
	public void test1(String test){
		System.out.println("B"+test);
	}
	
	public void test2(){
		System.out.println("B test2");
	}

	/** 
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: tanzhen  2014-3-5 上午9:33:53
	 * @param args    
	 * @return void 
	 */
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		a.test1();
		a=b;
		a.test1();
		b.test1();
		b.test1("test");
		b.test2();
	}

}
