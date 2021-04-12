# Service Chain Orchestrator
This project is to develop an ODL feature which can perform network service chaining and network slicing.

## How to Guide for Ubuntu:

### Prerequisites

- Maven 3.5.2 or later
- JDK 11
- An appropriate Maven settings.xml file.


### Install Maven
```
sudo apt install maven
```

#### A simple way to get the default OpenDaylight settings.xml file is:
```
cp -n ~/.m2/settings.xml{,.orig} ; wget -q -O - https://raw.githubusercontent.com/opendaylight/odlparent/master/settings.xml > ~/.m2/settings.xml
```
If the above command throws error, try it again.


### Install ``git``

```
sudo apt install git
```

### Installation of Open-JDK
Update apt repository

```
sudo apt update
```

Install Open-JDK-11

```
sudo apt install openjdk-11-jdk openjdk-11-jre
```

### Building an example module:

- Create an Example project using Maven and an archetype called the opendaylight-startup-archetype. If you are downloading this project for the first time, then it will take sometime to pull all the code from the remote repository.

```
mvn archetype:generate -DarchetypeGroupId=org.opendaylight.archetypes -DarchetypeArtifactId=opendaylight-startup-archetype -DarchetypeCatalog=remote -DarchetypeVersion=1.4.0-SNAPSHOT
```

Archetype versions:

| OpenDaylight Simultaneous Release | opendaylight-startup-archetype version |
| --------------------------------- | -------------------------------------- |
| Aluminium SR1 Development             | 1.0.0-SNAPSHOT                         |


OpenDaylight Simultaneous Release | opendaylight-startup-archetype version
--- | ---
*Aluminium Development* | 1.0.0-SNAPSHOT

- Update the properties values as follows. Ensure that the values for the groupId and the artifactId are in lower case.

```
Define value for property 'groupId': : de.tuchemnitz.kn
Define value for property 'artifactId': : servicechainorchestrator
Define value for property 'version':  1.0-SNAPSHOT: : 1.0.0-SNAPSHOT
Define value for property 'package':  org.opendaylight.example: : de.tuchemnitz.kn.servicechainorchestrator
Define value for property 'classPrefix':  ServiceChainOrchestrator
Define value for property 'copyright': : Copyright (c) 2020 Hanif Kukkalli
```


### Perform ``mvn clean install``
```
mvn clean install
```

## Developer environment


* build artifacts

```
mvn clean install
```

* build installer

```
mvn clean install -f installer
```

* build distribution

```
mvn clean install -f dist
```

* run distribution

```
$ docker run -d -p 8181:8181 -p 6633:6633 -p 6653:6653 --rm --name sco odl-sco
$ docker exec -ti sco bash
```
