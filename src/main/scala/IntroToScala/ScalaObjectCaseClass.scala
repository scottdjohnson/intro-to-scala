package IntroToScala

class ScalaMessage(val sender: String, val recipient: String, val message: String)

// Companion object
object ScalaMessage {
  def apply(sender: String, recipient: String, message: String) = new ScalaMessage(sender, recipient, message)
  def apply(sender: String, recipient: String) = new ScalaMessage(sender, recipient, "")
  def apply(senderAndRecipient: String) = new ScalaMessage(senderAndRecipient, senderAndRecipient, "")
}

case class MessageCaseClass(sender: String, recipient: String, message: String)
object MessageCaseClass {
  def apply(sender: String, recipient: String) = new MessageCaseClass(sender, recipient, "")
  def apply(senderAndRecipient: String) = new MessageCaseClass(senderAndRecipient, senderAndRecipient, "")
}

object CaseClass {
  def main(args: Array[String]): Unit = {
    val message1: ScalaMessage = new ScalaMessage("me", "you", "hi!")
    val message2: ScalaMessage = ScalaMessage("me", "you", "hi!")

    println(message1.recipient, message1.sender, message1.message)
    println(message2.recipient, message2.sender, message2.message)


/*
    // What if we want to compare two classes?
    if (message1 == message2)
      println("The classes are the same!")
    else
      println("The classes are different!")


    //println(message1)
    //println(message2)

    // Case classes
    val messageCaseClass1: MessageCaseClass = MessageCaseClass("me", "you", "hi!")
    val messageCaseClass2: MessageCaseClass = MessageCaseClass("me", "you")
    val messageCaseClass3: MessageCaseClass = MessageCaseClass("me")

    println(messageCaseClass1)
    println(messageCaseClass2)
    println(messageCaseClass3)



    // Create another case class, different instance but same values as messageCaseClass1
    val messageCaseClass4: MessageCaseClass = MessageCaseClass("me", "you", "hi!")

    println(messageCaseClass1)
    println(messageCaseClass4)


    // Are they the same?
    if (messageCaseClass1 == messageCaseClass4)
      println("The case classes are the same!")
    else
      println("The case classes are different!")



    // How can we tell what kind of class this is?
    val obj: java.lang.Object = messageCaseClass1


    // antipattern
    if(messageCaseClass1.isInstanceOf[MessageCaseClass]) {
      val mcc = obj.asInstanceOf[MessageCaseClass]
      println(s"Found a Message Case Class with ${mcc.sender}, ${mcc.recipient}, ${mcc.message}")
    }


    // Match against the class type
    obj match {
      case MessageCaseClass(s, r, m) =>
        println(s"Found a Message Case Class with $s, $r, $m")
      case _ =>
        println(s"Not sure what this class is")
    }
*/
  }
}