# DiscordExperiments
DiscordExperiments is a tool that decodes the information from the experiements endpoint of the Discord API automatically and turns it into Java objects.

## Usage
When using the library, I'd appreciate if you could leave a piece of credit to this repositiory somewhere on your site/application!

## Documentation
### Maven
Repository:
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Dependency:
```xml
<dependency>
  <groupId>com.github.seailz</groupId>
  <artifactId>discordexperiments</artifactId>
  <version>1.0</version>
</dependency>
```

### Gradle
Repository:
```gradle
allprojects {
  repositories {
	maven { url 'https://jitpack.io' }
	}
}
```

Dependency:
```gradle
dependencies {
  implementation 'com.github.seailz:discordexperiments:1.0'
}
```  

Here's a simple guide on using this library:

- Call the `DiscordExperiments#getExperiments` method.

```java
import com.seailz.experiments.DiscordExperiments;

...
List<GuildExperiment> exp = DiscordExperiments.getExperiments();
```
and that's it!


# REST API
Because I know not everyone uses Java, I'm also offering a REST API that allows you to retrieve simplified JSON data instead of the complicated JSON data you'd normally get from `https://discord.com/api/v10/experiments?with_guild_experiments=true`. Please [click here](https://seailz.notion.site/Discord-Experiments-99ce72247ad741e88bfc85307bf79a2a) for more info!
