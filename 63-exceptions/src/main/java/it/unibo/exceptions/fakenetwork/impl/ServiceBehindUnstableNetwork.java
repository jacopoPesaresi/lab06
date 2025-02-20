package it.unibo.exceptions.fakenetwork.impl;

import it.unibo.exceptions.arithmetic.ArithmeticService;
import it.unibo.exceptions.fakenetwork.api.NetworkComponent;

import it.unibo.exceptions.myexceptions.NetworkException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import static it.unibo.exceptions.arithmetic.ArithmeticService.KEYWORDS;
import static it.unibo.exceptions.arithmetic.ArithmeticUtil.nullIfNumberOrException;

/**
 * A {@link NetworkComponent} mimicking an unstable network.
 */
public final class ServiceBehindUnstableNetwork implements NetworkComponent {
    private final double failProbability;
    private final RandomGenerator randomGenerator;
    private final List<String> commandQueue = new ArrayList<>();

    /**
     * @param failProbability the probability that a network communication fails
     * @param randomSeed random generator seed for reproducibility
     * @throws IllegalAccessException
     */
    public ServiceBehindUnstableNetwork(final double failProbability, final int randomSeed) throws IllegalArgumentException {
        /*
         * The probability should be in [0, 1[!
         */
        if (failProbability<0 || failProbability>=1) {
            final String errString = "The failProbability value isn't included between 0 and 1. Actual value = " + failProbability;
            throw new IllegalArgumentException(errString);
        }
        this.failProbability = failProbability;
        randomGenerator = new Random(randomSeed);
    }

    /**
     * @param failProbability the probability that a network communication fails
     */
    public ServiceBehindUnstableNetwork(final double failProbability) {
        this(failProbability, 0);
    }

    /**
     * Builds a new service with an unstable network.
     */
    public ServiceBehindUnstableNetwork() {
        this(0.5);
    }

    @Override
    public void sendData(final String data) throws IOException {
        accessTheNework(data);
        final var exceptionWhenParsedAsNumber = nullIfNumberOrException(data);
        if (KEYWORDS.contains(data) || exceptionWhenParsedAsNumber == null) {
            commandQueue.add(data);
        } else {
            final var message = data + " is not a valid keyword (allowed: " + KEYWORDS + "), nor is a number";
            /*System.out.println(message);*/

            commandQueue.clear();
            //throw new NumberFormatException(message + exceptionWhenParsedAsNumber);
            throw new IllegalArgumentException(message, exceptionWhenParsedAsNumber);
            
            /*
             * This method, in this point, should throw an IllegalStateException.
             * Its cause, however, is the previous NumberFormatException.
             * Always preserve the original stacktrace!
             *
             * The previous exceptions must be set as the cause of the new exception
             */
        }
    }

    @Override
    public String receiveResponse() throws IOException {
        accessTheNework(null);
        try {
            return new ArithmeticService(Collections.unmodifiableList(commandQueue)).process();
        } finally {
            commandQueue.clear();
        }
    }

    private void accessTheNework(final String message) throws IOException {
        if (randomGenerator.nextDouble() < failProbability) {
            //throw new NetworkException("Generic I/O error");
            //messaggio adeguato?
            throw message == null ? new NetworkException() : new NetworkException(message);
        }
    }

}
