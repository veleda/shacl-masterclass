## Exercises
The following are exercises regarding the masterclass on SHACL validation at [Knowledge Connexions 2020](https://www.knowledgeconnexions.world/talks/validating-semantic-knowledge-graphs-using-shacl/) by Veronika Heimsbakk.

### 1 Complete the shapes
Using the file **data.ttl** (*src/main/resources/data.ttl*), complete the shapes described in **shapes.ttl** (*src/main/resources/shapes.ttl*).
Then use either the RDF4J SHACL engine or [SHACL Playground](https://shacl.org/playground/) to ensure that your validation conforms true.

### 2 Extend the shapes graph
Pick the data graph of your choice from the *folder src/main/resources/exercises/2*.
Copy-paste the data into data.ttl and work with the data graph as a whole.

1. Extend the shapes graph in *src/main/resources/shapes.ttl* in order for the data to conform to these shapes.
2. Test your shapes using the RDF4J SHACL engine or SHACL Playground.

*TIP*: You might have to use logical constraint components and NodeShape inheritance. 

You could extend the shapes graphs to support as many of the data graphs in this folder as you like.

### 3 Add new data
Create a data graph containing a new instance of your classes described. 

#### Example:
```
:LOTR a :Book ;
  :title "The Lord of the Rings" ;
  :release "1954-07-29"^^xsd:date ;
  :author :JRRTolkien .
```

### If you still have time
If there are any relationships missing that you want to add, feel free!
Examples of relationships missing (that could be useful) is ISBN, editor, publisher etc.