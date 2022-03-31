import org.scalatest._
import flatspec._
import matchers._
import scala.io.Source

class MainSpec extends AnyFlatSpec with should.Matchers {

  import calculator._

  "calculator.from" should "create values in Objects after spliting lines" in {
    Add.from("+ 2") should be(calculator.Add(2))
    Subtract.from("- 2") should be(calculator.Subtract(2))
    Multiply.from("* 2") should be(calculator.Multiply(2))
    Divide.from("/ 2") should be(calculator.Divide(2))
  }

  val lines = Source.fromFile("instructions.txt").getLines.toSeq
  val commands = Seq("+ 2 2", "- 1", "* 4", "+ 2", "/ 3", "/ 2 2")

  "lines" should "read lines from file and return as Seq of strings" in {
    lines should be(commands)
  }
}
