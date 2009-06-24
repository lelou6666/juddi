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
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.FindBinding;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.FindRelatedBusinesses;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.FindTModel;
import org.uddi.api_v3.GetBindingDetail;
import org.uddi.api_v3.GetBusinessDetail;
import org.uddi.api_v3.GetOperationalInfo;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.GetTModelDetail;
import org.uddi.api_v3.OperationalInfos;
import org.uddi.api_v3.RelatedBusinessesList;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceList;
import org.uddi.api_v3.TModelDetail;
import org.uddi.api_v3.TModelList;


/**
 * This portType defines all of the UDDI inquiry operations.
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.5-b03-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "UDDI_Inquiry_PortType", targetNamespace = "urn:uddi-org:api_v3_portType")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
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
public interface UDDIInquiryPortType extends Remote{


    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.BindingDetail
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "find_binding", action = "find_binding")
    @WebResult(name = "bindingDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public BindingDetail findBinding(
        @WebParam(name = "find_binding", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        FindBinding body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.BusinessList
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "find_business", action = "find_business")
    @WebResult(name = "businessList", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public BusinessList findBusiness(
        @WebParam(name = "find_business", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        FindBusiness body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.RelatedBusinessesList
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "find_relatedBusinesses", action = "find_relatedBusinesses")
    @WebResult(name = "relatedBusinessesList", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public RelatedBusinessesList findRelatedBusinesses(
        @WebParam(name = "find_relatedBusinesses", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        FindRelatedBusinesses body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.ServiceList
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "find_service", action = "find_service")
    @WebResult(name = "serviceList", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public ServiceList findService(
        @WebParam(name = "find_service", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        FindService body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.TModelList
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "find_tModel", action = "find_tModel")
    @WebResult(name = "tModelList", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public TModelList findTModel(
        @WebParam(name = "find_tModel", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        FindTModel body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.BindingDetail
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "get_bindingDetail", action = "get_bindingDetail")
    @WebResult(name = "bindingDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public BindingDetail getBindingDetail(
        @WebParam(name = "get_bindingDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        GetBindingDetail body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.BusinessDetail
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "get_businessDetail", action = "get_businessDetail")
    @WebResult(name = "businessDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public BusinessDetail getBusinessDetail(
        @WebParam(name = "get_businessDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        GetBusinessDetail body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.OperationalInfos
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "get_operationalInfo", action = "get_operationalInfo")
    @WebResult(name = "operationalInfos", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public OperationalInfos getOperationalInfo(
        @WebParam(name = "get_operationalInfo", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        GetOperationalInfo body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.ServiceDetail
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "get_serviceDetail", action = "get_serviceDetail")
    @WebResult(name = "serviceDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public ServiceDetail getServiceDetail(
        @WebParam(name = "get_serviceDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        GetServiceDetail body)
        throws DispositionReportFaultMessage, RemoteException
    ;

    /**
     * 
     * @param body
     * @return
     *     returns org.uddi.api_v3.TModelDetail
     * @throws DispositionReportFaultMessage, RemoteException
     */
    @WebMethod(operationName = "get_tModelDetail", action = "get_tModelDetail")
    @WebResult(name = "tModelDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
    public TModelDetail getTModelDetail(
        @WebParam(name = "get_tModelDetail", targetNamespace = "urn:uddi-org:api_v3", partName = "body")
        GetTModelDetail body)
        throws DispositionReportFaultMessage, RemoteException
    ;

}
