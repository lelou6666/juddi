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
package org.apache.juddi.api_v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
<<<<<<< HEAD
import javax.xml.bind.annotation.XmlTransient;
=======
>>>>>>> refs/remotes/apache/master
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for clientSubscriptionInfoDetail complex type.
<<<<<<< HEAD
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 *
=======
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
>>>>>>> refs/remotes/apache/master
 * <pre>
 * &lt;complexType name="clientSubscriptionInfoDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientSubscriptionInfo" type="{urn:juddi-apache-org:api_v3}clientSubscriptionInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
<<<<<<< HEAD
 *
 *
=======
 * 
 * 
>>>>>>> refs/remotes/apache/master
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clientSubscriptionInfoDetail", propOrder = {
        "clientSubscriptionInfo"
})
<<<<<<< HEAD
public class ClientSubscriptionInfoDetail implements Serializable {

        @XmlTransient
        private static final long serialVersionUID = -409328006334478420L;
        @XmlElement(nillable = true)
        protected List<ClientSubscriptionInfo> clientSubscriptionInfo;

        /**
         * Gets the value of the clientSubscriptionInfo property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a
         * snapshot. Therefore any modification you make to the returned list
         * will be present inside the JAXB object. This is why there is not a
         * <CODE>set</CODE> method for the clientSubscriptionInfo property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClientSubscriptionInfo().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
     * {@link ClientSubscriptionInfo }
         *
         *
         */
        public List<ClientSubscriptionInfo> getClientSubscriptionInfo() {
                if (clientSubscriptionInfo == null) {
                        clientSubscriptionInfo = new ArrayList<ClientSubscriptionInfo>();
                }
                return this.clientSubscriptionInfo;
=======
public class ClientSubscriptionInfoDetail {

    @XmlElement(nillable = true)
    protected List<ClientSubscriptionInfo> clientSubscriptionInfo;

    /**
     * Gets the value of the clientSubscriptionInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientSubscriptionInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientSubscriptionInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClientSubscriptionInfo }
     * 
     * 
     */
    public List<ClientSubscriptionInfo> getClientSubscriptionInfo() {
        if (clientSubscriptionInfo == null) {
            clientSubscriptionInfo = new ArrayList<ClientSubscriptionInfo>();
>>>>>>> refs/remotes/apache/master
        }
}
