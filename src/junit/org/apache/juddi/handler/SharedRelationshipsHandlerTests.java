/*
 * Copyright 2001-2004 The Apache Software Foundation.
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
 */
package org.apache.juddi.handler;

import java.io.IOException;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.apache.juddi.datatype.KeyedReference;
import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.datatype.SharedRelationships;
import org.apache.juddi.util.xml.XMLUtils;
import org.w3c.dom.Element;

/**
 * @author anou_mana@apache.org
 */
public class  SharedRelationshipsHandlerTests extends TestCase
{
	private static final String TEST_ID = "juddi.handler.DeleteSharedRelationships.test";
	private  SharedRelationshipsHandler handler = null;

  public  SharedRelationshipsHandlerTests(String arg0)
  {
    super(arg0);
  }

  public static void main(String[] args)
  {
    junit.textui.TestRunner.run( SharedRelationshipsHandlerTests.class);
  }

  public void setUp()
  {
		HandlerMaker maker = HandlerMaker.getInstance();
		handler = ( SharedRelationshipsHandler)maker.lookup( SharedRelationshipsHandler.TAG_NAME);
  }

	private RegistryObject getRegistryObject()
	{

		SharedRelationships object = new SharedRelationships();
		object.addKeyedReference(new KeyedReference("sharedRefKeyName","sharedRefKeyValue"));
		object.addKeyedReference(new KeyedReference("uuid:8ff45356-acde-4a4c-86bf-f953611d20c6","sharedRefKeyName2","sharedRefKeyValue2"));

		return object;

	}

	private String getXMLString(Element element)
	{
		StringWriter writer = new StringWriter();
		XMLUtils.writeXML(element,writer);

		String xmlString = writer.toString();

		try
		{
			writer.close();
		}
		catch(IOException exp)
		{
		}

		return xmlString;
	}

	private Element getMarshalledElement(RegistryObject regObject)
	{
		Element parent = XMLUtils.newRootElement();
		Element child = null;

		if(regObject == null)
			regObject = this.getRegistryObject();

		handler.marshal(regObject,parent);
		child = (Element)parent.getFirstChild();
		parent.removeChild(child);

		return child;
	}

	public void testMarshal()
	{
		Element child = getMarshalledElement(null);

		String marshalledString = this.getXMLString(child);

		assertNotNull("Marshalled  SharedRelationships ", marshalledString);

	}

	public void testUnMarshal()
	{

		Element child = getMarshalledElement(null);
		RegistryObject regObject = handler.unmarshal(child);

		assertNotNull("UnMarshalled  SharedRelationships ", regObject);

	}

  public void testMarshUnMarshal()
  {
		Element child = getMarshalledElement(null);

		String marshalledString = this.getXMLString(child);

		assertNotNull("Marshalled  SharedRelationships ", marshalledString);

		RegistryObject regObject = handler.unmarshal(child);

		child = getMarshalledElement(regObject);

		String unMarshalledString = this.getXMLString(child);

		assertNotNull("Unmarshalled  SharedRelationships ", unMarshalledString);

		boolean equals = marshalledString.equals(unMarshalledString);

		assertEquals("Expected SharedRelationships: ", marshalledString, unMarshalledString );
  }

}
