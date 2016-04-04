/*
 * Copyright 2001-2013 The Apache Software Foundation.
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
package org.apache.juddi.samples;

import javax.xml.datatype.DatatypeFactory;
import org.apache.juddi.jaxb.PrintUDDI;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.subscription.ISubscriptionCallback;
import org.apache.juddi.v3.client.subscription.SubscriptionCallbackListener;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3_service.JUDDIApiPortType;
import org.uddi.api_v3.*;
import org.uddi.sub_v3.Subscription;
import org.uddi.sub_v3.SubscriptionFilter;
import org.uddi.sub_v3.SubscriptionResultsList;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;
import org.uddi.v3_service.UDDISubscriptionPortType;

/**
 * Thie class shows you how to create a business and a subscription using UDDI
 * Subscription asynchronous callbacks
 *
 * @author <a href="mailto:alexoree@apache.org">Alex O'Ree</a>
 */
public class UddiSubscribe implements ISubscriptionCallback, Runnable {

        private static UDDISecurityPortType security = null;
        private static JUDDIApiPortType juddiApi = null;
        private static UDDIPublicationPortType publish = null;
        private static UDDIInquiryPortType uddiInquiryService = null;
        private static UDDISubscriptionPortType uddiSubscriptionService = null;
        boolean callbackRecieved = false;
        private UDDIClerk clerk = null;
        private UDDIClient client = null;

        public UddiSubscribe() {
                try {
                        // create a manager and read the config in the archive; 
                        // you can use your config file name
                        client = new UDDIClient("META-INF/simple-publish-uddi.xml");
                        clerk = client.getClerk("default");
                        Transport transport = client.getTransport();
                        // Now you create a reference to the UDDI API
                        security = transport.getUDDISecurityService();
                        juddiApi = transport.getJUDDIApiService();
                        publish = transport.getUDDIPublishService();
                        uddiInquiryService = transport.getUDDIInquiryService();
                        uddiSubscriptionService = transport.getUDDISubscriptionService();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
<<<<<<< HEAD
    }

    public static void main(String args[]) throws Exception {
        UddiSubscribe sp = new UddiSubscribe();
        sp.SetupAndRegisterCallback();
    }

    private void SetupAndRegisterCallback() throws Exception {
        String url = "http://localhost:7777/uddi_subscription_back";
        Endpoint ep = Endpoint.publish(url, new ClientSubscriptionCallback(this));


        DatatypeFactory df = DatatypeFactory.newInstance();
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTimeInMillis(System.currentTimeMillis());
        XMLGregorianCalendar xcal = df.newXMLGregorianCalendar(gcal);

        //?? note, you won't get subscription updates if you own the entity, at least with juddi


        //in this case, the user "root" is subscribing to a business entity published by the user "uddi"
        //"uddi" then updates the item and then logically "root" should be notified
        //before this happens "root" needs to publish a business, service and binding template that is used for the 
        //subscription callback

        /*
         * so the order of operation is
         * 1) uddi publisher's an entity 
         * 2) root wants updates on uddi's entity
         * 3) root starts a subscription listener service to process updates
         * 4) root creates a business and service with a bindingtemplate with the access point value = the the subscription listener's address
         * 5) root creates a subscription
         * 6) uddi updates the entity
         * 7) root's sub listener is notified (hopefully)
         * 
         */


        GetAuthToken getAuthTokenRoot = new GetAuthToken();
        getAuthTokenRoot.setUserID("root");
        getAuthTokenRoot.setCred("root");

        // Making API call that retrieves the authentication token for the 'root' user.
        AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);
        System.out.println("root AUTHTOKEN = " + "***** don't log auth tokens");


        getAuthTokenRoot = new GetAuthToken();
        getAuthTokenRoot.setUserID("uddi");
        getAuthTokenRoot.setCred("uddi");

        // Making API call that retrieves the authentication token for the 'root' user.
        AuthToken uddiAuthToken = security.getAuthToken(getAuthTokenRoot);
        System.out.println("uddi AUTHTOKEN = " + "***** don't log auth tokens");

        //first publish a business user the user uddi
        BusinessEntity myBusEntity = new BusinessEntity();
        Name myBusName = new Name();
        myBusName.setLang("en");
        myBusName.setValue("User UDDI's Business" + " " + xcal.toString());
        myBusEntity.getName().add(myBusName);
        SaveBusiness sb = new SaveBusiness();
        sb.getBusinessEntity().add(myBusEntity);
        sb.setAuthInfo(uddiAuthToken.getAuthInfo());
        BusinessDetail bd = publish.saveBusiness(sb);

        String keyRootSubscribesTo = bd.getBusinessEntity().get(0).getBusinessKey();
        System.out.println("UDDI's business saved as biz key " + keyRootSubscribesTo);
        BusinessEntity uddisBusiness = bd.getBusinessEntity().get(0);

        //reset
        List<Subscription> subscriptions = uddiSubscriptionService.getSubscriptions(rootAuthToken.getAuthInfo());
        DeleteSubscription ds = new DeleteSubscription();
        ds.setAuthInfo(rootAuthToken.getAuthInfo());
        for (int i = 0; i < subscriptions.size(); i++) {
            ds.getSubscriptionKey().add(subscriptions.get(i).getSubscriptionKey());
=======
        String nodename = "default";
        public UddiSubscribe(UDDIClient client, String nodename, Transport transport) {
                try {
                        // create a manager and read the config in the archive; 
                        // you can use your config file name
                        //client = new UDDIClient("META-INF/simple-publish-uddi.xml");
                        clerk = client.getClerk(nodename);
                        this.nodename = nodename;
                        // Now you create a reference to the UDDI API
                        security = transport.getUDDISecurityService();
                        juddiApi = transport.getJUDDIApiService();
                        publish = transport.getUDDIPublishService();
                        uddiInquiryService = transport.getUDDIInquiryService();
                        uddiSubscriptionService = transport.getUDDISubscriptionService();
                } catch (Exception e) {
                        e.printStackTrace();
                }
>>>>>>> refs/remotes/apache/master
        }

        public static void main(String args[]) throws Exception {
                UddiSubscribe sp = new UddiSubscribe();
                sp.Fire();
        }

        public void Fire() throws Exception {

                TModel createKeyGenator = UDDIClerk.createKeyGenator("somebusiness", "A test key domain SubscriptionCallbackTest1", "SubscriptionCallbackTest1");

                clerk.register(createKeyGenator);
                System.out.println("Registered tModel keygen: " + createKeyGenator.getTModelKey());

                //setup the business to attach to
                BusinessEntity be = new BusinessEntity();
                be.setBusinessKey("uddi:somebusiness:somebusinesskey");
                be.getName().add(new Name("somebusiness SubscriptionCallbackTest1", null));
                be.setBusinessServices(new BusinessServices());
                BusinessService bs = new BusinessService();
                bs.setBusinessKey("uddi:somebusiness:somebusinesskey");
                bs.setServiceKey("uddi:somebusiness:someservicekey");
                bs.getName().add(new Name("service SubscriptionCallbackTest1", null));
                be.getBusinessServices().getBusinessService().add(bs);
                BusinessEntity register = clerk.register(be);
                System.out.println("Registered business keygen: " + register.getBusinessKey());

                //start up our listener
                BindingTemplate start = SubscriptionCallbackListener.start(client, nodename);

                //register for callbacks
                SubscriptionCallbackListener.registerCallback(this);

                Subscription sub = new Subscription();
                sub.setNotificationInterval(DatatypeFactory.newInstance().newDuration(1000));
                sub.setBindingKey(start.getBindingKey());
                sub.setSubscriptionFilter(new SubscriptionFilter());
                sub.getSubscriptionFilter().setFindBusiness(new FindBusiness());
                sub.getSubscriptionFilter().getFindBusiness().setFindQualifiers(new FindQualifiers());
                sub.getSubscriptionFilter().getFindBusiness().getFindQualifiers().getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
                sub.getSubscriptionFilter().getFindBusiness().getName().add(new Name(UDDIConstants.WILDCARD, null));

                Subscription subscriptionBiz = clerk.register(sub, clerk.getUDDINode().getApiNode());

                System.out.println("Registered FindBusiness subscription key: " + (subscriptionBiz.getSubscriptionKey()) + " bindingkey: " + subscriptionBiz.getBindingKey());

                sub = new Subscription();
                sub.setNotificationInterval(DatatypeFactory.newInstance().newDuration(1000));
                sub.setBindingKey(start.getBindingKey());
                sub.setSubscriptionFilter(new SubscriptionFilter());
                sub.getSubscriptionFilter().setFindService(new FindService());
                sub.getSubscriptionFilter().getFindService().setFindQualifiers(new FindQualifiers());
                sub.getSubscriptionFilter().getFindService().getFindQualifiers().getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
                sub.getSubscriptionFilter().getFindService().getName().add(new Name(UDDIConstants.WILDCARD, null));

                Subscription subscriptionSvc = clerk.register(sub, clerk.getUDDINode().getApiNode());

                System.out.println("Registered FindService subscription key: " + (subscriptionSvc.getSubscriptionKey()) + " bindingkey: " + subscriptionSvc.getBindingKey());

                sub = new Subscription();
                sub.setNotificationInterval(DatatypeFactory.newInstance().newDuration(1000));
                sub.setBindingKey(start.getBindingKey());
                sub.setSubscriptionFilter(new SubscriptionFilter());
                sub.getSubscriptionFilter().setFindTModel(new FindTModel());
                sub.getSubscriptionFilter().getFindTModel().setFindQualifiers(new FindQualifiers());
                sub.getSubscriptionFilter().getFindTModel().getFindQualifiers().getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
                sub.getSubscriptionFilter().getFindTModel().setName(new Name(UDDIConstants.WILDCARD, null));

                Subscription subscriptionTM = clerk.register(sub, clerk.getUDDINode().getApiNode());

                System.out.println("Registered FindTModel subscription key: " + (subscriptionTM.getSubscriptionKey()) + " bindingkey: " + subscriptionTM.getBindingKey());

                sub = new Subscription();
                sub.setNotificationInterval(DatatypeFactory.newInstance().newDuration(1000));
                sub.setBindingKey(start.getBindingKey());
                sub.setSubscriptionFilter(new SubscriptionFilter());
                sub.getSubscriptionFilter().setGetAssertionStatusReport(new GetAssertionStatusReport());
                sub.getSubscriptionFilter().getGetAssertionStatusReport().setCompletionStatus(CompletionStatus.STATUS_COMPLETE);

                Subscription subscriptionPA = clerk.register(sub, clerk.getUDDINode().getApiNode());

                System.out.println("Registered Completed PublisherAssertion subscription key: " + (subscriptionPA.getSubscriptionKey()) + " bindingkey: " + subscriptionTM.getBindingKey());

                sub = new Subscription();
                sub.setNotificationInterval(DatatypeFactory.newInstance().newDuration(1000));
                sub.setBindingKey(start.getBindingKey());
                sub.setSubscriptionFilter(new SubscriptionFilter());
                sub.getSubscriptionFilter().setGetAssertionStatusReport(new GetAssertionStatusReport());
                sub.getSubscriptionFilter().getGetAssertionStatusReport().setCompletionStatus(CompletionStatus.STATUS_FROM_KEY_INCOMPLETE);

                Subscription subscriptionPA2 = clerk.register(sub, clerk.getUDDINode().getApiNode());

                System.out.println("Registered FROM incomplete PublisherAssertion subscription key: " + (subscriptionPA2.getSubscriptionKey()) + " bindingkey: " + subscriptionTM.getBindingKey());

                sub = new Subscription();
                sub.setNotificationInterval(DatatypeFactory.newInstance().newDuration(1000));
                sub.setBindingKey(start.getBindingKey());
                sub.setSubscriptionFilter(new SubscriptionFilter());
                sub.getSubscriptionFilter().setGetAssertionStatusReport(new GetAssertionStatusReport());
                sub.getSubscriptionFilter().getGetAssertionStatusReport().setCompletionStatus(CompletionStatus.STATUS_TO_KEY_INCOMPLETE);

                Subscription subscriptionPA3 = clerk.register(sub, clerk.getUDDINode().getApiNode());

                System.out.println("Registered TO incomplete PublisherAssertion subscription key: " + (subscriptionPA3.getSubscriptionKey()) + " bindingkey: " + subscriptionTM.getBindingKey());

                sub = new Subscription();
                sub.setNotificationInterval(DatatypeFactory.newInstance().newDuration(1000));
                sub.setBindingKey(start.getBindingKey());
                sub.setSubscriptionFilter(new SubscriptionFilter());
                sub.getSubscriptionFilter().setGetAssertionStatusReport(new GetAssertionStatusReport());
                sub.getSubscriptionFilter().getGetAssertionStatusReport().setCompletionStatus(CompletionStatus.STATUS_BOTH_INCOMPLETE);

                Subscription subscriptionPA4 = clerk.register(sub, clerk.getUDDINode().getApiNode());

                System.out.println("Registered recently deleted PublisherAssertion subscription key: " + (subscriptionPA4.getSubscriptionKey()) + " bindingkey: " + subscriptionTM.getBindingKey());

                System.out.println("Waiting for callbacks. Now would be a good time to launch either another program or juddi-gui to make some changes. Press any key to stop!");
                //Thread hook = new Thread(this);
                //  Runtime.getRuntime().addShutdownHook(hook);

                System.in.read();

                SubscriptionCallbackListener.stop(client, nodename, start.getBindingKey());
                clerk.unRegisterSubscription(subscriptionBiz.getSubscriptionKey());
                clerk.unRegisterSubscription(subscriptionSvc.getSubscriptionKey());
                clerk.unRegisterSubscription(subscriptionTM.getSubscriptionKey());
                clerk.unRegisterSubscription(subscriptionPA.getSubscriptionKey());
                clerk.unRegisterSubscription(subscriptionPA2.getSubscriptionKey());
                clerk.unRegisterSubscription(subscriptionPA3.getSubscriptionKey());
                clerk.unRegisterSubscription(subscriptionPA4.getSubscriptionKey());

                clerk.unRegisterTModel(createKeyGenator.getTModelKey());

                clerk.unRegisterBusiness("uddi:somebusiness:somebusinesskey");

                //Runtime.getRuntime().removeShutdownHook(hook);
        }

        private boolean running = true;
        PrintUDDI<SubscriptionResultsList> p = new PrintUDDI<SubscriptionResultsList>();

        @Override
        public void HandleCallback(SubscriptionResultsList body) {
                System.out.println("Callback received!");
                System.out.println(p.print(body));
        }

        @Override
        public void NotifyEndpointStopped() {
                System.out.println("The endpoint was stopped!");
        }

        @Override
        public void run() {
                running = false;
        }
}
