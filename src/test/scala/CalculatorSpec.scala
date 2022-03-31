import org.scalatest._

import flatspec._
import matchers._
import scala.io.Source

class CalculatorSpec extends AnyFlatSpec with should.Matchers {

  import calculator._

  "calculator.from" should "create values in Objects after spliting lines" in {
    Add.from("+ 2") should be(calculator.Add(2))
    Subtract.from("- 2") should be(calculator.Subtract(2, negation = false))
    Multiply.from("* 2") should be(calculator.Multiply(2))
    Divide.from("/ 2") should be(calculator.Divide(2))
  }

  val lines = Source.fromFile("instructions.txt").getLines.toSeq
  val result = Seq("+ 2", "-", "* 4", "# + 21 2 3", "/ 3", "PRINT", "* 22", "/ 33", "- 21", "PRINT")

  "lines" should "read lines from file and return as Seq of strings" in {
    lines should be(result)
  }

  val commandsWithNegation = List(Ignore("+ 2 2"), Add(2), Multiply(5), Subtract(2, negation = true), Divide(1), Ignore("/ 2 2"))
  val commandsNoNegation = List(Ignore("+ 2 2"), Add(2), Multiply(5), Subtract(2, negation = false), Divide(1), Ignore("/ 2 2"))

  "calculator.execute" should "execute mathematical operations" in {
    calculator.execute(commandsWithNegation) should be(-10)
    calculator.execute(commandsNoNegation) should be(10)
  }
}
