/*
 * Copyright © 2020 Copyright (c) 2020 Hanif Kukkalli and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package de.tuchemnitz.kn.servicechainorchestrator.impl;

import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.RpcProviderService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.ServicechainorchestratorService;
import org.opendaylight.yangtools.concepts.ObjectRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceChainOrchestratorProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceChainOrchestratorProvider.class);

    private final DataBroker dataBroker;
    private ObjectRegistration<ServicechainorchestratorService> servicechainorchestratorService;
    private RpcProviderService rpcProviderService;

    public ServiceChainOrchestratorProvider(final DataBroker dataBroker, final RpcProviderService rpcProviderService) {
        this.dataBroker = dataBroker;
        this.rpcProviderService = rpcProviderService;
    }

    /**
     * Method called when the blueprint container is created.
     */
    public void init() {
        LOG.info("ServiceChainOrchestratorProvider Session Initiated");
        servicechainorchestratorService = rpcProviderService.registerRpcImplementation(
                ServicechainorchestratorService.class, new ServiceChainOrchestratorImpl());
    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        LOG.info("ServiceChainOrchestratorProvider Closed");
        if (servicechainorchestratorService != null) {
            servicechainorchestratorService.close();
        }
    }
}



