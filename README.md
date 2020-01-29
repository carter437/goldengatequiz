Word Count using Scala Repl

`prompt> scala`

```
scala> scala.io.Source.fromFile("lorem.txt").getLines
         .flatMap(_.split(" ")).toList
         .map(_.replaceAll("""\W""","")) //remove punctuation
         .filterNot(_.isBlank)
         .groupBy(identity)
         .mapValues(_.size).toList
         .map{case (k,v) => (v,k)}
         .sortBy(_._2)
```

