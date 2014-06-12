# UMLToPHP Generator

A php sources class/interface generator from UML Models in XMI.
It has been written using the great Acceleo eclipse plugin.

## Features
- Generate classes and interfaces skeletons from any UML Model in XMI (made with Papyrus or PlantUML for example).
- Make sure the handwritten code inside generated classes methods is not erased after each generation (thanks to Acceleo engine).
- Generate PSR0 Compatible class name and file structure.
- Map UML class packages as php namespaces.
- Convert well multiplicities of classifier properties like "\*..n" to php arrays.
- Handle UML dependencies, generalisations, associations, realisation and interface realisations.
- Handle class/interface templates using some design pattern that mimic the use of generics / templates in PHP.
- Map primitive datatypes with PHP equivalents.
- Can optionnaly generate PHPUnit units tests skeletons.
- Ready to be used as a Standalone Java command line app.
- Can be included inside eclipse as an Acceleo Projet.
- All the benefits of an Acceleo generator.

## Build (using Maven)
Make sure a java runtime and Maven >= 3+ is installed in your system. 
Clone this repository somewhere in your drive and launch the compilation using Maven : 

```bash
mkdir /destpath
cd /destpath
git clone https://github.com/TiBeN/UmlToPhpGenerator ./
mvn clean package
```

## Usage
To launch the generator, go to the folder where you cloned the github repository then type:
```bash
$ cd /repo-folder
$ mvn exec:java -Dumltophp.model="/path/to/your/model/file.uml" -Dumltophp.out="/out" 
```
where `/path/to/your/model/file.uml` is the path to your dot uml model file made with some eclipse EMF compatible tool like Papyrus and `/out` is the output path where the PHP classes will be generated. 

For the generator handle your UML model correctly, you should consider some rules :
- Place all your model components inside a root Package named "Model" in your UML model. (this is a temporary limitation, see TODO.md)
- The use of UML profiles has not been well tested, specially using the stand alone generator. So handling of models with profiles is not guaranteed. 
- Despite some special stereotypes names (see documentation when it will be written), stereotypes applied to classifiers have no meanings nor effects on the generation.

Once the generation is done. You can edit the generated classes inside some markers :
```php
// Start of user code of SomeClass.someMethod
 Place your code here
//
```
Edit only your classes inside theses marker and don't remove them! 
They are used by the generator to update yours classes/interfaces according to the changes made in the model without erase your handwritten code. 
If you change the name of yours class methods in your model or remove some of thems, don't worry, the generator will backup the lost code inside some txt file. 
Theses behavior are made possible thanks to the Acceleo nature of project. For more information about the global behavior of the generator, please take a look at [The Acceleo Project](http://www.eclipse.org/acceleo/)

## Use As an Eclipse plugin
Once Acceleo plugin installed inside eclipse, create a new Acceleo project then simply import the .mtl sources files available at `src/main/java/umltophp/main/` of this project. Please refer to Acceleo documentation for more information. 
Note : This use case could be improved (see TODO.md)

## Some notes about this project.
For the sake of the design of a web Framework i started to make some diagrams for modeling the components of the framework by using Eclipse Papyrus. I read at the same time a lot of articles about software design and especially on MDA/MDE methodologies. The case of this web framework was a great "pretext" to go a bit further into my discovery of the MDE design. 

So I've started to integrate the auto generation ("model to text") of class skeletons of this project into my workflow. For this purpose the eclipse EMF platform hosts a very good plugin named "Acceleo". The aim is to orchestrate the generation of formated text file (code, documentation, sql statements and so on..) from any ecore compatible models. The generation is done using templates written in "MOF2Text" language wich is an OMG language specification. 

The initial complexity of the eclipse modeling tools and environnment is a little bit frustrating but, once this gap passed, the workflow is very effective. 
I started to write some simple acceleo template in order to convert my framework's UML model into PHP classes. As my initial project evolve i encountered a lot of different cases to handle like generating interfaces, class extends, interface implementation, template typed classes, multiplicity and so on, making the generator more and more generic and become usable for differents kinds of projects. 

So, despite some issues about the build, the lack of documentation and the need of some minor improvements to be used in production i decided to share it on github.
