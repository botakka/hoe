package hu.oe.hoe.bardcontest;

import hu.oe.hoe.base.DataConflictException;
import hu.oe.hoe.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BardContestService {
  private static final Logger logger = LogManager.getLogger(BardContestService.class);

  @Value("${service.bards}")
  private String serviceBardUrl;

  @Value("${service.empire}")
  private String serviceEmpireUrl;

  @Autowired BardContestRepository bardContestRepository;
  @Autowired ContenderRepository contenderRepository;

  @Transactional
  public List<Contender> createBardContest() throws DataConflictException {
    BardContest openContest = findOpenContest();
    checkInvitation(openContest.getId(), true);
    List<Bard> bards = new ArrayList<>();
    String uriEmp = serviceEmpireUrl + "/empirehandler/getallempires";
    RestTemplate restTemplate = new RestTemplate();
    Empire[] tmp = restTemplate.getForObject(uriEmp, Empire[].class);
    List<Empire> empires = Arrays.asList(tmp);
    for (Empire emp : empires) {
      if (emp.getBard() != null) {
        uriEmp = serviceEmpireUrl + "/empirehandler/invitebard/" + emp.getId();
        Bard bard = restTemplate.getForObject(uriEmp, Bard.class);
        bards.add(bard);
      }
    }
    List<Contender> contenders = new ArrayList<>();
    for (Bard bard : bards) {
      if (!bard.getEpicSongs().isEmpty()) {
        Contender contender =
            Contender.builder().bardId(bard.getId()).bardContest(openContest).build();
        contender.setBardContest(openContest);
        contenderRepository.save(contender);
        contenders.add(contender);
      }
    }
    bardContestRepository.save(openContest);
    return contenders;
  }

  public List<EpicSong> startContest() throws DataConflictException {
    BardContest openContest = findOpenContest();
    checkInvitation(openContest.getId(), false);
    List<Contender> contenders = openContest.getContenders();
    List<Long> bardIds = contenders.stream().map(Contender::getBardId).collect(Collectors.toList());
    String uri = serviceBardUrl + "/bard/contest/" + new ArrayList<>(bardIds);
    RestTemplate restTemplate = new RestTemplate();
    EpicSong[] songs = restTemplate.getForObject(uri, EpicSong[].class);
    List<EpicSong> epicSongs = Arrays.asList(songs);
    for (EpicSong song : epicSongs) {
      logger.info(song.getText());
    }
    openContest.setClosed(true);
    bardContestRepository.save(openContest);
    return Arrays.asList(songs);
  }

  private BardContest findOpenContest() throws DataConflictException {
    List<BardContest> openContest = bardContestRepository.findByStatus(false);
    if (openContest.size() != 1) {
      throw new DataConflictException();
    }
    return openContest.get(0);
  }

  private void checkInvitation(Long bardContestId, boolean shallBeEmpty) {
    List<Contender> invitedContenders = contenderRepository.findByBardContestId(bardContestId);
    if (shallBeEmpty && !invitedContenders.isEmpty()) {
      throw new DataConflictException();
    }
    if (!shallBeEmpty && invitedContenders.isEmpty()) {
      throw new DataConflictException();
    }
  }
}
