package com.executor;

import org.apache.mesos.Executor;
import org.apache.mesos.ExecutorDriver;
import org.apache.mesos.MesosExecutorDriver;
import org.apache.mesos.Protos;

public class SampleExecutor implements Executor {
    @Override
    public void registered(ExecutorDriver driver, Protos.ExecutorInfo executorInfo, Protos.FrameworkInfo frameworkInfo, Protos.SlaveInfo slaveInfo) {

        System.out.println("Registered executor on " + slaveInfo.getHostname());
    }

    @Override
    public void reregistered(ExecutorDriver driver, Protos.SlaveInfo slaveInfo) {

        System.out.println("Registered executor on " + slaveInfo.getHostname());
    }

    @Override
    public void disconnected(ExecutorDriver driver) {

    }

    @Override
    public void launchTask(ExecutorDriver driver, Protos.TaskInfo taskInfo) {
        Protos.TaskStatus status = Protos.TaskStatus.newBuilder().setTaskId(taskInfo.getTaskId())
                .setState(Protos.TaskState.TASK_RUNNING).build();
        driver.sendStatusUpdate(status);

        String taskData = taskInfo.getData().toStringUtf8();
        String message = "The task is complete with " + taskData;
        log(message);
        driver.sendFrameworkMessage(message.getBytes());

        status = Protos.TaskStatus.newBuilder().setTaskId(taskInfo.getTaskId())
                .setState(Protos.TaskState.TASK_FINISHED).build();
        driver.sendStatusUpdate(status);
    }

    @Override
    public void killTask(ExecutorDriver driver, Protos.TaskID taskId) {
        log("Killling task ");
    }

    private void log(String message) {
        //This will go to stdout of the task container to directory like
        ///var/lib/mesos/slaves/1588da5d-357e-4ecc-83c1-b952b849571a-S7/frameworks/1588da5d-357e-4ecc-83c1-b952b849571a-0008/executors/CmdExecutor/runs/2aa0bca5-3865-4b23-9a8c-10813941db85/

        System.out.println(message);
    }

    @Override
    public void frameworkMessage(ExecutorDriver driver, byte[] data) {
        log("Framework message ");

    }

    @Override
    public void shutdown(ExecutorDriver driver) {

    }

    @Override
    public void error(ExecutorDriver driver, String message) {
        log("Error " + message);
    }

    public static void main(String[] args) throws Exception {
        MesosExecutorDriver driver = new MesosExecutorDriver(new SampleExecutor());
        System.exit(driver.run() == Protos.Status.DRIVER_STOPPED ? 0 : 1);
    }
}
