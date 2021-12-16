package me.dibdin.adventofcode.year2021;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import me.dibdin.adventofcode.AbstractChallenge;
import me.dibdin.adventofcode.util.InputDecoder;

/**
 * Advent of Code Challenge 2021 - Day 15: Chiton.
 * https://adventofcode.com/2021/day/15
 */
public class Day15 extends AbstractChallenge {

    int[][] puzzle = null;
    boolean readyToSolve = false;

    /**
     * Constructor.
     */
    public Day15() {
        super("Chiton", 2021, 15);
    }

    /**
     * A node in the graph
     */
    class Node {
        public int x;
        public int y;
        public int weight;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node n = (Node) obj;
                return ((this.x == n.x) && (this.y == n.y));
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return (1009 * x) + y;
        }
    }

    class CompareNodeweight implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return n1.weight - n2.weight;
        }

        public boolean equals(Object obj) {
            return (obj instanceof CompareNodeweight);
        }
    }

    /**
     * Find the shortest path from top let to bottom right, using the weightings in the grid.
     * Implementations use the Dijkstra's alogrithm with a priority queue.
     * @param grid the input grod
     * @return the total weight of the shortest path
     */
    private int findShortestPath(int[][] grid) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(grid.length * grid[0].length, new CompareNodeweight());

        Node[][] nodes = new Node[grid.length][grid[0].length];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                nodes[y][x] = new Node();
                nodes[y][x].x = x;
                nodes[y][x].y = y;
                nodes[y][x].weight = Integer.MAX_VALUE;
                pq.add(nodes[y][x]);
            }
        }

        // set the starting node to zero
        pq.remove(nodes[0][0]);
        nodes[0][0].weight = 0;
        pq.add(nodes[0][0]);

        while (pq.size() > 0) {
            // remove the node with the smallest weight from the queue
            Node current = pq.poll();

            // have we reached the end point
            if ((current.x == grid[0].length - 1) && (current.y == grid.length - 1)) {
                return current.weight;
            }

            if ((pq.size() % 100) == 0) {
                int target = (grid.length * grid[0].length);
                System.out.println((target - pq.size()) + " / " + target);
            }

            // look left
            if (current.x > 0) {
                Node left = nodes[current.y][current.x - 1];
                if (pq.remove(left)) {
                    left.weight = Integer.min(left.weight, current.weight + grid[current.y][current.x - 1]);
                    pq.add(left);
                }
            }

            // look right
            if (current.x < grid[0].length - 1) {
                Node right = nodes[current.y][current.x + 1];
                if (pq.remove(right)) {
                    right.weight = Integer.min(right.weight, current.weight + grid[current.y][current.x + 1]);
                    pq.add(right);
                }
            }

            // look up
            if (current.y > 0) {
                Node up = nodes[current.y - 1][current.x];
                if (pq.remove(up)) {
                    up.weight = Integer.min(up.weight, current.weight + grid[current.y - 1][current.x]);
                    pq.add(up);
                }
            }

            // look down
            if (current.y < grid.length - 1) {
                Node down = nodes[current.y + 1][current.x];
                if (pq.remove(down)) {
                    down.weight = Integer.min(down.weight, current.weight + grid[current.y + 1][current.x]);
                    pq.add(down);
                }
            }
        }

        return nodes[grid.length - 1][grid[0].length - 1].weight;
    }

    /**
     * Solve part one of the puzzle
     */
    public long solvePartOne() {
        if (!readyToSolve) {
            throw new IllegalStateException("No puzzle input set");
        }

        return findShortestPath(puzzle);
    }

    /**
     * Solve part two of the puzzle
     */
    public long solvePartTwo() {
        if (!readyToSolve) {
            throw new IllegalStateException("No puzzle input set");
        }

        // enlarge the puzzle
        int factor = 5;
        int[][] enlargedPuzzle = new int[puzzle.length * factor][puzzle[0].length * factor];

        for (int y = 0; y < enlargedPuzzle.length; y++) {
            for (int x = 0; x < enlargedPuzzle[y].length; x++) {
                enlargedPuzzle[y][x] = (puzzle[y % puzzle.length][x % puzzle[0].length]
                        + (y / puzzle.length) + (x / puzzle[0].length));

                while (enlargedPuzzle[y][x] > 9) {
                    enlargedPuzzle[y][x] -= 9;
                }
            }
        }

        return findShortestPath(enlargedPuzzle);
    }
    

    /**
     * Take the stream of strings, and decode the input into puzzle data for this
     * challenge.
     */
    public void setPuzzleInput(Stream<String> input) {
        puzzle = InputDecoder.decodeAsTwoDimensionalIntArray(input);

        readyToSolve = true;
    }
}