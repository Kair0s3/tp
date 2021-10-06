### Library Imports Order

All the related imports should be grouped together, and each of these grouped imports,
should be separated with a line spacing.

At the moment, the order of our import groupings should be as follows
(You may add on if some different type of imports are introduced)

1. Java Utils imports
2. Decodex imports
3. JUnit imports (Only applicable for Test)

But you might be thinking, but there's so many Decodex.* imports.
For e.g. `import decodex.modules.BaseModule;` and `import decodex.data.Data;`

For this case, we sort them in lexicographical (alphabetical) order, `decodex.data.*`
should come before `decodex.modules.*` since 'd' in data < 'm' in modules.

You should do this for all other modules that you intend to import.

An example of the imports order and grouping is shown below

```
package...;

// Note that Arrays comes before Scanner.
import java.util.Arrays;
import java.util.Scanner;

// Showing the lexicographical order for 
// the class name and package name.
// eg. Base64Decoder < Base64.Encoder
// eg. base64 < hex 
import decodex.modules.base64.Base64Decoder;
import decodex.modules.base64.Base64Encoder;
import decodex.modules.hex.HexEncoder;
...
...

public class RandomClass {
```

> Note that the package line, we can just leave it as default on line 1.
> 
> Also note that, inline comments are just there explain the case scenario for the order of imports
> , it does not signify a spacing line break.


### Access Modifiers Order

The order for the variables in terms of Access Modifiers is as follows

1. Public
2. Private
3. Protected (If we intend to use, but at the moment just the first 2)

The variables with the same access modifiers should be grouped together,
unless you intend to provide a comment to explain the reasons for such variables.

The syntax we should follow is

`<Access Modifier> <static> <final> variableName;`

An example of the ordering and their grouping is as follows

````
...
...

public class RandomClass() {

    public publicVar1;
    public publicVar2;
    
    public final PUBLICFINAL;
    
    public static publicStatic1;
    
    /**
    * Sample comment to explain what this variable does.
    * When that happens, leave one line spacing before the comment.
    **/
    public static publicStatic2;
    
    public static final PUBLICSTATICFINAL;
    
    private ... (You get the idea)
    
````