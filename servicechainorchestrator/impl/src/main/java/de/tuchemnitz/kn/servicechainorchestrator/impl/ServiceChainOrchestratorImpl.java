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
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.CreateVMInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.CreateVMOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.servicechainorchestrator.rev210411.CreateVMOutputBuilder;
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
    public ListenableFuture<RpcResult<CreateVMOutput>> createVM(CreateVMInput input) {
        CreateVMOutputBuilder virtualBuilder = new CreateVMOutputBuilder();
        virtualBuilder.setVmAttributes("Initializing with values" + input.getCvm());
        LOG.info("IMPL triggered for service chain");
        return RpcResultBuilder.success(virtualBuilder.build()).buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<CreateServiceChainOutput>> createServiceChain(CreateServiceChainInput input) {
        CreateServiceChainOutputBuilder createBuilder = new CreateServiceChainOutputBuilder();
        createBuilder.setGreeting("VMs and virtual links template" + input.getVms());
        PasswordCredentials passwordCredentials = new PasswordCredentials();
        passwordCredentials.setUsername("admin");
        passwordCredentials.setPassword("tuckn2020");
        Authentication authentication = new Authentication();
        authentication.setPasswordCredentials(passwordCredentials);
        KeystoneClient keystone = new KeystoneClient("http://10.10.0.21:5000/v3/");
        //access with unscoped token
        Access access = keystone.execute(new Authenticate(authentication));

        //use the token in the following requests
        keystone.setToken(access.getToken().getId());

        Tenants tenants = keystone.execute(new ListTenants());
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