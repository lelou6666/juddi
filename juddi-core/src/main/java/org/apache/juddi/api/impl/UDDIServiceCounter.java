package org.apache.juddi.api.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.api.util.QueryStatus;
import org.apache.juddi.api.util.UDDIQuery;

public class UDDIServiceCounter implements DynamicMBean, Serializable {

    private static Log log = LogFactory.getLog(UDDIServiceCounter.class);

    private Hashtable <String, LongHolder> queryProcessingTime;
    private Hashtable <String, IntHolder> totalQueryCounter;
    private Hashtable <String, IntHolder> successQueryCounter;
    private Hashtable <String, IntHolder> faultQueryCounter;

    private ObjectName listObjectName = null;
    
    private static final String PROCESSING_TIME = "processing time";
    private static final String TOTAL_QUERIES = "total queries";
    private static final String SUCCESSFUL_QUERIES = "successful queries";
    private static final String FAILED_QUERIES = "failed queries";
    
    public static final String RESET_COUNTER = "resetCounter";
        
    public void initList(Class klass, List<String> queries) {
        try {
            listObjectName = new ObjectName("apache.juddi:" + "counter=" + klass.getName());
        } catch (MalformedObjectNameException mone) {
            log.error(mone);
        }
        
        queryProcessingTime = new Hashtable<String,LongHolder>();
        totalQueryCounter = new Hashtable<String, IntHolder>();
        successQueryCounter = new Hashtable<String, IntHolder>();
        faultQueryCounter = new Hashtable<String, IntHolder>();
            
        for (String query : queries) {
            queryProcessingTime.put(query + " " +  PROCESSING_TIME, new LongHolder());
            totalQueryCounter.put(query + " " + TOTAL_QUERIES, new IntHolder());
            successQueryCounter.put(query + " " + SUCCESSFUL_QUERIES, new IntHolder());
            faultQueryCounter.put(query + " " + FAILED_QUERIES, new IntHolder());
        }
    }
    
    protected void registerMBean() {
        MBeanServer mbeanServer = null;
        
        mbeanServer = getServer();
        if (mbeanServer == null) {
            try {
//                mbeanServer = MBeanServerLocator.locateJBoss();
            } catch (IllegalStateException ise) {
                // If we can't find a JBoss MBeanServer, just return
                // Needed for unit tests
                return;
            }            
        }
        
        try {
            mbeanServer.registerMBean(this, listObjectName);
        } catch (InstanceAlreadyExistsException e) {
            log.warn("", e);
        } catch (MBeanRegistrationException e) {
            log.warn("", e);
        } catch (NotCompliantMBeanException e) {
            log.warn("", e);
        }   
    }
        
    private MBeanServer getServer() {
        MBeanServer mbserver = null;

        ArrayList mbservers = MBeanServerFactory.findMBeanServer(null);

        if (mbservers.size() > 0) {
          mbserver = (MBeanServer) mbservers.get(0);
        }

        if (mbserver != null) {
          System.out.println("Found our MBean server");
        } else {
          mbserver = MBeanServerFactory.createMBeanServer();
        } 

        return mbserver;
    }
    
    public void resetCounts() {
        for (String key : queryProcessingTime.keySet()) {
            queryProcessingTime.put(key, new LongHolder());
        }
        
        for (String key : totalQueryCounter.keySet()) {
            totalQueryCounter.put(key, new IntHolder());
        }
        
        for (String key : successQueryCounter.keySet()) {
            successQueryCounter.put(key, new IntHolder());
        }
        
        for (String key : faultQueryCounter.keySet()) {
            faultQueryCounter.put(key, new IntHolder());
        }
    }

    public synchronized void update(UDDIQuery queryObject, QueryStatus queryStatus, 
            long procTime) {        
        //log.info("Updating " + queryObject.getQuery() + " time " + procTime);
        String query = queryObject.getQuery();
        
        LongHolder totalProcTime = queryProcessingTime.get(query + " " +  PROCESSING_TIME);
        if (totalProcTime != null) {
            totalProcTime.value += procTime;
        } else {
            throw new RuntimeException("Exception in Update : " + queryObject.getQuery() 
                    + " time " + procTime + " queryprocessingtime.size() + "
                    + queryProcessingTime.size());
        }
        IntHolder queryCounter = totalQueryCounter.get(query + " " + TOTAL_QUERIES);
        if (queryCounter != null) { 
            queryCounter.value++;
        } else {
            throw new RuntimeException("Exception in Update : " + queryObject.getQuery() 
                    + " time " + procTime + " totalQueryCounter.size() + "
                    + totalQueryCounter.size());
        }

        if (queryStatus == QueryStatus.SUCCESS) {
            IntHolder successQuery = successQueryCounter.get(query + " " + SUCCESSFUL_QUERIES);
            if (successQuery != null) {
                successQuery.value++;
            } else {
                throw new RuntimeException("Exception in Update : " + queryObject.getQuery() 
                        + " time " + procTime + " successQueryCounter.size() "
                        + successQueryCounter.size());
            }
        } else if (queryStatus == queryStatus.FAILED) {
            IntHolder faultQuery = faultQueryCounter.get(query + " " + SUCCESSFUL_QUERIES);
            if (faultQuery != null) {
                faultQuery.value++;
            } else {
                throw new RuntimeException("Exception in Update : " + queryObject.getQuery() 
                        + " time " + procTime + " faultQueryCounter.size() "
                        + faultQueryCounter.size());
            }            
        }
    }
    
    @Override
    public Object getAttribute(String attribute)
            throws AttributeNotFoundException, MBeanException,
            ReflectionException {
        if (queryProcessingTime.containsKey(attribute)) {
            return queryProcessingTime.get(attribute);
        } else if (totalQueryCounter.containsKey(attribute)) {
            return totalQueryCounter.get(attribute);
        } else if (successQueryCounter.containsKey(attribute)) {
            return successQueryCounter.get(attribute);
        } else if (faultQueryCounter.containsKey(attribute)) {
            return faultQueryCounter.get(attribute);
        }
        return null;
    }

    @Override
    public void setAttribute(Attribute attribute)
            throws AttributeNotFoundException, InvalidAttributeValueException,
            MBeanException, ReflectionException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        AttributeList attributeList = new AttributeList();

        for (String key : queryProcessingTime.keySet()) {
            Attribute at = new Attribute(key, queryProcessingTime.get(key).toString());
            attributeList.add(at);
        }
                
        for (String key : totalQueryCounter.keySet()) {
            Attribute at = new Attribute(key, totalQueryCounter.get(key).toString());
            attributeList.add(at);
        }
        
        for (String key : successQueryCounter.keySet()) {
            Attribute at = new Attribute(key, successQueryCounter.get(key).toString());
            attributeList.add(at);
        }

        for (String key : faultQueryCounter.keySet()) {
            Attribute at = new Attribute(key, faultQueryCounter.get(key).toString());
            attributeList.add(at);
        }
        
        return attributeList;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature)
            throws MBeanException, ReflectionException {
        if (actionName.equalsIgnoreCase(RESET_COUNTER)) {
            resetCounts();
            return "Invoking the " + actionName + " on the lifecycle.";
        } else {
            throw new ReflectionException(new NoSuchMethodException(actionName));
        }
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        int count = queryProcessingTime.size() + totalQueryCounter.size() +
            successQueryCounter.size() + faultQueryCounter.size();

        MBeanAttributeInfo[] attrs = new MBeanAttributeInfo[count];
        int counter = 0;

        for (String key : queryProcessingTime.keySet()) {
            attrs[counter] = new MBeanAttributeInfo(
                    key, "java.lang.Double", "Property " + key, true, false, false);
            counter++;
        }

        for (String key : totalQueryCounter.keySet()) {
            attrs[counter] = new MBeanAttributeInfo(
                    key, "java.lang.Integer", "Property " + key, true, false, false);
            counter++;
        }

        for (String key : successQueryCounter.keySet()) {
            attrs[counter] = new MBeanAttributeInfo(
                    key, "java.lang.Integer", "Property " + key, true, false, false);
            counter++;
        }

        for (String key : faultQueryCounter.keySet()) {
            attrs[counter] = new MBeanAttributeInfo(
                    key, "java.lang.Integer", "Property " + key, true, false, false);
            counter++;
        }
        
        MBeanOperationInfo[] opers = {
                new MBeanOperationInfo(
                                "resetCounts", "Reset the counter",
                        null, "void", MBeanOperationInfo.ACTION)
        };        
        
        return new MBeanInfo(this.getClass().getName(), "Service Counter MBean", 
                attrs, null, opers, null);
    }
    
    private static final class IntHolder implements Serializable
    {
            int value ;
            @Override
            public String toString() {
                    return Integer.toString(value);
            }
    }

    private static final class LongHolder implements Serializable
    {
            long value ;
            @Override
            public String toString() {
                    return Long.toString(value);
            }
    }


}