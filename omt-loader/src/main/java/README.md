### OMT-LOADER

The OMT-LOADER module is responsible for translating the yaml structure into a Java representation.

### MODEL

The OMT model is parsed by consuming all Yaml nodes and returning specific OMTElement classes in return.
The OMTFile itself also inherits from OMTElement.

The model is structured by the same 3 qualifiers as the Yaml nodes:

- maps
- sequences
- scalars

#### Maps
Maps can be strict or lenient.

##### Strict maps
Strict maps have a pre-defined set of properties that it expects with specific
value types for each property. Any key that is not matched will return in a null value

##### Lenient maps
Lenient maps will return 1 fixed type for any key that is found

#### Sequences
Sequences behave similar to Lenient maps in that they always return the same type and are not 
confined to the number of elements they contain

#### Scalars
Scalars do not contain any children themselves. The AbstractScalar should be extended to specific classes
on places where specific validations are required. This way, it is easy to write a specific visitor for that class
or to find only those elements using the TreeUtil::findChildren;

For advanced lookups in the structure, use the TreeUtil.
