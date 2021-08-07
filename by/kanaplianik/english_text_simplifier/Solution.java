package by.kanaplianik.english_text_simplifier;

import java.io.*;

public class Solution {
	private static StringBuilder sb;
	private static String input;

	public static void main(String[] args) {

		input = inputText();
		if (input.length() > 1) {
			input = removeC(input);
			//System.out.println(input);
			input = removeDoubleLetters(input);
			//System.out.println(input);
			input = removeLastE(input);
			//System.out.println(input);
			input = removeArticles(input);
			System.out.println(input);
		} else if (input.length() == 1) {
			if (input.equals("a")) {
				System.out.println("An article was removed");
			} else
				System.out.println(input);
		}
	}

	private static String inputText() {
		System.out.println("Type text please, to input text type \">\" in new line: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		try {
			while (true) {
				String line = br.readLine();
				if (line.equals(">"))
					break;
				sb.append(line).append("\n");
			}
		} catch (IOException e) {
			System.out.println("No text found.");
		}
		return sb.toString().trim();
	}

	private static String removeC(String string) {
		sb = new StringBuilder(string);
		boolean isDone;
		do {
			int i = 0;
			isDone = true;
			while (i < sb.length() - 1) {
				if (sb.charAt(i) == 'C' || sb.charAt(i) == 'c') {
					isDone = false;
					if (sb.charAt(i + 1) == 'i')
						sb.replace(i, i + 2, sb.charAt(i) == 'C' ? "Si" : "si");
					else if (sb.charAt(i + 1) == 'e')
						sb.replace(i, i + 2, sb.charAt(i) == 'C' ? "Se" : "se");
					else if (sb.charAt(i + 1) == 'k')
						sb.replace(i, i + 2, sb.charAt(i) == 'C' ? "K" : "k");
					else
						sb.replace(i, i + 1, sb.charAt(i) == 'C' ? "K" : "k");				
				} else if (sb.charAt(sb.length() - 1) == 'C' || sb.charAt(sb.length() - 1) == 'c') {
					isDone = false;
					sb.replace(sb.length() - 1, sb.length(), sb.charAt(sb.length() - 1) == 'C' ? "K" : "k");
				}
				i++;
			}
		} while (!isDone);
		return sb.toString();
	}
	
	private static String removeDoubleLetters(String string) {
		sb = new StringBuilder(string);
		boolean isDone;
		do {
			int i = 0;
			isDone = true;
			while (i < sb.length() - 1) {
				if (sb.charAt(i) == 'e' || sb.charAt(i) == 'E') {
					if (sb.charAt(i + 1) == 'e') {
						isDone = false;
						sb.replace(i, i + 2, sb.charAt(i) == 'E' ? "I" : "i");
					}
				} else if (sb.charAt(i) == 'o' || sb.charAt(i) == 'O') {
					if (sb.charAt(i + 1) == 'o') {
						isDone = false;
						sb.replace(i, i + 2, sb.charAt(i) == 'O' ? "U" : "u");
					}
				} else {
					if (Character.toLowerCase(sb.charAt(i)) == sb.charAt(i + 1)) {
						isDone = false;
						sb.replace(i, i + 2, sb.charAt(i) + "");
					}
				}
				i++;
			}
		} while (!isDone);
		return sb.toString();
	}
	
	private static String removeLastE(String string) {
		return string.replaceAll("(?<=\\w)e(?=\\W+|$)", "").trim();
	}

	private static String removeArticles(String string) {
		String[] strings = string.split("\\n");
		sb = new StringBuilder();
		for (String s : strings) {
			s = s.trim();
			if (!s.equals("a") || !s.equals("an") || !s.equals("th") || !s.equals("A") || !s.equals("An") || !s.equals("Th"))
				sb.append(s.replaceAll("(^|\\s)(a|an|th|A|An|Th)(?=\\W|$)", "").trim()).append("\n");	
		}
		return sb.toString().trim();
	}
	
}
