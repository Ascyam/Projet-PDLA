# Projet-PDLA
**A student project app to help people.**

In order to use the app, you need to follow a few steps:

1/ Open a terminal on your computer and clone the repository wherever you want by using the command:
```
git clone https://github.com/Ascyam/Projet-PDLA.git
``` 

2/ Then open the directory which contains pom.xml with your mouse and right click to open a terminal or use:
```
cd Projet-PDLA/pdla
```

3/ After, you just have to copy and paste the following commands so that you install maven on the computer:
```
mkdir -p ~/bin  # create a bin/ directory in your home
cd ~/bin  # jump to it
wget https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz -O maven.tar.gz  # download maven
tar xf maven.tar.gz  # decompress it
echo 'export PATH=~/bin/apache-maven-3.9.5/bin:$PATH' >> ~/.bashrc  # add mvn's directory to the PATH
source ~/.bashrc  # reload terminal configuration
```

4/ In order to verify that all went well, use: 
```
mvn --version
```

5/ Execute: 
```
mvn compile
```
and: 
```
mvn clean package
```

6/ Finally, use the following command to execute: 
```
mvn exec:java -Dexec.mainClass="pdla.Main"
``` 

