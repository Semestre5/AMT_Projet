package com.amt.authentification;

import com.amt.authentification.Utils.PasswordUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("auth")
public class AuthREST {

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        try{
            User user = User.fetchOneByName(username);
            if (user == null || !PasswordUtils.validatePassword(password, user.getPassword())){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            return Response.ok(JWT.generateToken(user)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("username") String username, @FormParam("password") String password){
        try{
            User user = new User(username, password, "user");
            Integer userId = User.register(user);
            // The password must match the regex
            if (!PasswordUtils.isValidPassword(password)){
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            // the user already exist
            if (userId != null){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            return Response.status(Response.Status.CREATED).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }
}
