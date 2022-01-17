package com.amt.authentification;

import com.amt.authentification.DTO.AuthLoginCommand;
import com.amt.authentification.Utils.PasswordUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthREST {
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(/*AuthLoginCommand authLoginCommand*/) {
        return Response.ok("{ \"ok\" : \"ok\" }").build();

        /*try
            User user = User.fetchOneByName(authLoginCommand.getUsername());
            if (user == null || !PasswordUtils.validatePassword(authLoginCommand.getPassword(), user.getPassword())){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            return Response.ok(JWT.generateToken(user)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }*/
    }

    @POST
    @Path("accounts/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(AuthLoginCommand authLoginCommand){
        try{
            User user = new User(authLoginCommand.getUsername(), authLoginCommand.getPassword(), "user");
            // The password must match the regex
            if (!PasswordUtils.isValidPassword( authLoginCommand.getPassword())){
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            Integer userId = User.register(user);
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
