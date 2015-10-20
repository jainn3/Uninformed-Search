import java.io.*;
import java.util.*;
class edge
{
	int cost;
	boolean time[];
}

class graph
{
	edge cityGraph [][];
	HashMap<String, Integer> nodeMap;
	static int dcost[];
 public String buildBFSGraph(String[] data)
 {
	 try
	 {
	 return buildGraph(data);
	 }
	 catch(Exception e)
	 {return "None";}
 }
 
 public String searchBFS(String source, String destination, edge[][]graph, int noOfNodes, int initialCost )
 {
	// if(destination.contains(source))
	 //{
		// return source + " " + initialCost;
	 //}
	 
	 Queue<Integer> queue = new LinkedList<Integer>();
	 boolean [] visited = new boolean[noOfNodes];

	 int dcost [] = new int[noOfNodes];
	 Arrays.fill(dcost, Integer.MAX_VALUE);
	 
	 visited[nodeMap.get(source)] = true;
	 queue.add(nodeMap.get(source));
	 dcost[nodeMap.get(source)] = initialCost;
	 
	 String destinations[] = destination.split(" ");
	 int destLength = destinations.length;
	 String output = "";
	 
	 if(Arrays.asList(destinations).contains(source))
	 {
		 return source + " " + initialCost;
		 
	 }
	 
	 outerloop:while(!queue.isEmpty())
	 {
		 int current = queue.remove();		 
		 for(int i =0;i<noOfNodes;i++)
			 if(graph[current][i]!=null && graph[current][i].cost>0 && !visited[i])
			 {
				 dcost[i] = dcost[current]+1;
				 visited[i] = true;
				 queue.add(i);
				
				 for (int k = 0; k<destLength; k++)
				 {
						 if(i == nodeMap.get(destinations[k]))
						 { 
							 output = destinations[k] + " " + (dcost[i]%24);
							 break outerloop;
						 }
				 }
			 }
	 }
	 
	 if(output=="")
		 return "None";
	 return output;
 }
 
 public String buildDFSGraph(String[] data)
 {
	 try
	 {
	 return buildGraph(data);
	 }
	 catch(Exception e){return "None";}
 } 
 
 public String searchDFS(String source, String destination, edge[][]graph, int noOfNodes, int initialCost )
 {
	 //if(destination.contains(source))
	 //{
	//	 return source + " " + initialCost;
	 //}
	 
	 Stack<Integer> stack = new Stack<Integer>();
	 boolean [] visited = new boolean[noOfNodes];
	 visited[nodeMap.get(source)] = true;
	 stack.push(nodeMap.get(source));
	 
	 int dcost [] = new int[noOfNodes];
	 Arrays.fill(dcost, Integer.MAX_VALUE);
	 
	 dcost[nodeMap.get(source)] = initialCost;
	 
	 String destinations[] = destination.split(" ");
	 int destLength = destinations.length;
	 String output = "";
	 
	 if(Arrays.asList(destinations).contains(source))
	 {
		 return source + " " + initialCost;
		 
	 }
	 
	 outerDFSloop:while(!stack.isEmpty())
	 {
		 int current = stack.peek();
		 int i = 0;
		 while(i<noOfNodes)
		 {
			 if(graph[current][i]!=null && graph[current][i].cost>0 && !visited[i])
			 {
				 stack.push(i);
				 visited[i]=true;
				 dcost[i] = dcost[current]+1;
				 
				 for (int k = 0; k<destLength; k++)
				 {
					
						 if(i == nodeMap.get(destinations[k]))
						 { 
							 output = destinations[k] + " " + dcost[i]%24;
							 break outerDFSloop;
						 }
				 }
				 current = i;
				 i = -1;				 
			 }
		 i++;
		 }
			 stack.pop();
	 }	 
	 
	 if(output=="")
		 return "None";
	 return output;
 }
 
 public String buildGraph(String[] data)
 {	  
	 nodeMap = new HashMap<String, Integer>();
	 String temp = data[1] + " " + data[2] + " " + data[3];
	 String []arr = temp.split(" ");
	 Arrays.sort(arr);
	 String prev = arr[0];
	 int count = 0;
	 nodeMap.put(prev, count++);
	 
	 for(String s: arr)
	 {
		 if(!s.equals(prev))
			 {
			 	prev=s;
			 	nodeMap.put(s, count++);
			 }
	 }

	 int noOfNodes = count;
	 cityGraph = new edge[noOfNodes][noOfNodes];
	 int noOfEdges = Integer.parseInt(data[4]);
	 for (int i=1;i<=noOfEdges;i++)
	 {
		 String []edgeData = data[4+i].split(" ");
		 int source = nodeMap.get(edgeData[0]);
		 int dest = nodeMap.get(edgeData[1]);
		 edge newEdge = new edge();
		 newEdge.cost = 1;
		 cityGraph[source][dest]= newEdge;
	 }	
	 if(data[0].equals("BFS"))
	 {
		 return searchBFS(data[1], data[2], cityGraph, noOfNodes,Integer.parseInt(data[data.length-1])); 
	 }
	 else if(data[0].equals("DFS"))
	 {
		 return searchDFS(data[1], data[2], cityGraph, noOfNodes,Integer.parseInt(data[data.length-1]));
	 }
	 else return "None";
 }
 public String buildUCSGraph(String[] data)
 {
	 try
	 {
	 nodeMap = new HashMap<String, Integer>();
	 
	 String temp = data[1] + " " + data[2] + " " + data[3];
	 String []arr = temp.split(" ");
	 Arrays.sort(arr);
	 String prev = arr[0];
	 int count = 0;
	 nodeMap.put(prev, count++);
	 
	 for(String s: arr)
	 {
		 if(!s.equals(prev))
			 {
			 	prev=s;
			 	nodeMap.put(s, count++);
			 }
	 }
	 
	 int noOfNodes = count;//data[1].replaceAll("\\s","").length() + data[2].replaceAll("\\s","").length() + data[3].replaceAll("\\s","").length();
	 cityGraph = new edge[noOfNodes][noOfNodes];
	 String output = "";
	 int noOfEdges = Integer.parseInt(data[4]);
	 
	 for (int i=1;i<=noOfEdges;i++)
	 {
		 String edgeData[] = data[4+i].split(" ");
		
		 int source = nodeMap.get(edgeData[0]);
		 int dest = nodeMap.get(edgeData[1]);
		 edge newEdge = new edge();
		 newEdge.cost = Integer.parseInt(edgeData[2]);
		 newEdge.time = new boolean[24];
		 for(int j = 1; j<=Integer.parseInt(edgeData[3]); j++)
		 {
			 String timing[] = edgeData[3+j].split("-");
			 int start = Integer.parseInt(timing[0]);
			 int end = Integer.parseInt(timing[1]);
			 while(start<=end)
			 {
				 newEdge.time[start] = true;
				 start++;
			 }			 
		 }
		 cityGraph[source][dest]= newEdge;
	 }
	 
	 output = searchUCS(data[1], data[2], cityGraph, noOfNodes,Integer.parseInt(data[data.length-1]));
	 return output;
	 }
	 catch(Exception e){
		 return "None";
	 }
}

 public String searchUCS(String source, String destination, edge[][]graph, int noOfNodes, int initialCost)
{
	 //if(destination.contains(source))
	 //{
		// return source + " " + initialCost;
	 //}
	 PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(idcomparator);
	 boolean [] visited = new boolean[noOfNodes];
	 dcost = new int[noOfNodes];
	 Arrays.fill(dcost, Integer.MAX_VALUE);
	 
	 String output = "";
	 visited[nodeMap.get(source)]=true;
	 dcost[nodeMap.get(source)] = initialCost;
	 priorityQueue.add(nodeMap.get(source));
	 String destinations[] = destination.split(" ");
	 int destLength = destinations.length;
	 
	 if(Arrays.asList(destinations).contains(source))
	 {
		 return source + " " + initialCost;
		 
	 }
	 
	outerUCSloop:while(!priorityQueue.isEmpty())
	 {
		 int current = priorityQueue.remove();
		 	 
		 for (int k = 0; k<destLength; k++)
		 {
			
				 if(current == nodeMap.get(destinations[k]))
				 { 
					 output = destinations[k] + " " + dcost[current]%24;
					 break outerUCSloop;
				 }
		 }
		 visited[current] = true;

		 int dest = 0;
		 
		 while(dest<noOfNodes)
		 {
			 if(!visited[dest] && cityGraph[current][dest]!=null && !cityGraph[current][dest].time[dcost[current]%24])
				 if(dcost[dest]>cityGraph[current][dest].cost + dcost[current])
				 {
					 dcost[dest] = cityGraph[current][dest].cost + dcost[current];
					 if(priorityQueue.contains(dest))
						 priorityQueue.remove(dest);
					 
					 priorityQueue.add(dest);
				 } 
			 dest++;
		 }
	 }
	 dcost = null;
	 if(output=="")
		 return "None";
	 return output;
}
 public static Comparator<Integer> idcomparator = new Comparator<Integer>(){
	@Override
	public int compare(Integer o1, Integer o2) {
		if(dcost[o1]!=dcost[o2])
		return (int) (dcost[o1] - dcost[o2]);
		else
			return o1-o2;
	}
 };

}

class cityStructure
{
	public static ArrayList<String> readInputFile(String fileName)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = "";
			String fileData = "";
			ArrayList<String> data = new ArrayList<String>();
			int fsize = Integer.parseInt(br.readLine()) ;
			int  minNoOfLines = 5;
			int counter = 1;
			while(fsize>0)
			{
				while(counter<=minNoOfLines)
				{
						line = br.readLine().trim();
						fileData+=line + "\r\n";
						
						if(counter==5)
						{
							minNoOfLines+=Integer.parseInt(line)+1;
						}
						counter++;
				}
			line = br.readLine();
			data.add(fileData);
			fileData = "";
			--fsize;
			minNoOfLines = 5;
			counter = 1; 
			}
			br.close();
			return data;
		}
		catch(Exception e){return null;}		
	}
	public void buildCity(ArrayList<String> data)
	{
		String output = "";
		graph obj = new graph();
		for (String temp : data) {
			if(temp==null || temp=="")
				continue;
			String dataArray[] = temp.split("\\r?\\n");
			if(dataArray[0].equals("BFS"))
			{				
				output+=obj.buildBFSGraph(dataArray) + "\r\n";
				//System.out.println("Output is BFS: " + output);
			}
			else if(dataArray[0].equals("DFS"))
			{
				output+=obj.buildDFSGraph(dataArray) + "\r\n";
				//System.out.println("Output is DFS: " + output);
			}
			else if(dataArray[0].equals("UCS"))
			{
				output+=obj.buildUCSGraph(dataArray)+"\r\n";
				//System.out.println("Output is UCS: " + output);
			}
		}
		Writer writer = null;
		try
		{
			File file = new File("output.txt");
		    writer = new BufferedWriter((new OutputStreamWriter(new FileOutputStream(file))));
			writer.write(output);
			writer.close();
		}
		catch(Exception e){}
		
		System.out.println("Final Output is: " + "\n" + output);
	}
}
public class waterFlow {

	public int x =5;
	public static void main(String[] args) 
	{
		/*
	long startTime = System.currentTimeMillis();
	cityStructure myCity = new cityStructure();
	ArrayList<String> fileData = cityStructure.readInputFile("C:\\Users\\Nimesh\\workspace\\AI\\src\\gradingTestCases.txt");
	myCity.buildCity(fileData);		
	long endTime = System.currentTimeMillis();
	long totalTime = endTime - startTime;
	System.out.println("Nimesh" + totalTime);
	*/
		
		waterFlow obj = new waterFlow();
		System.out.println(obj.x);
		tp(obj);
		System.out.println(obj.x);

		
	}
public static void tp(waterFlow myobj)
{
	waterFlow myobj1 = new waterFlow();
	try {
		myobj1 =  (waterFlow)myobj.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	myobj1.x = 99;
}
	
}