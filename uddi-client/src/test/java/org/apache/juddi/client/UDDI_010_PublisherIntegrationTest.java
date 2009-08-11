/*
 * Copyright 2001-2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.juddi.client;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.Registry;
import org.apache.log4j.helpers.Loader;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.client.config.ClientConfig;
import org.uddi.api_v3.client.config.Property;
import org.uddi.api_v3.client.transport.InVMTransport;
import org.uddi.api_v3.client.transport.Transport;
import org.uddi.v3_service.UDDISecurityPortType;

/**
 * @author <a href="mailto:kstam@apache.org">Kurt T Stam</a>
 */
public class UDDI_010_PublisherIntegrationTest {
	
	@BeforeClass
	public static void startRegistry() throws ConfigurationException {
		String clazz = ClientConfig.getConfiguration().getString(Property.UDDI_PROXY_TRANSPORT,Property.DEFAULT_UDDI_PROXY_TRANSPORT);
		if (InVMTransport.class.getName().equals(clazz)) {
			Registry.start();
		}
	}
	
	@AfterClass
	public static void stopRegistry() throws ConfigurationException {
		String clazz = ClientConfig.getConfiguration().getString(Property.UDDI_PROXY_TRANSPORT,Property.DEFAULT_UDDI_PROXY_TRANSPORT);
		if (InVMTransport.class.getName().equals(clazz)) {
			Registry.stop();
		}
	}
	
     @Test
     public void testAuthToken() {
	     try {
	    	 String clazz = ClientConfig.getConfiguration().getString(Property.UDDI_PROXY_TRANSPORT,Property.DEFAULT_UDDI_PROXY_TRANSPORT);
	         Class<?> transportClass = Loader.loadClass(clazz);
	         if (transportClass!=null) {
	        	 Transport transport = (Transport) transportClass.newInstance();
	        	 
	        	 UDDISecurityPortType securityService = transport.getUDDISecurityService();
	        	 GetAuthToken getAuthToken = new GetAuthToken();
	        	 getAuthToken.setUserID("root");
	        	 getAuthToken.setCred("");
	        	 AuthToken authToken = securityService.getAuthToken(getAuthToken);
	        	 System.out.println(authToken.getAuthInfo());
	        	 Assert.assertNotNull(authToken);
	         } else {
	        	 Assert.fail();
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	         Assert.fail();
	     } 
     }
	
}