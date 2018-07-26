import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ClientJWS {

	/**
	 * �����÷����Ĺ��ܼ��㷨����
	 * http://wenwen.sogou.com/z/q184737400.htm
	 * @autor: tanzhen 2014-2-11 ����2:56:37
	 * @param args
	 * @return void
	 * @throws ServiceException 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException, ServiceException {
		
		String endPoint = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";
		String operation = "getRegionCountry";
		String sopaction= "http://WebXml.com.cn/getRegionCountry";
		Object[] objs = new Object[]{};
		//invokeWEBService(endPoint, operation, objs ,sopaction);

		invoke();
	}
	
	

	public static void invokeWEBService(String url,String operation,Object[] objs,String sopAcion) {
		try {

			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setReturnClass(java.lang.String[].class);
			call.setOperationName(operation);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(sopAcion);
			String[] results = (String[])call.invoke(objs);
			if (null != results) {
				for (int i = 0; i < results.length; i++) {
					System.out.println(results[i]);
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
     * axis2 client动态调用WCF webservice
     * @param endpoint wsdl地址,如http://172.16.5.21/WcfService2/Service1.svc?wsdl
     * @param SOAPActionURI 调用的方法action地址如"wsdl:input wsaw:Action="http://tempuri.org/IService1/GetData1" message="tns:IService1_GetData1_InputMessage" 
     * @param namespaceURI命名空间如targetNamespace="http://tempuri.org/"
     * @param methodName方法名如wsdl:operation name="GetData1"
     * @param parameterName方法变量名
     * @param parameterValue方法变量值
     * @return返回值
     * @throws ServiceException
     * @throws MalformedURLException
     * @throws RemoteException
     */
    public static String invokeWCF(String endpoint,String SOAPActionURI,String namespaceURI,String methodName,String parameterName,String parameterValue) 
            throws ServiceException, MalformedURLException, RemoteException
    {

        List<HashMap<String, Object>> mList = new ArrayList<HashMap<String,Object>>();
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(endpoint));
        call.addParameter(new QName(namespaceURI,parameterName),org.apache.axis.Constants.XSD_STRING,ParameterMode.IN);
        call.setReturnType(org.apache.axis.Constants.XSD_STRING);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI(SOAPActionURI);
        call.setOperationName(new QName(namespaceURI, methodName));
        String remsg=(String) call.invoke(new java.lang.Object[] {parameterValue});
        return remsg;
    }

    public static void invoke() throws ServiceException, RemoteException{
    	String endPoint = " http://webservice.ips.com.cn/web/Service.asmx";
    	Service service = new Service();
    	Call call;
    	call = (Call) service.createCall();
    	call.setTargetEndpointAddress(endPoint);
    	call.setReturnClass(java.lang.String.class);
    	call.setOperationName("GetBankList");
    	call.setUseSOAPAction(true);
    	call.setSOAPActionURI("http://IpsBankList/GetBankList");
    	call.addParameter("MerCode", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
    	call.addParameter("SignMD5", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
    	String result = (String) call.invoke(new Object[]{"test","whatever"});
    	System.out.println(result);
    }
}
