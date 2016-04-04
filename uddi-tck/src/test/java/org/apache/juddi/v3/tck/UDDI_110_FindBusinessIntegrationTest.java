package org.apache.juddi.v3.tck;

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
import java.util.List;
import javax.xml.ws.BindingProvider;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.DeleteBusiness;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.TModelBag;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

/**
 * Test to verify JUDDI-398
 *
 * @author <a href="mailto:tcunning@apache.org">Tom Cunningham</a>
 * @author <a href="mailto:alexoree@apache.org">Alex O'Ree</a>
 */
public class UDDI_110_FindBusinessIntegrationTest {

        final static String TOM_PUBLISHER_TMODEL_XML = "uddi_data/tompublisher/tModelKeyGen.xml";
        final static String TOM_PUBLISHER_TMODEL01_XML = "uddi_data/tompublisher/tModel01.xml";
        final static String TOM_PUBLISHER_TMODEL02_XML = "uddi_data/tompublisher/tModel02.xml";
        final static String TOM_PUBLISHER_TMODEL_KEY = "uddi:uddi.tompublisher.com:keygenerator";
        final static String TOM_PUBLISHER_TMODEL01_KEY = "uddi:uddi.tompublisher.com:tmodeltest01";
        final static String TOM_PUBLISHER_TMODEL01_NAME = "tmodeltest01";
        final static String TOM_PUBLISHER_TMODEL02_KEY = "uddi:uddi.tompublisher.com:tmodeltest02";
        final static String TOM_BUSINESS_XML = "uddi_data/tompublisher/businessEntity.xml";
        final static String TOM_BUSINESS_KEY = "uddi:uddi.tompublisher.com:businesstest01";
        final static String TOM_PUBLISHER_SERVICEINFO_NAME = "servicetest01";
        private static Log logger = LogFactory.getLog(UDDI_040_BusinessServiceIntegrationTest.class);
        protected static TckTModel tckTModel = null;
        protected static TckTModel tckTModel01 = null;
        protected static TckTModel tckTModel02 = null;
        protected static TckBusiness tckBusiness = null;
        protected static String authInfoJoe = null;
        private static UDDIInquiryPortType inquiry = null;
<<<<<<< HEAD
=======
        private static UDDIPublicationPortType publication=null;
>>>>>>> refs/remotes/apache/master
        private static UDDIClient manager;

        @AfterClass
        public static void stopManager() throws ConfigurationException {
<<<<<<< HEAD
=======
             if (!TckPublisher.isEnabled()) return;
>>>>>>> refs/remotes/apache/master
                tckTModel.deleteCreatedTModels(authInfoJoe);
                tckTModel01.deleteCreatedTModels(authInfoJoe);
                tckTModel02.deleteCreatedTModels(authInfoJoe);
                manager.stop();
        }

        @BeforeClass
        public static void startManager() throws ConfigurationException {
<<<<<<< HEAD
=======
             if (!TckPublisher.isEnabled()) return;
>>>>>>> refs/remotes/apache/master
                manager = new UDDIClient();
                manager.start();

                logger.debug("Getting auth tokens..");
                try {
<<<<<<< HEAD
                        Transport transport = manager.getTransport();
=======
                        Transport transport = manager.getTransport("uddiv3");
>>>>>>> refs/remotes/apache/master
                        UDDISecurityPortType security = transport.getUDDISecurityService();
                        authInfoJoe = TckSecurity.getAuthToken(security, TckPublisher.getJoePublisherId(), TckPublisher.getJoePassword());
                        //Assert.assertNotNull(authInfoJoe);
                        
<<<<<<< HEAD
                        UDDIPublicationPortType publication = transport.getUDDIPublishService();
=======
                        publication = transport.getUDDIPublishService();
>>>>>>> refs/remotes/apache/master
                        inquiry = transport.getUDDIInquiryService();
                        if (!TckPublisher.isUDDIAuthMode()){
                                TckSecurity.setCredentials((BindingProvider) publication, TckPublisher.getJoePublisherId(), TckPublisher.getJoePassword());
                                TckSecurity.setCredentials((BindingProvider) inquiry, TckPublisher.getJoePublisherId(), TckPublisher.getJoePassword());
                        }
<<<<<<< HEAD
=======
                        
>>>>>>> refs/remotes/apache/master

                        tckTModel = new TckTModel(publication, inquiry);
                        tckTModel01 = new TckTModel(publication, inquiry);
                        tckTModel02 = new TckTModel(publication, inquiry);
                        tckBusiness = new TckBusiness(publication, inquiry);

                } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        Assert.fail("Could not obtain authInfo token.");
                }
<<<<<<< HEAD
        }

        @Test
        public void findBusinessByTModelBag() {
=======
                JUDDI_300_MultiNodeIntegrationTest.testSetupReplicationConfig();
        }

        /**
         * JUDDI-881
         * "If a tModelBag or find_tModel was used in the search, the resulting serviceInfos structure reflects data only for the businessServices that actually contained a matching bindingTemplate.
         */
        @Test
        public void findBusinessByTModelBag() {
             Assume.assumeTrue(TckPublisher.isEnabled());
>>>>>>> refs/remotes/apache/master
                try {
                        tckTModel.saveTModel(authInfoJoe, TOM_PUBLISHER_TMODEL_XML, TOM_PUBLISHER_TMODEL_KEY);
                        tckTModel.saveTModel(authInfoJoe, TOM_PUBLISHER_TMODEL01_XML, TOM_PUBLISHER_TMODEL01_KEY);
                        tckTModel.saveTModel(authInfoJoe, TOM_PUBLISHER_TMODEL02_XML, TOM_PUBLISHER_TMODEL02_KEY);

<<<<<<< HEAD
                        tckBusiness.saveBusinesses(authInfoJoe, TOM_BUSINESS_XML, TOM_BUSINESS_KEY, 1);

=======
                        try{
                                  // Delete the entity and make sure it is removed
                                DeleteBusiness db = new DeleteBusiness();
                                db.setAuthInfo(authInfoJoe);
                                db.getBusinessKey().add(TOM_BUSINESS_KEY);
                                publication.deleteBusiness(db);
                        }       catch (Exception ex){}
                        tckBusiness.saveBusinesses(authInfoJoe, TOM_BUSINESS_XML, TOM_BUSINESS_KEY, 1);

                        String before =TckCommon.DumpAllBusinesses(authInfoJoe, inquiry);
>>>>>>> refs/remotes/apache/master
                        try {
                                int size = 0;
                                BusinessList bl = null;

                                FindBusiness fbb = new FindBusiness();
                                TModelBag tmb = new TModelBag();
                                tmb.getTModelKey().add(TOM_PUBLISHER_TMODEL01_KEY);
                                fbb.setTModelBag(tmb);
                                bl = inquiry.findBusiness(fbb);
                                size = bl.getBusinessInfos().getBusinessInfo().size();
<<<<<<< HEAD
                                if (size != 1) {
=======
                                //JUDDI-881
                                
                                if (size != 1) {
                                        logger.error("Test failed, dumping the business list " + before);
>>>>>>> refs/remotes/apache/master
                                        Assert.fail("Should have found one entry on FindBusiness with TModelBag, "
                                                + "found " + size);
                                } else {
                                        List<BusinessInfo> biList = bl.getBusinessInfos().getBusinessInfo();
<<<<<<< HEAD
                                        if (biList.get(0).getServiceInfos().getServiceInfo().size() != 2) {
                                                Assert.fail("Should have found two ServiceInfos");
=======
                                        if (biList.get(0).getServiceInfos().getServiceInfo().size() != 1) {
                                                logger.error("Test failed, dumping the business list " + before);
                                                Assert.fail("Should have found one ServiceInfos");
>>>>>>> refs/remotes/apache/master
                                        } else {
                                                List<ServiceInfo> siList = biList.get(0).getServiceInfos().getServiceInfo();
                                                ServiceInfo si = siList.get(0);
                                                if (!TOM_PUBLISHER_SERVICEINFO_NAME.equals(si.getName().get(0).getValue())) {
<<<<<<< HEAD
=======
                                                        logger.error("Test failed, dumping the business list " + before);
>>>>>>> refs/remotes/apache/master
                                                        Assert.fail("Should have found " + TOM_PUBLISHER_TMODEL01_NAME + " as the "
                                                                + "ServiceInfo name, found " + si.getName().get(0).getValue());
                                                }
                                        }
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                                Assert.fail(e.getMessage());
                        }
                } finally {
                        tckBusiness.deleteBusinesses(authInfoJoe, TOM_BUSINESS_XML, TOM_BUSINESS_KEY, 1);

                        tckTModel.deleteTModel(authInfoJoe, TOM_PUBLISHER_TMODEL_XML, TOM_PUBLISHER_TMODEL_KEY);
                        tckTModel.deleteTModel(authInfoJoe, TOM_PUBLISHER_TMODEL01_XML, TOM_PUBLISHER_TMODEL01_KEY);
                        tckTModel.deleteTModel(authInfoJoe, TOM_PUBLISHER_TMODEL02_XML, TOM_PUBLISHER_TMODEL02_KEY);

                }
        }
}
