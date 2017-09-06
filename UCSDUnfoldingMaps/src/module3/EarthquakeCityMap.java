package module3;

import java.security.PublicKey;
//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.jogamp.opengl.math.geom.Frustum.Location;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	private Object bigEqs;

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			//map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.RoadProvider() {
				
				
			});
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    if (earthquakes.size() > 0) {
	    	PointFeature f = earthquakes.get(0);
	    	System.out.println(f.getProperties());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	// PointFeatures also have a getLocation method
	    }
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    
	    //TODO: Add code here as appropriate
	    
	    for(PointFeature eq: earthquakes)
	    {
	    	markers.add(createMarker(eq));
	    	map.addMarkers(markers);
	    }
	    
	    
	    /*de.fhpotsdam.unfolding.geo.Location valLoc=new de.fhpotsdam.unfolding.geo.Location(-38.14f,-73.03f);
	    Feature valEq=new PointFeature(valLoc);
	    valEq.addProperty("title", "Valdivia, Chile");
	    valEq.addProperty("magnitude", "9.5");
	    valEq.addProperty("date", "may 22,1960");
	    valEq.addProperty("year", "1960");
	    
	    
	    de.fhpotsdam.unfolding.geo.Location valAlaska=new de.fhpotsdam.unfolding.geo.Location(61.02f,-147.65f);
	    Feature alaskaEq=new PointFeature(valAlaska);
	    alaskaEq.addProperty("title", "Alaska");
	    alaskaEq.addProperty("magnitude", "9.2");
	    alaskaEq.addProperty("date", "march 23,2003");
	    alaskaEq.addProperty("year", "2003");
	    
	    List<PointFeature> bigEqs=new ArrayList<PointFeature>();
	    
	    bigEqs.add((PointFeature) valEq);
	    bigEqs.add((PointFeature) alaskaEq);
	    
	    List<Marker> markers2=new ArrayList<Marker>();
	    for(PointFeature eq: bigEqs) {
	    	markers2.add(new SimplePointMarker(eq.getLocation(),eq.getProperties()));
	    	
	    }
	    map.addMarkers(markers2);
	    
	    int yellow1=color(255,255,0);
	    int gray=color(150,150,150);
	    
	    for(Marker mk: markers2) {
	    	if((int) mk.getProperty("year")>2000)
	    	{
	    		mk.setColor(yellow1);
	    	}
	    	else {
	    		mk.setColor(gray);
	    	}
	    }*/
	   
	    
	    
	    
	    
	    
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature)
	{
		int yellow = color(255, 255, 0);
	    int red=color(255,0,0);
	    int blue=color(0,0,255);
		// finish implementing and use this method, if it helps.
		if((float)feature.getProperty("magnitude")<4.0)
		{
			//createMarker(feature);
			SimplePointMarker marker=new SimplePointMarker(feature.getLocation());
			marker.setColor(blue);
			marker.setRadius(7.0f);
			return marker;
			
		}
		else if ((float)feature.getProperty("magnitude")>=4.0 && (float)feature.getProperty("magnitude")<=4.9) {
			
			SimplePointMarker marker=new SimplePointMarker(feature.getLocation());
			marker.setColor(yellow);
			marker.setRadius(10.0f);
			return marker;
		}
		
		else {
			SimplePointMarker marker=new SimplePointMarker(feature.getLocation());
			marker.setColor(red);
			marker.setRadius(13.0f);
			return marker;
		}
		//return new SimplePointMarker(feature.getLocation());
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		fill(255,255,255);
		rect(10, 50, 180, 350);
		fill(0,0,0);
		//rect(10, 50, 180, 350);
		
		textSize(20);
		text("Earthquake Key", 20, 70);
		
		fill(255,0,0);
		ellipse(25, 100, 15, 15);
		fill(0);
		textSize(13);
		text("5.0+ Magnitude", 50, 102);
		
		fill(255,209,0);
		ellipse(25,140, 12, 12);
		fill(0);
		text("4.0+ Magnitude", 50, 142);
		
		fill(0,0,255);
		ellipse(25,180, 8, 8);
		fill(0);
		text("Below 4.0", 50, 182);
		
		
		
		// Remember you can use Processing's graphics methods here
	
	}
}
