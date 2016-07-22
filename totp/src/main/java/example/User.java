package example;


import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
public class User {
    @POST
    @Path("login")
    public Response login(@FormParam("key") String key, @FormParam("verificationCode") String verificationCode) {
        if (!new Credentials().authorize(key, verificationCode)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.seeOther(URI.create("/profile")).build();
    }

    @POST
    @Path("key")
    @Produces(MediaType.APPLICATION_JSON)
    public VerificationKey newKey() {
        VerificationKey key = new Credentials().newSecretKey();
        return key;
    }

    @GET
    @Path("profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile profile() {
        Profile result = new Profile();
        return result;
    }
}