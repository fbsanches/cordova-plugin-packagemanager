---
Title: Information Installed Applications
description: Returns a custom list of applications installed on the system.
---
<!--
# license: Licensed to the Apache Software Foundation (ASF) under one
#         or more contributor license agreements.  See the NOTICE file
#         distributed with this work for additional information
#         regarding copyright ownership.  The ASF licenses this file
#         to you under the Apache License, Version 2.0 (the
#         "License"); you may not use this file except in compliance
#         with the License.  You may obtain a copy of the License at
#
#           http://www.apache.org/licenses/LICENSE-2.0
#
#         Unless required by applicable law or agreed to in writing,
#         software distributed under the License is distributed on an
#         "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#         KIND, either express or implied.  See the License for the
#         specific language governing permissions and limitations
#         under the License.
-->


# cordova-plugin-packagemanager

	A simple plugin that will return a list of installed applications or all on your smartphone.  
    Return uid, dataDir and packageName.

## Installation

    cordova plugin add cordova-plugin-packagemanager

### Example

    //Success Callback Receive
    function successCallback(e) {
        document.getElementById('divApps').innerHTML += JSON.stringify(e);
    }

    //Error Callback Receive
    function errorCallback(e) {
        alert('Error');
    }

    window.plugins.packagemanager.show(true, successCallback, errorCallback);

#### Parameters

    1. InstalledApps (Boolean)
        True: Displays installed applications
        False: Shows all applications

    2. successCallback (Function)
        Success Callback Receive
        * Return: JSONArray
        * Type Return Example:  
        ["10001;/data/data/com.sec.android.gallery3d;com.sec.android.gallery3d"] 
        Uid;DataDir;PackageName

    3. errorCallback (Function)
        Error Callback Receive

##### Supported Platforms

- Android
