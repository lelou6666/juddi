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

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.api.util.QueryStatus;
import org.apache.juddi.api.util.ValueSetValidationQuery;
import org.apache.juddi.config.PersistenceManager;
import org.apache.juddi.model.ValueSetValues;
import org.apache.juddi.v3.error.ErrorMessage;
import org.apache.juddi.v3.error.InvalidValueException;
import org.apache.juddi.v3.error.ValueNotAllowedException;
=======
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import static org.apache.juddi.api.impl.AuthenticatedService.logger;
import org.apache.juddi.api.util.QueryStatus;
import org.apache.juddi.api.util.ValueSetValidationQuery;
import org.apache.juddi.config.PersistenceManager;
import org.apache.juddi.model.Tmodel;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.UDDIConstantsV2;
import org.apache.juddi.v3.error.ErrorMessage;
import org.apache.juddi.v3.error.FatalErrorException;
import org.apache.juddi.v3.error.ValueNotAllowedException;
import org.apache.juddi.validation.vsv.AbstractSimpleValidator;
import org.apache.juddi.validation.vsv.ValueSetValidator;
>>>>>>> refs/remotes/apache/master
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.DispositionReport;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.KeyedReferenceGroup;
import org.uddi.api_v3.PublisherAssertion;
import org.uddi.api_v3.Result;
import org.uddi.api_v3.TModel;
import org.uddi.api_v3.TModelInstanceInfo;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIValueSetValidationPortType;
import org.uddi.vs_v3.ValidateValues;

/**
 * Implementation the UDDI v3 spec for Value Set Validation This is basically
 * used to validate Keyed Reference value sets and offers validation via jUDDI's
 * VSV extensibility framework.<Br><BR>
 * To use this, define a tModel containing the following
 * <pre>&lt;categoryBag&gt;
 * &lt;keyedReference keyName=&quot;&quot;
 * keyValue=&quot;uddi:juddi.apache.org:servicebindings-valueset-cp&quot;
 * tModelKey=&quot;uddi:uddi.org:identifier:validatedby&quot;/&gt;
 * &lt;/categoryBag&gt;
 * </pre>Where uddi:juddi.apache.org:servicebindings-valueset-cp is the binding
 * key of the service implementing the VSV API (this service).
 * <Br><BR>
 * From there, you need to create a class that either implements
 * {@link ValueSetValidator} or extends {@link AbstractSimpleValidator}. It must
 * be in the package named org.apache.juddi.validation.vsv and must by named
 * following the convention outlined in {@link #ConvertKeyToClass(java.lang.String)
 * }
 *
 * @see ValueSetValidator
 * @see AbstractSimpleValidator
 * @author <a href="mailto:alexoree@apache.org">Alex O'Ree</a>
 */
public class UDDIValueSetValidationImpl extends AuthenticatedService implements
<<<<<<< HEAD
        UDDIValueSetValidationPortType {
=======
     UDDIValueSetValidationPortType {
>>>>>>> refs/remotes/apache/master

        private UDDIServiceCounter serviceCounter;

        public UDDIValueSetValidationImpl() {
                super();
                serviceCounter = ServiceCounterLifecycleResource.getServiceCounter(this.getClass());
        }

        @Override
        public DispositionReport validateValues(ValidateValues body)
<<<<<<< HEAD
                throws DispositionReportFaultMessage {
=======
             throws DispositionReportFaultMessage {
>>>>>>> refs/remotes/apache/master
                long startTime = System.currentTimeMillis();

                if (body == null) {
                        long procTime = System.currentTimeMillis() - startTime;
                        serviceCounter.update(ValueSetValidationQuery.VALIDATE_VALUES,
<<<<<<< HEAD
                                QueryStatus.FAILED, procTime);
=======
                             QueryStatus.FAILED, procTime);
>>>>>>> refs/remotes/apache/master

                        throw new ValueNotAllowedException(new ErrorMessage("errors.valuesetvalidation.noinput"));
                }
                /*
                 * The UDDI node that is calling validate_values MUST pass one 
                 * or more businessEntity elements, one or more businessService 
                 * elements, one or more bindingTemplate elements, one or more 
                 * tModel elements, or one or more publisherAssertion elements 
                 * as the sole argument to this Web service. 
                 * */


                /* performs validation on all of the keyedReferences or keyedReferenceGroups */

                /*when the entity being saved is a businessEntity, contained 
                 * businessService and bindingTemplate entities may themselves 
                 * reference values from the authorized value sets as well. */
<<<<<<< HEAD
                validateValuesBT(body.getBindingTemplate(), "");
                validateValuesBE(body.getBusinessEntity());
                validateValuesBS(body.getBusinessService(), "");
                validateValuesPA(body.getPublisherAssertion());
                validateValuesTM(body.getTModel());
=======
                //go through all published items
                //pull out all keys
                //look up keys in database for a validation class
                //dedup results
                //run validation classes
                List<String> classNames = new ArrayList<String>();
                classNames.addAll(validateValuesBindingTemplate(body.getBindingTemplate()));
                classNames.addAll(validateValuesBusinessEntity(body.getBusinessEntity()));
                classNames.addAll(validateValuesBusinessService(body.getBusinessService()));
                classNames.addAll(validateValuesPublisherAssertion(body.getPublisherAssertion()));
                classNames.addAll(validateValuesTModel(body.getTModel()));
                Set<String> set = new HashSet<String>(classNames);
                Iterator<String> iterator = set.iterator();
                Set<String> validators = new HashSet<String>();
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                //for each key to process
                try {
                        while (iterator.hasNext()) {

                                String key = iterator.next();
                                //find out if it needs to be validated
                                Tmodel find = em.find(org.apache.juddi.model.Tmodel.class, key);
                                if (find != null) {
                                        //if it is, added it to the list
                                        if (ContainsValidatedKey(find, UDDIConstants.IS_VALIDATED_BY)) {
                                                validators.add(key);
                                        }
                                        if (ContainsValidatedKey(find, UDDIConstantsV2.IS_VALIDATED_BY)) {
                                                validators.add(key);
                                        }
                                }
                        }

                } catch (Exception drfm) {
                        logger.warn("Unable to process vsv validation", drfm);
                        throw new FatalErrorException(new ErrorMessage("errors.valuesetvalidation.fatal", drfm.getMessage()));
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
                Iterator<String> iterator1 = validators.iterator();
                while (iterator1.hasNext()) {
                        String tmodelkey = iterator1.next();
                        String clazz = ConvertKeyToClass(tmodelkey);
                        ValueSetValidator vsv;
                        if (clazz == null) {
                                logger.info("No validator found for " + tmodelkey);
                        } else {
                                try {
                                        vsv = (ValueSetValidator) Class.forName(clazz).newInstance();
                                        logger.info("translated " + tmodelkey + " to class " + clazz);
                                        vsv.validateValuesBindingTemplate(body.getBindingTemplate(), "");
                                        vsv.validateValuesBusinessEntity(body.getBusinessEntity());
                                        vsv.validateValuesBusinessService(body.getBusinessService(), "");
                                        vsv.validateValuesPublisherAssertion(body.getPublisherAssertion());
                                        vsv.validateValuesTModel(body.getTModel());
                                } catch (ClassNotFoundException ex) {
                                        logger.warn("Unable to process vsv validation for " + tmodelkey, ex);
                                        throw new FatalErrorException(new ErrorMessage("errors.valuesetvalidation.fatal", "key=" + tmodelkey + " class=" + clazz + " " + ex.getMessage()));
                                } catch (InstantiationException ex) {
                                        logger.warn("Unable to process vsv validation for " + tmodelkey, ex);
                                        throw new FatalErrorException(new ErrorMessage("errors.valuesetvalidation.fatal", "key=" + tmodelkey + " class=" + clazz + " " + ex.getMessage()));
                                } catch (IllegalAccessException ex) {
                                        logger.warn("Unable to process vsv validation for " + tmodelkey, ex);
                                        throw new FatalErrorException(new ErrorMessage("errors.valuesetvalidation.fatal", "key=" + tmodelkey + " class=" + clazz + " " + ex.getMessage()));
                                }
                        }
                }
>>>>>>> refs/remotes/apache/master

                DispositionReport r = new DispositionReport();
                r.getResult().add(new Result());
                long procTime = System.currentTimeMillis() - startTime;
                serviceCounter.update(ValueSetValidationQuery.VALIDATE_VALUES,
<<<<<<< HEAD
                        QueryStatus.SUCCESS, procTime);

                return r;
        }

        private void validateValuesBT(List<BindingTemplate> items, String xpath) throws DispositionReportFaultMessage {
                if (items == null) {
                        return;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCategoryBag() != null) {
                                validatedValuesKeyRef(items.get(i).getCategoryBag().getKeyedReference(), xpath + "bindingTemplate(" + i + ").categoryBag.");
                                validatedValuesKeyRefGrp(items.get(i).getCategoryBag().getKeyedReferenceGroup(), xpath + "bindingTemplate(" + i + ").categoryBag.");
                        }
                        if (items.get(i).getTModelInstanceDetails() != null) {

                                validateTmodelInstanceDetails(items.get(i).getTModelInstanceDetails().getTModelInstanceInfo(), xpath + "bindingTemplate(" + i + ").tModelInstanceDetails.");
                        }
                }
        }

        private void validateValuesBE(List<BusinessEntity> items) throws DispositionReportFaultMessage {
                if (items == null) {
                        return;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCategoryBag() != null) {
                                validatedValuesKeyRef(items.get(i).getCategoryBag().getKeyedReference(), "businessEntity(" + i + ").categoryBag.");
                                validatedValuesKeyRefGrp(items.get(i).getCategoryBag().getKeyedReferenceGroup(), "businessEntity(" + i + ").categoryBag.");
                        }
                        if (items.get(i).getIdentifierBag() != null) {
                                validatedValuesKeyRef(items.get(i).getIdentifierBag().getKeyedReference(), "businessEntity(" + i + ").identifierBag.");
                        }
                        if (items.get(i).getBusinessServices() != null) {
                                validateValuesBS(items.get(i).getBusinessServices().getBusinessService(), "businessEntity(" + i + ").");
                        }
                }
        }

        private void validateValuesBS(List<BusinessService> items, String xpath) throws DispositionReportFaultMessage {
                if (items == null) {
                        return;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCategoryBag() != null) {
                                validatedValuesKeyRef(items.get(i).getCategoryBag().getKeyedReference(), xpath + "businessService(" + i + ").categoryBag.");
                                validatedValuesKeyRefGrp(items.get(i).getCategoryBag().getKeyedReferenceGroup(), xpath + "businessService(" + i + ").categoryBag.");
                        }
                        if (items.get(i).getBindingTemplates() != null) {
                                validateValuesBT(items.get(i).getBindingTemplates().getBindingTemplate(), xpath + xpath + "businessService(" + i + ").identifierBag.");
                        }
                }
        }

        private void validateValuesPA(List<PublisherAssertion> items) throws DispositionReportFaultMessage {
                if (items == null) {
                        return;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getKeyedReference() != null) {
                                List<KeyedReference> temp = new ArrayList<KeyedReference>();
                                temp.add(items.get(i).getKeyedReference());
                                validatedValuesKeyRef(temp, "publisherAssertion(" + i + ").");
                        }
                }
        }

        private void validateValuesTM(List<TModel> items) throws DispositionReportFaultMessage {
                if (items == null) {
                        return;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCategoryBag() != null) {
                                validatedValuesKeyRef(items.get(i).getCategoryBag().getKeyedReference(), "tModel(" + i + ").categoryBag.");
                                validatedValuesKeyRefGrp(items.get(i).getCategoryBag().getKeyedReferenceGroup(), "tModel(" + i + ").categoryBag.");
                        }
                        if (items.get(i).getIdentifierBag() != null) {
                                validatedValuesKeyRef(items.get(i).getIdentifierBag().getKeyedReference(), "tModel(" + i + ").identifierBag.");
                        }
                }
        }

        /**
         * returns null if no valid values are defined (i.e. anything goes)
         *
         * @param tModelkey
         * @return
         */
        public static List<String> getValidValues(String tModelKey) {
                List<String> ret = null;
                EntityManager em = PersistenceManager.getEntityManager();
                EntityTransaction tx = em.getTransaction();
                try {
                        tx.begin();
                        ValueSetValues items = em.find(ValueSetValues.class, tModelKey);
                        if (items != null && items.getValues() != null) {
                                ret = new ArrayList<String>();
                                for (int i = 0; i < items.getValues().size(); i++) {
                                        ret.add(items.getValues().get(i).getValue());
                                }
                        }
                        tx.commit();
                } finally {
                        if (tx.isActive()) {
                                tx.rollback();
                        }
                        em.close();
                }
                return ret;

        }

        private void validatedValuesKeyRef(List<KeyedReference> items, String xpath) throws DispositionReportFaultMessage {
                if (items == null) {
                        return;
                }
                String err = "";
                for (int i = 0; i < items.size(); i++) {
                        List<String> validValues = getValidValues(items.get(i).getTModelKey());
                        if (validValues != null) {
                                //ok we have some work to do
                                boolean valid = false;
                                for (int k = 0; k < validValues.size(); k++) {
                                        if (validValues.get(i).equals(items.get(i).getKeyValue())) {
                                                valid = true;
                                        }
                                }
                                if (!valid) {
                                        err += xpath + "keyedReference(" + i + ") ";
                                }
                        }
                }
                if (err.length() > 0) {
                        throw new InvalidValueException(new ErrorMessage("errors.valuesetvalidation.invalidcontent", err));
                }
        }

        private void validatedValuesKeyRefGrp(List<KeyedReferenceGroup> items, String xpath) throws DispositionReportFaultMessage {
                if (items == null) {
                        return;
                }
                for (int i = 0; i < items.size(); i++) {
                        validatedValuesKeyRef(items.get(i).getKeyedReference(), xpath + "keyReferenceGroup(" + i + ").");
                }
        }

        private void validateTmodelInstanceDetails(List<TModelInstanceInfo> tModelInstanceInfo, String xpath) throws DispositionReportFaultMessage {
                /*
                 if (tModelInstanceInfo == null) {
                 return;
                 }
                 String err = "";
                 for (int i = 0; i < tModelInstanceInfo.size(); i++) {
                 List<String> validValues = getValidValues(tModelInstanceInfo.get(i).getTModelKey());
                 if (validValues != null) {
                 //compare against the instance info
                 if (tModelInstanceInfo.get(i).getInstanceDetails() == null) {
                 err += xpath + ".(" + i + ").instanceDetails=null ";
                 } else {
                 boolean ok = false;
                 for (int k = 0; k < validValues.size(); k++) {
                 if (validValues.get(k).equals(tModelInstanceInfo.get(i).getInstanceDetails().getInstanceParms())) {
                 ok = true;
                 }
                 }
                 if (!ok) {
                 err += xpath + ".(" + i + ").instanceDetails.instanceParams ";
                 }
                 }
                 }
                 }
                 if (err.length() > 0) {
                 throw new InvalidValueException(new ErrorMessage("errors.valuesetvalidation.invalidcontent", err));
                 }*/
=======
                     QueryStatus.SUCCESS, procTime);

                return r;
        }

        private List<String> validateValuesBindingTemplate(List<BindingTemplate> items) {
                List<String> ret = new ArrayList<String>();
                if (items == null) {
                        return ret;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCategoryBag() != null) {
                                ret.addAll(validateValuesKeyRef(items.get(i).getCategoryBag().getKeyedReference()));
                                ret.addAll(validateValuesKeyRefGrp(items.get(i).getCategoryBag().getKeyedReferenceGroup()));
                        }
                        if (items.get(i).getTModelInstanceDetails() != null) {

                                //validateTmodelInstanceDetails(items.get(i).getTModelInstanceDetails().getTModelInstanceInfo(), xpath + "bindingTemplate(" + i + ").tModelInstanceDetails.");
                        }
                }
                return ret;
        }

        private List<String> validateValuesBusinessEntity(List<BusinessEntity> items) {
                List<String> ret = new ArrayList<String>();
                if (items == null) {
                        return ret;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCategoryBag() != null) {
                                ret.addAll(validateValuesKeyRef(items.get(i).getCategoryBag().getKeyedReference()));
                                ret.addAll(validateValuesKeyRefGrp(items.get(i).getCategoryBag().getKeyedReferenceGroup()));
                        }
                        if (items.get(i).getIdentifierBag() != null) {
                                ret.addAll(validateValuesKeyRef(items.get(i).getIdentifierBag().getKeyedReference()));
                        }
                        if (items.get(i).getBusinessServices() != null) {
                                ret.addAll(validateValuesBusinessService(items.get(i).getBusinessServices().getBusinessService()));
                        }
                }
                return ret;
        }

        private List<String> validateValuesBusinessService(List<BusinessService> items) {
                List<String> ret = new ArrayList<String>();
                if (items == null) {
                        return ret;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCategoryBag() != null) {
                                ret.addAll(validateValuesKeyRef(items.get(i).getCategoryBag().getKeyedReference()));
                                ret.addAll(validateValuesKeyRefGrp(items.get(i).getCategoryBag().getKeyedReferenceGroup()));
                        }
                        if (items.get(i).getBindingTemplates() != null) {
                                ret.addAll(validateValuesBindingTemplate(items.get(i).getBindingTemplates().getBindingTemplate()));
                        }
                }
                return ret;
        }

        private List<String> validateValuesPublisherAssertion(List<PublisherAssertion> items) {

                List<String> ret = new ArrayList<String>();
                if (items == null) {
                        return ret;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getKeyedReference() != null) {
                                List<KeyedReference> temp = new ArrayList<KeyedReference>();
                                temp.add(items.get(i).getKeyedReference());
                                ret.addAll(validateValuesKeyRef(temp));
                        }
                }
                return ret;
        }

        private List<String> validateValuesTModel(List<TModel> items) {
                List<String> ret = new ArrayList<String>();
                if (items == null) {
                        return ret;
                }
                for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getCategoryBag() != null) {
                                ret.addAll(validateValuesKeyRef(items.get(i).getCategoryBag().getKeyedReference()));
                                ret.addAll(validateValuesKeyRefGrp(items.get(i).getCategoryBag().getKeyedReferenceGroup()));
                        }
                        if (items.get(i).getIdentifierBag() != null) {
                                ret.addAll(validateValuesKeyRef(items.get(i).getIdentifierBag().getKeyedReference()));
                        }
                }
                return ret;
        }

        private List<String> validateValuesKeyRef(List<KeyedReference> items) {
                List<String> ret = new ArrayList<String>();
                if (items == null) {
                        return ret;
                }

                for (int i = 0; i < items.size(); i++) {
                        ret.add(items.get(i).getTModelKey());
                }

                return ret;
        }

        private List<String> validateValuesKeyRefGrp(List<KeyedReferenceGroup> items) {
                List<String> ret = new ArrayList<String>();
                if (items == null) {
                        return ret;
                }
                for (int i = 0; i < items.size(); i++) {
                        validateValuesKeyRef(items.get(i).getKeyedReference());
                }
                return ret;
        }

        

        public static String ConvertKeyToClass(String tmodelkey) {

                if (tmodelkey == null) {
                        return null;
                }
                if (tmodelkey.length() < 2) {
                        return null;
                }

                String key = new String(new char[]{tmodelkey.charAt(0)}).toUpperCase() + tmodelkey.substring(1).toLowerCase();
                key = key.replaceAll("[^a-zA-Z0-9]", "");

                String clazz = "org.apache.juddi.validation.vsv." + key;

                return clazz;

        }

        public static List<String> getValidValues(String modelKey) {
                try {
                        ValueSetValidator vsv = (ValueSetValidator) Class.forName(ConvertKeyToClass(modelKey)).newInstance();
                        return vsv.getValidValues();
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                }
                return null;
        }

        private boolean ContainsValidatedKey(Tmodel find, String key) {
                if (find.getCategoryBag() != null) {
                        if (find.getCategoryBag().getKeyedReferences() != null) {
                                for (int i = 0; i < find.getCategoryBag().getKeyedReferences().size(); i++) {
                                        if (key.equalsIgnoreCase(find.getCategoryBag().getKeyedReferences().get(i).getTmodelKeyRef())) {
                                                return true;
                                        }
                                }
                        }
                        if (find.getCategoryBag().getKeyedReferenceGroups() != null) {
                                for (int i = 0; i < find.getCategoryBag().getKeyedReferenceGroups().size(); i++) {
                                        for (int k = 0; k < find.getCategoryBag().getKeyedReferenceGroups().get(i).getKeyedReferences().size(); k++) {
                                                if (key.equalsIgnoreCase(find.getCategoryBag().getKeyedReferenceGroups().get(i).getKeyedReferences().get(k).getTmodelKeyRef())) {
                                                        return true;
                                                }
                                        }
                                }
                        }
                }
                if (find.getTmodelIdentifiers() != null) {

                        for (int i = 0; i < find.getTmodelIdentifiers().size(); i++) {
                                if (key.equalsIgnoreCase(find.getTmodelIdentifiers().get(i).getTmodelKeyRef())) {
                                        return true;
                                }
                        }
                }

                return false;
>>>>>>> refs/remotes/apache/master
        }
}
