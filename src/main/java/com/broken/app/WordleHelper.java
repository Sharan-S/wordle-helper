package com.broken.app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordleHelper {

  private static final int WORD_LENGTH = 5;

  static List<Character> matchingCharacters = List.of('o', 'l');
  static List<Character> nonMatchingCharacters = List.of('r', 'a', 't', 'e', 'f', 'i', 's');

  private static final String STARTING_LETTERS = "";
  private static final String ENDING_LETTERS = "";
  private static final String NON_STARTING_LETTERS = "";
  private static final String NON_ENDING_LETTERS = "";

  public static void main(String[] args) {
    getwords();
  }

  private static Boolean doesContain(String word) {
    List<Character> charList = wordToChar(word);
    return charList.containsAll(matchingCharacters);
  }

  private static final List<Character> wordToChar(String word) {
    return word.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
  }

  private static Boolean doesNotContain(String word) {
    List<Character> charList = wordToChar(word);
    for (Character character : charList) {
      if (nonMatchingCharacters.contains(character))
        return false;
    }
    return true;
  }

  private static void getwords() {
    URL resource = WordleHelper.class.getResource("/words_alpha.txt");
    try (Stream<String> words = Files.lines(Paths.get(resource.toURI()))) {
      words.filter(s -> s.length() == WORD_LENGTH).filter(WordleHelper::doesContain)
          .filter(WordleHelper::doesNotContain)
          // .filter(s -> s.startsWith(STARTING_LETTERS))
          // .filter(s -> s.endsWith(ENDING_LETTERS)).filter(s -> !s.endsWith(NON_ENDING_LETTERS))
          // .filter(s -> !s.endsWith(NON_STARTING_LETTERS)).filter(s -> s.charAt(1) != 'o')
          .filter(s -> s.charAt(3) == 'l').filter(s -> s.charAt(0) != 'o')
          .filter(s -> s.charAt(1) != 'o')
          // .filter(s -> s.charAt(0) != 'o').filter(s -> s.charAt(2) != 'a')
          // .filter(s -> s.charAt(1) != 'v').filter(s -> s.charAt(3) != 'r')
          .sorted().forEach(System.out::println);
    } catch (IOException | URISyntaxException e) {
      System.out.println(e);
    }
  }

}
