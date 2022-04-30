package hu.nye.albums.populator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Profile("!albums")
@Slf4j
public class DBPopulatorService {

    private final List<DBPopulator> dbPopulators;

    public DBPopulatorService(final List<DBPopulator> dbPopulators) {
        this.dbPopulators = dbPopulators;
    }

    @PostConstruct
    public void populateDatabase() {
        log.info("Populates data base...");
        dbPopulators.forEach(DBPopulator::populateDatabase);
        log.info("Database populate process is finished.");
    }
}

