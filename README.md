# Typesafe config extension

## Usage:

reference.conf:
```conf
environment=dev

server.url="http://server.${environment}.example.com" # IMPORTANT: ${} is *inside* string quotes
```

application.conf:
```
environment=prod
```

Config.scala
```
import com.yetu.typesafeconfigextentension.ConfigExtension

object Config extends ConfigExtension {
  val config = ConfigFactory.load().substitutePropertyValues("environment") // need to specify explicitly which substitutions are desired

  val myvariable  = config.getString("server.url") // will resolve to "http://server.prod.example.com"
}

```

## How to publish updates of this library to bintray

After making changes, push all commits, then release to bintray with:

```
# make sure to use java 7, not java 8 for publishing, for the time being.
export JAVA_HOME=`/usr/libexec/java_home -v 1.7`
java -version # this must give you java 7 now, otherwise please install it
sbt "cross release with-defaults"
```

## Motivation

The typesafe config has one drawback, which is that if you have:

reference.conf:
```
environment=dev

server.url="http://server."${environment}".example.com"
```

then this substitution works fine within that file, but if you wish to have

application.conf:
```
environment=prod
```

then the `server.url` is **not** updated to `http://server.prod.example.com`

This library tries to circumvent this behaviour and substitutes a `${variable.tosubstitute}` across different conf files, like application.conf and reference.conf, but only for strings, and only if it finds the exact `${variable.tosubstitute}` within a string.


