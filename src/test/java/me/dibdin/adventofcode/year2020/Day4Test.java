package me.dibdin.adventofcode.year2020;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import me.dibdin.adventofcode.Challenge;

/**
 * Unit tests for Year 2020, Day 4
 */
@TestMethodOrder(OrderAnnotation.class)
class Day4Test {
    
    static Challenge challenge;
    
    @BeforeAll
    static void initAll() {
        challenge = new Day4();
    }

    @Test
    @Order(1)
    void informationTest() 
    {
        assertTrue(challenge.getName().equals("Passport Processing"));
        assertTrue(challenge.getYear() == 2020);
        assertTrue(challenge.getDay() == 4);
    }

    @Test
    @Order(2)
    void cannotSolveNullInputTest() {
        assertThrows(IllegalStateException.class, () -> challenge.solvePartOne());
        assertThrows(IllegalStateException.class, () -> challenge.solvePartOne());
    }

    @Test
    @Order(3)
    void solveWithSampleInputTest()
    {
        // form the test  input stream
        Stream<String> input = Stream.of(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
            "byr:1937 iyr:2017 cid:147 hgt:183cm",
            "",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
            "hcl:#cfa07d byr:1929",
            "",
            "hcl:#ae17e1 iyr:2013",
            "eyr:2024",
            "ecl:brn pid:760753108 byr:1931",
            "hgt:179cm",
            "",
            "hcl:#cfa07d eyr:2025 pid:166559648",
            "iyr:2011 ecl:brn hgt:59in"
        );

        // find the solution
        challenge.setPuzzleInput(input);

        // assert the result is corrent
        assertEquals(2, challenge.solvePartOne());
        assertEquals(2, challenge.solvePartTwo());
    }

    @Test
    @Order(4)
    void solveWithSampleInputAllInvalidTest()
    {
        // form the test  input stream
        Stream<String> input = Stream.of(
            "eyr:1972 cid:100",
            "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
            "",
            "iyr:2019",
            "hcl:#602927 eyr:1967 hgt:170cm",
            "ecl:grn pid:012533040 byr:1946",
            "",
            "hcl:dab227 iyr:2012",
            "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
            "",
            "hgt:59cm ecl:zzz",
            "eyr:2038 hcl:74454a iyr:2023",
            "pid:3556412378 byr:2007"
        );

        // find the solution
        challenge.setPuzzleInput(input);

        // assert the result is correct
       assertEquals(0, challenge.solvePartTwo());
    }

    @Test
    @Order(5)
    void solveWithSampleInputAllValidTest()
    {
        // form the test  input stream
        Stream<String> input = Stream.of(
            "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980",
            "hcl:#623a2f",
            "",
            "eyr:2029 ecl:blu cid:129 byr:1989",
            "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
            "",
            "hcl:#888785",
            "hgt:164cm byr:2001 iyr:2015 cid:88",
            "pid:545766238 ecl:hzl",
            "eyr:2022",
            "",
            "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
        );

        // find the solution
        challenge.setPuzzleInput(input);

        // assert the result is correct
       assertEquals(4, challenge.solvePartTwo());
    }

    /**
     * Test the solution with actual input data from the challenge.
     */
    @Test
    @DisplayName("Solve With Actual Input Test")
    @Order(6)
    void solveWithActualInputTest() {
        String filename = String.format("data/year%d/day%d.txt", challenge.getYear(), challenge.getDay());
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        ClassLoader.getSystemResourceAsStream(filename)))
                                .lines()) {

            // Solve the challenge
            challenge.setPuzzleInput(stream);

            // assert the result is corrent
            assertEquals(190, challenge.solvePartOne());
            assertEquals(121, challenge.solvePartTwo());
        }
    }
}
