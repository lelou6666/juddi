/*
 * Copyright 2001-2008 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package org.uddi.v3_service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.uddi.api_v3.DispositionReport;
import org.uddi.subr_v3.NotifySubscriptionListener;


/**
 * This portType defines all of the UDDI subscriptionListener operations.
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.5-b03-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "UDDI_SubscriptionListener_PortType", targetNamespace = "urn:uddi-org:subr_v3_portType")
@XmlSeeAlso({
    org.uddi.custody_v3.ObjectFactory.class,
    org.uddi.repl_v3.ObjectFactory.class,
    org.uddi.subr_v3.ObjectFactory.class,
    org.uddi.api_v3.ObjectFactory.class,
    org.uddi.vscache_v3.ObjectFactory.class,
    org.uddi.vs_v3.ObjectFactory.class,
    org.uddi.sub_v3.ObjectFactory.class,
    org.w3._2000._09.xmldsig_.ObjectFactory.class,
    org.uddi.policy_v3.ObjectFactory.class,
    org.uddi.policy_v3_instanceparms.ObjectFactory.class
})
public interface UDDISubscriptionListenerPortType extends Remote{


    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.DispositionReport
     * @throws DispositionReportFaultMessage
     */
    @WebMethod(operationName = "notify_subscriptionListener", action = "notify_subscriptionListener")
    @WebResult(name = "dispositionReport", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public DispositionReport notifySubscriptionListener(
        @WebParam(name = "notify_subscriptionListener", targetNamespace = "urn:uddi-org:subr_v3", partName = "body")
        NotifySubscriptionListener body)
        throws DispositionReportFaultMessage, RemoteException
    ;

}
