import org.scalatest._
import flatspec._
import matchers._

class MainSpec extends AnyFlatSpec with should.Matchers {

  "calculator.from" should "create values in Objects after spliting lines" in {
    calculator.Add.from("+ 2") should be(calculator.Add(2))
    calculator.Subtract.from("- 2") should be(calculator.Subtract(2))
    calculator.Multiply.from("* 2") should be(calculator.Multiply(2))
    calculator.Divide.from("/ 2") should be(calculator.Divide(2))
  }
}
