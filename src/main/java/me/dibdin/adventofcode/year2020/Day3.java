package me.dibdin.adventofcode.year2020;

import me.dibdin.adventofcode.AbstractChallenge;

import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * Advent of Code Challenge 2020 - Day 3: Toboggan Trajectory.
 * https://adventofcode.com/2020/day/3
 */
public class Day3 extends AbstractChallenge {

    char[][] puzzle;

    char OPEN = '.';
    char TREE = '#';

    public class Slope {
        private int right;
        private int down;

        public Slope(int right, int down) {
            this.right = right;
            this.down = down;
        }
        
        public int getRight() {
            return this.right;
        }

        public int getDown() {
            return this.down;
        }
    }

    /**
     * Initialise the instance, with the name, year and day of the challege.
     */
    public Day3() {
        super ("Toboggan Trajectory", 2020, 3);
    }

    private int findTrees(char[][] map, Slope slope) {
        int trees = 0; // how many trees encountered so far

        int x = 0; // top of map
        int y = 0; // left of map

        int width = map[0].length; // width of the map
        int height = map.length;   // height of the map
    
        // while there is still height in the mountain
        while (y < height) {
            if (map[y][x] == TREE) {
                trees++;
            }
            x = (x + slope.getRight()) % width;  // map repeats itself horizontally
            y = y + slope.getDown();
        }
        
        return trees;
    }
    
    public long solvePartOne() {
        if (puzzle == null) { 
            throw new IllegalStateException("No puzzle input set");
        }

        return findTrees(puzzle, new Slope(3, 1));
    }

    public long solvePartTwo() {
        if (puzzle == null) { 
            throw new IllegalStateException("No puzzle input set");
        }

        long result = 1;

        ArrayList<Slope> slopes = new ArrayList<Slope>();
        slopes.add(0, new Slope(1,1));
        slopes.add(1, new Slope(3,1));
        slopes.add(2, new Slope(5,1));
        slopes.add(3, new Slope(7,1));
        slopes.add(4, new Slope(1,2));

        for (int i = 0; i < slopes.size(); i++) {
            result = result * findTrees(puzzle, slopes.get(i));
        }

        return result;
    }
    
    /**
     * Solve both parts ofhar hte puzzle for the given input. 
     * @param input a stream of strings to process as the puzzle input
     */
    public void setPuzzleInput(Stream<String> input) {

        // Convert the input into an array chars
        ArrayList<char[]> list = input.map(str -> str.toCharArray()).collect(Collectors.toCollection(ArrayList<char[]>::new));
        
        puzzle = new char[list.size()][list.get(0).length];
        for (int i = 0; i < puzzle.length; i++) {
            puzzle[i] = list.get(i);
        }
    }
}