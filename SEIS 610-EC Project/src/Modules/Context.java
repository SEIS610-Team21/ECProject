package Modules;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Context {
	private static Context myInstance;
	Document myDocument;
	public int populationSize;
	public int treeDepth;
	public int degree;
	public int survivalThreshold;//Percentage value
	public int trainingDataFrom;
	public int trainingDataTo;
	public int trainingDataCount;
	public double mutationProbability;
	public double crossoverProbability;
	public int maxIterations;


	private Context() {
		loadConfig();
	}

	public static Context getInstance() {
		if (myInstance == null)
			myInstance = new Context();
		return myInstance;
	}

	public void loadConfig() {
		File fXmlFile = new File("./res/Config/config.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			myDocument = dBuilder.parse(fXmlFile);
			myDocument.getDocumentElement().normalize();

			NodeList nList = myDocument.getElementsByTagName("Config");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					this.populationSize = Integer
							.parseInt((eElement.getElementsByTagName("PopulationSize").item(0).getTextContent()));
					this.treeDepth = Integer
							.parseInt((eElement.getElementsByTagName("TreeDepth").item(0).getTextContent()));
					this.degree = Integer
							.parseInt((eElement.getElementsByTagName("PolyDegree").item(0).getTextContent()));
					this.survivalThreshold = Integer
							.parseInt((eElement.getElementsByTagName("SurvivalThreshold").item(0).getTextContent()));
					this.trainingDataFrom = Integer
							.parseInt((eElement.getElementsByTagName("TrainingDataFrom").item(0).getTextContent()));
					this.trainingDataTo = Integer
							.parseInt((eElement.getElementsByTagName("TrainingDataTo").item(0).getTextContent()));
					this.trainingDataCount = Integer
							.parseInt((eElement.getElementsByTagName("TrainingDataCount").item(0).getTextContent()));
					this.mutationProbability = Double
							.parseDouble((eElement.getElementsByTagName("MutationProbability").item(0).getTextContent()));
					this.crossoverProbability = Double
							.parseDouble((eElement.getElementsByTagName("CrossoverProbability").item(0).getTextContent()));
					this.maxIterations=Integer
							.parseInt((eElement.getElementsByTagName("MaximumIterations").item(0).getTextContent()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveConfig() throws TransformerFactoryConfigurationError, TransformerException
	{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Result output = new StreamResult(new File("./res/Config/config.xml"));
		Source input = new DOMSource(myDocument);

		transformer.transform(input, output);
	}
}
