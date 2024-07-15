package Factory;

import java.util.HashMap;

import Server.Configurations;
import algorithms.Product;
public class Factory {
    private static final HashMap<String, ICreator> collection = new HashMap<>();
    private static Factory factoryForUSR;

    private Factory() {
        collection.put("EmptyMazeGenerator", new EmptyMazeCreator());
        collection.put("MyMazeGenerator", new MyMazeCreator());
        collection.put("SimpleMazeGenerator", new SimpleMazeCreator());
        collection.put("BestFirstSearch", new BestCreator());
        collection.put("BreadthFirstSearch", new BFSCreator());
        collection.put("DepthFirstSearch", new DFSCreator());
    }

    public static Factory getInstance(){
        if (factoryForUSR == null){
            factoryForUSR = new Factory();
        }
        return factoryForUSR;
    }


    public Product create(String askedObj){
        if(collection.get(askedObj) != null){
            return collection.get(askedObj).create();
        }
        return null;
    }

}
