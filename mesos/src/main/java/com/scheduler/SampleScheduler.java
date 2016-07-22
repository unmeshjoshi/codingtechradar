package com.scheduler;

import com.google.protobuf.ByteString;
import org.apache.mesos.Protos;
import org.apache.mesos.Scheduler;
import org.apache.mesos.SchedulerDriver;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SampleScheduler implements Scheduler {

    private Protos.ExecutorInfo executorInfo;

    public SampleScheduler(Protos.ExecutorInfo executorInfo) {
        this.executorInfo = executorInfo;
    }

    @Override
    public void registered(SchedulerDriver driver, Protos.FrameworkID frameworkId, Protos.MasterInfo masterInfo) {
        System.out.println("Registered! ID = " + frameworkId.getValue());
    }

    @Override
    public void reregistered(SchedulerDriver driver, Protos.MasterInfo masterInfo) {
        System.out.println("Registered!  " + masterInfo);
    }

    @Override
    public void resourceOffers(SchedulerDriver driver, List<Protos.Offer> offers) {
        for (Protos.Offer offer : offers) {
            Protos.TaskID taskId = newTaskId();
            Protos.TaskInfo task = Protos.TaskInfo
                    .newBuilder()
                    .setName("task " + taskId.getValue())
                    .setTaskId(taskId)
                    .setSlaveId(offer.getSlaveId())
                    .addResources(
                            Protos.Resource.newBuilder().setName("cpus").setType(Protos.Value.Type.SCALAR)
                                    .setScalar(Protos.Value.Scalar.newBuilder().setValue(1)))
                    .addResources(
                            Protos.Resource.newBuilder().setName("mem").setType(Protos.Value.Type.SCALAR)
                                    .setScalar(Protos.Value.Scalar.newBuilder().setValue(128)))
                    .setData(ByteString.copyFromUtf8("Some Data needed by the task"))
                    .setExecutor(Protos.ExecutorInfo.newBuilder(executorInfo)).build();

            driver.launchTasks(Arrays.asList(offer.getId()), Arrays.asList(task));
        }
    }

    private Protos.TaskID newTaskId() {
        return Protos.TaskID.newBuilder().setValue("" + new Random().nextInt(1000)).build();
    }

    @Override
    public void offerRescinded(SchedulerDriver driver, Protos.OfferID offerId) {

    }

    @Override
    public void statusUpdate(SchedulerDriver driver, Protos.TaskStatus status) {

    }

    @Override
    public void frameworkMessage(SchedulerDriver driver, Protos.ExecutorID executorId, Protos.SlaveID slaveId, byte[] data) {

    }

    @Override
    public void disconnected(SchedulerDriver driver) {

    }

    @Override
    public void slaveLost(SchedulerDriver driver, Protos.SlaveID slaveId) {

    }

    @Override
    public void executorLost(SchedulerDriver driver, Protos.ExecutorID executorId, Protos.SlaveID slaveId, int status) {

    }

    @Override
    public void error(SchedulerDriver driver, String message) {

    }
}
