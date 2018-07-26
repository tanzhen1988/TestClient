
public class HelloWorldJWS {

	public String test(String a,Integer b){
		String result="a="+a+",b="+b;
		System.out.println("Received:"+result);
		return "Server Repsonse Ok,you said:"+result;
	} 

}
