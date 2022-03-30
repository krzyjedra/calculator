import scala.io.Source

object calculator extends App {

  val lines = Source.fromFile("instructions.txt").getLines.toSeq
    lines.foldLeft(0){(result, line) =>
    case line.startsWith
    }

  case class Sum (x: Long, y: Long)
}
