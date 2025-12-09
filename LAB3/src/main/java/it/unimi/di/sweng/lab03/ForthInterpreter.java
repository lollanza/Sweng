package it.unimi.di.sweng.lab03;

import jdk.dynalink.Operation;

import java.util.*;

public class ForthInterpreter implements Interpreter {

    private final Deque<Integer> program = new ArrayDeque<>();
    private final Map<String, Operation> operations = new HashMap<>();

    private interface Operation {
        void run();
    }

    public ForthInterpreter() {
        operations.put("+", () -> program.push(program.pop()+program.pop()));
        operations.put("-", () -> {
            int x = program.pop();
            program.push(x-program.pop());
        });
        operations.put("*", () -> program.push(program.pop()*program.pop()));
        operations.put("/", () -> {
            int x = program.pop();
            program.push(program.pop()/x);
        });
        operations.put("dup", () -> program.push(program.getFirst()));
        operations.put("swap", () -> {
            int x = program.pop();
            int y = program.pop();
            program.push(x);
            program.push(y);
        });
        operations.put("drop", program::pop);
    }

    @Override
    public void input(String program) {
        try (Scanner scanner = new Scanner(program)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                if (token.matches("[0-9]+")) this.program.push(Integer.valueOf(token));
                else if (token.matches("[+/*-]") || token.matches("[a-z]+")) operations.get(token).run();
                else throw new IllegalArgumentException("Token error " + token);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Stack underflow");
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ");
        for (Integer i : program.reversed())
            joiner.add(i.toString());
        joiner.add("<- Top");
        return joiner.toString();
    }

}
