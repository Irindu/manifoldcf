/* $Id$ */

/**
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements. See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.apache.manifoldcf.core.interfaces;

/** An IFetchThrottler object is meant to be used as part of a fetch cycle.  It is not
* thread-local, and does not require access to a thread context.  It thus also does not
* throw ManifoldCFExceptions.  It is thus suitable for use in background threads, etc.
* These objects are typically created by IConnectionThrottler objects - they are not meant
* to be created directly.
*/
public interface IFetchThrottler
{
  public static final String _rcsid = "@(#)$Id$";

  /** Get the throttle group for this fetch throttler.
  *@return the throttle group.
  */
  public String getThrottleGroup();
  
  /** Get the throttle specification for this fetch throttler.
  *@return the throttle specification.
  */
  public IThrottleSpec getThrottleSpecification();
  
  /** Get the bin names for this fetch throttler.
  *@return the bin names.
  */
  public String[] getBinNames();

  /** Get permission to fetch a document.  This grants permission to start
  * fetching a single document, within the connection that has already been
  * granted permission that created this object.  When done (or aborting), call
  * releaseFetchDocumentPermission() to note the completion of the document
  * fetch activity.
  *@param currentTime is the current time, in ms. since epoch.
  *@return the stream throttler to use to throttle the actual data access.
  */
  public IStreamThrottler obtainFetchDocumentPermission(long currentTime)
    throws InterruptedException;
  
  /** Release permission to fetch a document.  Call this only when you
  * called obtainFetchDocumentPermission() successfully earlier.
  *@param currentTime is the current time, in ms. since epoch.
  */
  public void releaseFetchDocumentPermission(long currentTime);
  
}
