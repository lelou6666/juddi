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
package org.apache.juddi.replication;

<<<<<<< HEAD
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.xml.ws.BindingProvider;


=======
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.xml.bind.JAXB;
import javax.xml.ws.BindingProvider;
>>>>>>> refs/remotes/apache/master
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.api_v3.Node;
import org.apache.juddi.config.AppConfig;
import org.apache.juddi.config.PersistenceManager;
import org.apache.juddi.config.Property;
<<<<<<< HEAD
import org.apache.juddi.mapping.MappingModelToApi;
import org.apache.juddi.model.ReplicationConfiguration;

import org.apache.juddi.v3.client.UDDIService;
import org.uddi.repl_v3.ChangeRecordIDType;
import org.uddi.repl_v3.CommunicationGraph;
import org.uddi.repl_v3.HighWaterMarkVectorType;
import org.uddi.repl_v3.NotifyChangeRecordsAvailable;
import org.uddi.v3_service.UDDIReplicationPortType;

/**
=======
import org.apache.juddi.mapping.MappingApiToModel;
import org.apache.juddi.mapping.MappingModelToApi;
import org.apache.juddi.model.ChangeRecord;
import org.apache.juddi.model.ReplicationConfiguration;
import org.apache.juddi.v3.client.UDDIService;
import org.apache.juddi.v3.client.cryptor.TransportSecurityHelper;
import org.uddi.repl_v3.ChangeRecordIDType;
import org.uddi.repl_v3.CommunicationGraph.Edge;
import org.uddi.repl_v3.HighWaterMarkVectorType;
import org.uddi.repl_v3.NotifyChangeRecordsAvailable;
import org.uddi.repl_v3.Operator;
import org.uddi.v3_service.UDDIReplicationPortType;

/**
 * Handles when local records have been changed, change journal storage and
 * notifications to all remote replication nodes that something has been
 * altered.
>>>>>>> refs/remotes/apache/master
 *
 * @author <a href="mailto:alexoree@apache.org">Alex O'Ree</a>
 *
 */
public class ReplicationNotifier extends TimerTask {

<<<<<<< HEAD
        private Log log = LogFactory.getLog(this.getClass());
        private Timer timer = null;
        private long startBuffer = AppConfig.getConfiguration().getLong(Property.JUDDI_NOTIFICATION_START_BUFFER, 20000l); // 20s startup delay default 
        private long interval = AppConfig.getConfiguration().getLong(Property.JUDDI_NOTIFICATION_INTERVAL, 300000l); //5 min default
        private long acceptableLagTime = AppConfig.getConfiguration().getLong(Property.JUDDI_NOTIFICATION_ACCEPTABLE_LAGTIME, 1000l); //1000 milliseconds
=======
        private static Log log = LogFactory.getLog(ReplicationNotifier.class);
        private Timer timer = null;
        private long startBuffer = 5000;//AppConfig.getConfiguration().getLong(Property.JUDDI_NOTIFICATION_START_BUFFER, 20000l); // 20s startup delay default 
        private long interval = 5000;//AppConfig.getConfiguration().getLong(Property.JUDDI_NOTIFICATION_INTERVAL, 300000l); //5 min default
        private static String node = null;
        private static UDDIService uddiService = new UDDIService();
>>>>>>> refs/remotes/apache/master

        /**
         * default constructor
         *
         * @throws ConfigurationException
         */
        public ReplicationNotifier() throws ConfigurationException {
                super();
                timer = new Timer(true);
<<<<<<< HEAD
=======
                startBuffer=AppConfig.getConfiguration().getLong(Property.JUDDI_REPLICATION_START_BUFFER, 5000l);
                interval = AppConfig.getConfiguration().getLong(Property.JUDDI_REPLICATION_INTERVAL, 5000l);
>>>>>>> refs/remotes/apache/master
                timer.scheduleAtFixedRate(this, startBuffer, interval);
                if (queue == null) {
                        queue = new ConcurrentLinkedQueue();
                }
<<<<<<< HEAD
=======
                node = AppConfig.getConfiguration().getString(Property.JUDDI_NODE_ID, "UNDEFINED_NODE_NAME");
>>>>>>> refs/remotes/apache/master
        }

        @Override
        public boolean cancel() {
                timer.cancel();
                //TODO notify other nodes that i'm going down
                return super.cancel();
        }

        //ReplicationNotifier.Enqueue(this);
<<<<<<< HEAD
        public synchronized static void Enqueue(Object change) {
                if (queue == null) {
                        queue = new ConcurrentLinkedQueue();
                }
                queue.add(change);
        }
        static Queue queue;
=======
        public synchronized static void Enqueue(org.apache.juddi.model.ChangeRecord change) {
                if (queue == null) {
                        queue = new ConcurrentLinkedQueue<org.apache.juddi.model.ChangeRecord>();
                }
                queue.add(change);
        }

        public synchronized static void EnqueueRetransmit(org.uddi.repl_v3.ChangeRecord change) {
                if (queue2 == null) {
                        queue2 = new ConcurrentLinkedQueue<org.uddi.repl_v3.ChangeRecord>();
                }
                queue2.add(change);
        }
        static Queue<org.apache.juddi.model.ChangeRecord> queue;
        static Queue<org.uddi.repl_v3.ChangeRecord> queue2;

        /**
         * Note: this is for locally originated changes only, see null null null         {@link org.apache.juddi.api.impl.UDDIReplicationImpl.PullTimerTask#PersistChangeRecord PersistChangeRecord
         * } for how remote changes are processed
         *
         * @param j must be one of the UDDI save APIs
         *
         */
        protected void ProcessChangeRecord(org.apache.juddi.model.ChangeRecord j) {
                //store and convert the changes to database model

                //TODO need a switch to send the notification without persisting the record
                //this is to support multihop notifications
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        j.setIsAppliedLocally(true);
                        em.persist(j);
                        j.setOriginatingUSN(j.getId());
                        em.merge(j);
                        log.info("CR saved locally, it was from " + j.getNodeID()
                                + " USN:" + j.getOriginatingUSN()
                                + " Type:" + j.getRecordType().name()
                                + " Key:" + j.getEntityKey()
                                + " Local id:" + j.getId());
                        tx.commit();
                } catch (Exception ex) {
                        log.fatal("unable to store local change record locally!!", ex);
                        if (tx != null && tx.isActive()) {
                                tx.rollback();
                        }
                        JAXB.marshal(MappingModelToApi.mapChangeRecord(j), System.out);
                } finally {
                        em.close();
                }

                log.debug("ChangeRecord: " + j.getId() + "," + j.getEntityKey() + "," + j.getNodeID() + "," + j.getOriginatingUSN() + "," + j.getRecordType().toString());
                SendNotifications(j.getId(), j.getNodeID(), false);

        }

        private void SendNotifications(Long id, String origin_node, boolean isRetrans) {

                org.uddi.repl_v3.ReplicationConfiguration repcfg = FetchEdges();

                if (repcfg == null) {
                        log.debug("No replication configuration is defined!");
                        return;

                }
                if (id == null || origin_node == null) {
                        log.fatal("Either the id is null or the origin_node is null. I can't send out this alert!!");
                        //throw new Exception(node);
                        return;
                }

                Set<Object> destinationUrls = new HashSet<Object>();

                /**
                 * In the absence of a communicationGraph element from the
                 * Replication Configuration Structure (although it's mandatory
                 * in the xsd), all nodes listed in the node element MAY send
                 * any and all messages to any other node of the registry.
                 */
                if (repcfg.getCommunicationGraph() == null
                        || repcfg.getCommunicationGraph().getEdge().isEmpty() && !isRetrans) {
                        //no edges or graph defined, default to the operator list
                        //retransmission only applies to non-directed-edge replication, thus the extra check
                        for (Operator o : repcfg.getOperator()) {
                                //no need to tell myself about a change at myself or the origin
                                if (!o.getOperatorNodeID().equalsIgnoreCase(node) && !o.getOperatorNodeID().equalsIgnoreCase(origin_node)) {
                                        destinationUrls.add(o.getSoapReplicationURL());
                                }
                        }
                } else {
                        //this is for directed graph replication
                        //find all nodes that i need to notify
                        Iterator<Edge> iterator = repcfg.getCommunicationGraph().getEdge().iterator();
                        while (iterator.hasNext()) {
                                Edge next = iterator.next();

                                if (next.getMessageSender().equalsIgnoreCase(node)) {

                                        //this is my server and i need to transmit the notification to
                                        String messageReceiver = next.getMessageReceiver();
                                        PrimaryAlternate container = new PrimaryAlternate();
                                        //pointless to send a notification to myself or the origin
                                        if (!messageReceiver.equalsIgnoreCase(node) && !messageReceiver.equalsIgnoreCase(origin_node)) {
                                                //look up the endpoint urls
                                                for (int x = 0; x < repcfg.getOperator().size(); x++) {
                                                        if (repcfg.getOperator().get(x).getOperatorNodeID().equalsIgnoreCase(messageReceiver)) {
                                                                container.primaryUrl = repcfg.getOperator().get(x).getSoapReplicationURL();
                                                        }
                                                }
                                                for (int y = 0; y < next.getMessageReceiverAlternate().size(); y++) {
                                                        for (int x = 0; x < repcfg.getOperator().size(); x++) {
                                                                if (repcfg.getOperator().get(x).getOperatorNodeID().equalsIgnoreCase(next.getMessageReceiverAlternate().get(y))) {
                                                                        container.alternateUrls.add(repcfg.getOperator().get(x).getSoapReplicationURL());
                                                                }
                                                        }
                                                }
                                        }
                                        if (container.primaryUrl != null) {
                                                destinationUrls.add(container);
                                        } else {
                                                log.warn("Unable to find primary url for directed edge graph replication from this node " + node + " to "
                                                        + "destination node " + next.getMessageReceiver() + " it will be ignored!");
                                        }

                                }

                        }

                }

                if (destinationUrls.isEmpty()) {
                        log.debug("Something is bizarre with the replication config. I should have had at least one node to notify, but I have none!");
                        return;
                }
                UDDIReplicationPortType x = uddiService.getUDDIReplicationPort();
                TransportSecurityHelper.applyTransportSecurity((BindingProvider) x);

                for (Object s : destinationUrls) {

                        NotifyChangeRecordsAvailable req = new NotifyChangeRecordsAvailable();

                        req.setNotifyingNode(node);
                        HighWaterMarkVectorType highWaterMarkVectorType = new HighWaterMarkVectorType();

                        highWaterMarkVectorType.getHighWaterMark().add(new ChangeRecordIDType(origin_node, id));
                        req.setChangesAvailable(highWaterMarkVectorType);

                        if (s instanceof String) {
                                SendNotification(x, (String) s, req);
                        } else if (s instanceof PrimaryAlternate) {
                                //more complex directed graph stuff
                                PrimaryAlternate pa = (PrimaryAlternate) s;
                                if (!SendNotification(x, pa.primaryUrl, req)) {
                                        for (String url : pa.alternateUrls) {
                                                if (SendNotification(x, url, req)) {
                                                        break;
                                                }
                                                //no need to continue to additional alternates
                                        }
                                } else {
                                        //primary url succeeded, no further action required
                                }

                        }

                        //TODO the spec talks about control messages, should we even support it? seems pointless
                }

        }

        /**
         * return true if successful
         *
         * @param x
         * @param s
         * @param req
         * @return
         */
        private boolean SendNotification(UDDIReplicationPortType x, String s, NotifyChangeRecordsAvailable req) {
                ((BindingProvider) x).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, s);
                try {
                        x.notifyChangeRecordsAvailable(req);
                        log.info("Successfully sent change record available message to " + s + " this node: " + node);
                        return true;
                } catch (Exception ex) {
                        log.warn("Unable to send change notification to " + s + " this node: " + node + " reason: " + ex.getMessage());
                        log.debug("Unable to send change notification to " + s, ex);
                }
                return false;
        }

        class PrimaryAlternate {

                String primaryUrl = null;
                List<String> alternateUrls = new ArrayList<String>();
        }
>>>>>>> refs/remotes/apache/master

        public synchronized void run() {
                log.debug("Replication thread triggered");
                if (queue == null) {
                        queue = new ConcurrentLinkedQueue();
                }
<<<<<<< HEAD
                while (!queue.isEmpty()) {
                        log.info("Notifying nodes of change records " + queue.size());
                        //TODO identify chnage set format
                        Object j = queue.poll();
                        org.uddi.repl_v3.ReplicationConfiguration repcfg = FetchEdges();
                        if (repcfg == null) {
                                log.debug("No replication configuration is defined!");
                                queue.clear();
                                break;
                        }
                        Iterator<CommunicationGraph.Edge> it = repcfg.getCommunicationGraph().getEdge().iterator();

                        while (it.hasNext()) {

                                //for (int i = 0; i < endpoints.size(); i++) {
                                UDDIReplicationPortType x = new UDDIService().getUDDIReplicationPort();
                                CommunicationGraph.Edge next = it.next();
                                next.getMessageReceiver(); //Node ID
                                Node destinationNode = getNode(next.getMessageSender());
                                if (destinationNode == null) {
                                        log.warn(next.getMessageSender() + " node was not found, cannot deliver replication messages");
                                } else {
                                        ((BindingProvider) x).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, destinationNode.getReplicationUrl());
                                        NotifyChangeRecordsAvailable req = new NotifyChangeRecordsAvailable();
                                        String node = "UNKNOWN";
                                        try {
                                                node = AppConfig.getConfiguration().getString(Property.JUDDI_NODE_ID);
                                        } catch (ConfigurationException ex) {
                                                log.fatal(ex);
                                        }
                                        req.setNotifyingNode(node);
                                        HighWaterMarkVectorType highWaterMarkVectorType = new HighWaterMarkVectorType();
                                        String nextWatermark = ""; //TODO get current watermark + 1 toString()
                                        //TODO save watermark along with change set

                                        highWaterMarkVectorType.getHighWaterMark().add(new ChangeRecordIDType(node, 1L));
                                        req.setChangesAvailable(highWaterMarkVectorType);
                                        try {
                                                x.notifyChangeRecordsAvailable(req);
                                        } catch (Exception ex) {
                                                log.warn("Unable to send change notification to " + next.getMessageSender(), ex);
                                        }
                                }
                        }
                }
        }

        private org.uddi.repl_v3.ReplicationConfiguration FetchEdges() {
=======
                if (queue2 == null) {
                        queue2 = new ConcurrentLinkedQueue();
                }
                //TODO revisie this
                if (!queue.isEmpty()) {
                        log.info("Replication, Notifying nodes of new change records. " + queue.size() + " queued");
                }

                //TODO check for replication config changes
                while (!queue.isEmpty()) {
                        //for each change at this node

                        ChangeRecord j = queue.poll();
                        ProcessChangeRecord(j);

                }

                while (!queue2.isEmpty()) {
                        //for each change at this node

                        org.uddi.repl_v3.ChangeRecord j = queue2.poll();

                        ChangeRecord model = new ChangeRecord();
                        try {
                                model = MappingApiToModel.mapChangeRecord(j);
                        } catch (UnsupportedEncodingException ex) {
                                Logger.getLogger(ReplicationNotifier.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        log.info("retransmitting CR notificationm entity owner: " + j.getChangeID().getNodeID() + " CR: " + j.getChangeID().getOriginatingUSN() + " key:" + model.getEntityKey() + " " + model.getRecordType().name() + " accepted locally:" + model.getIsAppliedLocally());
                        SendNotifications(j.getChangeID().getOriginatingUSN(), j.getChangeID().getNodeID(), true);

                }
        }

        /**
         * returns the latest version of the replication config or null if there
         * is no config
         *
         * @return
         */
        public static org.uddi.repl_v3.ReplicationConfiguration FetchEdges() {
>>>>>>> refs/remotes/apache/master

                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = null;
                org.uddi.repl_v3.ReplicationConfiguration item = new org.uddi.repl_v3.ReplicationConfiguration();
                try {
                        tx = em.getTransaction();
                        tx.begin();
<<<<<<< HEAD
                        Query q = em.createQuery("SELECT item FROM ReplicationConfiguration item");
                        q.setMaxResults(1);
                        List<ReplicationConfiguration> results = (List<ReplicationConfiguration>) q.getResultList();
                        //   ReplicationConfiguration find = em.find(ReplicationConfiguration.class, null);
                        if (results != null && !results.isEmpty()) {
                                MappingModelToApi.mapReplicationConfiguration(results.get(0), item);
                        } else {
                                item = null;
                        }
                        tx.commit();
                        return item;
                } catch (Exception ex) {
                        log.error("error", ex);
=======
                        Query q = em.createQuery("SELECT item FROM ReplicationConfiguration item order by item.serialNumber DESC");
                        q.setMaxResults(1);
                        ReplicationConfiguration results = (ReplicationConfiguration) q.getSingleResult();
                        //   ReplicationConfiguration find = em.find(ReplicationConfiguration.class, null);
                        if (results != null) {
                                MappingModelToApi.mapReplicationConfiguration(results, item);
                        }

                        tx.commit();
                        return item;
                } catch (Exception ex) {
                        //log.error("error", ex);
                        //no config available
>>>>>>> refs/remotes/apache/master
                        if (tx != null && tx.isActive()) {
                                tx.rollback();
                        }
                } finally {
                        em.close();
                }
                return null;
        }

<<<<<<< HEAD
        private Node getNode(String messageSender) {
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = null;
                org.uddi.repl_v3.ReplicationConfiguration item = new org.uddi.repl_v3.ReplicationConfiguration();
=======
        @Deprecated
        private Node getNode(String messageSender) {
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = null;
>>>>>>> refs/remotes/apache/master
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        Node api = new Node();
                        org.apache.juddi.model.Node find = em.find(org.apache.juddi.model.Node.class, messageSender);
                        if (find != null) {
                                MappingModelToApi.mapNode(find, api);
                        }
                        tx.commit();
                        return api;
                } catch (Exception ex) {
                        log.error("error", ex);
                        if (tx != null && tx.isActive()) {
                                tx.rollback();
                        }
                } finally {
                        em.close();
                }
                return null;
        }
}
