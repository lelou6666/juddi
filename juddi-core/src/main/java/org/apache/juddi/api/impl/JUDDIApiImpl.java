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
package org.apache.juddi.api.impl;

import java.io.StringWriter;
import java.math.BigInteger;
import java.rmi.RemoteException;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
>>>>>>> refs/remotes/apache/master
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.ws.Holder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.ClassUtil;
<<<<<<< HEAD
=======
import static org.apache.juddi.api.impl.JUDDIApiImpl.sub;
import org.apache.juddi.api.util.JUDDIQuery;
import org.apache.juddi.api.util.QueryStatus;
>>>>>>> refs/remotes/apache/master
import org.apache.juddi.api_v3.AdminSaveBusinessWrapper;
import org.apache.juddi.api_v3.AdminSaveTModelWrapper;
import org.apache.juddi.api_v3.Clerk;
import org.apache.juddi.api_v3.ClerkDetail;
import org.apache.juddi.api_v3.ClerkList;
import org.apache.juddi.api_v3.ClientSubscriptionInfoDetail;
import org.apache.juddi.api_v3.DeleteClerk;
import org.apache.juddi.api_v3.DeleteClientSubscriptionInfo;
import org.apache.juddi.api_v3.DeleteNode;
import org.apache.juddi.api_v3.DeletePublisher;
import org.apache.juddi.api_v3.GetAllPublisherDetail;
<<<<<<< HEAD
=======
import org.apache.juddi.api_v3.GetClientSubscriptionInfoDetail;
import org.apache.juddi.api_v3.GetEntityHistoryMessageRequest;
import org.apache.juddi.api_v3.GetEntityHistoryMessageResponse;
import org.apache.juddi.api_v3.GetFailedReplicationChangeRecordsMessageRequest;
import org.apache.juddi.api_v3.GetFailedReplicationChangeRecordsMessageResponse;
>>>>>>> refs/remotes/apache/master
import org.apache.juddi.api_v3.GetPublisherDetail;
import org.apache.juddi.api_v3.Node;
import org.apache.juddi.api_v3.NodeDetail;
import org.apache.juddi.api_v3.NodeList;
import org.apache.juddi.api_v3.PublisherDetail;
import org.apache.juddi.api_v3.SaveClerkInfo;
import org.apache.juddi.api_v3.SaveClientSubscriptionInfo;
import org.apache.juddi.api_v3.SaveNodeInfo;
import org.apache.juddi.api_v3.SavePublisher;
import org.apache.juddi.api_v3.SubscriptionWrapper;
import org.apache.juddi.api_v3.SyncSubscription;
import org.apache.juddi.api_v3.SyncSubscriptionDetail;
<<<<<<< HEAD
import org.apache.juddi.api_v3.ValidValues;
=======
import org.apache.juddi.config.AppConfig;
>>>>>>> refs/remotes/apache/master
import org.apache.juddi.config.PersistenceManager;
import org.apache.juddi.config.Property;
import org.apache.juddi.mapping.MappingApiToModel;
import org.apache.juddi.mapping.MappingModelToApi;
import org.apache.juddi.model.BusinessEntity;
import org.apache.juddi.model.ChangeRecord;
import org.apache.juddi.model.ClientSubscriptionInfo;
import org.apache.juddi.model.Node;
import org.apache.juddi.model.Publisher;
import org.apache.juddi.model.ReplicationConfiguration;
import org.apache.juddi.model.Tmodel;
import org.apache.juddi.model.UddiEntityPublisher;
<<<<<<< HEAD
import org.apache.juddi.model.ValueSetValue;
import org.apache.juddi.model.ValueSetValues;
=======
import org.apache.juddi.replication.ReplicationNotifier;
>>>>>>> refs/remotes/apache/master
import org.apache.juddi.subscription.NotificationList;
import org.apache.juddi.subscription.notify.TemporaryMailContainer;
import org.apache.juddi.subscription.notify.USERFRIENDLYSMTPNotifier;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.error.ErrorMessage;
import org.apache.juddi.v3.error.FatalErrorException;
import org.apache.juddi.v3.error.InvalidKeyPassedException;
import org.apache.juddi.v3.error.InvalidValueException;
import org.apache.juddi.v3.error.UserMismatchException;
import org.apache.juddi.v3_service.JUDDIApiPortType;
import org.apache.juddi.validation.ValidateClerk;
import org.apache.juddi.validation.ValidateClientSubscriptionInfo;
import org.apache.juddi.validation.ValidateNode;
import org.apache.juddi.validation.ValidatePublish;
import org.apache.juddi.validation.ValidatePublisher;
<<<<<<< HEAD
import org.apache.juddi.validation.ValidateValueSetValidation;
import org.uddi.api_v3.DeleteTModel;
import org.uddi.api_v3.DispositionReport;
import org.uddi.api_v3.Result;
import org.uddi.repl_v3.ReplicationConfiguration;
=======
import org.apache.juddi.validation.ValidateReplication;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessInfos;
import org.uddi.api_v3.Contact;
import org.uddi.api_v3.DeleteTModel;
import org.uddi.api_v3.DispositionReport;
import org.uddi.api_v3.GetRegisteredInfo;
import org.uddi.api_v3.InfoSelection;
import org.uddi.api_v3.PersonName;
import org.uddi.api_v3.RegisteredInfo;
import org.uddi.api_v3.Result;
import org.uddi.api_v3.SaveBusiness;
import org.uddi.api_v3.SaveTModel;
import org.uddi.api_v3.TModelInfo;
import org.uddi.api_v3.TModelInfos;
import org.uddi.repl_v3.ChangeRecords;
import org.uddi.repl_v3.CommunicationGraph;
import org.uddi.repl_v3.Operator;
import org.uddi.repl_v3.OperatorStatusType;
>>>>>>> refs/remotes/apache/master
import org.uddi.sub_v3.GetSubscriptionResults;
import org.uddi.sub_v3.Subscription;
import org.uddi.sub_v3.SubscriptionResultsList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDISubscriptionPortType;

/**
<<<<<<< HEAD
 * Implements the jUDDI API service
 *
 * As of 3.3, this interface and implementation has significantly changed and is
 * not backwards compatible.
=======
 * Implements the jUDDI API service. These methods are outside of the UDDI spec
 * and are specific to jUDDI. They are primarily used for administrative
 * functions.
>>>>>>> refs/remotes/apache/master
 *
 * @author <a href="mailto:jfaath@apache.org">Jeff Faath</a>
 * @author <a href="mailto:kstam@apache.org">Kurt T Stam</a>
 * @author <a href="mailto:alexoree@apache.org">Alex O'Ree</a>
 */
@WebService(serviceName = "JUDDIApiService",
        endpointInterface = "org.apache.juddi.v3_service.JUDDIApiPortType",
<<<<<<< HEAD
        targetNamespace = "urn:juddi-apache-org:v3_service")
public class JUDDIApiImpl extends AuthenticatedService implements JUDDIApiPortType {

        private Log log = LogFactory.getLog(this.getClass());

        /**
         * Saves publisher(s) to the persistence layer. This method is specific
         * to jUDDI.
         */
        @Override
        public PublisherDetail savePublisher(SavePublisher body)
                throws DispositionReportFaultMessage {

=======
        targetNamespace = "urn:juddi-apache-org:v3_service", wsdlLocation = "classpath:/juddi_api_v1.wsdl")
public class JUDDIApiImpl extends AuthenticatedService implements JUDDIApiPortType {

        private Log log = LogFactory.getLog(this.getClass());
        private UDDIServiceCounter serviceCounter = ServiceCounterLifecycleResource.getServiceCounter(this.getClass());

        /**
         * Saves publisher(s) to the persistence layer. This method is specific
         * to jUDDI. Administrative privilege required.
         *
         * @param body
         * @return PublisherDetail
         * @throws DispositionReportFaultMessage
         */
        public PublisherDetail savePublisher(SavePublisher body)
                throws DispositionReportFaultMessage {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, body.getAuthInfo());

                        new ValidatePublish(publisher).validateSavePublisher(em, body);

                        PublisherDetail result = new PublisherDetail();

                        List<org.apache.juddi.api_v3.Publisher> apiPublisherList = body.getPublisher();
                        for (org.apache.juddi.api_v3.Publisher apiPublisher : apiPublisherList) {

                                org.apache.juddi.model.Publisher modelPublisher = new org.apache.juddi.model.Publisher();

                                MappingApiToModel.mapPublisher(apiPublisher, modelPublisher);

                                Object existingUddiEntity = em.find(modelPublisher.getClass(), modelPublisher.getAuthorizedName());
                                if (existingUddiEntity != null) {
                                        em.remove(existingUddiEntity);
                                }

                                em.persist(modelPublisher);

                                result.getPublisher().add(apiPublisher);
                        }

                        tx.commit();
<<<<<<< HEAD
                        return result;
=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SAVE_PUBLISHER,
                                QueryStatus.SUCCESS, procTime);
                        return result;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SAVE_PUBLISHER,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
        }

        /**
         * Deletes publisher(s) from the persistence layer. This method is
<<<<<<< HEAD
         * specific to jUDDI.
=======
         * specific to jUDDI. Administrative privilege required. Also removes
         * all registered business entities of the user and marks all created
         * tModels as "deleted" but not does not remove the tModel from
         * persistence. All subscriptions are also destroyed
         *
         * @param body
         * @throws DispositionReportFaultMessage
>>>>>>> refs/remotes/apache/master
         */
        @Override
        public void deletePublisher(DeletePublisher body)
                throws DispositionReportFaultMessage {
<<<<<<< HEAD

=======
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, body.getAuthInfo());

                        new ValidatePublish(publisher).validateDeletePublisher(em, body);

                        List<String> entityKeyList = body.getPublisherId();
<<<<<<< HEAD
                        for (String entityKey : entityKeyList) {
                                Object obj = em.find(org.apache.juddi.model.Publisher.class, entityKey);
=======
                        List<Publisher> deletedPubs = new ArrayList<Publisher>();
                        for (String entityKey : entityKeyList) {
                                Publisher obj = em.find(org.apache.juddi.model.Publisher.class, entityKey);
                                deletedPubs.add(obj);
                                //get an authtoken for this publisher so that we can get its registeredInfo
                                UDDISecurityImpl security = new UDDISecurityImpl();
                                AuthToken authToken = security.getAuthToken(entityKey);

                                GetRegisteredInfo r = new GetRegisteredInfo();
                                r.setAuthInfo(authToken.getAuthInfo());
                                r.setInfoSelection(InfoSelection.ALL);

                                log.info("removing all businesses owned by publisher " + entityKey + ".");
                                UDDIPublicationImpl publish = new UDDIPublicationImpl();
                                RegisteredInfo registeredInfo = publish.getRegisteredInfo(r);
                                BusinessInfos businessInfos = registeredInfo.getBusinessInfos();
                                if (businessInfos != null && businessInfos.getBusinessInfo() != null) {
                                        Iterator<BusinessInfo> iter = businessInfos.getBusinessInfo().iterator();
                                        while (iter.hasNext()) {
                                                BusinessInfo businessInfo = iter.next();
                                                Object business = em.find(org.apache.juddi.model.BusinessEntity.class, businessInfo.getBusinessKey());
                                                em.remove(business);
                                        }
                                }

                                log.info("mark all tmodels for publisher " + entityKey + " as deleted.");
                                TModelInfos tmodelInfos = registeredInfo.getTModelInfos();
                                if (tmodelInfos != null && tmodelInfos.getTModelInfo() != null) {
                                        Iterator<TModelInfo> iter = tmodelInfos.getTModelInfo().iterator();
                                        while (iter.hasNext()) {
                                                TModelInfo tModelInfo = iter.next();
                                                Tmodel tmodel = (Tmodel) em.find(org.apache.juddi.model.Tmodel.class, tModelInfo.getTModelKey());
                                                tmodel.setDeleted(true);
                                                em.persist(tmodel);
                                        }
                                }
                                log.info("remove all persisted AuthTokens for publisher " + entityKey + ".");
                                Query q1 = em.createQuery("DELETE FROM AuthToken auth WHERE auth.authorizedName = ?1");
                                q1.setParameter(1, entityKey);
                                q1.executeUpdate();
                                log.info("remove all subscriptions for publisher " + entityKey + ".");
                                q1 = em.createQuery("DELETE FROM Subscription s WHERE s.authorizedName = ?1");
                                q1.setParameter(1, entityKey);
                                q1.executeUpdate();

                                log.info("removing publisher " + entityKey + ".");
                                //delete the publisher
>>>>>>> refs/remotes/apache/master
                                em.remove(obj);
                        }

                        tx.commit();
<<<<<<< HEAD
=======
                        for (Publisher p: deletedPubs){
                                USERFRIENDLYSMTPNotifier.notifyAccountDeleted(new TemporaryMailContainer(null, p, (Publisher) publisher));
                        }
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.DELETE_PUBLISHER,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.DELETE_PUBLISHER,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
        }

        /**
         * Retrieves publisher(s) from the persistence layer. This method is
<<<<<<< HEAD
         * specific to jUDDI.
         */
        @Override
        public PublisherDetail getPublisherDetail(GetPublisherDetail body)
                throws DispositionReportFaultMessage {

=======
         * specific to jUDDI. Administrative privilege required.
         *
         * @param body
         * @return PublisherDetail
         * @throws DispositionReportFaultMessage
         */
        public PublisherDetail getPublisherDetail(GetPublisherDetail body)
                throws DispositionReportFaultMessage {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                new ValidatePublisher(null).validateGetPublisherDetail(body);

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        this.getEntityPublisher(em, body.getAuthInfo());

                        PublisherDetail result = new PublisherDetail();

                        List<String> publisherIdList = body.getPublisherId();
                        for (String publisherId : publisherIdList) {
                                org.apache.juddi.model.Publisher modelPublisher = null;
                                try {
                                        modelPublisher = em.find(org.apache.juddi.model.Publisher.class, publisherId);
                                } catch (ClassCastException e) {
                                }
                                if (modelPublisher == null) {
                                        throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.PublisherNotFound", publisherId));
                                }

                                org.apache.juddi.api_v3.Publisher apiPublisher = new org.apache.juddi.api_v3.Publisher();

                                MappingModelToApi.mapPublisher(modelPublisher, apiPublisher);

                                result.getPublisher().add(apiPublisher);
                        }

                        tx.commit();
<<<<<<< HEAD
                        return result;
=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_PUBLISHER_DETAIL,
                                QueryStatus.SUCCESS, procTime);
                        return result;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_PUBLISHER_DETAIL,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

        }

<<<<<<< HEAD
        @SuppressWarnings("unchecked")
        @Override
        public PublisherDetail getAllPublisherDetail(GetAllPublisherDetail body)
                throws DispositionReportFaultMessage {

=======
        /**
         * Retrieves all publisher from the persistence layer. This method is
         * specific to jUDDI. Administrative privilege required. Use caution
         * when calling, result set is not bound. If there are many publishers,
         * it is possible to have a result set that is too large
         *
         * @param body
         * @return PublisherDetail
         * @throws DispositionReportFaultMessage
         * @throws RemoteException
         */
        @SuppressWarnings("unchecked")
        public PublisherDetail getAllPublisherDetail(GetAllPublisherDetail body)
                throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                new ValidatePublisher(null).validateGetAllPublisherDetail(body);

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        this.getEntityPublisher(em, body.getAuthInfo());

                        PublisherDetail result = new PublisherDetail();

                        Query query = em.createQuery("SELECT p from Publisher as p");
                        List<Publisher> modelPublisherList = query.getResultList();

                        for (Publisher modelPublisher : modelPublisherList) {

                                org.apache.juddi.api_v3.Publisher apiPublisher = new org.apache.juddi.api_v3.Publisher();

                                MappingModelToApi.mapPublisher(modelPublisher, apiPublisher);

                                result.getPublisher().add(apiPublisher);
                        }

                        tx.commit();
<<<<<<< HEAD
                        return result;
=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_PUBLISHER_DETAIL,
                                QueryStatus.SUCCESS, procTime);
                        return result;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_PUBLISHER_DETAIL,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
        }

<<<<<<< HEAD
        @Override
        public void adminDeleteTModel(DeleteTModel body) throws DispositionReportFaultMessage {


=======
        /**
         * Completely deletes a tModel from the persistence layer.
         * Administrative privilege required. All entities that reference this
         * tModel will no longer be able to use the tModel if jUDDI Option
         * Enforce referential Integrity is enabled.<br>
         * Required permission, you must be am administrator
         * {@link Property#JUDDI_ENFORCE_REFERENTIAL_INTEGRITY}. In addition,
         * tModels that are owned by another node via replication cannot be
         * deleted using this method and will throw an exception
         *
         *
         * @param body
         * @throws DispositionReportFaultMessage
         */
        @Override
        public void adminDeleteTModel(DeleteTModel body)
                throws DispositionReportFaultMessage {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, body.getAuthInfo());

                        new ValidatePublish(publisher).validateAdminDeleteTModel(em, body);

<<<<<<< HEAD
                        List<String> entityKeyList = body.getTModelKey();
                        for (String entityKey : entityKeyList) {
                                Object obj = em.find(org.apache.juddi.model.Tmodel.class, entityKey);
                                em.remove(obj);
                        }

                        tx.commit();
=======
                        //TODO if referiental integrity is turned on, check to see if this is referenced anywhere and prevent the delete
                        List<ChangeRecord> changes = new ArrayList<ChangeRecord>();
                        List<String> entityKeyList = body.getTModelKey();
                        for (String entityKey : entityKeyList) {
                                org.apache.juddi.model.Tmodel obj = em.find(org.apache.juddi.model.Tmodel.class, entityKey);

                                if (obj == null) {
                                        throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.TModelNotFound", entityKey));
                                }
                                if (!obj.getNodeId().equals(node)) {
                                        throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.TModelNodeOwner", entityKey + " this node " + node + " owning node " + obj.getNodeId()));
                                }
                                em.remove(obj);
                                changes.add(UDDIPublicationImpl.getChangeRecord_deleteTModelDelete(entityKey, node));

                        }

                        tx.commit();
                        for (ChangeRecord cr : changes) {
                                ReplicationNotifier.Enqueue(cr);
                        }
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_DELETE_TMODEL,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_DELETE_TMODEL,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
        }

<<<<<<< HEAD
        @Override
        public void deleteClientSubscriptionInfo(DeleteClientSubscriptionInfo body)
                throws DispositionReportFaultMessage {

=======
        /**
         * Delete's a client's subscription information. This is typically used
         * for server to server subscriptions Administrative privilege required.
         *
         * @param body
         * @throws DispositionReportFaultMessage
         * @throws RemoteException
         */
        public void deleteClientSubscriptionInfo(DeleteClientSubscriptionInfo body)
                throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, body.getAuthInfo());

                        new ValidateClientSubscriptionInfo(publisher).validateDeleteClientSubscriptionInfo(em, body);

                        List<String> entityKeyList = body.getSubscriptionKey();
                        for (String entityKey : entityKeyList) {
                                Object obj = em.find(org.apache.juddi.model.ClientSubscriptionInfo.class, entityKey);
                                em.remove(obj);
                        }

                        tx.commit();
<<<<<<< HEAD
=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.DELETE_CLIENT_SUB,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.DELETE_CLIENT_SUB,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

        }

<<<<<<< HEAD
        @Override
        public ClientSubscriptionInfoDetail saveClientSubscriptionInfo(SaveClientSubscriptionInfo body)
                throws DispositionReportFaultMessage {
=======
        /**
         * Adds client subscription information. This effectively links a server
         * to serverr subscription to clerk Administrative privilege required.
         *
         * @param body
         * @return ClientSubscriptionInfoDetail
         * @throws DispositionReportFaultMessage
         * @throws RemoteException
         */
        public ClientSubscriptionInfoDetail saveClientSubscriptionInfo(SaveClientSubscriptionInfo body)
                throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, body.getAuthInfo());

                        new ValidateClientSubscriptionInfo(publisher).validateSaveClientSubscriptionInfo(em, body);

                        ClientSubscriptionInfoDetail result = new ClientSubscriptionInfoDetail();

                        List<org.apache.juddi.api_v3.ClientSubscriptionInfo> apiClientSubscriptionInfoList = body.getClientSubscriptionInfo();
                        for (org.apache.juddi.api_v3.ClientSubscriptionInfo apiClientSubscriptionInfo : apiClientSubscriptionInfoList) {

                                org.apache.juddi.model.ClientSubscriptionInfo modelClientSubscriptionInfo = new org.apache.juddi.model.ClientSubscriptionInfo();

                                MappingApiToModel.mapClientSubscriptionInfo(apiClientSubscriptionInfo, modelClientSubscriptionInfo);

                                Object existingUddiEntity = em.find(modelClientSubscriptionInfo.getClass(), modelClientSubscriptionInfo.getSubscriptionKey());
                                if (existingUddiEntity != null) {
                                        em.remove(existingUddiEntity);
                                }

                                em.persist(modelClientSubscriptionInfo);

                                result.getClientSubscriptionInfo().add(apiClientSubscriptionInfo);
                        }

                        tx.commit();
<<<<<<< HEAD
                        return result;
=======

                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SAVE_CLIENT_SUB,
                                QueryStatus.SUCCESS, procTime);
                        return result;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SAVE_CLIENT_SUB,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
        }

<<<<<<< HEAD
        @SuppressWarnings("unchecked")
        @Override
        public List<SubscriptionWrapper> getAllClientSubscriptionInfo(String authInfo) throws DispositionReportFaultMessage {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private ClientSubscriptionInfoDetail getAllClientSubscriptionInfoDetail(String authinfo)
                throws DispositionReportFaultMessage {

                new ValidateClientSubscriptionInfo(null).validateGetAllClientSubscriptionDetail(authinfo);
=======
        /**
         * Gets all client subscription information. This is used for server to
         * server subscriptions Administrative privilege required.
         *
         * @param body
         * @return ClientSubscriptionInfoDetail
         * @throws DispositionReportFaultMessage
         */
        @SuppressWarnings("unchecked")
        public ClientSubscriptionInfoDetail getAllClientSubscriptionInfoDetail(GetAllClientSubscriptionInfoDetail body)
                throws DispositionReportFaultMessage {
                long startTime = System.currentTimeMillis();
                new ValidateClientSubscriptionInfo(null).validateGetAllClientSubscriptionDetail(body);
>>>>>>> refs/remotes/apache/master

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

<<<<<<< HEAD
                        this.getEntityPublisher(em, authinfo);
=======
                        this.getEntityPublisher(em, body.getAuthInfo());
>>>>>>> refs/remotes/apache/master

                        ClientSubscriptionInfoDetail result = new ClientSubscriptionInfoDetail();

                        Query query = em.createQuery("SELECT cs from ClientSubscriptionInfo as cs");
                        List<org.apache.juddi.model.ClientSubscriptionInfo> modelClientSubscriptionInfoList = query.getResultList();

                        for (ClientSubscriptionInfo modelClientSubscriptionInfo : modelClientSubscriptionInfoList) {

                                org.apache.juddi.api_v3.ClientSubscriptionInfo apiClientSubscriptionInfo = new org.apache.juddi.api_v3.ClientSubscriptionInfo();

<<<<<<< HEAD
                                MappingModelToApi.mapClientSubscriptionInfo(modelClientSubscriptionInfo, apiClientSubscriptionInfo);
=======
                                MappingModelToApi.mapClientSubscriptionInfo(modelClientSubscriptionInfo, apiClientSubscriptionInfo, em);
>>>>>>> refs/remotes/apache/master

                                result.getClientSubscriptionInfo().add(apiClientSubscriptionInfo);
                        }

                        tx.commit();
<<<<<<< HEAD
                        return result;
=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_CLIENT_SUB,
                                QueryStatus.SUCCESS, procTime);
                        return result;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_CLIENT_SUB,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

        }

        /**
         * Retrieves clientSubscriptionKey(s) from the persistence layer. This
<<<<<<< HEAD
         * method is specific to jUDDI.
         *
         * This is in a test case, but not exposed as a web service
         */
        public ClientSubscriptionInfoDetail getClientSubscriptionInfoDetail(String authinfo, List<String> key)
                throws DispositionReportFaultMessage {

                new ValidateClientSubscriptionInfo(null).validateGetClientSubscriptionInfoDetail(authinfo, key);
=======
         * method is specific to jUDDI. Used for server to server subscriptions
         * Administrative privilege required.
         *
         * @param body
         * @return ClientSubscriptionInfoDetail
         * @throws DispositionReportFaultMessage
         */
        public ClientSubscriptionInfoDetail getClientSubscriptionInfoDetail(GetClientSubscriptionInfoDetail body)
                throws DispositionReportFaultMessage {
                long startTime = System.currentTimeMillis();
                new ValidateClientSubscriptionInfo(null).validateGetClientSubscriptionInfoDetail(body);
>>>>>>> refs/remotes/apache/master

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

<<<<<<< HEAD
                        this.getEntityPublisher(em, authinfo);

                        ClientSubscriptionInfoDetail result = new ClientSubscriptionInfoDetail();

                        List<String> subscriptionKeyList = key;
                        for (String subscriptionKey : subscriptionKeyList) {

                                org.apache.juddi.model.ClientSubscriptionInfo modelClientSubscriptionInfo = null;
=======
                        this.getEntityPublisher(em, body.getAuthInfo());

                        ClientSubscriptionInfoDetail result = new ClientSubscriptionInfoDetail();

                        List<String> subscriptionKeyList = body.getClientSubscriptionKey();
                        for (String subscriptionKey : subscriptionKeyList) {

                                org.apache.juddi.model.ClientSubscriptionInfo modelClientSubscriptionInfo = null;

>>>>>>> refs/remotes/apache/master
                                try {
                                        modelClientSubscriptionInfo = em.find(org.apache.juddi.model.ClientSubscriptionInfo.class, subscriptionKey);
                                } catch (ClassCastException e) {
                                }
                                if (modelClientSubscriptionInfo == null) {
                                        throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.SubscripKeyNotFound", subscriptionKey));
                                }

                                org.apache.juddi.api_v3.ClientSubscriptionInfo apiClientSubscriptionInfo = new org.apache.juddi.api_v3.ClientSubscriptionInfo();

<<<<<<< HEAD
                                MappingModelToApi.mapClientSubscriptionInfo(modelClientSubscriptionInfo, apiClientSubscriptionInfo);
=======
                                MappingModelToApi.mapClientSubscriptionInfo(modelClientSubscriptionInfo, apiClientSubscriptionInfo, em);
>>>>>>> refs/remotes/apache/master

                                result.getClientSubscriptionInfo().add(apiClientSubscriptionInfo);
                        }

                        tx.commit();
<<<<<<< HEAD
                        return result;
=======

                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_CLIENT_SUB,
                                QueryStatus.SUCCESS, procTime);
                        return result;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_CLIENT_SUB,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

        }

        /**
         * Saves clerk(s) to the persistence layer. This method is specific to
<<<<<<< HEAD
         * jUDDI.
         */
        @Override
        public ClerkDetail saveClerk(SaveClerkInfo body)
                throws DispositionReportFaultMessage {

=======
         * jUDDI. This is used for server to server subscriptions and for future
         * use with replication. Administrative privilege required.
         *
         * @param body
         * @return ClerkDetail
         * @throws DispositionReportFaultMessage
         */
        @Override
        public ClerkDetail saveClerk(SaveClerk body)
                throws DispositionReportFaultMessage {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, body.getAuthInfo());

                        new ValidateClerk(publisher).validateSaveClerk(em, body);

                        ClerkDetail result = new ClerkDetail();

                        List<org.apache.juddi.api_v3.Clerk> apiClerkList = body.getClerk();;
                        for (org.apache.juddi.api_v3.Clerk apiClerk : apiClerkList) {

                                org.apache.juddi.model.Clerk modelClerk = new org.apache.juddi.model.Clerk();

                                MappingApiToModel.mapClerk(apiClerk, modelClerk);
<<<<<<< HEAD

                                Object existingUddiEntity = em.find(modelClerk.getClass(), modelClerk.getClerkName());
                                if (existingUddiEntity != null) {
=======
                                org.apache.juddi.model.Node node2 = em.find(org.apache.juddi.model.Node.class, apiClerk.getNode().getName());
                                if (node2 == null) {
                                        //it doesn't exist yet
                                        node2 = new Node();
                                        MappingApiToModel.mapNode(apiClerk.getNode(), node2);
                                        em.persist(node2);
                                }

                                modelClerk.setNode(node2.getName());
                                Object existingUddiEntity = em.find(modelClerk.getClass(), modelClerk.getClerkName());
                                if (existingUddiEntity != null) {

>>>>>>> refs/remotes/apache/master
                                        em.merge(modelClerk);
                                } else {
                                        em.persist(modelClerk);
                                }

                                result.getClerk().add(apiClerk);
                        }

                        tx.commit();
<<<<<<< HEAD
                        return result;
=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SAVE_CLERK,
                                QueryStatus.SUCCESS, procTime);
                        return result;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SAVE_CLERK,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
        }

        /**
         * Saves nodes(s) to the persistence layer. This method is specific to
<<<<<<< HEAD
         * jUDDI.
         */
        @Override
        public NodeDetail saveNode(SaveNodeInfo body)
                throws DispositionReportFaultMessage {

=======
         * jUDDI. Administrative privilege required. This is used for server to
         * server subscriptions and for future use with replication.
         * Administrative privilege required.
         *
         * @param body
         * @return NodeDetail
         * @throws DispositionReportFaultMessage
         */
        public NodeDetail saveNode(SaveNode body)
                throws DispositionReportFaultMessage {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, body.getAuthInfo());

                        new ValidateNode(publisher).validateSaveNode(em, body);

                        NodeDetail result = new NodeDetail();

                        List<org.apache.juddi.api_v3.Node> apiNodeList = body.getNode();;
                        for (org.apache.juddi.api_v3.Node apiNode : apiNodeList) {

                                org.apache.juddi.model.Node modelNode = new org.apache.juddi.model.Node();

                                MappingApiToModel.mapNode(apiNode, modelNode);

                                Object existingUddiEntity = em.find(modelNode.getClass(), modelNode.getName());
                                if (existingUddiEntity != null) {
                                        em.merge(modelNode);
                                } else {
                                        em.persist(modelNode);
                                }

                                result.getNode().add(apiNode);
                        }

                        tx.commit();
<<<<<<< HEAD
                        return result;
=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SAVE_NODE,
                                QueryStatus.SUCCESS, procTime);
                        return result;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SAVE_NODE,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
        }

        /**
         * Instructs the registry to perform a synchronous subscription
         * response.
<<<<<<< HEAD
=======
         *
         * @param body
         * @return SyncSubscriptionDetail
         * @throws DispositionReportFaultMessage
         * @throws RemoteException
>>>>>>> refs/remotes/apache/master
         */
        @SuppressWarnings("unchecked")
        @Override
        public SyncSubscriptionDetail invokeSyncSubscription(
<<<<<<< HEAD
                SyncSubscription body) throws DispositionReportFaultMessage {

                //validate

                SyncSubscriptionDetail syncSubscriptionDetail = new SyncSubscriptionDetail();

                Map<String, org.apache.juddi.api_v3.ClientSubscriptionInfo> clientSubscriptionInfoMap = new HashMap<String, org.apache.juddi.api_v3.ClientSubscriptionInfo>();
=======
                SyncSubscription body) throws DispositionReportFaultMessage,
                RemoteException {
                long startTime = System.currentTimeMillis();
                //validate
                SyncSubscriptionDetail syncSubscriptionDetail = new SyncSubscriptionDetail();

                Map<String, org.apache.juddi.api_v3.ClientSubscriptionInfo> clientSubscriptionInfoMap
                        = new HashMap<String, org.apache.juddi.api_v3.ClientSubscriptionInfo>();
>>>>>>> refs/remotes/apache/master
                //find the clerks to go with these subscriptions
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        this.getEntityPublisher(em, body.getAuthInfo());
<<<<<<< HEAD
                        for (GetSubscriptionResults getSubscriptionResult : body.getList()) {
                                String subscriptionKey = getSubscriptionResult.getSubscriptionKey();
                                org.apache.juddi.model.ClientSubscriptionInfo modelClientSubscriptionInfo = null;
=======
                        for (GetSubscriptionResults getSubscriptionResult : body.getGetSubscriptionResultsList()) {
                                String subscriptionKey = getSubscriptionResult.getSubscriptionKey();
                                org.apache.juddi.model.ClientSubscriptionInfo modelClientSubscriptionInfo = null;

>>>>>>> refs/remotes/apache/master
                                try {
                                        modelClientSubscriptionInfo = em.find(org.apache.juddi.model.ClientSubscriptionInfo.class, subscriptionKey);
                                } catch (ClassCastException e) {
                                }
                                if (modelClientSubscriptionInfo == null) {
                                        throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.SubscripKeyNotFound", subscriptionKey));
                                }
                                org.apache.juddi.api_v3.ClientSubscriptionInfo apiClientSubscriptionInfo = new org.apache.juddi.api_v3.ClientSubscriptionInfo();
<<<<<<< HEAD
                                MappingModelToApi.mapClientSubscriptionInfo(modelClientSubscriptionInfo, apiClientSubscriptionInfo);
=======
                                MappingModelToApi.mapClientSubscriptionInfo(modelClientSubscriptionInfo, apiClientSubscriptionInfo, em);
>>>>>>> refs/remotes/apache/master
                                clientSubscriptionInfoMap.put(apiClientSubscriptionInfo.getSubscriptionKey(), apiClientSubscriptionInfo);
                        }

                        tx.commit();
<<<<<<< HEAD
=======
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.INVOKE_SYNCSUB,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

<<<<<<< HEAD
                for (GetSubscriptionResults getSubscriptionResult : body.getList()) {
=======
                for (GetSubscriptionResults getSubscriptionResult : body.getGetSubscriptionResultsList()) {
>>>>>>> refs/remotes/apache/master
                        try {
                                String subscriptionKey = getSubscriptionResult.getSubscriptionKey();
                                Clerk fromClerk = clientSubscriptionInfoMap.get(subscriptionKey).getFromClerk();
                                Clerk toClerk = clientSubscriptionInfoMap.get(subscriptionKey).getToClerk();
                                String clazz = fromClerk.getNode().getProxyTransport();
                                Class<?> transportClass = ClassUtil.forName(clazz, this.getClass());
<<<<<<< HEAD
                                Transport transport = (Transport) transportClass.getConstructor(String.class).newInstance(fromClerk.getNode().getName());
                                UDDISubscriptionPortType subscriptionService = transport.getUDDISubscriptionService(fromClerk.getNode().getSubscriptionUrl());
                                SubscriptionResultsList listr = subscriptionService.getSubscriptionResults(getSubscriptionResult);

                                JAXBContext context = JAXBContext.newInstance(listr.getClass());
                                Marshaller marshaller = context.createMarshaller();
                                StringWriter sw = new StringWriter();
                                marshaller.marshal(listr, sw);

                                log.info("Notification received by UDDISubscriptionListenerService : " + sw.toString());

                                NotificationList<String> nl = NotificationList.getInstance();
                                nl.getNotifications().add(sw.toString());

                                //update the registry with the notification list.
                                XRegisterHelper.handle(fromClerk, toClerk, listr);

                                syncSubscriptionDetail.getList().add(listr);
                        } catch (Exception ce) {
                                log.error(ce.getMessage(), ce);
=======
                                Transport transport = (Transport) transportClass.getConstructor(String.class
                                ).newInstance(fromClerk.getNode().getName());
                                UDDISubscriptionPortType subscriptionService = transport.getUDDISubscriptionService(fromClerk.getNode().getSubscriptionUrl());
                                SubscriptionResultsList list = subscriptionService.getSubscriptionResults(getSubscriptionResult);

                                JAXBContext context = JAXBContext.newInstance(list.getClass());
                                Marshaller marshaller = context.createMarshaller();
                                StringWriter sw = new StringWriter();

                                marshaller.marshal(list, sw);

                                log.info(
                                        "Notification received by UDDISubscriptionListenerService : " + sw.toString());

                                NotificationList<String> nl = NotificationList.getInstance();

                                nl.getNotifications()
                                        .add(sw.toString());

                                //update the registry with the notification list.
                                XRegisterHelper.handle(fromClerk, toClerk, list);

                                syncSubscriptionDetail.getSubscriptionResultsList()
                                        .add(list);
                        } catch (Exception ce) {
                                log.error(ce.getMessage(), ce);
                                long procTime = System.currentTimeMillis() - startTime;
                                serviceCounter.update(JUDDIQuery.SAVE_NODE,
                                        QueryStatus.FAILED, procTime);
>>>>>>> refs/remotes/apache/master
                                if (ce instanceof DispositionReportFaultMessage) {
                                        throw (DispositionReportFaultMessage) ce;
                                }
                                if (ce instanceof RemoteException) {
                                        DispositionReportFaultMessage x = new FatalErrorException(new ErrorMessage("errors.subscriptionnotifier.client", ce.getMessage()));
                                        throw x;
                                }
                        }
                }
                //for now sending a clean object back

<<<<<<< HEAD
=======
                long procTime = System.currentTimeMillis() - startTime;
                serviceCounter.update(JUDDIQuery.INVOKE_SYNCSUB,
                        QueryStatus.SUCCESS, procTime);
>>>>>>> refs/remotes/apache/master
                return syncSubscriptionDetail;
        }

        @Override
<<<<<<< HEAD
        public NodeList getAllNodes(String authInfo) throws DispositionReportFaultMessage {

                NodeList r = new NodeList();


=======
        public NodeList getAllNodes(String authInfo) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
                NodeList r = new NodeList();

>>>>>>> refs/remotes/apache/master
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, authInfo);

                        new ValidatePublish(publisher).validateGetAllNodes();

                        StringBuilder sql = new StringBuilder();
                        sql.append("select distinct c from Node c ");
                        sql.toString();
                        Query qry = em.createQuery(sql.toString());
                        List<org.apache.juddi.model.Node> resultList = qry.getResultList();
                        for (int i = 0; i < resultList.size(); i++) {
<<<<<<< HEAD
                                Node api = new Node();
=======
                                org.apache.juddi.api_v3.Node api = new org.apache.juddi.api_v3.Node();
>>>>>>> refs/remotes/apache/master
                                MappingModelToApi.mapNode(resultList.get(i), api);
                                r.getNode().add(api);

                        }

                        tx.commit();
<<<<<<< HEAD

=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_NODES,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_NODES,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

                return r;
        }

        @Override
<<<<<<< HEAD
        public ClerkList getAllClerks(String authInfo) throws DispositionReportFaultMessage {

=======
        public ClerkList getAllClerks(String authInfo) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                ClerkList ret = new ClerkList();
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, authInfo);

                        new ValidatePublish(publisher).validateGetAllNodes();

                        StringBuilder sql = new StringBuilder();
                        sql.append("select distinct c from Clerk c ");
                        sql.toString();
                        Query qry = em.createQuery(sql.toString());
                        List<org.apache.juddi.model.Clerk> resultList = qry.getResultList();
                        for (int i = 0; i < resultList.size(); i++) {
                                Clerk api = new Clerk();
<<<<<<< HEAD
                                MappingModelToApi.mapClerk(resultList.get(i), api);
=======
                                MappingModelToApi.mapClerk(resultList.get(i), api, em);
>>>>>>> refs/remotes/apache/master
                                ret.getClerk().add(api);

                        }
                        tx.commit();
<<<<<<< HEAD
=======
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_CLERKS,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_CLERKS,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master

                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

                return ret;

        }

        @Override
<<<<<<< HEAD
        public void deleteNode(DeleteNode req) throws DispositionReportFaultMessage {
=======
        public void deleteNode(DeleteNode req) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                boolean found = false;
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();
<<<<<<< HEAD

                        
                        UddiEntityPublisher publisher = this.getEntityPublisher(em, req.getAuthInfo());
                        new ValidatePublish(publisher).validateDeleteNode(em, req);
                        

=======
                        //TODO if the given node is in the replication config, prevent deletion
                        UddiEntityPublisher publisher = this.getEntityPublisher(em, req.getAuthInfo());
                        new ValidatePublish(publisher).validateDeleteNode(em, req, getReplicationNodes(req.getAuthInfo()));
>>>>>>> refs/remotes/apache/master

                        org.apache.juddi.model.Node existingUddiEntity = em.find(org.apache.juddi.model.Node.class, req.getNodeID());
                        if (existingUddiEntity != null) {

<<<<<<< HEAD
                                //TODO cascade delete all clerks tied to this node

                                em.remove(existingUddiEntity);
                                found = true;
                        }
                        tx.commit();
=======
                                //cascade delete all clerks tied to this node, confirm that it works
                                Query createQuery = em.createQuery("delete from Clerk c where c.node = :nodename");
                                createQuery.setParameter("nodename", req.getNodeID());
                                createQuery.executeUpdate();

                                em.remove(existingUddiEntity);
                                found = true;
                        } else {
                                throw new InvalidKeyPassedException(new ErrorMessage("errors.deleteNode.NotFound"));
                        }

                        tx.commit();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.DELETE_NODE,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.DELETE_NODE,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master

                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

                if (!found) {
<<<<<<< HEAD
                        
                   
                throw new InvalidKeyPassedException(new ErrorMessage("errors.deleteNode.NotFound"));
=======

                        throw new InvalidKeyPassedException(new ErrorMessage("errors.deleteNode.NotFound", req.getNodeID()));
>>>>>>> refs/remotes/apache/master
                }
        }

        @Override
<<<<<<< HEAD
        public void deleteClerk(DeleteClerk req) throws DispositionReportFaultMessage {

=======
        public void deleteClerk(DeleteClerk req) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
>>>>>>> refs/remotes/apache/master
                boolean found = false;
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, req.getAuthInfo());

                        new ValidatePublish(publisher).validateDeleteClerk(em, req);

                        org.apache.juddi.model.Clerk existingUddiEntity = em.find(org.apache.juddi.model.Clerk.class, req.getClerkID());
<<<<<<< HEAD
                        if (existingUddiEntity != null) { 
                                em.remove(existingUddiEntity);
                                found = true;
                        }
                        tx.commit();
=======
                        if (existingUddiEntity
                                != null) {
                                em.remove(existingUddiEntity);
                                found = true;
                        }

                        tx.commit();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.DELETE_CLERK,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.DELETE_CLERK,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
>>>>>>> refs/remotes/apache/master

                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

                if (!found) {
<<<<<<< HEAD
                throw new InvalidKeyPassedException(new ErrorMessage("errors.deleteClerk.NotFound"));      
                }
                

        }

        /**
         * enables tmodel owners to setup valid values for tmodel instance infos
         * to use, TODO
=======
                        throw new InvalidKeyPassedException(new ErrorMessage("errors.deleteClerk.NotFound"));
                }

        }

        /*
         * enables tmodel owners to setup valid values for tmodel instance infos
         * to use?
>>>>>>> refs/remotes/apache/master
         *
         * @param authInfo
         * @param values
         * @return
         * @throws DispositionReportFaultMessage
<<<<<<< HEAD
         */
        @Override
        public DispositionReport setAllValidValues(String authInfo, List<ValidValues> values) throws DispositionReportFaultMessage {

                EntityManager em = PersistenceManager.getEntityManager();
                UddiEntityPublisher entityPublisher = getEntityPublisher(em, authInfo);

                new ValidateValueSetValidation(entityPublisher).validateSetAllValidValues(values);


                EntityTransaction tx = em.getTransaction();
                try {

                        //TODO is this tModel used anywhere?, if so, validate all instances against the new rule?

                        tx.begin();

                        //each tmodel/value set
                        for (int i = 0; i < values.size(); i++) {
                                //remove any existing references to the key
                                ValueSetValues find = em.find(ValueSetValues.class, values.get(i).getTModekKey());

                                if (find != null) {

                                        //first pass, add any missing values
                                        for (int x = 0; x < values.get(i).getValue().size(); x++) {
                                                boolean found = false;
                                                for (int k = 0; k < find.getValues().size(); k++) {
                                                        if (find.getValues().get(k).getValue().equals(values.get(i).getValue().get(x))) {
                                                                found = true;
                                                                break;
                                                        }
                                                }
                                                if (!found) {
                                                        ValueSetValue valueSetValue = new org.apache.juddi.model.ValueSetValue(values.get(i).getTModekKey(), values.get(i).getValue().get(x));
                                                        find.getValues().add(valueSetValue);
                                                }
                                        }

                                        //second pass, remove any values that were present, but now are not
                                        for (int k = 0; k < find.getValues().size(); k++) {

                                                boolean found = false;
                                                for (int x = 0; x < values.get(i).getValue().size(); x++) {
                                                        if (find.getValues().get(k).getValue().equals(values.get(i).getValue().get(x))) {
                                                                found = true;
                                                                break;
                                                        }
                                                }
                                                if (!found) {
                                                        em.remove(find.getValues().get(k));
                                                }
                                        }

                                        em.persist(find);

                                } else {
                                        org.apache.juddi.model.ValueSetValues vv = new ValueSetValues();
                                        vv.setTModelKey(values.get(i).getTModekKey());
                                        List<ValueSetValue> items = new ArrayList<ValueSetValue>();
                                        for (int k = 0; k < values.get(i).getValue().size(); k++) {
                                                ValueSetValue valueSetValue = new org.apache.juddi.model.ValueSetValue(values.get(i).getTModekKey(), values.get(i).getValue().get(k));
                                                valueSetValue.setParent(vv);
                                                em.persist(valueSetValue);
                                                items.add(valueSetValue);
                                        }
                                        vv.setValues(items);
                                        em.persist(vv);
                                }
                        }

                        tx.commit();
=======
         
         @Override
         public DispositionReport setAllValidValues(String authInfo, List<ValidValues> values) throws DispositionReportFaultMessage, RemoteException {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         /*  EntityManager em = PersistenceManager.getEntityManager();
         UddiEntityPublisher entityPublisher = getEntityPublisher(em, authInfo);

         new ValidateValueSetValidation(entityPublisher).validateSetAllValidValues(values);

         EntityTransaction tx = em.getTransaction();
         try {

         // is this tModel used anywhere?, if so, validate all instances against the new rule?
         tx.begin();

         //each tmodel/value set
         for (int i = 0; i < values.size(); i++) {
         //remove any existing references to the key
         ValueSetValues find = em.find(ValueSetValues.class, values.get(i).getTModekKey());

         if (find != null) {
         find.setValidatorClass(values.get(i).getValidationClass());
         em.persist(find);

         } else {
         org.apache.juddi.model.ValueSetValues vv = new ValueSetValues();
         vv.setTModelKey(values.get(i).getTModekKey());
         vv.setValidatorClass(values.get(i).getValidationClass());
         em.persist(vv);
         }
         }

         tx.commit();
         } finally {
         if (tx.isActive()) {
         tx.rollback();
         }
         em.close();
         }
         DispositionReport r = new DispositionReport();
         r.getResult().add(new Result());
         return r;
         }*/
        @Override
        public void adminDeleteSubscription(String authInfo, List<String> subscriptionKey) throws DispositionReportFaultMessage, RemoteException {

                long startTime = System.currentTimeMillis();

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher requestor = this.getEntityPublisher(em, authInfo);
                        if (!((Publisher) requestor).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }
                        //new ValidateSubscription(publisher).validateDeleteSubscription(em, body);
                        List<TemporaryMailContainer> notifications = new ArrayList<TemporaryMailContainer>();
                        List<String> subscriptionKeyList = subscriptionKey;
                        for (String key : subscriptionKeyList) {
                                if (key != null && key.length() > 0) {
                                        org.apache.juddi.model.Subscription obj = em.find(org.apache.juddi.model.Subscription.class, key);
                                        Publisher publisher = em.find(Publisher.class, obj.getAuthorizedName());
                                        notifications.add(new TemporaryMailContainer(obj, publisher, (Publisher) requestor));
                                        em.remove(obj);
                                }
                        }

                        tx.commit();
                        for (TemporaryMailContainer t : notifications) {
                                USERFRIENDLYSMTPNotifier.notifySubscriptionDeleted(t);
                        }
                        notifications.clear();
                        notifications = null;
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_DELETE_SUB,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_DELETE_SUB,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

        }

        @Override
        public DispositionReport adminSaveBusiness(String authInfo, List<AdminSaveBusinessWrapper> values) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();
                        UddiEntityPublisher requestor = this.getEntityPublisher(em, authInfo);
                        if (!((Publisher) requestor).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }

                        for (int i = 0; i < values.size(); i++) {
                                //impersonate the user
                                AuthToken authToken = sec.getAuthToken(values.get(i).getPublisherID());

                                SaveBusiness stm = new SaveBusiness();

                                stm.setAuthInfo(authToken.getAuthInfo());
                                stm.getBusinessEntity().addAll(values.get(i).getBusinessEntity());
                                pub.saveBusiness(stm);
                        }

                        tx.commit();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_SAVE_BUSINESS,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_SAVE_BUSINESS,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

                DispositionReport r = new DispositionReport();
                return r;

        }

        @Override
        public DispositionReport adminSaveTModel(String authInfo, List<AdminSaveTModelWrapper> values) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();
                        UddiEntityPublisher requestor = this.getEntityPublisher(em, authInfo);
                        if (!((Publisher) requestor).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }

                        for (int i = 0; i < values.size(); i++) {
                                //impersonate the user
                                AuthToken authToken = sec.getAuthToken(values.get(i).getPublisherID());
                                SaveTModel stm = new SaveTModel();
                                stm.setAuthInfo(authToken.getAuthInfo());
                                stm.getTModel().addAll(values.get(i).getTModel());
                                pub.saveTModel(stm);
                        }
                        tx.commit();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_SAVE_TMODEL,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_SAVE_TMODEL,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

>>>>>>> refs/remotes/apache/master
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
<<<<<<< HEAD
                DispositionReport r = new DispositionReport();
                r.getResult().add(new Result());
=======

                DispositionReport r = new DispositionReport();
>>>>>>> refs/remotes/apache/master
                return r;
        }

        @Override
<<<<<<< HEAD
        public void adminDeleteSubscription(String authInfo, List<String> subscriptionKey) throws DispositionReportFaultMessage {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void adminSaveSubscription(String authInfo, String publisherOrUsername, List<Subscription> subscriptions) throws DispositionReportFaultMessage {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DispositionReport adminSaveBusiness(String authInfo, List<AdminSaveBusinessWrapper> values) throws DispositionReportFaultMessage {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DispositionReport adminSaveTModel(String authInfo, List<AdminSaveTModelWrapper> values) throws DispositionReportFaultMessage {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public ReplicationConfiguration getReplicationNodes(String authInfo) throws DispositionReportFaultMessage {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public DispositionReport setReplicationNodes(String authInfo, ReplicationConfiguration replicationConfiguration) throws DispositionReportFaultMessage {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
        public List<SubscriptionWrapper> getAllClientSubscriptionInfo(String authInfo) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();

                List<SubscriptionWrapper> r = new ArrayList<SubscriptionWrapper>();

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, authInfo);
                        if (!((Publisher) publisher).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }

                        StringBuilder sql = new StringBuilder();
                        sql.append("select distinct c from ReplicationConfiguration c ");
                        sql.toString();
                        Query qry = em.createQuery(sql.toString());
                        List<org.apache.juddi.model.Subscription> resultList = qry.getResultList();
                        for (int i = 0; i < resultList.size(); i++) {
                                Subscription sub = new Subscription();
                                MappingModelToApi.mapSubscription(resultList.get(i), sub);
                                SubscriptionWrapper x = new SubscriptionWrapper();
                                x.getSubscription().add(sub);
                                x.setPublisherIdOrUsername(resultList.get(i).getAuthorizedName());
                                r.add(x);
                        }

                        tx.commit();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_CLIENT_SUB,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_CLIENT_SUB,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

                return r;
        }

        @Override
        public synchronized DispositionReport setReplicationNodes(String authInfo, org.uddi.repl_v3.ReplicationConfiguration replicationConfiguration) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        org.uddi.repl_v3.ReplicationConfiguration oldConfig = null;
                        UddiEntityPublisher publisher = this.getEntityPublisher(em, authInfo);
                        if (!((Publisher) publisher).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }
                        new ValidateReplication(publisher).validateSetReplicationNodes(replicationConfiguration, em, node, AppConfig.getConfiguration());

                        //StringWriter sw = new StringWriter();
                        //JAXB.marshal(replicationConfiguration, sw);
                        org.apache.juddi.model.ReplicationConfiguration model = null;
                        logger.info(publisher.getAuthorizedName() + " is setting the replication config from " + getRequestorsIPAddress());// + " " + sw.toString());
                        try {
                                model = (ReplicationConfiguration) em.createQuery("select c FROM ReplicationConfiguration c order by c.serialNumber desc").getSingleResult();
                        } catch (Exception ex) {
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmZ");
                        if (model == null) {
                                //this is a brand new configuration and we didn't have one before
                                model = new ReplicationConfiguration();
                                MappingApiToModel.mapReplicationConfiguration(replicationConfiguration, model, em);
                                model.setSerialNumber(System.currentTimeMillis());
                                model.setTimeOfConfigurationUpdate(sdf.format(new Date()));
                                em.persist(model);
                                //if (newReplicationNode(model)){
                                //tell the replication notifier to start transfering with
                                //the first change record
                                //}

                        } else {
                                //a config exists, remove it, add the new one
                                //spec doesn't appear to mention if recording a change history on the config is required
                                //assuming we'll keep it for now, might be useful later.
                                //em.remove(model);
                                oldConfig = new org.uddi.repl_v3.ReplicationConfiguration();
                                MappingModelToApi.mapReplicationConfiguration(model, oldConfig);

                                ReplicationConfiguration model2 = new ReplicationConfiguration();
                                MappingApiToModel.mapReplicationConfiguration(replicationConfiguration, model2, em);
                                model2.setSerialNumber(System.currentTimeMillis());

                                model2.setTimeOfConfigurationUpdate(sdf.format(new Date()));
                                em.persist(model2);

                        }

                        tx.commit();
                        UDDIReplicationImpl.notifyConfigurationChange(oldConfig, replicationConfiguration);
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SET_REPLICATION_NODES,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.SET_REPLICATION_NODES,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
                } catch (Exception ex) {
                        logger.error(ex, ex);
                        JAXB.marshal(replicationConfiguration, System.out);
                        throw new FatalErrorException(new ErrorMessage("E_fatalError", ex.getMessage()));
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
                DispositionReport d = new DispositionReport();
                Result res = new Result();

                d.getResult().add(res);
                return d;
        }

        @Override
        public org.uddi.repl_v3.ReplicationConfiguration getReplicationNodes(String authInfo) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
                org.uddi.repl_v3.ReplicationConfiguration r = new org.uddi.repl_v3.ReplicationConfiguration();

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();

                        UddiEntityPublisher publisher = this.getEntityPublisher(em, authInfo);
                        if (!((Publisher) publisher).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }

                        StringBuilder sql = new StringBuilder();
                        sql.append("select c from ReplicationConfiguration c order by c.serialNumber desc");
                        //sql.toString();
                        Query qry = em.createQuery(sql.toString());
                        qry.setMaxResults(1);

                        org.apache.juddi.model.ReplicationConfiguration resultList = (org.apache.juddi.model.ReplicationConfiguration) qry.getSingleResult();
                        MappingModelToApi.mapReplicationConfiguration(resultList, r);
                        tx.commit();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_NODES,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.GET_ALL_NODES,
                                QueryStatus.FAILED, procTime);
                        throw drfm;
                } catch (Exception ex) {
                        //possible that there is no config to return
                        logger.debug("Error caught, is there a replication config is avaiable? Returning a default config (no replication): ", ex);

                        r.setCommunicationGraph(new CommunicationGraph());
                        Operator op = new Operator();
                        op.setOperatorNodeID(node);
                        op.setSoapReplicationURL(baseUrlSSL + "replication/services/replication");

                        op.getContact().add(new Contact());
                        op.getContact().get(0).getPersonName().add(new PersonName("Unknown", null));
                        op.setOperatorStatus(OperatorStatusType.NORMAL);

                        r.getOperator().add(op);
                        r.getCommunicationGraph().getNode().add(node);
                        r.getCommunicationGraph().getControlledMessage().add("*");
                        long procTime = System.currentTimeMillis() - startTime;
                        r.setSerialNumber(0);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmZ");
                        r.setTimeOfConfigurationUpdate(sdf.format(new Date()));
                        r.setRegistryContact(new org.uddi.repl_v3.ReplicationConfiguration.RegistryContact());
                        try {
                                // pull from root business
                                if (!tx.isActive()) {
                                        tx = em.getTransaction();
                                }

                                BusinessEntity rootbiz = em.find(BusinessEntity.class, AppConfig.getConfiguration().getString(Property.JUDDI_NODE_ROOT_BUSINESS));
                                if (rootbiz != null) {

                                        for (int i = 0; i < rootbiz.getContacts().size(); i++) {
                                                Contact c = new Contact();
                                                MappingModelToApi.mapContact(rootbiz.getContacts().get(i), c);
                                                r.getRegistryContact().setContact(c);
                                                break;
                                        }

                                }
                                tx.rollback();

                        } catch (Exception ex1) {
                                logger.warn("unexpected error", ex1);
                        }
                        if (r.getRegistryContact().getContact() == null) {
                                r.getRegistryContact().setContact(new Contact());
                                r.getRegistryContact().getContact().getPersonName().add(new PersonName("Unknown", null));
                        }
                        serviceCounter.update(JUDDIQuery.GET_REPLICATION_NODES,
                                QueryStatus.SUCCESS, procTime);

                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

                r.setMaximumTimeToGetChanges(BigInteger.ONE);
                r.setMaximumTimeToSyncRegistry(BigInteger.ONE);
                //StringWriter sw = new StringWriter();
                //JAXB.marshal(r, sw);
                //logger.info("dumping returned replication config " + sw.toString());
                return r;
        }

        static UDDISubscriptionImpl sub = new UDDISubscriptionImpl();
        static UDDISecurityImpl sec = new UDDISecurityImpl();
        static UDDIPublicationImpl pub = new UDDIPublicationImpl();

        @Override
        public void adminSaveSubscription(String authInfo, String publisherOrUsername, Holder<List<Subscription>> subscriptions) throws DispositionReportFaultMessage {
                long startTime = System.currentTimeMillis();
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();
                        UddiEntityPublisher requestor = this.getEntityPublisher(em, authInfo);
                        if (!((Publisher) requestor).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }
                        //impersonate the user
                        AuthToken authToken = sec.getAuthToken(publisherOrUsername);
                        sub.saveSubscription(authToken.getAuthInfo(), subscriptions);
                        tx.commit();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_SAVE_SUB,
                                QueryStatus.SUCCESS, procTime);
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_SAVE_SUB,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }

        }

        /**
         * {@inheritDoc }
         *
         * @param body
         * @return item history or null if not found
         * @throws DispositionReportFaultMessage
         * @throws RemoteException
         */
        @Override
        public GetEntityHistoryMessageResponse getEntityHistory(GetEntityHistoryMessageRequest body) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
                if (body == null) {
                        throw new InvalidValueException(new ErrorMessage("errors.NullInput"));
                }
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();
                        UddiEntityPublisher requestor = this.getEntityPublisher(em, body.getAuthInfo());
                        if (!((Publisher) requestor).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }
                        if (body.getMaxRecords() <= 0) {
                                body.setMaxRecords(20);
                        }
                        if (body.getOffset() < 0) {
                                body.setOffset(0);
                        }
                        Query createQuery = em.createQuery("select m from ChangeRecord m where m.entityKey = :key order by m.id DESC");
                        createQuery.setMaxResults((int) body.getMaxRecords());
                        createQuery.setParameter("key", body.getEntityKey());
                        createQuery.setFirstResult((int) body.getOffset());
                        List<ChangeRecord> resultList = createQuery.getResultList();
                        GetEntityHistoryMessageResponse res = new GetEntityHistoryMessageResponse();
                        res.setChangeRecords(new ChangeRecords());
                        for (ChangeRecord cr : resultList) {
                                res.getChangeRecords().getChangeRecord().add(MappingModelToApi.mapChangeRecord(cr));
                        }

                        tx.rollback();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_GET_HISTORY,
                                QueryStatus.SUCCESS, procTime);
                        return res;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_GET_HISTORY,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
        }

        /**
         * {@inheritDoc }
         *
         * @param body
         * @return
         * @throws DispositionReportFaultMessage
         * @throws RemoteException
         */
        @Override
        public GetFailedReplicationChangeRecordsMessageResponse getFailedReplicationChangeRecords(
                GetFailedReplicationChangeRecordsMessageRequest body)
                throws DispositionReportFaultMessage, RemoteException {
                //public GetFailedReplicationChangeRecordsMessageResponse getFailedReplicationChangeRecords(GetFailedReplicationChangeRecordsMessageRequest body) throws DispositionReportFaultMessage, RemoteException {
                long startTime = System.currentTimeMillis();
                if (body == null) {
                        throw new InvalidValueException(new ErrorMessage("errors.NullInput"));
                }
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();
                        UddiEntityPublisher requestor = this.getEntityPublisher(em, body.getAuthInfo());
                        if (!((Publisher) requestor).isAdmin()) {
                                throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));
                        }
                        if (body.getMaxRecords() <= 0) {
                                body.setMaxRecords(20);
                        }
                        if (body.getOffset() < 0) {
                                body.setOffset(0);
                        }
                        Query createQuery = em.createQuery("select m from ChangeRecord m where m.isAppliedLocally=false order by m.id DESC ");
                        createQuery.setMaxResults((int) body.getMaxRecords());
                        createQuery.setFirstResult((int) body.getOffset());
                        List<ChangeRecord> resultList = createQuery.getResultList();
                        GetFailedReplicationChangeRecordsMessageResponse res = new GetFailedReplicationChangeRecordsMessageResponse();
                        res.setChangeRecords(new ChangeRecords());
                        for (ChangeRecord cr : resultList) {
                                res.getChangeRecords().getChangeRecord().add(MappingModelToApi.mapChangeRecord(cr));
                        }

                        tx.rollback();
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_GET_FAILED_CRS,
                                QueryStatus.SUCCESS, procTime);
                        return res;
                } catch (DispositionReportFaultMessage drfm) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(JUDDIQuery.ADMIN_GET_FAILED_CRS,
                                QueryStatus.FAILED, procTime);
                        throw drfm;

                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
>>>>>>> refs/remotes/apache/master
        }
}
