## TP2 JAVA - Amirhossein Pouyanfar - 262575 Gr2 Mr Rémy Forax


# Exercise 1

1. On considère le code suivant :
```java
var s = "toto";
System.out.println(s.length());
```     
Quel est le type de s ? Comment le compilateur fait-il pour savoir qu'il existe une méthode length() sur s ? 

**Answer** : Using `var` we don't need to explicitly declare the type, the compiler 
will infer it by looking at what is used as the initializer of this
variable when declared.
By knowing the type of this variable, the compiler can verify if
there is a `length()` method defined in it's class.

Hence, here `s` is initialized with "toto" which is a string, so the 
compiler will know it's a String class object, on which there is a 
`length()` method defined.

2. Qu'affiche le code suivant ? Expliquer.
```java
var s1 = "toto";
var s2 = s1;
var s3 = new String(s1);

System.out.println(s1 == s2);
System.out.println(s1 == s3);
```     

**Answer** : In java, Object typed are handled by address.
`s1` is a literal string, because it's value starts with quotes ("").
literal strings are stored in a dictionnary at runtime.

This `s1 == s2` is verifying if `s1` and `s2` have the same address in
memory. the "==" does not indicate the value equality of `s1` and `s2`.
And this `s1 == s2` returns true because as we said, Object typed are 
handled by address, and this line `var s2 = s1;` doesn't create a new
object, it just  copies the address of `s1` in `s2`.

so, `System.out.println(s1 == s2);` prints true, because as we said `s1`
and `s2` have the same address.

However, `System.out.println(s1 == s3);` prints false, because `s3` is a new
object as it's created using the keyword `new`, hence it has a different address
in memory, and `s1 == s3` results false as `s1` and `s2 don't have the same value.

3. Quelle est la méthode à utiliser si l'on veut tester si le contenu des chaînes de caractères est le même ?
```java
var s4 = "toto";
var s5 = new String(s4);

System.out.println(/* comparer contenue de s4 et s5 */);
```      

**Answer** : The method `equals()` can be used to compare two strings, first by their have
length and then by their characters.
This method is a part of `java.lang.String` library, which is a part of Java Standard 
Library, and you don't need to import any specific package to use it.

4. Qu'affiche le code suivant ? Expliquer.
```java
var s6 = "toto";
var s7 = "toto";

System.out.println(s6 == s7);
```     

**Answer** : `s6` and `s7` are literal strings. As previously mentionned, literal strings are stored in a
dictionnary/cache at runtime. This implies that `s6` and `s7` have the same address in memory
(they reference the same object `"toto"` in memory), therefore `System.out.println(s6 == s7)`
will ultimately print true as `==` compares addresses when used with objects.

5. Expliquer pourquoi il est important que java.lang.String ne soit pas mutable. 

**Answer** : First of all, Java saves a lot of heap space because different string variables can refer
to the same thing in memory.
Secondly, if String class wasn't immutable it would cause several security threats to the
application. 
In addition, since a string is immutable, its hashcode is generated at the time of creation
and therefore it doesn't need to be recalculated again.
At last, the fact that the String Class is immutable, makes if safe for multi-
threading. A single instance can be shared across multiple threads.

6. Qu'affiche le code suivant ?
```java
var s8 = "hello";
s8.toUpperCase();
System.out.println(s8);
```     
Expliquer. 

**Answer** : As mentionned before, String class is immutable. That means, once created,
their value can't be modified. The method `toUpperCase()` doesn't 
change the object, but it returns a new instance of the String class, containing
the characters of `s8`, in capitals. Therefore, a call to `System.out.println(s8);`
will print "hello" and not "HELLO".
In order to print "HELLO", we can write :

```java
var s8 = "hello";
var s9 = s8.toUpperCase();
System.out.println(s9);
```

Here `s9` is a new instance of String class, storing the return value of `s8.toUpperCase()`.


# Exercise 2

Écrire une classe Morse qui permet, lors de son exécution, d'afficher les chaînes de caractères prises en argument séparées par des "Stop.".
```bash
      $ java Morse ceci est drole
      ceci Stop. est Stop. drole Stop.
```    


1. Utiliser dans un premier temps l'opérateur + qui permet la concaténation de chaînes de caractères. 

**Answer** : Here's a class `Morse`, printing the strings given as arguments, separated by `"Stop."` :

```java
public class Morse {
  public static void main (String[] args) {
    String res = join(args, " Stop. ");
    System.out.println(res);
  }

  public static String join(String[] array, String separator) {
    var res = array[0] + separator;
    for (int i = 1; i < array.length; i++) {
      res = res + array[i] + separator;
    }
      return res;
    }
}
```
The `join` method uses the `+` operator to concatenate the strings.

2. A quoi sert l'objet java.lang.StringBuilder ?
Pourquoi sa méthode append(String) renvoie-t-elle un objet de type `StringBuilder` ? 

**Answer** : The `java.lang.StringBuilder` object is an extensible buffer of characters,
allowing you to build a string without having too many intermediate string
allocation. 
The `append` method of this class (StringBuilder) returns a StringBuilder object
in order to allow for method chaining. This means it's possible to call multiple
`append` methods one after another. You might say that it would be possible to do
so if append returned a string instead of a StringBuilder object, but note that 
returning a StringBuilder object avoids creating a new string each time, because
the StringBuilder class is mutable, unlike String class. So when you write
`build.append(separator)`, the current StringBuilder object is modified, there's no
new object created.

3. Réécrire la classe Morse en utilisant un StringBuilder. 
Quel est l'avantage par rapport à la solution précédente ? 

**Answer** : Here's the `Morse` class, now using `StringBuilder`:

```java
public class Morse {
  public static void main (String[] args) {
    String res = join(args, " Stop. ");
    System.out.println(res);
  }

  public static String join(String[] array, String separator) {
    var build = new StringBuilder();
    var sep = "";
    for(var i : array) {
      build.append(sep).append(i);
      sep = separator;
    }
      build.append(sep);
      return build.toString();
    }
}    
```

The upside of this, compared to the previous version of `Morse` class, is 
probably it's performance. Using `StringBuilder`, you avoid creating useless
temporary string objects every time you iterate through the loop, this can
considerably increase the performance et reduce the memory use, when you are handling
massive quantities of data.  


4. Recopier le code suivant dans une classe de Test :
```java
public static void main(String[] args) {
  var first = args[0];
  var second = args[1];
  var last = args[2];
  System.out.println(first + ' ' + second + ' ' + last);
}
```
Pourquoi peut-on utiliser ' ' à la place de " " ?
Compiler le code puis utiliser la commande javap pour afficher le bytecode Java (qui n'est pas un assembleur) généré.
```bash
javap -c Test
```      

Que pouvez-vous en déduire ? 

**Answer** : Here it is :

```java
public class Test {
  public static void main(String[] args) {
    var first = args[0];
    var second = args[1];
    var last = args[2];
    System.out.println(first + ' ' + second + ' ' + last);
  }
}
```

In Java, single quotes (`' '`) are usually used for literal
characters, whereas double quotes (`" "`) are used for literal
strings. In the example above, a white space is a character(a `char`).
Here we've used single quotes so to treat these white spaces like characters.
If we used double characters, we would treat them like literal strings.

Based on the result of java bytecode resulting from `javap -c Test`,
we can see that the compiler uses `invokedynamic` to concatenate those
three strings (`first`,`second`,`last`), using `makeConcatWithConstants`.
This operation is optimized for String concatenation, in an efficient way,
regardless of the fact that they contain individual chars (`' '`) or strings
(`" "`). 
In summary, in the context of string concatenation in Java, we can use either
`""` or `''` to represent these spaces, and the compiler will treat them in the 
same way.

5. Compiler le code de la question 1, puis utiliser la commande javap pour afficher le bytecode Java généré.
Que pouvez-vous en déduire ?
Dans quel cas doit-on utiliser StringBuilder.append() plutôt que le + ?
Et pourquoi est-ce que le chargé de TD va me faire les gros yeux si j'écris un + dans un appel à la méthode append? 

**Answer** : If we use `javap -c` on question 1, we can see that, using 
StringBuilder, instead of `invokedynamic`, we'll have `invokevirtual`, that means, StringBuilder doesn't actually concatenate, those are virtual strings and as a consequence,
it would create less String objects in memory, which is somehow more efficient.

In these particular case we would prefer StringBuilder to `+` :
- String concatenation in a loop
- Complex strings construction
- If the code is used by multiple threads (as StringBuilder is thread-safe as we mentioned)


The TP responsible would look me in the eye bizarrely if I use
`+` in a `append` of StringBuilder, because, to use StringBuilder is to allocate less new strings, and `+` actually creates new strings, so it's unfavorable.

# Exercise 3

Le but de cet exercice est la manipulation d'expressions régulières en java. Nous utiliserons pour cela les classes du paquetage javautil.regex.


1. A quoi servent la classe java.util.regex.Pattern et sa méthode compile ?
A quoi sert la classe java.util.regex.Matcher ? 


**Answer** : `java.util.regex.Pattern` class and its method `compile` are used for following purposes :

- Pattern compilation: the `compile` method takes a regular expression as a string and tranforms
it into an internal representation that can be used for pattern matching operations.

- Pattern Matching : Once a regular expression is compiled to a pattern, it can be used perform
various pattern matching across a text, these operations include searching for matches, extracting
matched parts, and replacing the matched portions with other strings.

The `java.util.regex.Matcher` class has numerous useful methods that can be used in pettern matching,
such as :
- matches() -> to see if the whole text matches the given pattern
- lookingAt() -> to see if the beginning of the text matches the given pattern
- start() -> index of the character where that matched portion starts
- end() -> index of the character where that matched portion ends
etc.

2. Écrire un programme qui lit des chaînes de caractères sur la ligne de commande et affiche les chaînes qui correspondent à des nombres, c'est-à-dire les chaînes dont tous les caractères sont compris entre '0' et '9'. 

**Answer** : Here's a java program printing just numbers out of all arguments given in the command line :
```java
import java.util.regex.*;

public class Reg {
  public static void main(String[] args) {
    for(var a : args) {
      if(a.matches("\\d+")) {
        System.out.println(a);
        }
    }
  }  
}
```

3. Modifier le programme pour que l'on reconnaisse (et extrait) un nombre même dans le cas où le nombre est précédé par des caractères qui ne sont pas des chiffres.
Par exemple, les chaïnes "abc", "4de" et "f6h" ne sont pas valide car soit il n'y a pas de nombre soit elles ne finissent pas par un nombre. La chaïne "789" correspond au nombre 789 et la chaïne "ab3" correspond au nombre 3 (les caractères avant le nombre ne sont pas pris en compte).
Note: il y a plusieurs façons de faire, penser que matches() n'est pas la seule méthode qui existe sur un Matcher.
Attention: on ne veut pas créer plusieurs Pattern différents, ou plusieurs Matcher différents (il y a plus simple). 

**Answer** : Here's a Java program, finding and extracting between command line arguments, even if these
numbers are preceded by non-digit characters. 
```java
import java.util.regex.*;

public class Reg {
  public static void main (String[] args) {
    // Compiling the regular expression
    // Note : $ is for having just those
    // who finish by a number...
    var pattern = Pattern.compile("\\d+$");
    // Defining a matcher
    var matcher = pattern.matcher(args[0]);
    // Let's loop through the arguments...
    for(var a : args) {
      matcher = pattern.matcher(a);
        while (matcher.find()) {
          String matchedNum = matcher.group();
            System.out.println(matchedNum);
      }
    }
  }    
}
```

4. Écrire une méthode qui prend en paramètre une chaîne de caractères contenant une adresse IPv4 et renvoie un tableau de 4 bytes. Il faut tester qu'il s'agit bien d'une adresse valide. Vous utiliserez pour cela la notion de groupe.



**Answer** : Here's a Java method receiving an ipv4 IP address as argument and returns the correponding 4 element
byte array.

```java
public static byte[] ipParser(String ipAddress) {
  var pattern  = Pattern.compile("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}");
  var matcher = pattern.matcher(ipAddress);
  // Define a byte array having 4 elements
  byte[] ipArray = new byte[4];
  if(matcher.matches()) {
    // Split the IP address into 4 portions
    String[] portions = matcher.group().split("\\.");
    for(int i = 0; i < 4; ++i) {
      int digit = Integer.parseInt(portions[i]);
      if(digit < 0 || digit > 255) {
        throw new IllegalArgumentException("This is not a valid ipv4");  
      }
      // Type cast the integer into a byte 
      ipArray[i] = (byte) digit;

    }
    } else {
        throw new IllegalArgumentException("ipv4 not found");
    }
    return ipArray;  
}
```

A valid ipv4 IP address is in this form : `xxx.xxx.xxx.xxx` ,
in which each `xxx` is a number between 0 and 255.

Now, if we need to print this arrays of bytes, we must make sure not 
to have any negative value in the printed bytes. To achieve this, we'll use 
**binary mask**, in this case,  **`0xFF`** to ensure all the values are positive.







