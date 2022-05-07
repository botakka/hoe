package hu.oe.hoe.bardcontest;

import hu.oe.hoe.base.DataException;
import hu.oe.hoe.base.NotFoundException;
import hu.oe.hoe.model.BardContest;
import hu.oe.hoe.model.Contender;
import hu.oe.hoe.model.EpicSong;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(
    path = {"/bardcontest"},
    produces = MediaType.APPLICATION_JSON_VALUE)
public class BardContestResource {
  @Autowired private BardContestRepository repositoryBardContest;

  @Autowired private BardContestService bardContestService;

  @Operation(
      description = "Új dalnokverseny felvitele.",
      security = {
        @SecurityRequirement(
            name = "jwt-token",
            scopes = {"user"})
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
        @ApiResponse(responseCode = "403", description = "Hibás felvitel")
      })
  @RolesAllowed("user")
  @PostMapping(produces = "application/json")
  public @ResponseBody BardContest addBardContest(@RequestBody BardContest pBardContest) {
    repositoryBardContest.save(pBardContest);
    return repositoryBardContest.findById(pBardContest.getId()).orElseThrow();
  }

  @Operation(
      description = "Eddigi dalnok versenyek lista lekérdezése.",
      security = {
        @SecurityRequirement(
            name = "jwt-token",
            scopes = {"user"})
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
        @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
      })
  @RolesAllowed("user")
  @GetMapping(path = "/getallcontest", produces = "application/json")
  public @ResponseBody List<BardContest> getAllBardContests(
      @RequestParam(required = false) Boolean closed) {
    if (closed == null)
      return repositoryBardContest.findAll(Sort.by(BardContest.Fields.contestName));
    else return repositoryBardContest.findByStatus(closed);
  }

  @Operation(
      description = "Birodalmak dalnokainak meghívása.",
      security = {
        @SecurityRequirement(
            name = "jwt-token",
            scopes = {"user"})
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
        @ApiResponse(responseCode = "403", description = "Hibás lekérdezés"),
        @ApiResponse(
            responseCode = "409",
            description = "Nincs nyitott verseny, vagy egynél több van")
      })
  @RolesAllowed("user")
  @GetMapping(path = "/contest", produces = "application/json")
  public @ResponseBody List<Contender> requestForParticipation() {
    return bardContestService.createBardContest();
  }

    @Operation(
            description = "A dalnok verseny indítása.",
            security = {
                    @SecurityRequirement(
                            name = "jwt-token",
                            scopes = {"user"})
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
                    @ApiResponse(responseCode = "403", description = "Hibás lekérdezés"),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Nincs nyitott verseny, vagy egynél több van")
            })
    @RolesAllowed("user")
    @GetMapping(path = "/start", produces = "application/json")
    public @ResponseBody List<EpicSong> startContest() {
        return bardContestService.startContest();
    }

  @Operation(
      description = "Egy dalnok verseny lekérdezése.",
      security = {
        @SecurityRequirement(
            name = "jwt-token",
            scopes = {"user"})
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
        @ApiResponse(responseCode = "403", description = "Hibás lekérdezés")
      })
  @RolesAllowed("user")
  @GetMapping(path = "/byname/{name}", produces = "application/json")
  public @ResponseBody BardContest getBardContestByName(@PathVariable String name) {
    return repositoryBardContest.findByContestName(name);
  }

  @Operation(
      description = "Egy dalnokverseny törlése.",
      security = {
        @SecurityRequirement(
            name = "jwt-token",
            scopes = {"user"})
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "Sikeres művelet."),
        @ApiResponse(responseCode = "403", description = "Hibás lekérdezés"),
        @ApiResponse(responseCode = "404", description = "Nem található")
      })
  @RolesAllowed("user")
  @DeleteMapping(path = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean removeBardContestByiD(@PathVariable(name = "id") Long pId) {
    BardContest contest = repositoryBardContest.findById(pId).orElseThrow(NotFoundException::new);
    if (!contest.getClosed()) {
      throw new DataException();
    }
    repositoryBardContest.deleteById(pId);
    return true;
  }
}
