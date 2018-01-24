package ProgPack;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class terminalExecution {
    JTextArea terminalOutput;

    public terminalExecution(JTextArea terminalOutput){
        this.terminalOutput = terminalOutput;
    }

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }

    public boolean isWindows(){
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        return isWindows;
    }

    public void execute(String command) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows()) {
            builder.command("cmd.exe", "/c", command);
        } else {
            builder.command("sh", "-c", command);
        }
        builder.directory(new File(System.getProperty("user.dir")));
        Process process = builder.start();
//        StreamGobbler streamGobbler =
//                new StreamGobbler(process.getInputStream(), System.out::println);
//        Executors.newSingleThreadExecutor().submit(streamGobbler);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));

        // read the output from the command
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            terminalOutput.append(s);
            System.out.println(s);
        }

        // read any errors from the attempted command
        while ((s = stdError.readLine()) != null) {
            terminalOutput.append(s);
            System.out.println(s);
        }

        int exitCode = process.waitFor();
        assert exitCode == 0;
    }

    public void execute2() throws IOException {
        Runtime rt = Runtime.getRuntime();
        String[] commands = {"system.exe",""};
        Process proc = rt.exec(commands);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }

}

