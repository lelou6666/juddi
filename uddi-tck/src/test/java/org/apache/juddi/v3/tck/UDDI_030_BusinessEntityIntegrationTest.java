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
package org.apache.juddi.v3.tck;

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
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

/**
 * @author <a href="mailto:jfaath@apache.org">Jeff Faath</a>
 * @author <a href="mailto:kstam@apache.org">Kurt T Stam</a>
 * @author <a href="mailto:alexoree@apache.org">Alex O'Ree</a>
 */
public class UDDI_030_BusinessEntityIntegrationTest {

        protected static Log logger = LogFactory.getLog(UDDI_030_BusinessEntityIntegrationTest.class);
        protected static TckTModel tckTModelJoe = null;
        protected static TckBusiness tckBusinessJoe = null;
        protected static TckFindEntity tckFindEntityJoe = null;
        protected static TckTModel tckTModelSam = null;
        protected static TckBusiness tckBusinessSam = null;
        protected static TckFindEntity tckFindEntitySam = null;
        protected static UDDIInquiryPortType inquiryJoe = null;
        protected static UDDIInquiryPortType inquirySam = null;
        protected static String authInfoJoe = null;
        protected static String authInfoSam = null;
        private static UDDIClient manager;

        @AfterClass
        public static void stopManager() throws ConfigurationException {
<<<<<<< HEAD
=======
                if (!TckPublisher.isEnabled()) {
                        return;
                }
>>>>>>> refs/remotes/apache/master
                tckTModelJoe.deleteCreatedTModels(authInfoJoe);
                tckTModelSam.deleteCreatedTModels(authInfoSam);
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
                logger.info("UDDI_030_BusinessEntityIntegrationTest");
                logger.debug("Getting auth tokens..");
                try {
<<<<<<< HEAD
                        Transport transport = manager.getTransport();
=======
                        Transport transport = manager.getTransport("uddiv3");
>>>>>>> refs/remotes/apache/master
                        UDDISecurityPortType security = transport.getUDDISecurityService();

                        authInfoJoe = TckSecurity.getAuthToken(security, TckPublisher.getJoePublisherId(), TckPublisher.getJoePassword());
                        authInfoSam = TckSecurity.getAuthToken(security, TckPublisher.getSamPublisherId(), TckPublisher.getSamPassword());
                        //Assert.assertNotNull(authInfoJoe);
                        //Assert.assertNotNull(authInfoSam);

                        UDDIPublicationPortType publication = transport.getUDDIPublishService();
                        UDDIInquiryPortType inquiry = transport.getUDDIInquiryService();
                        if (!TckPublisher.isUDDIAuthMode()) {
                                TckSecurity.setCredentials((BindingProvider) publication, TckPublisher.getJoePublisherId(), TckPublisher.getJoePassword());
                                TckSecurity.setCredentials((BindingProvider) inquiry, TckPublisher.getJoePublisherId(), TckPublisher.getJoePassword());
                        }
                        tckTModelJoe = new TckTModel(publication, inquiry);
                        tckBusinessJoe = new TckBusiness(publication, inquiry);
                        tckFindEntityJoe = new TckFindEntity(inquiry);
                        inquiryJoe = inquiry;

<<<<<<< HEAD
                        transport = manager.getTransport();
=======
                        transport = manager.getTransport("uddiv3");
>>>>>>> refs/remotes/apache/master
                        publication = transport.getUDDIPublishService();
                        inquiry = transport.getUDDIInquiryService();
                        if (!TckPublisher.isUDDIAuthMode()) {
                                TckSecurity.setCredentials((BindingProvider) publication, TckPublisher.getSamPublisherId(), TckPublisher.getSamPassword());
                                TckSecurity.setCredentials((BindingProvider) inquiry, TckPublisher.getSamPublisherId(), TckPublisher.getSamPassword());
                        }
                        tckTModelSam = new TckTModel(publication, inquiry);
                        tckBusinessSam = new TckBusiness(publication, inquiry);
                        tckFindEntitySam = new TckFindEntity(inquiry);
                        inquirySam = inquiry;

                        String authInfoUDDI = TckSecurity.getAuthToken(security, TckPublisher.getUDDIPublisherId(), TckPublisher.getUDDIPassword());
<<<<<<< HEAD
                        transport = manager.getTransport();
=======
                        transport = manager.getTransport("uddiv3");
>>>>>>> refs/remotes/apache/master
                        publication = transport.getUDDIPublishService();
                        inquiry = transport.getUDDIInquiryService();
                        if (!TckPublisher.isUDDIAuthMode()) {
                                TckSecurity.setCredentials((BindingProvider) publication, TckPublisher.getUDDIPublisherId(), TckPublisher.getUDDIPassword());
                                TckSecurity.setCredentials((BindingProvider) inquiry, TckPublisher.getUDDIPublisherId(), TckPublisher.getUDDIPassword());
                        }
                        TckTModel tckTModelUddi = new TckTModel(publication, inquiry);
                        tckTModelUddi.saveUDDIPublisherTmodel(authInfoUDDI);
                        tckTModelUddi.saveTModels(authInfoUDDI, TckTModel.TMODELS_XML);

<<<<<<< HEAD

=======
>>>>>>> refs/remotes/apache/master
                } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        Assert.fail("Could not obtain authInfo token.");
                }
<<<<<<< HEAD
=======
                JUDDI_300_MultiNodeIntegrationTest.testSetupReplicationConfig();
>>>>>>> refs/remotes/apache/master
        }

        @Test
        public void testJoePublisherBusinessEntitySignature() {
<<<<<<< HEAD
=======
                Assume.assumeTrue(TckPublisher.isEnabled());
>>>>>>> refs/remotes/apache/master
                try {
                        tckTModelJoe.saveJoePublisherTmodel(authInfoJoe);
                        tckBusinessJoe.saveJoePublisherBusinessX509Signature(authInfoJoe);
                        tckFindEntityJoe.findAllBusiness();
                        tckBusinessJoe.deleteJoePublisherBusiness(authInfoJoe);
                } finally {
                        tckTModelJoe.deleteJoePublisherTmodel(authInfoJoe);
                }
        }

        @Test
<<<<<<< HEAD
        public void testJoePublisherBusinessEntity() throws Exception{
=======
        public void testJoePublisherBusinessEntity() throws Exception {
                Assume.assumeTrue(TckPublisher.isEnabled());
>>>>>>> refs/remotes/apache/master
                try {
                        tckTModelJoe.saveJoePublisherTmodel(authInfoJoe);
                        tckBusinessJoe.saveJoePublisherBusiness(authInfoJoe);
                        tckFindEntityJoe.findAllBusiness();
                        tckBusinessJoe.deleteJoePublisherBusiness(authInfoJoe);
                } finally {
                        tckTModelJoe.deleteJoePublisherTmodel(authInfoJoe);
                }
        }

        @Test
<<<<<<< HEAD
        public void testSamSyndicatorBusiness() throws Exception{
=======
        public void testSamSyndicatorBusiness() throws Exception {
                Assume.assumeTrue(TckPublisher.isEnabled());
>>>>>>> refs/remotes/apache/master
                try {
                        tckTModelSam.saveSamSyndicatorTmodel(authInfoSam);
                        tckBusinessSam.saveSamSyndicatorBusiness(authInfoSam);
                        tckBusinessSam.deleteSamSyndicatorBusiness(authInfoSam);
                } finally {
                        tckTModelSam.deleteSamSyndicatorTmodel(authInfoSam);
                }
        }
}
