/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.m2m.classaction.executor;

import com.okmich.m2m.classaction.executor.db.CacheService;
import com.okmich.m2m.classaction.executor.db.CacheServiceImpl;
import com.okmich.m2m.classaction.executor.db.CommandAuditHBaseRepoImpl;
import com.okmich.m2m.classaction.executor.db.CommandAuditRepo;
import com.okmich.m2m.classaction.executor.kakfa.KafkaMessageConsumer;
import com.okmich.m2m.classaction.executor.kakfa.KafkaMessageProducer;
import com.okmich.m2m.classaction.executor.kakfa.KafkaMessageProducerImpl;
import com.okmich.m2m.classaction.executor.mqtt.CommandPublisher;
import com.okmich.m2m.classaction.executor.mqtt.CommandPublisherImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application entry Class
 *
 * @author m.enudi
 */
public final class Main {

    private final KafkaMessageConsumer kafkaMessageConsumer;
    private final KafkaMessageProducer kafkaMessageProducer;
    /**
     * cacheService
     */
    private final CacheService cacheService;
    /**
     * commandPublisher
     */
    private final CommandPublisher commandPublisher;
    /**
     * commandAuditRepo
     */
    private final CommandAuditRepo commandAuditRepo;

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private Main() throws Exception {
        this.kafkaMessageProducer = new KafkaMessageProducerImpl();
        this.commandAuditRepo = new CommandAuditHBaseRepoImpl();
        this.commandPublisher = new CommandPublisherImpl();
        this.cacheService = new CacheServiceImpl();

        this.kafkaMessageConsumer = new KafkaMessageConsumer(
                cacheService,
                commandPublisher,
                kafkaMessageProducer,
                commandAuditRepo);
    }

    public static void main(String[] args) {
        //initilize the OptionRegistry
        LOG.info("Initializing the configuration registry");
        OptionRegistry.initialize(args);

        try {
            //initialize CommandRegistry
            LOG.info("Initializing the command registry registry");
            CommandRegistry.initialize();
            LOG.info("Starting the main process");
            new Main().start();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            System.exit(-1);
        }

    }

    private void start() {
        LOG.info("Starting the kafka consumer");
        this.kafkaMessageConsumer.start();
    }
}
