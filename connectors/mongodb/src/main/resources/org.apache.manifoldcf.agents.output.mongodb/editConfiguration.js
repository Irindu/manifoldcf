<!--
 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->

<script type="text/javascript">
<!--
function checkConfig() {
  if (editconnection.serverlocation) {
    if (editconnection.serverlocation.value == "") {
      alert("$Encoder.bodyJavascriptEscape($ResourceBundle.getString('MongodbConnector.PleaseSupplyValidElasticSearchLocation'))");
      editconnection.serverlocation.focus();
      return false;
    }
  }
  if (editconnection.PortNumber) {
    if (editconnection.PortNumber.value == "") {
      alert("$Encoder.bodyJavascriptEscape($ResourceBundle.getString('MongodbConnector.PleaseSupplyValidPortNumber'))");
      editconnection.indexname.focus();
      return false;
    }
  }
  if (editconnection.UserName) {
    if (editconnection.indextype.value == "") {
      alert("$Encoder.bodyJavascriptEscape($ResourceBundle.getString('MongodbConnector.PleaseSupplyValidUserName'))");
      editconnection.indextype.focus();
      return false;
    }
  }
  return true;
}

function checkConfigForSave() {
  if (editconnection.serverlocation) {
    if (editconnection.serverlocation.value == "") {
      alert("$Encoder.bodyJavascriptEscape($ResourceBundle.getString('MongodbConnector.PleaseSupplyValidElasticSearchLocation'))");
      SelectTab("$Encoder.javascriptBodyEscape($ResourceBundle.getString('MongodbConnector.Parameters'))");
      editconnection.serverlocation.focus();
      return false;
    }
  }
  if (editconnection.PortNumber) {
    if (editconnection.PortNumber.value == "") {
      alert("$Encoder.bodyJavascriptEscape($ResourceBundle.getString('MongodbConnector.PleaseSupplyValidPortNumber'))");
      SelectTab("$Encoder.javascriptBodyEscape($ResourceBundle.getString('MongodbConnector.Parameters'))");
      editconnection.indexname.focus();
      return false;
    }
  }
  
  return true;
}
//-->
</script>
