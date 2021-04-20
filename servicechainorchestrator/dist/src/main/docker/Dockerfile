FROM ${base.sdnc.image.repo} AS stage0
FROM ${base.image.repo}

ENV ODL_HOME /opt/opendaylight
ENV JAVA_OPTS="-Xms512m -Xmx3g"
ENV ODL_ADMIN_PASSWORD "admin"
ENV SCO_REPO mvn:de.tuchemnitz.kn/features-servicechainorchestrator/${project.version}/xml/features
ENV FEATURES_BOOT_INITIAL odl-mdsal-apidocs,features-openflowplugin,features-servicechainorchestrator
USER root

# Install sudo and IP utilities
RUN apk update && apk --no-cache add sudo iputils openssl bash unzip rsync nss
# Enable wheel group
RUN sed -e 's/# %wheel ALL=(ALL) NOPASSWD: ALL/%wheel ALL=(ALL) NOPASSWD: ALL/g' -i /etc/sudoers
# Create odl user
RUN addgroup -S odl && adduser -S odl -G odl
RUN addgroup odl wheel

# copy ODL
COPY --chown=odl:odl odl/karaf-${odl.karaf.version} $ODL_HOME
RUN ln -s /opt/opendaylight /opt/opendaylight/current

# copy apps dependencies
COPY --chown=odl:odl system /tmp/system
RUN rsync -a --ignore-existing /tmp/system $ODL_HOME

# Copy scripts
COPY --from=stage0 --chown=odl:odl /opt/onap /opt/onap

# copy bash init script into container
COPY --chown=odl:odl opt/onap/sdnc/bin/* /opt/onap/sdnc/bin/

RUN chmod 775 /opt/onap/sdnc/bin/*.sh

#Adjust repositories
RUN sed -i -e "\|featuresRepositories|s|$|, ${SCO_REPO}|"  $ODL_HOME/etc/org.apache.karaf.features.cfg
#Adjust featuresboot
RUN sed -i -e "\|featuresBoot *=|s|$|,${FEATURES_BOOT_INITIAL}|"  $ODL_HOME/etc/org.apache.karaf.features.cfg

#RUN chown -R odl:odl /opt

USER odl
WORKDIR /opt/opendaylight

CMD [ "/bin/bash" ]
ENTRYPOINT [ "/opt/onap/sdnc/bin/startODL.sh" ]
EXPOSE 8181 1090 1099 6633 6653 6640
