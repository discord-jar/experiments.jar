# DiscordExperiments
DiscordExperiments is a tool that decodes the information from the experiements endpoint of the Discord API automatically and turns it into Java objects.

## Usage
When using the libraru, I'd appreciate if you could leave a piece of credit to this repositiory somewhere on your site/application!

## Documentation
Here's a simple guide on using this library:

- Call the `DiscordExperiments#getExperiments` method.

```java
import com.seailz.experiments.DiscordExperiments;

...
List<GuildExperiment> exp = DiscordExperiments.getExperiments();
```
and that's it!


# REST API
Because I know not everyone uses Java, I'm also offering a REST API that allows you to retrieve simplified JSON data instead of the complicated JSON data you'd normally get from `https://discord.com/api/v10/experiments?with_guild_experiments=true`.
Here's some more info:
**COMING SOON**
