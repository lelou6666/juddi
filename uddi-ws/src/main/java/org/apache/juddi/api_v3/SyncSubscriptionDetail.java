/*
 * Copyright 2001-2009 The Apache Software Foundation.
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
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/apache/master
package org.apache.juddi.api_v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
<<<<<<< HEAD
import javax.xml.bind.annotation.XmlTransient;import org.uddi.sub_v3.SubscriptionResultsList;
=======
import org.uddi.sub_v3.SubscriptionResultsList;
>>>>>>> refs/remotes/apache/master


/**
 * <p>Java class for syncSubscriptionDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="syncSubscriptionDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
<<<<<<< HEAD
 *         &lt;element name="list" type="{urn:uddi-org:sub_v3}subscriptionResultsList" maxOccurs="unbounded" minOccurs="0"/>
=======
 *         &lt;element name="subscriptionResultsList" type="{urn:uddi-org:sub_v3}subscriptionResultsList" maxOccurs="unbounded" minOccurs="0"/>
>>>>>>> refs/remotes/apache/master
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "syncSubscriptionDetail", propOrder = {
    "subscriptionResultsList"
})
<<<<<<< HEAD
public class SyncSubscriptionDetail implements Serializable{
	
	@XmlTransient
	private static final long serialVersionUID = -4039550325209019888L;
    @XmlElement(nillable = true)
    protected List<SubscriptionResultsList> list;

    /**
     * Gets the value of the list property.
=======
public class SyncSubscriptionDetail {

    @XmlElement(nillable = true)
    protected List<SubscriptionResultsList> subscriptionResultsList;

    /**
     * Gets the value of the subscriptionResultsList property.
>>>>>>> refs/remotes/apache/master
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
<<<<<<< HEAD
     * This is why there is not a <CODE>set</CODE> method for the list property.
=======
     * This is why there is not a <CODE>set</CODE> method for the subscriptionResultsList property.
>>>>>>> refs/remotes/apache/master
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
<<<<<<< HEAD
     *    getList().add(newItem);
=======
     *    getSubscriptionResultsList().add(newItem);
>>>>>>> refs/remotes/apache/master
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubscriptionResultsList }
     * 
     * 
     */
<<<<<<< HEAD
    public List<SubscriptionResultsList> getList() {
        if (list == null) {
            list = new ArrayList<SubscriptionResultsList>();
        }
        return this.list;
=======
    public List<SubscriptionResultsList> getSubscriptionResultsList() {
        if (subscriptionResultsList == null) {
            subscriptionResultsList = new ArrayList<SubscriptionResultsList>();
        }
        return this.subscriptionResultsList;
>>>>>>> refs/remotes/apache/master
    }

}
