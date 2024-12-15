package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine{
    private List<Simulation> simulationList = new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();
    private ExecutorService pool = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulationList) {
        this.simulationList = simulationList;
    }


    public void runSync() {
        for (Simulation simulation: simulationList) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation: simulationList) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }
    }

    public void awaitSimulationsEnd() {
        try {
            for (Thread thread : threads) {
                thread.join();
            }
            pool.shutdown();
            if (!pool.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
                pool.shutdownNow();
            }
        }
            catch (InterruptedException e) {
                System.out.println("Stopped: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        public void runAsyncInThreadPool() {
        try {
            for (Simulation simulation: simulationList){
                pool.submit(simulation);
            }
        } finally {
            pool.shutdown();
        }
        }
    }



