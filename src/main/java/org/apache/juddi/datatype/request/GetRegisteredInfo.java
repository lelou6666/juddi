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
package org.apache.juddi.datatype.request;

import org.apache.juddi.datatype.RegistryObject;

/**
 * "Used to request an abbreviated synopsis of all information currently
 *  managed by a given individual."
 *
 * @author Steve Viens (sviens@apache.org)
 */
public class GetRegisteredInfo implements RegistryObject,Publish
{
  String generic;
  AuthInfo authInfo;

  /**
   *
   */
  public GetRegisteredInfo()
  {
  }

  /**
   *
   */
  public GetRegisteredInfo(AuthInfo info)
  {
    setAuthInfo(info);
  }

  /**
   *
   * @param genericValue
   */
  public void setGeneric(String genericValue)
  {
    this.generic = genericValue;
  }

  /**
   *
   * @return String UDDI request's generic value.
   */
  public String getGeneric()
  {
    return this.generic;
  }

  /**
   *
   */
  public void setAuthInfo(AuthInfo info)
  {
    this.authInfo = info;
  }

  /**
   *
   */
  public AuthInfo getAuthInfo()
  {
    return authInfo;
  }
}