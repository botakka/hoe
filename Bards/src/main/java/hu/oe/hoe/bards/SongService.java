package hu.oe.hoe.bards;

import hu.oe.hoe.model.Bard;
import hu.oe.hoe.model.EpicSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class SongService {
  private static final String epicSongTemplate =
      "A hős {0} és a gonosz {1} összecsapott és győzött {2}.";

  private static final String epicSongName = "{0} versus {1}";
  @Autowired SongRepository repositorySong;

  public EpicSong createSong(Bard bard, EpicSong song) {
    song.setBard(bard);
    String theSong =
        MessageFormat.format(
            epicSongTemplate, song.getProtectorName(), song.getAttackerName(), song.getWinner());
    song.setText(theSong);
    song.setSongName(
        MessageFormat.format(epicSongName, song.getProtectorName(), song.getAttackerName()));
    repositorySong.save(song);
    return song;
  }
}
