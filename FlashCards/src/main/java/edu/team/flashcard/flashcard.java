package java.edu.team.flashcard;


public class flashcard {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getMessage() {
        // Return a simple message
        String output = "Welcome to flashcard application";
        return Response.status(200).entity(output).build();
    }
}
