
# Shopping Simulator Application

![Static Badge](https://img.shields.io/badge/Build-passing-flat)
[![Static Badge](https://img.shields.io/badge/Coverage-99%25-flat)](https://jacobnatural.github.io/shopping-simulator/jacoco/index.html)
[![Static Badge](https://img.shields.io/badge/docs-blue)](https://jacobnatural.github.io/shopping-simulator/apidocs/index.html)
## Overview
This application simulates customer shopping behavior based on their product preferences and 
available financial resources. Customers select products according to predefined categories, 
and their choices are constrained by the amount of money they have. The program uses advanced 
object-oriented principles, maps, and collections to track customer purchases, analyze preferences, 
and generate reports.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 22+**
- **Apache Maven 3.9.6+**

### Example Files
The example input files are included in the repository and will be automatically 
placed in the target directory alongside the JAR file during the Maven build lifecycle. 
You can replace or modify these files according to your needs. 
These files represent the input data required by the application:
- `clients.txt`: Contains client details with preferences.
- `preferences.txt`: Maps preference codes to product categories.
- `products.txt`: Lists available products with quantities and prices.   

Sample data within these files is formatted as follows:
- `clients.txt`:
```text
# The format is: <id>;<name>;<surname>;<age>;<balance>;<preferences>:<preferences>...
1;JAN;KOWAL;20;1000;2:1:4
2;SEBASTIAN;ZIOBRO;25;200;1:2:3
3;MATEUSZ;KOWALSKI;34;2300;3:4:5
```

- `preferences.txt`:
```text
# The format is: <id>;<category name>
1;GROCERIES
2;HOME_APPLIANCES
3;ELECTRONICS
```

- `products.txt`
```text
# The format is: <id>;<product name>;<amount>;<price>;<product category>

1;APPLES;2;2.55;GROCERIES
8;VACUUM_CLEANER;1;350;HOME_APPLIANCES
12;LAPTOP;1;7000;ELECTRONICS
```

### Cloning the repository:
- To clone the repository and navigate into the project directory, run:

```bash
git clone https://github.com/JacobNatural/shopping-simulator.git
cd shopping-simulator
```


### Running the application:
- To build the application and run it:
```bash
mvn clean package -DskipTests
cd target  
java --enable-preview -jar shopping-simulator-1.0.jar
```

### Running Tests
- To execute the tests, use the following command:
```bash
mvn clean test
```


### Integrate into Your Project
- If you want to use this application as a dependency in another Maven project,
  first install it into your local Maven repository by running:
```bash
mvn clean install -DskipTests
```
- Then, you can add the following dependency to your pom.xml file:

```xml
<dependency>
    <groupId>com.app</groupId>
    <artifactId>shopping-simulator</artifactId>
    <version>1.0</version>
</dependency>
```


## Contributing

We welcome contributions to improve the Shopping Simulator Application. Here's how you can contribute:

1. Fork the repository on GitHub.
2. Make enhancements or fix issues in your forked copy.
3. Submit a pull request to merge your changes into the main repository.

Please ensure your code adheres to our coding standards and is thoroughly tested before submitting a pull request.

