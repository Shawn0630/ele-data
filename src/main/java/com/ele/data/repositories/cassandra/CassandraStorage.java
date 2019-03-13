package com.ele.data.repositories.cassandra;

import akka.actor.ActorSystem;
import com.datastax.driver.core.ConsistencyLevel;
import com.ele.data.repositories.ShopRepository;
import com.ele.data.repositories.SystemStorage;
import com.github.racc.tscg.TypesafeConfig;
import com.hhandoko.cassandra.migration.CassandraMigration;
import com.hhandoko.cassandra.migration.api.configuration.KeyspaceConfiguration;

import javax.inject.Inject;

public class CassandraStorage implements SystemStorage {

    final String contactPoints;
    final int port;
    final String keyspace;
    final String username;
    final String password;
    final String migrationLocation;
    final int parallelism;


    @Inject
    public CassandraStorage(
            final ActorSystem system,
            @TypesafeConfig("ele.data.cassandra.contact-points") String contactPoints,
            @TypesafeConfig("ele.data.cassandra.port") int port,
            @TypesafeConfig("ele.data.cassandra.keyspace") String keyspace,
            @TypesafeConfig("ele.data.cassandra.username") String username,
            @TypesafeConfig("ele.data.cassandra.password") String password,
            @TypesafeConfig("ele.data.cassandra.migration-location") String migrationLocation,
            @TypesafeConfig("ele.data.cassandra.parallelism") int parallelism,
            @TypesafeConfig("ele.data.cassandra.migration") boolean migration
            ) {
        this.contactPoints = contactPoints;
        this.port = port;
        this.keyspace = keyspace;
        this.username = username;
        this.password = password;
        this.migrationLocation = migrationLocation;
        this.parallelism = parallelism;

        if(migration) {
            migrate();
        }
    }
    @Override
    public ShopRepository getShopRepository() {
        return null;
    }

    private void migrate() {
        String[] locations = { migrationLocation };
        String[] contactPointsList = contactPoints.split(",");

        KeyspaceConfiguration config = new KeyspaceConfiguration();
        config.setName(keyspace);
        config.setConsistency(ConsistencyLevel.QUORUM);
        config.getClusterConfig().setContactpoints(contactPointsList);
        config.getClusterConfig().setPort(port);
        config.getClusterConfig().setUsername(username);
        config.getClusterConfig().setPassword(password);
        CassandraMigration cm = new CassandraMigration();
        cm.setLocations(locations);
        cm.setKeyspaceConfig(config);
        cm.migrate();
    }
}
