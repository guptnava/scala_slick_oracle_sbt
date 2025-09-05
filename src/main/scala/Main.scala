import com.typesafe.config.ConfigFactory
import scala.slick.driver.JdbcDriver.simple._

case class Person(id: Int, name: String)

class People(tag: Tag) extends Table[Person](tag, "PEOPLE") {
  def id   = column[Int]("ID", O.PrimaryKey)
  def name = column[String]("NAME")

  def * = (id, name) <> (Person.tupled, Person.unapply)
}

object Main extends App {

val conf = ConfigFactory.load().getConfig("slick.dbs.default.db")

  val url  = conf.getString("url")
  val user = conf.getString("user")
  val pass = conf.getString("password")

  println(s"Connecting with URL: $url")
  println(s"User: $user")
  println(s"Password: $pass")

  val db = Database.forURL(
    url     = url,
    user    = user,
    password = pass,
    driver  = conf.getString("driver")
  )



  val people = TableQuery[People]

  db.withSession { implicit session =>
    try {
      people.ddl.drop
    } catch {
      case _: Throwable => // ignore if doesn't exist
    }

    people.ddl.create

    people.insertAll(
      Person(1, "Alice"),
      Person(2, "Bob")
    )

    people.list.foreach(p => println(s"${p.id}: ${p.name}"))
  }
}
