import repository.entity.Dungeon;
import repository.entity.Game;
import repository.entity.PlayerCharacter;
import services.GameService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.validation.Validator;
import java.util.List;

@Path("/game")
public class GameResource {

    public class Error {
        public String code;
        public String description;

        public Error(String code, String description) {
            this.code = code;
            this.description = description;
        }
    }

    @Inject
    GameService gameService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String welomeScreen() {
        return "Welcome to QoQ";
    }

    @GET
    @Path("/start")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startGame()
    {
        Game game = gameService.startGame();
        if(game !=null) return Response.ok(game).build();
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findGame(@PathParam("id") Integer id)
    {
        Game game = gameService.findGame(id);
        if(game != null) return Response.ok(game).build();
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @GET
    @Path("/{id}/move")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMoves(@PathParam("id") Integer id)
    {
        Game game = gameService.getMoves(id);
        if(game != null) return Response.ok(game).build();
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

    @GET
    @Path("{id}/move/fight")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fightMonsters(@PathParam("id") Integer id)
    {
        Game game = gameService.fightMonsters(id);
        if(game != null) return Response.ok(game).build();
        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }
}