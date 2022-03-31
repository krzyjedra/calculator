import org.scalatest._
import flatspec._
import matchers._

class MainSpec extends AnyFlatSpec with should.Matchers {

  import calculator._
  "calculator.from" should "create values in Objects after spliting lines" in {
    Add.from("+ 2") should be(calculator.Add(2))
    Subtract.from("- 2") should be(calculator.Subtract(2))
    Multiply.from("* 2") should be(calculator.Multiply(2))
    Divide.from("/ 2") should be(calculator.Divide(2))
  }
}
