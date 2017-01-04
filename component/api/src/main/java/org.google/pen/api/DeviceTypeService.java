/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.google.pen.api;

import io.swagger.annotations.*;
import org.google.pen.api.dto.DeviceJSON;
import org.wso2.carbon.apimgt.annotations.api.*;


import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * This is the API which is used to control and manage device type functionality
 */
@SwaggerDefinition(
        info = @Info(
                version = "1.0.0",
                title = "",
                extensions = {
                        @Extension(properties = {
                                @ExtensionProperty(name = "name", value = "ipen"),
                                @ExtensionProperty(name = "context", value = "/ipen"),
                        })
                }
        ),
        tags = {
                @Tag(name = "ipen", description = "")
        }
)
public interface DeviceTypeService {

    /**
     * @param agentInfo device owner,id
     * @return true if device instance is added to map
     */
    @Path("device/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @org.wso2.carbon.apimgt.annotations.api.Scope(key = "device:firealarm:enroll", name = "", description = "")
    Response registerDevice(final DeviceJSON agentInfo);

    /**
     * @param deviceId  unique identifier for given device type instance
     * @param state     change status of sensor: on/off
     */
    @Path("device/{deviceId}/change-status")
    @POST
    @org.wso2.carbon.apimgt.annotations.api.Scope(key = "device:firealarm:enroll", name = "", description = "")
    Response changeStatus(@PathParam("deviceId") String deviceId,
                          @QueryParam("state") String state,
                          @Context HttpServletResponse response);

    /**
     * Retrieve Sensor data for the given time period
     * @param deviceId unique identifier for given device type instance
     * @param from  starting time
     * @param to    ending time
     * @return  response with List<SensorRecord> object which includes sensor data which is requested
     */
    @Path("device/stats/{deviceId}")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @org.wso2.carbon.apimgt.annotations.api.Scope(key = "device:firealarm:enroll", name = "", description = "")
    Response getSensorStats(@PathParam("deviceId") String deviceId, @QueryParam("from") long from,
                            @QueryParam("to") long to, @QueryParam("sensorType") String sensorType);

    /**
     * Remove device type instance using device id
     * @param deviceId  unique identifier for given device type instance
     */
    @Path("/device/{device_id}")
    @DELETE
    @org.wso2.carbon.apimgt.annotations.api.Scope(key = "device:firealarm:enroll", name = "", description = "")
    Response removeDevice(@PathParam("device_id") String deviceId);

    /**
     * Update device instance name
     * @param deviceId  unique identifier for given device type instance
     * @param name      new name for the device type instance
     */
    @Path("/device/{device_id}")
    @PUT
    @org.wso2.carbon.apimgt.annotations.api.Scope(key = "device:firealarm:enroll", name = "", description = "")
    Response updateDevice(@PathParam("device_id") String deviceId, @QueryParam("name") String name);

    /**
     * To get device information
     * @param deviceId  unique identifier for given device type instance
     * @return
     */
    @Path("/device/{device_id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @org.wso2.carbon.apimgt.annotations.api.Scope(key = "device:firealarm:enroll", name = "", description = "")
    Response getDevice(@PathParam("device_id") String deviceId);

    /**
     * Get all device type instance which belongs to user
     * @return  Array of devices which includes device's information
     */
    @Path("/devices")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @org.wso2.carbon.apimgt.annotations.api.Scope(key = "device:firealarm:enroll", name = "", description = "")
    Response getAllDevices();

    /**
     * To download device type agent source code as zip file
     * @param deviceName   name for the device type instance
     * @param sketchType   folder name where device type agent was installed into server
     * @return  Agent source code as zip file
     */
    @Path("/device/download")
    @GET
    @Produces("application/zip")
    @org.wso2.carbon.apimgt.annotations.api.Scope(key = "device:firealarm:enroll", name = "", description = "")
    Response downloadSketch(@QueryParam("deviceName") String deviceName, @QueryParam("sketchType") String sketchType);
}