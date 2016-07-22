package com.main;

import com.scheduler.SampleScheduler;
import org.apache.mesos.MesosSchedulerDriver;
import org.apache.mesos.Protos.CommandInfo;
import org.apache.mesos.Protos.ExecutorID;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Protos.Status;
import org.apache.mesos.Scheduler;

public class Main {
    public static void main(String[] args) throws Exception {

        FrameworkInfo frameworkInfo = createFrameworkInfo();
        ExecutorInfo executorInfo = createExecutorInfo();

        String mesosMasterUrl = args[0];
        runScheduler(frameworkInfo, executorInfo, mesosMasterUrl);
    }

    private static void runScheduler(FrameworkInfo frameworkInfo, ExecutorInfo executorInfo, String mesosMasterUrl) {
        Scheduler scheduler = new SampleScheduler(executorInfo);
        MesosSchedulerDriver driver = new MesosSchedulerDriver(scheduler, frameworkInfo, mesosMasterUrl);
        int status = driver.run() == Status.DRIVER_STOPPED ? 0 : 1;
        // Ensure that the driver process terminates.
        driver.stop();
        System.exit(status);
    }

    private static FrameworkInfo createFrameworkInfo() {
        FrameworkInfo.Builder frameworkBuilder = FrameworkInfo
                .newBuilder()
                .setFailoverTimeout(120000)
                .setUser("") //default user
                .setName("Sample Mesos Framework (Java)")
                .setPrincipal("test-framework-java");

        return frameworkBuilder.build();
    }

    private static ExecutorInfo createExecutorInfo() {
        CommandInfo.URI requiredJarUris = getRequiredJarsUrls();

        String cmd = "java -cp mesos-scheduler-1.0-all.jar com.executor.CmdExecutor";
        CommandInfo commandInfo = buildCommandInfo(requiredJarUris, cmd);

        return ExecutorInfo.newBuilder()
                .setExecutorId(ExecutorID.newBuilder().setValue("CmdExecutor"))
                .setCommand(commandInfo).setName("Cmd Executor (Java)").setSource("java").build();
    }

    private static CommandInfo buildCommandInfo(CommandInfo.URI uri, String commandCrawler) {
        return CommandInfo.newBuilder().setValue(commandCrawler).addUris(uri)
                .build();
    }

    private static CommandInfo.URI getRequiredJarsUrls() {
        String path = System.getProperty("user.dir")
                + "/build/libs/mesos-scheduler-1.0-all.jar";

        return CommandInfo.URI.newBuilder().setValue(path).setExtract(false).build();
    }
}