/*
 * Copyright © 2020 Copyright (c) 2020 Hanif Kukkalli and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package de.tuchemnitz.kn.servicechainorchestrator.impl;

import com.google.common.util.concurrent.ListenableFuture;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.CreateServiceChainInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.CreateServiceChainOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.CreateServiceChainOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.HelloWorldInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.HelloWorldOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.HelloWorldOutputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.ServicechainorchestratorService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceChainOrchestratorImpl implements ServicechainorchestratorService {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceChainOrchestratorImpl.class);

    @Override
    public ListenableFuture<RpcResult<CreateServiceChainOutput>> createServiceChain(CreateServiceChainInput input) {
        CreateServiceChainOutputBuilder createBuilder = new CreateServiceChainOutputBuilder();
        createBuilder.setGreeting("VMs and virtual links template" + input.getName());
        LOG.info("IMPL triggered for service chain");
        return RpcResultBuilder.success(createBuilder.build()).buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<HelloWorldOutput>> helloWorld(HelloWorldInput input) {
        HelloWorldOutputBuilder helloBuilder = new HelloWorldOutputBuilder();
        helloBuilder.setGreeting("Hello " + input.getName());
        LOG.info("IMPL triggered ");
        return RpcResultBuilder.success(helloBuilder.build()).buildFuture();
    }
}