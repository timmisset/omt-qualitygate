## OMT Quality Gate
The OMT Quality Gate Sonarqube plugin adds the OMT Language to Sonarqube along with an "OMT Rules" Quality Profile.

### OMT
The OMT Language is an extension of YAML (with a strict model) and is treated as such.
OMT files are parsed using SnakeYAML compose to retrieve node information from the YAML structure.
This node information contains fundamentals such as types (map, sequence, scalar), location, values etc.

Using the OMTElement parsing routine, the OMT File is processed and Java classes are instantiated for
each corresponding element.
Scalar such as strings can be extended to be made more specific in order to recognize them or to implement certain rules

### Validation
To challenge an OMT file with the registered checks an ElementVisitor will recursively visit all elements in the
OMT tree. Context is important in many validations, so it's also important to determine at which level you want to
run the validation. For example, to determine if a variable is used the context is the ModelItem (!Activity, !Component) where it
is declared. Therefore, a ModelItemVisitor extends the ElementVisitor and will call visit every ModelItem in the OMT File.
Within this scope the TreeUtil can be used to obtain all DeclaredVariable instances (which will return variables, params and bindings).
Then the usage of the variable (currently, simple by checking the presence of the name) is determined in any structure part of the ModelItem which
excluding the DeclaredVariable itself.

The design is to have a specific visitor per OMT element which can validate 1 or more rules.

### Build plugin
Use mvn clean package to generate the jar. Copy the jar to the /extensions/plugin on the Sonarqube server

### Test local
To test the plugin locally, use the https://bitbucket.ontwikkel.local/projects/OPP/repos/opp-docker/browse/sonarqube repo
Use the remove/start routine when you want to deploy a new jar.

