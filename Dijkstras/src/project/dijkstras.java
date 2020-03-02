package project;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class dijkstras {
	private static Edge[] e = null;
	private static boolean undirectedGraph = false;
	static List<Edge> listEdges = new ArrayList<>();
	static String typeOfGraph, noOfEdges, noOfVertices;
	static String everything = null;
	static String startNode = null;
	static HashSet<String> collectVertices = new HashSet<>();

	private static int k = 0;

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter full path of the input text file: ");
		String path = sc.nextLine();
		System.out.println("Enter: 1. Dijkstra's Algorithm \n 2. Kruskal's Algorithm");
		int a = sc1.nextInt();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			String line1 = line;
			int first = line1.indexOf(" ");
			int second = line1.indexOf(" ", first + 1);
			int third = line1.indexOf(" ", second + 1);
			noOfVertices = line1.substring(0, first).trim();
			e = new Edge[Integer.parseInt(noOfVertices)];
			noOfEdges = line1.substring(first, second).trim();
			typeOfGraph = line1.substring(second, third).trim();
			System.out.println("noOfVertices: " + noOfVertices);
			System.out.println("noOfEdges: " + noOfEdges);
			System.out.println("typeOfGraph: " + typeOfGraph);

			if (typeOfGraph.toLowerCase().equals("u")) {
				undirectedGraph = true;
			} else {
				undirectedGraph = false;
			}

			int num = Integer.parseInt(noOfEdges);

			e = new Edge[num];

			while (num != 0) {
				line = br.readLine();
				createNode(line);
				num--;
			}

			if (a == 1) {

				if ((line = (br.readLine())) != null) {
					// line = br.readLine();
					startNode = line.trim().substring(0, 1);
				} else {
					System.out.println("Enter start node: ");
					startNode = sc2.nextLine();
					startNode.toLowerCase().trim();

				}

				System.out.println("Line 1 " + line1);
				double timeStart = System.currentTimeMillis();
				Graph g = new Graph(e, undirectedGraph);
				g.dijkstra(startNode);
				g.printAllPaths();
				double time = System.currentTimeMillis() - timeStart;
				System.out.println("Execution Time: " + time);
			} else if (a == 2) {
				if (undirectedGraph) {
					Kruskal kruskal = new Kruskal();
					String x[] = collectVertices.toArray(new String[collectVertices.size()]);
					double timeStart = System.currentTimeMillis();
					kruskal.KruskalAlgo(x, e);
					double time = System.currentTimeMillis() - timeStart;
					System.out.println("Execution Time: " + time);

				} else {
					System.err.println("Cannot find MST for directed graph");
				}
			}

		}

		catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			br.close();
		}
	}

	private static void createNode(String l) {
		String li = l.trim();
		String s1 = li.substring(0, 1).trim();
		String s2 = li.substring(2, 3).trim();
		String s3 = li.substring(4, li.length()).trim();

		collectVertices.add(s1);
		collectVertices.add(s2);
		int ss = Integer.valueOf(s3);

		e[k] = new Edge(s1, s2, ss);

		System.out.println(e[k]);
		k++;
	}

}