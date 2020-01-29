Word Count using Scala Repl

`prompt> scala`

```
scala> scala.io.Source.fromFile("lorem.txt").getLines
       .flatMap(_.split(" ")).toList.groupBy(identity)
       .mapValues(_.size).toList
       .sortBy(_._2)
```

