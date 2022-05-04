package hu.nye.albums.populator;

import java.util.List;
import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;



/**
 * Database populator.
 */
@Service
@Profile("!albums")
@Slf4j
public class DBPopulatorService {

    private final List<DBPopulator> dbPopulators;

    public DBPopulatorService(final List<DBPopulator> dbPopulators) {
        this.dbPopulators = dbPopulators;
    }

    /**
     * Populates database.
     */
    @PostConstruct
    public void populateDatabase() {
        log.info("Populates data base...");
        dbPopulators.forEach(DBPopulator::populateDatabase);
        log.info("Database populate process is finished.");
    }
}

