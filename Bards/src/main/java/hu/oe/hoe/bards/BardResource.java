package hu.oe.hoe.bards;

import hu.oe.hoe.model.Bard;
import hu.oe.hoe.model.EpicSong;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

@RestController
@CrossOrigin("*")
@RequestMapping(
    path = {"/bard"},
    produces = MediaType.APPLICATION_JSON_VALUE)
public class BardResource {
  @Autowired private BardRepository repositoryBard;
  @Autowired private SongRepository repositorySong;
  @Autowired private SongService songService;

  @Operation(
      description = "Új dalnok felvitele.",
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
  public @ResponseBody Bard addBard(@RequestBody Bard pData) {
    repositoryBard.save(pData);
    return repositoryBard.findById(pData.getId()).orElseThrow();
  }

  @Operation(
      description = "Dalnokok lista lekérdezése.",
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
  @GetMapping(path = "/getallbards", produces = "application/json")
  public @ResponseBody Collection<Bard> getAllBards() {
    return repositoryBard.findAll(Sort.by(Bard.Fields.name));
  }

  @Operation(
      description = "Egy Birodalom dalnokainak lekérdezése.",
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
  @GetMapping(path = "/getBardsFromEmpire/{empireId}", produces = "application/json")
  public @ResponseBody Collection<Bard> getBardsFromEmpire(@PathVariable Long empireId) {
    return repositoryBard.findByEmpireIdOrderByNameAsc(empireId);
  }

  @Operation(
      description = "Egy dalnok lekérdezése.",
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
  public @ResponseBody Bard getBardByName(@PathVariable String name) {
    return repositoryBard.findByName(name);
  }

  @Operation(
      description = "Egy dalnok törlése.",
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
  @DeleteMapping(path = "/remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public boolean removeBardByiD(@PathVariable(name = "id") Long pId) {
    repositoryBard.deleteById(pId);
    return true;
  }

    @Operation(
            description = "Egy hősi dal megrendelése.",
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
    @PostMapping(path = "/song/{bardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EpicSong createSong(@PathVariable Long bardId, @RequestBody EpicSong pSong) {
        Bard bard = repositoryBard.findById(bardId).orElseThrow();
        return songService.createSong(bard,pSong);
    }

}
