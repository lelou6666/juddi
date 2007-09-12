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
package org.apache.juddi.datastore.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.datatype.CategoryBag;
import org.apache.juddi.datatype.KeyedReference;
import org.apache.juddi.datatype.request.FindQualifiers;
import org.apache.juddi.datatype.tmodel.TModel;
import org.apache.juddi.util.jdbc.DynamicQuery;

/**
 * @author Steve Viens (sviens@apache.org)
 */
class FindServiceByCategoryQuery
{
  // private reference to the jUDDI logger
  private static Log log = LogFactory.getLog(FindServiceByCategoryQuery.class);

  static String selectSQL;
  static
  {
    // build selectSQL
    StringBuffer sql = new StringBuffer(200);
    sql.append("SELECT S.SERVICE_KEY,S.LAST_UPDATE ");
    sql.append("FROM BUSINESS_SERVICE S,SERVICE_CATEGORY C ");
    selectSQL = sql.toString();
  }

  /**
   * Select ...
   *
   * @param businessKey
   * @param categoryBag
   * @param keysIn
   * @param qualifiers
   * @param connection JDBC connection
   * @throws java.sql.SQLException
   */
  public static Vector select(String businessKey,CategoryBag categoryBag,Vector keysIn,FindQualifiers qualifiers,Connection connection)
    throws java.sql.SQLException
  {
    // If there is a keysIn vector but it doesn't contain
    // any keys then the previous query has exhausted
    // all possibilities of a match so skip this call.
    //
    if ((keysIn != null) && (keysIn.size() == 0))
      return keysIn;

    Vector keysOut = new Vector();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    // construct the SQL statement
    DynamicQuery sql = new DynamicQuery(selectSQL);
    appendWhere(sql,businessKey,categoryBag,qualifiers);
    appendIn(sql,keysIn);
    appendOrderBy(sql,qualifiers);

    try
    {
      log.debug(sql.toString());

      statement = sql.buildPreparedStatement(connection);
      resultSet = statement.executeQuery();

      while (resultSet.next())
        keysOut.addElement(resultSet.getString(1));//("SERVICE_KEY"));

      return keysOut;
    }
    finally
    {
      try {
        resultSet.close();
      }
      catch (Exception e)
      {
        log.warn("An Exception was encountered while attempting to close " +
          "the Find BusinessService ResultSet: "+e.getMessage(),e);
      }

      try {
        statement.close();
      }
      catch (Exception e)
      {
        log.warn("An Exception was encountered while attempting to close " +
          "the Find BusinessService Statement: "+e.getMessage(),e);
      }
    }
  }
  /**
   *
   */
  private static void appendWhere(DynamicQuery sql,String businessKey,CategoryBag categoryBag,FindQualifiers qualifiers)
  {
    sql.append("WHERE C.SERVICE_KEY = S.SERVICE_KEY ");
    if (businessKey != null && businessKey.length() > 0)
    {
      sql.append("AND S.BUSINESS_KEY = ? ");
      sql.addValue(businessKey);
    }
    
    if (categoryBag != null)
    {
      Vector keyedRefVector = categoryBag.getKeyedReferenceVector();
  
      if (keyedRefVector != null)
      {
        int vectorSize = keyedRefVector.size();
        if (vectorSize > 0)
        {
          sql.append("AND (");
  
          for (int i=0; i<vectorSize; i++)
          {
            KeyedReference keyedRef = (KeyedReference)keyedRefVector.elementAt(i);
            String key = keyedRef.getTModelKey();
            String name = keyedRef.getKeyName();
            String value = keyedRef.getKeyValue();
            
            if (name == null)
              name = "";
            
            if (value == null)
              value = "";
            
            // If the tModelKey involved is that of uddi-org:general_keywords, 
            // the keyNames are identical (DO NOT IGNORE keyName). Otherwise 
            // keyNames are not significant. Omitted keyNames are treated as 
            // identical to empty (zero length) keyNames.
            //
            if (key == null || key.length() == 0 || key.equals(TModel.GENERAL_KEYWORDS_TMODEL_KEY)) 
            {
              sql.append("(C.TMODEL_KEY_REF = ? AND C.KEY_NAME = ? AND C.KEY_VALUE = ?)");
              sql.addValue(TModel.GENERAL_KEYWORDS_TMODEL_KEY);
              sql.addValue(name);
              sql.addValue(value);

              if (i+1 < vectorSize)
                sql.append(" OR ");
            }
            else 
            {
              sql.append("(C.TMODEL_KEY_REF = ? AND C.KEY_VALUE = ?)");
              sql.addValue(key);
              sql.addValue(value);

              if (i+1 < vectorSize)
                sql.append(" OR ");
            }
          }
  
          sql.append(") ");
        }
      }
    }
  }

  /**
   * Select ...
   *
   * @param businessKey
   * @param keyedRef
   * @param keysIn
   * @param qualifiers
   * @param connection JDBC connection
   * @throws java.sql.SQLException
   */
  public static Vector select(String businessKey,KeyedReference keyedRef,Vector keysIn,FindQualifiers qualifiers,Connection connection)
    throws java.sql.SQLException
  {
    // If there is a keysIn vector but it doesn't contain
    // any keys then the previous query has exhausted
    // all possibilities of a match so skip this call.
    //
    if ((keysIn != null) && (keysIn.size() == 0))
      return keysIn;

    Vector keysOut = new Vector();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    // construct the SQL statement
    DynamicQuery sql = new DynamicQuery(selectSQL);
    appendWhere(sql,businessKey,keyedRef,qualifiers);
    appendIn(sql,keysIn);
    appendOrderBy(sql,qualifiers);

    try
    {
      log.debug(sql.toString());

      statement = sql.buildPreparedStatement(connection);
      resultSet = statement.executeQuery();

      while (resultSet.next())
        keysOut.addElement(resultSet.getString(1));//("SERVICE_KEY"));

      return keysOut;
    }
    finally
    {
      try {
        resultSet.close();
      }
      catch (Exception e)
      {
        log.warn("An Exception was encountered while attempting to close " +
          "the Find BusinessService ResultSet: "+e.getMessage(),e);
      }

      try {
        statement.close();
      }
      catch (Exception e)
      {
        log.warn("An Exception was encountered while attempting to close " +
          "the Find BusinessService Statement: "+e.getMessage(),e);
      }
    }
  }

  /**
   *
   */
  private static void appendWhere(DynamicQuery sql,String businessKey,KeyedReference keyedRef,FindQualifiers qualifiers)
  {
    sql.append("WHERE C.SERVICE_KEY = S.SERVICE_KEY ");
    if (businessKey != null)
    {
      sql.append("AND S.BUSINESS_KEY = ? ");
      sql.addValue(businessKey);
    }
  
    if (keyedRef != null)
    {
      sql.append("AND (");

      String key = keyedRef.getTModelKey();
      String name = keyedRef.getKeyName();
      String value = keyedRef.getKeyValue();
          
      if (name == null)
        name = "";
          
      if (value == null)
        value = "";
          
      // If the tModelKey involved is that of uddi-org:general_keywords, 
      // the keyNames are identical (DO NOT IGNORE keyName). Otherwise 
      // keyNames are not significant. Omitted keyNames are treated as 
      // identical to empty (zero length) keyNames.
      //
      if (key.equals(TModel.GENERAL_KEYWORDS_TMODEL_KEY)) 
      {
        sql.append("(C.TMODEL_KEY_REF = ? AND C.KEY_NAME = ? AND C.KEY_VALUE = ?)");
        sql.addValue(key);
        sql.addValue(name);
        sql.addValue(value); 
      }
      else 
      {
        sql.append("(C.TMODEL_KEY_REF = ? AND C.KEY_VALUE = ?)");
        sql.addValue(key);
        sql.addValue(value); 
      }

      sql.append(") ");
    }
  }

  /**
   * Utility method used to construct SQL "IN" statements such as
   * the following SQL example:
   *
   *   SELECT * FROM TABLE WHERE MONTH IN ('jan','feb','mar')
   *
   * @param sql StringBuffer to append the final results to
   * @param keysIn Vector of Strings used to construct the "IN" clause
   */
  private static void appendIn(DynamicQuery sql,Vector keysIn)
  {
    if (keysIn == null)
      return;

    sql.append("AND S.SERVICE_KEY IN (");

    int keyCount = keysIn.size();
    for (int i=0; i<keyCount; i++)
    {
      String key = (String)keysIn.elementAt(i);
      sql.append("?");
      sql.addValue(key);

      if ((i+1) < keyCount)
        sql.append(",");
    }

    sql.append(") ");
  }

  /**
   *
   */
  private static void appendOrderBy(DynamicQuery sql,FindQualifiers qualifiers)
  {
    sql.append("ORDER BY ");

    if (qualifiers == null)
      sql.append("S.LAST_UPDATE DESC");
    else if (qualifiers.sortByDateAsc)
      sql.append("S.LAST_UPDATE ASC");
    else
      sql.append("S.LAST_UPDATE DESC");
  }
}
