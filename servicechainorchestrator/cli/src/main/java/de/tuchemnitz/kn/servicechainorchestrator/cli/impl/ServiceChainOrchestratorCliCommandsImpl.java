/*
 * Copyright © 2020 Copyright (c) 2020 Hanif Kukkalli and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package de.tuchemnitz.kn.servicechainorchestrator.cli.impl;

import de.tuchemnitz.kn.servicechainorchestrator.cli.api.ServiceChainOrchestratorCliCommands;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceChainOrchestratorCliCommandsImpl implements ServiceChainOrchestratorCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceChainOrchestratorCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public ServiceChainOrchestratorCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("ServiceChainOrchestratorCliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }
}
