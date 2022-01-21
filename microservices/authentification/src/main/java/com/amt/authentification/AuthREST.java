package com.amt.authentification;

import com.amt.authentification.DTO.*;
import com.amt.authentification.Utils.PasswordUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class AuthREST {
    @POST
    @Path("/auth/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(AuthLoginCommand authLoginCommand) {
        ErrorsDTO errors = new ErrorsDTO();
        try {
            User user = User.fetchOneByName(authLoginCommand.getUsername());
            // invalid password or username -> FORBIDDEN
            if (user == null || !PasswordUtils.validatePassword(authLoginCommand.getPassword(), user.getPassword())){
                errors.add(new ErrorDTO(authLoginCommand.getUsername(), "connexion failed"));
                return Response.status(Response.Status.FORBIDDEN)
                        .entity(errors)
                        .build();
            }
            // login success
            return Response
                    .ok(new TokenDTO(JWT.generateToken(user), new AccountInfoDTO(user)))
                    .build();
        } catch (Exception e) {
            errors.add(new ErrorDTO( "unknown error", e.toString()));
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(errors)
                    .build();
        }
    }
    @POST
    @Path("/accounts/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(AccountRegisterCommand accountRegisterCommand){
        ErrorsDTO errors = new ErrorsDTO();
        try{
            // The password must match the accountRegisterCommand
            if (!PasswordUtils.isValidPassword(accountRegisterCommand.getPassword())){
                errors.add(new ErrorDTO(accountRegisterCommand.getPassword(), "Password Constraints:" +
                        " must have at least 8 characters," +
                        " must have at least 1 number," +
                        " must have at least 1  lowercase," +
                        " must have at least 1 uppercase" +
                        " must have at least 1 special characters (@#$%_-)"));
            }
            if (User.fetchOneByName(accountRegisterCommand.getUsername()) != null) {
                errors.add(new ErrorDTO(accountRegisterCommand.getUsername(), "this user already exit"));
            }
            // if any error, we return them
            if (!errors.getErrors().isEmpty()) {
                return Response.status(Response.Status.FORBIDDEN).entity(errors).build();
            }
            // input are ok there
            User user = new User(accountRegisterCommand.getUsername(),
                    PasswordUtils.createHash(accountRegisterCommand.getPassword()), "user");
            User.register(user);
            // the user already exist
            return Response.ok().build();
        }
        catch (Exception e){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(new ErrorDTO("unknown error", e.getMessage()))
                    .build();
        }
    }
}
