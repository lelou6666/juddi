package org.apache.juddi.test;

import javax.jws.WebService;

import org.apache.juddi.v3.annotations.UDDIService;
import org.apache.juddi.v3.annotations.UDDIServiceBinding;


@UDDIService(
		businessKey="uddi:juddi.apache.org:businesses-asf",
		serviceKey="uddi:juddi.apache.org:services-helloworld", 
		description = "Hello World test service")
@UDDIServiceBinding(
		bindingKey="uddi:juddi.apache.org:bindings-helloworld-wsdl",
	    description="WSDL endpoint for the HelloWorld Service. This service is used for "
				  + "testing the jUDDI annotation functionality",
	    accessPointType="wsdlDeployment",
	    accessPoint="http://${serverName}:${serverPort}/subscription-listener/services/helloworld?wsdl")
@WebService(
		endpointInterface = "org.apache.juddi.test.HelloWorld",
        serviceName = "HelloWorld")

public class HelloWorldImpl implements HelloWorld {
    
    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
	
}