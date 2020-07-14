package com.mowit.core;

import com.mowit.core.exception.InvalidCommand;
import com.mowit.core.exception.InvalidMovingCommand;
import com.mowit.core.exception.InvalidMowerStartingPosition;
import com.mowit.core.geo.Obstacles;
import com.mowit.core.geo.Position;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static com.mowit.core.exception.ErrorMessages.*;

class MowersController {

    private List<Mower> mowers;
    private ThreadPoolExecutor executor;

    public MowersController() {
        super();
        Obstacles.currentMowsPosition = new ConcurrentHashMap<>();
    }

    public void processFile(File inputCommands) throws IOException, InvalidCommand {
        List<Mower> extractedMowers = new LinkedList<>();
        try (FileInputStream inputStream = new FileInputStream(inputCommands);
             Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            String lawnDimension = sc.nextLine();
            Lawn lawn = new Lawn();
            lawn.createLimitLawnCoordinates(lawnDimension);
            if (!sc.hasNextLine()) {
                throw new InvalidMowerStartingPosition(EMPTY_MOWER_INITIAL_POSITION.getMsg());
            }
            while (sc.hasNextLine()) {
                String startingPosition = sc.nextLine();
                if (!sc.hasNextLine()) {
                    throw new InvalidMovingCommand(EMPTY_MOWER_MOVING_COMMANDS.getMsg() + (extractedMowers.size() + 1));
                }
                String instructions = sc.nextLine();
                Mower mower = new Mower(lawn);
                mower.init(startingPosition, instructions);
                extractedMowers.add(mower);
            }
        }
        this.mowers = Collections.unmodifiableList(extractedMowers);
    }

    public void startMowing() throws InterruptedException, ExecutionException {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        List<Future<Position>> results = executor.invokeAll(this.mowers);
        for (Future<Position> result : results) {
            System.out.println(result.get().toString());
        }
        executor.shutdown();
    }

    public List<Mower> getMowers() {
        return this.mowers;
    }

    public int getThreadPoolSize() {
        return executor.getMaximumPoolSize();
    }

}
