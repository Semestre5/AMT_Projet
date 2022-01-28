package com.amt.authentification;

import com.amt.authentification.DTO.*;
import com.amt.authentification.Utils.PasswordUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
                errors.add(new ErrorDTO("password", "Password Constraints:" +
                        " must have at least 8 characters," +
                        " must have at least 1 number," +
                        " must have at least 1  lowercase," +
                        " must have at least 1 uppercase" +
                        " must have at least 1 special characters (@#$%_-)"));
                return Response.status(422).entity(errors).build();
            }
            if (User.fetchOneByName(accountRegisterCommand.getUsername()) != null) {
                errors.add(new ErrorDTO(accountRegisterCommand.getUsername(), "this user already exit"));
                return Response.status(Response.Status.CONFLICT).entity(errors).build();
            }

            // input are ok there
            User user = new User(accountRegisterCommand.getUsername(),
                    PasswordUtils.createHash(accountRegisterCommand.getPassword()), "user");
            User.register(user);
            // the user already exist
            return Response.status(Response.Status.CREATED).entity("{ }").build();
        }
        catch (Exception e){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(new ErrorDTO("unknown error", e.getMessage()))
                    .build();
        }
    }
}
