import scala.io.Source

object calculator extends App {

  val lines = Source.fromFile("instructions.txt").getLines.toSeq
  val commands = lines.map {
    case line if line startsWith ("#") =>
      Comment(line)
    case line if line.split("\\s+").length == 2 => line match {
      case line if line startsWith ("+") =>
        val Array(_, x) = line.split("\\s+")
        Add(x.toInt)
      case line if line startsWith ("-") =>
        val Array(_, x) = line.split("\\s+")
        Subtract(x.toInt)
      case line if line startsWith ("*") =>
        val Array(_, x) = line.split("\\s+")
        Multiply(x.toInt)
      case line if line startsWith ("/") =>
        val Array(_, x) = line.split("\\s+")
        Divide(x.toInt)
    }
    case line => Ignore(line)
  }

  commands.foreach(println)

//  object Command{
//    val acceptedCommands = Map{
//        "+" -> (Add.apply _ ),
//        "-" -> (Subtract.apply _ ),
//        "*" -> (Multiply.apply _ ),
//        "/" -> (Divide.apply _ ),
//      }
//    def apply(line :String)={
//      val symbol = line.split("\\s+").head
//      acceptedCommands(symbol)(line)
//    }
//
//
//  }

  case class Comment(s: String)

  case class Add(x: Int)
//  object Add {
//    def apply(line: String): Add ={
//      val Array(_, x) = line.split("\\s+")
//      Add(x.toInt)
//    }
//  }

  case class Subtract(x: Int)

  case class Multiply(x: Int)

  case class Divide(x: Int)

  case class Ignore(s: String)

}
