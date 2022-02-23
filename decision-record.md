# Architecture Decision record

**Date:** 23 Feb. 2022

# Replace Spring oAuth 2.0 client 

## Status (proposed, accepted, rejected, deprecated, superseded, etc.?)

**proposed**

## Decision
Based on comparaison in the [Discussion](#discussion) section, **ScribeJava** will be used to replace current oAuth2.0 client.

## Context
Use Spring oAuth2.0 client for authentication, bring many related dependencies to the project, which increase final jar files of the project, and more important, it increases being impacted by a security vulnerability.

So it's preferable to replace spring-security-oauth2 with a lighter framework.

The choosen framework need to be:
+ Open Source.
+ Can be used for commercial project.
+ Light (minimum related dependencies).
+ Still active.
+ Higher Stars (if on GitHub/GitLab).
+ Higher Code Coverage.
+ Documentation available (tagged in Stackoverflow is a plus). 

## Discussion
Two libraries was selected for comparaison: [oauth2-essentials](https://github.com/dmfs/oauth2-essentials) and [ScribeJava](https://github.com/scribejava/scribejava).

|                                        | **oauth2-essentials** |  **ScribeJava**  |
|:--------------------------------------:|:---------------------:|:----------------:|
|            **Open Source**             |          Yes          |       Yes        |
| **Can be used for commercial project** |          Yes          |       Yes        |
|          **Total dependencies**          |          13           |        5         |
|              **Last version**              |  0.18 - Sep 20, 2019  | 8.3.1 - May 2021 |
|              **GitHub Stars**              |          64           |       5200       |
|             **Code Coverage**              |      Unavailable      |   Unavailable    |
|        **Documentation available**         |          Yes          |       Yes        |
|      **Active tag on StackOverflow**       |          No           |       Yes        |


## Consequences

We just need to check if the authentication is still working correctly with the chosen library, and this modification may require some abstraction by pushing the oAuth2.0 in its own class and making both current and future change less risky. 

## Reference
https://oauth.net/code/java/

https://github.com/scribejava/scribejava
https://mvnrepository.com/artifact/com.github.scribejava/scribejava-core/8.3.1
https://stackoverflow.com/questions/tagged/scribe

https://github.com/dmfs/oauth2-essentials
https://mvnrepository.com/artifact/org.dmfs/oauth2-essentials/0.18