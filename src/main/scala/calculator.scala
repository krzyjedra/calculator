import scala.io.Source

object calculator extends App {

  val lines = Source.fromFile("instructions.txt").getLines.toSeq
  //  val commands = lines.map(Command.from)
  val commands = lines.map {
    case line if line startsWith ("#") =>
      Ignore(line)
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
      case line => Ignore(line)
    }
    case line => Ignore(line)
  }

  commands.foreach(println)
  println(execute(commands))

  //  object Command {
  //    val acceptedCommands = Map(
  //      "+" -> (Add.apply _),
  //      "-" -> (Subtract.apply _),
  //      "*" -> (Multiply.apply _),
  //      "/" -> (Divide.apply _),
  //    )
  //
  //    def from(line: String) = {
  //      val symbol = line.split("\\s+").head
  //      val fromMethod = (acceptedCommands(symbol))
  //      fromMethod(line)
  //
  //    }
  //
  //  }

  def execute(cmd: Seq[Product]):Double = {
    cmd.foldLeft(0.0) { (result, n) =>
      n match {
        case a: Add =>
          result + a.x
        case s: Subtract =>
          result - s.x
        case m: Multiply =>
          result * m.x
        case d: Divide =>
          result / d.x
        case i: Ignore =>
          result
      }
    }
  }


  case class Add(x: Int)

  object Add {
    def from(line: String): Add = {
      val Array(_, x) = line.split("\\s+")
      Add(x.toInt)
    }
  }

  case class Subtract(x: Int)

  object Subtract {
    def from(line: String): Subtract = {
      val Array(_, x) = line.split("\\s+")
      Subtract(x.toInt)
    }
  }

  case class Multiply(x: Int)

  object Multiply {
    def from(line: String): Multiply = {
      val Array(_, x) = line.split("\\s+")
      Multiply(x.toInt)
    }
  }

  case class Divide(x: Int)

  object Divide {
    def from(line: String): Divide = {
      val Array(_, x) = line.split("\\s+")
      Divide(x.toInt)
    }
  }

  case class Ignore(s: String)

  object Ignore {
    def from(line: String): Ignore = {
      Ignore(line)
    }
  }

}
