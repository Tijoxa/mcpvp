# mcpvp

## setup server
[CraftBukkit](https://www.9minecraft.net/craftbukkit/#CraftBukkit_Minecraft_121_1201_Download_Links)

### utils
find /mnt/c/path-to-folder -type f -name "*:Zone.Identifier" -delete

## setup dev
[Java 8](https://repo.huaweicloud.com/java/jdk/8u202-b08/)

sudo tar -xf jdk-8u202-linux-x64.tar.gz -C /opt

added that to my bashrc
export JAVA_PATH=/opt/jdk1.8.0_202
export PATH=$JAVA_HOME/bin:$PATH

sudo update-alternatives --install /usr/bin/java java /opt/jdk1.8.0_202/bin/java 1
sudo update-alternatives --config java


## notes
`mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=mc -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false`

```XML
<repositories>
            <!-- This adds the Spigot Maven repository to the build -->
            <repository>
                    <id>spigot-repo</id>
                    <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
            </repository>
    </repositories>

    <properties>
            <maven.compiler.source>1.8</maven.compiler.source>
            <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencies>
            <!--This adds the Spigot API artifact to the build -->
            <dependency>
                    <groupId>org.spigotmc</groupId>
                    <artifactId>spigot-api</artifactId>
                    <version>1.19.3-R0.1-SNAPSHOT</version>
                    <scope>provided</scope>
            </dependency>
    </dependencies>
```

`mvn package`

`java -cp target/mc-1.0-SNAPSHOT.jar com.mycompany.app.App`

