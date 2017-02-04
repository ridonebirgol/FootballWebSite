/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.football.site.webservice;

import com.football.operations.UserOperations;
import com.football.site.db.DbHelper;
import com.football.site.entity.UserInfo;
import com.google.gson.Gson;
import com.matchscore.entity.Constants;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author RIDVAN
 */
@Path("userProcess")
public class UserProcessService {

    public UserProcessService() {
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("GetUserInfo")   
    public Response GetUserInfo(@HeaderParam("UserToken") String userToken, String content) {
        if (DbHelper.IsTokenExists(userToken)) {
            String resultMessage = UserOperations.GetUserInfo(content);
            if (resultMessage.startsWith(Constants.UNSUCCESS)) {
                return Response.status(Response.Status.CONFLICT).entity(resultMessage).build();
            } else {
                return Response.status(Response.Status.OK).entity(resultMessage).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("NOK:TokenNotAuthenticated").build();
        }
    }
    /**
     * POST method for creating an instance of UserInfos
     *
     * @param userToken
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("SaveUserInfo")
    public Response SaveUserInfo(@HeaderParam("UserToken") String userToken, String content) {
        if (DbHelper.IsTokenExists(userToken)) {
            String resultMessage = UserOperations.CheckOrSaveUser(content);
            if (resultMessage.startsWith(Constants.UNSUCCESS)) {
                return Response.status(Response.Status.CONFLICT).entity(resultMessage).build();
            } else {
                return Response.status(Response.Status.OK).entity(resultMessage).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("TokenNotAuthenticated").build();
        }
    }
}
