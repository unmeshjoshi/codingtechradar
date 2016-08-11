# Creating HelloWorld handler in JAVA for AWS Lambda

This is getting started with AWS Lambda, by creating a simple hello world function, which inserts a row in DynamoDB.
To create a Hello World Lambda function follow the steps mentioned here ->
http://docs.aws.amazon.com/lambda/latest/dg/get-started-create-function.html

## Creating and packaging handler in Java:
At the time you create a Lambda function you specify a handler that AWS Lambda can invoke when the service executes the Lambda function on your behalf.

The general syntax for the handler is as follows:
```sh
outputType handler-name(inputType input, Context context) {
...
}
```

In order for AWS Lambda to successfully invoke a handler it must be invoked with input data that can be serialized into the data type of the input parameter.

For example, consider the following Java example code.

```sh
package example;
import com.amazonaws.services.lambda.runtime.Context; 

public class Hello {
    public String myHandler(int myCount, Context context) {
        return String.valueOf(myCount);
    }
}
```


### Packaging
We need to create a complete package(Fat Jar) with all dependencies present in it. For that we will use
Apache maven-shade-plugin which packages jars to create a standalone .jar (a .zip file), your deployment package.

You can add the shade plugin like this :
```sh
...
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>2.3</version>
            <configuration>
                <createDependencyReducedPom>false</createDependencyReducedPom>
            </configuration>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
...
```
After this you can do : 
```
$ "mvn package" 
```
This will build the fat jar.


This jar can then be deployed on AWS lambda(Given in the getting started guide for AWS Lambda).
