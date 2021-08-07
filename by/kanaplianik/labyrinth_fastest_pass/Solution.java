package by.kanaplianik.labyrinth_fastest_pass;

import java.io.*;
import java.util.*;

public class Solution {
	private static String[][][] labyrinth;
	private static String text;
	private static int H;
	private static int M;
	private static int N;
	
	public static class Point {
		private String value;
		private int count;
		private int h;
		private int m;
		private int n;

		public Point(String value, int count, int h, int m, int n) {
			this.value = value;
			this.count = count;
			this.h = h;
			this.m = m;
			this.n = n;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + h;
			result = prime * result + m;
			result = prime * result + n;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (h != other.h)
				return false;
			if (m != other.m)
				return false;
			if (n != other.n)
				return false;
			return true;
		}

		public String getValue() {
			return value;
		}

		public int getCount() {
			return count;
		}
		
		public int getH() {
			return h;
		}

		public int getM() {
			return m;
		}

		public int getN() {
			return n;
		}
		

		@Override
		public String toString() {
			return "Point [value=" + value + ", h=" + h + ", m=" + m + ", n=" + n + "]";
		}
	}

	public static void main(String[] args) {
		readFromFile(readFromConsole());
		parseText();
		System.out.println(findPrince());
	}

	private static String readFromConsole() {
		System.out.println("Please type full file name (for example \"C:\\INPUT.TXT\"): ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String fileName = null;
		try {
			fileName = reader.readLine();
		} catch (IOException e) {
			System.out.println();
		}
		return fileName;
	}
	
	private static void readFromFile(String fileName) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			text = "";
			while (br.ready()) {
				text += (br.readLine() + "\n");
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Invalid file");
		}

	}

	private static void parseText() {
		String[] lines = text.split("\\n+");
		String[] dimensions = lines[0].split("\\s");
		try {
			H = Integer.parseInt(dimensions[0]);
			M = Integer.parseInt(dimensions[1]);
			N = Integer.parseInt(dimensions[2]);
		} catch (NumberFormatException e) {
			System.out.println("Incorrect dimensions of the labyrinth");
		}

		labyrinth = new String[H][M][N];
		// System.out.println(new String().format("%d %d %d \n", H, M, N));

		int i = 1;
		int h = 0;
		while (h < H) {
			for (int m = 0; m < M; m++) {
				for (int n = 0; n < N; n++) {
					labyrinth[h][m][n] = "" + lines[i + m].charAt(n);
					// System.out.print(labyrinth[h][m][n]);
				}
				// System.out.println();
			}
			// System.out.println();
			h++;
			i += M;
		}
	}

	private static int findPrince() {
		ArrayDeque<Point> trace = new ArrayDeque<>();
		int count = 0;
		// find coordinates of Prince's start point
		for (int m = 0; m < M; m++) {
			for (int n = 0; n < N; n++) {
				if (labyrinth[0][m][n].equals("1")) {
					// adding start point to the queue
					trace.add(new Point("1", 0, 0, m, n));
					break;
				}
			}
		}

		while (!trace.isEmpty()) {
			Point current = trace.pop();;
			if (current.getValue().equals("2")) {
				//System.out.println(current);
				count = current.getCount();
				break;
			}
			for (Point point : findAdjacentPoints(current)) {
				trace.add(point);
			}
		}

		return count * 5;
	}

	private static Set<Point> findAdjacentPoints(Point point) {
		Set<Point> points = new HashSet<>();
		int count = point.getCount();
		int h = point.getH();
		int m = point.getM();
		int n = point.getN();

		//System.out.println(h + " " + m + " " + n);

		if (h < H - 1) {
			if (!labyrinth[h + 1][m][n].equals("o")) {
				points.add(new Point(labyrinth[h + 1][m][n], count + 1, h + 1, m, n));
				// System.out.println(labyrinth[h + 1][m][n]);
			}
		}
		if (h > 0) {
			if (!labyrinth[h - 1][m][n].equals("o")) {
				points.add(new Point(labyrinth[h - 1][m][n], count + 1, h - 1, m, n));
				// System.out.println(labyrinth[h - 1][m][n]);
			}
		}

		if (m < M - 1) {
			if (!labyrinth[h][m + 1][n].equals("o")) {
				points.add(new Point(labyrinth[h][m + 1][n], count + 1, h, m + 1, n));
				// System.out.println(labyrinth[h][m + 1][n]);
			}
		}
		if (m > 0) {
			if (!labyrinth[h][m - 1][n].equals("o")) {
				points.add(new Point(labyrinth[h][m - 1][n], count + 1, h, m - 1, n));
				// System.out.println(labyrinth[h][m - 1][n]);
			}
		}

		if (n < N - 1) {
			if (!labyrinth[h][m][n + 1].equals("o")) {
				points.add(new Point(labyrinth[h][m][n + 1], count + 1, h, m, n + 1));
				// System.out.println(labyrinth[h][m][n + 1]);
			}
		}
		if (n > 0) {
			if (!labyrinth[h][m][n - 1].equals("o")) {
				points.add(new Point(labyrinth[h][m][n - 1], count + 1, h, m, n - 1));
				//System.out.println(labyrinth[h][m][n - 1]);
			}
		}

		return points;
	}

}


