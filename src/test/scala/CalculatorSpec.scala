import org.scalatest._
import flatspec._
import matchers._

class CalculatorSpec extends AnyFlatSpec with should.Matchers {
  "A Stack" should "" in {
    calculator.lines should be (calculator.commands)
  }
}
