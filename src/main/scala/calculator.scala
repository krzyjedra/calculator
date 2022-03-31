import scala.io.Source

object calculator extends App {

  val lines = Source.fromFile("instructions.txt").getLines.toSeq
  val commands = lines.map(Command.from)

  commands.foreach(println)
  execute(commands)

  object Command {
    val acceptedCommands = Map(
      "+" -> (Add.from _),
      "-" -> (Subtract.from _),
      "*" -> (Multiply.from _),
      "/" -> (Divide.from _),
      "#" -> (Ignore.from _),
      "PRINT" -> (Print.from _)
    )

    def from(line: String) = {
      val symbol = line.split("\\s+").head
      val fromMethod = (acceptedCommands(symbol))
      fromMethod(line)
    }
  }

  def execute(cmd: Seq[Product]): Double = {
    cmd.foldLeft(0.0) { (result, n) =>
      n match {
        case a: Add =>
          result + a.x
        case s: Subtract =>
          s.negation match {
            case true => - result
            case false => result - s.x
          }
        case m: Multiply =>
          result * m.x
        case d: Divide =>
          result / d.x
        case i: Ignore =>
          result
        case p: Print =>
          println(result)
          result
        case n: Negation =>
          -result
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

  case class Subtract(x: Int, negation: Boolean)

  object Subtract {
    def from(line: String): Subtract = {
      line.split("\\s+").toList match {
        case m :: Nil => Subtract(0, true)
        case m :: n :: Nil => Subtract(n.toInt, false)
      }
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

  case class Print(s: String)

  object Print {
    def from(line: String): Print = {
      Print(line)
    }
  }

  case class Negation()

  object Negation {
    def from(): Negation = {
      Negation()
    }
  }
}
