This project has a sample Vagrant setup as described in https://open.mesosphere.com/advanced-course
It also has a sample scheduler written in Java, to help understand how schedulers like Marathon interact with Mesos
For running the sample scheduler do following.

* Setup Vagrant box as described in https://open.mesosphere.com/advanced-course
* Run ./gradlew clean build shadowJar  This will build the scheduler project.
* vagrant ssh node1
* Assuming you are already running mesos master and slave as described in step 1
* Run the sample scheduler as java -jar build/libs/mesos-scheduler-1.0-all.jar 192.168.33.10:5050
* Monitor mesos logs 
* tail -f /var/log/mesos/mesos-slave.INFO
* tail -f /var/log/mesos/mesos-master.INFO
Notice how required jar as described in the scheduler are downloaded to locations like
/var/lib/mesos/slaves/1588da5d-357e-4ecc-83c1-b952b849571a-S7/frameworks/1588da5d-357e-4ecc-83c1-b952b849571a-0008/executors/CmdExecutor/runs/2aa0bca5-3865-4b23-9a8c-10813941db85/
and executor is run in a separate container.
